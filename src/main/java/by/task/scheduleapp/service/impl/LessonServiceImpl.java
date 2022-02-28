package by.task.scheduleapp.service.impl;

import by.task.scheduleapp.model.Lesson;
import by.task.scheduleapp.repository.LessonRepo;
import by.task.scheduleapp.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonServiceImpl implements LessonService {

    private final LessonRepo lessonRepo;

    @Autowired
    public LessonServiceImpl(LessonRepo lessonRepo) {
        this.lessonRepo = lessonRepo;
    }

    @Override
    public Optional<Lesson> showLesson(Long id) {
        return lessonRepo.findById(id);
    }

    @Override
    public List<Lesson> showAllLesson() {
        return lessonRepo.findAll();
    }

    @Override
    public int saveLesson(Lesson lesson) {
        return lessonRepo.save(lesson);
    }

    @Override
    public int updateLesson(Lesson lesson, Long id) {
        return lessonRepo.update(lesson, id);
    }

    @Override
    public List<Lesson> findLessonsInSchedule(Long scheduleId) {
        return lessonRepo.findLessonsInSchedule(scheduleId);
    }
}
