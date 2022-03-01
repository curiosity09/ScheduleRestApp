package by.task.scheduleapp.repository.impl;

import by.task.scheduleapp.model.Lesson;
import by.task.scheduleapp.model.Schedule;
import by.task.scheduleapp.repository.LessonRepo;
import by.task.scheduleapp.mapper.ScheduleMapper;
import by.task.scheduleapp.repository.ScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class ScheduleRepoImpl implements ScheduleRepo {

    public static final String SQL_FIND_SCHEDULE = "SELECT s.id AS id,st.id AS student_id, st.full_name AS full_name FROM schedule.schedule s JOIN schedule.student st ON s.student_id = st.id";
    public static final String SQL_WHERE_ID_PREDICATE = " WHERE s.id = ?";
    public static final String SQL_WHERE_STUDENT_ID_PREDICATE = " WHERE st.id = ?";
    public static final String SQL_FIND_SCHEDULE_BY_ID = SQL_FIND_SCHEDULE + SQL_WHERE_ID_PREDICATE;
    public static final String SQL_FIND_SCHEDULE_BY_STUDENT_ID = SQL_FIND_SCHEDULE + SQL_WHERE_STUDENT_ID_PREDICATE;
    public static final String SQL_SAVE_SCHEDULE = "INSERT INTO schedule.schedule(student_id) VALUES (?)";
    public static final String SQL_SAVE_LESSON_IN_SCHEDULE = "INSERT INTO schedule.lesson_schedule(schedule_id, lesson_id) VALUES (?,?)";
    public static final String SQL_UPDATE_SCHEDULE = "UPDATE schedule.schedule SET student_id = ? WHERE id = ?";
    public static final String SQL_DEL_FROM_LESSON_SCHEDULE_TABLE = "DELETE FROM schedule.lesson_schedule WHERE schedule_id = ?";
    public static final String SQL_DEL_FROM_SCHEDULE_TABLE = "DELETE FROM schedule.schedule WHERE id = ?";
    private final JdbcTemplate jdbcTemplate;
    private final LessonRepo lessonRepo;

    @Autowired
    public ScheduleRepoImpl(JdbcTemplate jdbcTemplate, LessonRepo lessonRepo) {
        this.jdbcTemplate = jdbcTemplate;
        this.lessonRepo = lessonRepo;
    }

    @Override
    public Optional<Schedule> findById(Long id) {
        if (Objects.nonNull(id) && id > 0L) {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_SCHEDULE_BY_ID, new ScheduleMapper(lessonRepo), id));
        }
        return Optional.empty();
    }

    @Override
    public List<Schedule> findByStudentId(Long id) {
        return jdbcTemplate.query(SQL_FIND_SCHEDULE_BY_STUDENT_ID, new ScheduleMapper(lessonRepo), id);
    }

    @Override
    public List<Schedule> findAll() {
        return jdbcTemplate.query(SQL_FIND_SCHEDULE, new ScheduleMapper(lessonRepo));
    }

    @Override
    public void save(Schedule schedule) {
        jdbcTemplate.update(SQL_SAVE_SCHEDULE, schedule.getStudent().getId());
    }

    @Override
    public void saveLessonInSchedule(Schedule schedule, Long id) {
        for (Lesson lesson : schedule.getLessons()) {
            jdbcTemplate.update(SQL_SAVE_LESSON_IN_SCHEDULE, id, lesson.getId());
        }
    }

    @Override
    public int update(Schedule schedule, Long id) {
        return jdbcTemplate.update(SQL_UPDATE_SCHEDULE, schedule.getStudent().getId(), id);
    }

    @Override
    public void updateLessonInSchedule(Schedule schedule, Long id) {
        jdbcTemplate.update(SQL_DEL_FROM_LESSON_SCHEDULE_TABLE, id);
        for (Lesson lesson : schedule.getLessons()) {
            jdbcTemplate.update(SQL_SAVE_LESSON_IN_SCHEDULE, id, lesson.getId());
        }
    }

    @Override
    public int deleteById(Long id) {
        jdbcTemplate.update(SQL_DEL_FROM_LESSON_SCHEDULE_TABLE, id);
        return jdbcTemplate.update(SQL_DEL_FROM_SCHEDULE_TABLE, id);
    }
}
