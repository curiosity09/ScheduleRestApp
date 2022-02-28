package by.task.scheduleapp.repository;

import by.task.scheduleapp.model.Lesson;
import by.task.scheduleapp.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepo {

    Optional<Student> findById(Long id);

    List<Student> findAll();

    int save(Student student);

    int update(Student student, Long id);
}
