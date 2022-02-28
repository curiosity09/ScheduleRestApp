package by.task.scheduleapp.service;

import by.task.scheduleapp.model.Student;
import by.task.scheduleapp.repository.StudentRepo;
import by.task.scheduleapp.repository.impl.StudentRepoImpl;
import by.task.scheduleapp.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.util.List;
import java.util.Optional;

class StudentServiceTest {

    private EmbeddedDatabase embeddedDatabase;
    private StudentService studentService;

    @BeforeEach
    public void setUp() {
        embeddedDatabase = new EmbeddedDatabaseBuilder()
                .addDefaultScripts()
                .setType(EmbeddedDatabaseType.H2)
                .build();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(embeddedDatabase);
        StudentRepo studentRepo = new StudentRepoImpl(jdbcTemplate);
        studentService = new StudentServiceImpl(studentRepo);
    }

    @AfterEach
    public void tearDown() {
        embeddedDatabase.shutdown();
    }

    @Test
    void showStudent() {
        Assertions.assertNotNull(studentService.showStudent(1L));
        Assertions.assertEquals(studentService.showStudent(0L), Optional.empty());
    }

    @Test
    void showAllStudent() {
        Assertions.assertNotNull(studentService.showAllStudent());
        Assertions.assertEquals(2, studentService.showAllStudent().size());
    }

    @Test
    void saveStudent() {
        studentService.saveStudent(Student.builder().fullName("Vlad Kovalchuk").build());
        List<Student> students = studentService.showAllStudent();
        Assertions.assertEquals(3, students.size());
        Assertions.assertEquals("Vlad Kovalchuk", students.get(2).getFullName());
    }

    @Test
    void updateStudent() {
        studentService.updateStudent(Student.builder().fullName("Vlad Kovalchuk").build(), 2L);
        Optional<Student> student = studentService.showStudent(2L);
        student.ifPresent(value -> Assertions.assertEquals("Vlad Kovalchuk", value.getFullName()));
    }
}