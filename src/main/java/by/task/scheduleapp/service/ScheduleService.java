package by.task.scheduleapp.service;

import by.task.scheduleapp.model.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleService {

    Optional<Schedule> showSchedule(Long id);

    List<Schedule> showStudentSchedule(Long id);

    List<Schedule> showAllSchedule();

    void saveSchedule(Schedule schedule);

    void updateSchedule(Schedule schedule, Long id);

    void deleteSchedule(Long id);

    void saveLessonInSchedule(Schedule schedule, Long id);

    void updateLessonInSchedule(Schedule schedule, Long id);
}
