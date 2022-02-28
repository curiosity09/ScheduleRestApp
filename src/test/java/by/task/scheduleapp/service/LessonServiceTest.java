package by.task.scheduleapp.service;

import by.task.scheduleapp.model.Lesson;
import by.task.scheduleapp.repository.LessonRepo;
import by.task.scheduleapp.repository.impl.LessonRepoImpl;
import by.task.scheduleapp.service.impl.LessonServiceImpl;
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

class LessonServiceTest {

    private EmbeddedDatabase embeddedDatabase;
    private LessonService lessonService;

    @BeforeEach
    public void setUp() {
        embeddedDatabase = new EmbeddedDatabaseBuilder()
                .addDefaultScripts()
                .setType(EmbeddedDatabaseType.H2)
                .build();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(embeddedDatabase);
        LessonRepo lessonRepo = new LessonRepoImpl(jdbcTemplate);
        lessonService = new LessonServiceImpl(lessonRepo);
    }

    @AfterEach
    public void tearDown() {
        embeddedDatabase.shutdown();
    }

    @Test
    void showLesson() {
        Assertions.assertNotNull(lessonService.showLesson(1L));
        Assertions.assertEquals(lessonService.showLesson(0L), Optional.empty());
    }

    @Test
    void showAllLesson() {
        Assertions.assertNotNull(lessonService.showAllLesson());
        Assertions.assertEquals(5, lessonService.showAllLesson().size());
    }

    @Test
    void saveLesson() {
        lessonService.saveLesson(Lesson.builder().name("Biology").build());
        List<Lesson> lessons = lessonService.showAllLesson();
        Assertions.assertEquals(6, lessons.size());
        Assertions.assertEquals("Biology", lessons.get(5).getName());
    }

    @Test
    void updateLesson() {
        lessonService.updateLesson(Lesson.builder().name("Biology").build(), 2L);
        Optional<Lesson> lesson = lessonService.showLesson(2L);
        lesson.ifPresent(value -> Assertions.assertEquals("Biology", value.getName()));
    }

    @Test
    void findLessonsInSchedule() {
        List<Lesson> lessonsInSchedule = lessonService.findLessonsInSchedule(1L);
        Assertions.assertEquals(3,lessonsInSchedule.size());
    }
}