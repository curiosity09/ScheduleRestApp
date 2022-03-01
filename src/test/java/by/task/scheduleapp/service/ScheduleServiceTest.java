package by.task.scheduleapp.service;

import by.task.scheduleapp.model.Schedule;
import by.task.scheduleapp.model.Student;
import by.task.scheduleapp.repository.LessonRepo;
import by.task.scheduleapp.repository.ScheduleRepo;
import by.task.scheduleapp.repository.impl.LessonRepoImpl;
import by.task.scheduleapp.repository.impl.ScheduleRepoImpl;
import by.task.scheduleapp.service.impl.ScheduleServiceImpl;
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

class ScheduleServiceTest {

    private EmbeddedDatabase embeddedDatabase;
    private ScheduleService scheduleService;

    @BeforeEach
    public void setUp() {
        embeddedDatabase = new EmbeddedDatabaseBuilder()
                .addDefaultScripts()
                .setType(EmbeddedDatabaseType.H2)
                .build();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(embeddedDatabase);
        LessonRepo lessonRepo = new LessonRepoImpl(jdbcTemplate);
        ScheduleRepo scheduleRepo = new ScheduleRepoImpl(jdbcTemplate,lessonRepo);
        scheduleService = new ScheduleServiceImpl(scheduleRepo);
    }

    @AfterEach
    public void tearDown() {
        embeddedDatabase.shutdown();
    }

    @Test
    void showSchedule() {
        Assertions.assertNotNull(scheduleService.showSchedule(1L));
        Assertions.assertEquals(scheduleService.showSchedule(0L), Optional.empty());
    }

    @Test
    void showStudentSchedule() {
        Assertions.assertNotNull(scheduleService.showStudentSchedule(1L));
        Assertions.assertEquals(1, scheduleService.showStudentSchedule(1L).size());
    }

    @Test
    void showAllSchedule() {
        Assertions.assertNotNull(scheduleService.showAllSchedule());
        Assertions.assertEquals(2, scheduleService.showAllSchedule().size());
    }

    @Test
    void saveSchedule() {
        scheduleService.saveSchedule(Schedule.builder().student(Student.builder().id(2L).build()).build());
        List<Schedule> schedules = scheduleService.showAllSchedule();
        Assertions.assertEquals(3, schedules.size());
    }

    @Test
    void updateSchedule() {
        scheduleService.updateSchedule(Schedule.builder().student(Student.builder().id(2L).build()).build(), 2L);
        Optional<Schedule> schedule = scheduleService.showSchedule(2L);
        schedule.ifPresent(value -> Assertions.assertEquals(2L, value.getStudent().getId()));
    }

    @Test
    void deleteSchedule() {
        scheduleService.deleteSchedule(2L);
        Assertions.assertEquals(1,scheduleService.showAllSchedule().size());
    }
}