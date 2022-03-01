package by.task.scheduleapp.repository.impl;

import by.task.scheduleapp.model.Student;
import by.task.scheduleapp.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class StudentRepoImpl implements StudentRepo {

    public static final String SQL_FIND_STUDENT = "SELECT id, full_name FROM schedule.student";
    public static final String SQL_WHERE_PREDICATE = " WHERE id = ?";
    public static final String SQL_FIND_STUDENT_BY_ID = SQL_FIND_STUDENT + SQL_WHERE_PREDICATE;
    public static final String SQL_SAVE_STUDENT = "INSERT INTO schedule.student (full_name) VALUES (?)";
    public static final String SQL_UPDATE_STUDENT = "UPDATE schedule.student SET full_name = ? WHERE id = ?";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentRepoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Student> findById(Long id) {
        if (Objects.nonNull(id) && id > 0L){
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_STUDENT_BY_ID, BeanPropertyRowMapper.newInstance(Student.class), id));
    }
        return Optional.empty();
}

    @Override
    public List<Student> findAll() {
        return jdbcTemplate.query(SQL_FIND_STUDENT, BeanPropertyRowMapper.newInstance(Student.class));
    }

    @Override
    public int save(Student student) {
        return jdbcTemplate.update(SQL_SAVE_STUDENT, student.getFullName());
    }

    @Override
    public int update(Student student, Long id) {
        return jdbcTemplate.update(SQL_UPDATE_STUDENT, student.getFullName(), id);
    }
}
