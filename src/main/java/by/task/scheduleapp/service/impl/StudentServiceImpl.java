package by.task.scheduleapp.service.impl;

import by.task.scheduleapp.model.Student;
import by.task.scheduleapp.repository.StudentRepo;
import by.task.scheduleapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepo studentRepo;

    @Autowired
    public StudentServiceImpl(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public Optional<Student> showStudent(Long id) {
        return studentRepo.findById(id);
    }

    @Override
    public List<Student> showAllStudent() {
        return studentRepo.findAll();
    }

    @Override
    public int saveStudent(Student student) {
        return studentRepo.save(student);
    }

    @Override
    public int updateStudent(Student student, Long id) {
        return studentRepo.update(student, id);
    }
}
