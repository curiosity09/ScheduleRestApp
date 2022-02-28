package by.task.scheduleapp.controller;

import by.task.scheduleapp.model.Lesson;
import by.task.scheduleapp.service.LessonService;
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
@RequestMapping("api/lesson")
public class LessonController {

    private final LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping("")
    public ResponseEntity<List<Lesson>> getAllLesson() {
        List<Lesson> lessons = lessonService.showAllLesson();
        if (lessons.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(lessons, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lesson> getLesson(@PathVariable("id") Long lessonId) {
        if (!Objects.nonNull(lessonId)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<Lesson> lesson = lessonService.showLesson(lessonId);
        if (lesson.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(lesson.get(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Lesson> saveLesson(@RequestBody Lesson lesson) {
        if (!Objects.nonNull(lesson)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        lessonService.saveLesson(lesson);
        return new ResponseEntity<>(lesson, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Lesson> updateLesson(@PathVariable Long id, @RequestBody Lesson lesson) {
        if (!Objects.nonNull(lesson)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(!Objects.nonNull(lessonService.showLesson(id))){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        lessonService.updateLesson(lesson, id);
        return new ResponseEntity<>(lesson, HttpStatus.OK);
    }
}
