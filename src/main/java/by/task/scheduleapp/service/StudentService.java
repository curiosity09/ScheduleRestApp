package by.task.scheduleapp.service;

import by.task.scheduleapp.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Optional<Student> showStudent(Long id);

    List<Student> showAllStudent();

    int saveStudent(Student student);

    int updateStudent(Student student, Long id);
}
