package by.task.scheduleapp.repository;

import by.task.scheduleapp.model.Lesson;
import by.task.scheduleapp.model.Schedule;

import java.util.List;
import java.util.Optional;

public interface LessonRepo {

    Optional<Lesson> findById(Long id);

    List<Lesson> findAll();

    int save(Lesson lesson);

    int update(Lesson lesson, Long id);

    List<Lesson> findLessonsInSchedule(Long scheduleId);
}
