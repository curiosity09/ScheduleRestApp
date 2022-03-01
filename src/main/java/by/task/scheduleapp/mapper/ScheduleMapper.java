package by.task.scheduleapp.mapper;

import by.task.scheduleapp.model.Lesson;
import by.task.scheduleapp.model.Schedule;
import by.task.scheduleapp.model.Student;
import by.task.scheduleapp.repository.LessonRepo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ScheduleMapper implements RowMapper<Schedule> {

    private final LessonRepo lessonRepo;

    public ScheduleMapper(LessonRepo lessonRepo) {
        this.lessonRepo = lessonRepo;
    }

    @Override
    public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        long scheduleId = rs.getLong("id");
        List<Lesson> lessonsInSchedule = lessonRepo.findLessonsInSchedule(scheduleId);
        Student student = Student.builder().id(rs.getLong("student_id")).fullName(rs.getString("full_name")).build();
        return Schedule.builder().id(scheduleId).student(student).lessons(lessonsInSchedule).build();
    }
}
