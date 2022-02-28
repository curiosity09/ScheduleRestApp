package by.task.scheduleapp.repository.impl;

import by.task.scheduleapp.model.Lesson;
import by.task.scheduleapp.repository.LessonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class LessonRepoImpl implements LessonRepo {

    public static final String SQL_FIND_LESSON = "SELECT id, name FROM schedule.lesson";
    public static final String SQL_WHERE_PREDICATE = " WHERE id = ?";
    public static final String SQL_FIND_LESSON_BY_ID = SQL_FIND_LESSON + SQL_WHERE_PREDICATE;
    public static final String SQL_SAVE_LESSON = "INSERT INTO schedule.lesson (name) VALUES (?)";
    public static final String SQL_UPDATE_LESSON = "UPDATE schedule.lesson SET name = ? WHERE id = ?";
    public static final String SQL_FIND_LESSON_IN_SCHEDULE = "SELECT l.id AS id, l.name AS name FROM schedule.lesson l JOIN schedule.lesson_schedule ls ON ls.lesson_id = l.id WHERE schedule_id = ?";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LessonRepoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Lesson> findById(Long id) {
        if (Objects.nonNull(id) && id > 0L) {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_LESSON_BY_ID, BeanPropertyRowMapper.newInstance(Lesson.class), id));
        }
        return Optional.empty();
    }

    @Override
    public List<Lesson> findAll() {
        return jdbcTemplate.query(SQL_FIND_LESSON, BeanPropertyRowMapper.newInstance(Lesson.class));
    }

    @Override
    public int save(Lesson lesson) {
        return jdbcTemplate.update(SQL_SAVE_LESSON, lesson.getName());
    }

    @Override
    public int update(Lesson lesson, Long id) {
        return jdbcTemplate.update(SQL_UPDATE_LESSON, lesson.getName(), id);
    }

    @Override
    public List<Lesson> findLessonsInSchedule(Long scheduleId) {
        return jdbcTemplate.query(SQL_FIND_LESSON_IN_SCHEDULE, BeanPropertyRowMapper.newInstance(Lesson.class), scheduleId);
    }
}
