package by.task.scheduleapp.controller;

import by.task.scheduleapp.model.Student;
import by.task.scheduleapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("api/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("")
    public ResponseEntity<List<Student>> getAllStudent() {
        List<Student> students = studentService.showAllStudent();
        if (students.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable("id") Long studentId) {
        if (!Objects.nonNull(studentId)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<Student> student = studentService.showStudent(studentId);
        if (student.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student.get(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        if (!Objects.nonNull(student)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        studentService.saveStudent(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        if (!Objects.nonNull(student)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(!Objects.nonNull(studentService.showStudent(id))){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        studentService.updateStudent(student, id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}
