package by.task.scheduleapp.repository;

import by.task.scheduleapp.model.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepo {

    Optional<Schedule> findById(Long id);

    List<Schedule> findByStudentId(Long id);

    List<Schedule> findAll();

    void save(Schedule schedule);

    int update(Schedule schedule, Long id);

    int deleteById(Long id);

    void saveLessonInSchedule(Schedule schedule, Long id);

    void updateLessonInSchedule(Schedule schedule, Long id);
}
