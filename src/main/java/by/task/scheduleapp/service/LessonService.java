package by.task.scheduleapp.service;

import by.task.scheduleapp.model.Lesson;

import java.util.List;
import java.util.Optional;

public interface LessonService {

    Optional<Lesson> showLesson(Long id);

    List<Lesson> showAllLesson();

    int saveLesson(Lesson lesson);

    int updateLesson(Lesson lesson, Long id);

    List<Lesson> findLessonsInSchedule(Long scheduleId);
}
