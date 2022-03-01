package by.task.scheduleapp.controller;

import by.task.scheduleapp.model.Schedule;
import by.task.scheduleapp.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/api/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("")
    public ResponseEntity<List<Schedule>> getAllSchedule() {
        List<Schedule> schedule = scheduleService.showAllSchedule();
        if (schedule.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getSchedule(@PathVariable("id") Long scheduleId) {
        if (scheduleId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<Schedule> schedule = scheduleService.showSchedule(scheduleId);
        if (schedule.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(schedule.get(), HttpStatus.OK);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<List<Schedule>> getStudentSchedule(@PathVariable("id") Long scheduleId) {
        if (scheduleId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Schedule> schedules = scheduleService.showStudentSchedule(scheduleId);
        if (schedules == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Schedule> saveSchedule(@RequestBody Schedule schedule) {
        if (!Objects.nonNull(schedule)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        scheduleService.saveSchedule(schedule);
        return new ResponseEntity<>(schedule, HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Schedule> saveLessonInSchedule(@RequestBody Schedule schedule, @PathVariable Long id) {
        if (!Objects.nonNull(schedule)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(!Objects.nonNull(scheduleService.showSchedule(id))){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        scheduleService.saveLessonInSchedule(schedule, id);
        return new ResponseEntity<>(schedule, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Schedule> deleteSchedule(@PathVariable Long id) {
        if (!Objects.nonNull(scheduleService.showSchedule(id))) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        scheduleService.deleteSchedule(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable Long id, @RequestBody Schedule schedule) {
        if (!Objects.nonNull(schedule)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(!Objects.nonNull(scheduleService.showSchedule(id))){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        scheduleService.updateSchedule(schedule, id);
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }

    @PatchMapping("/{id}/lesson")
    public ResponseEntity<Schedule> updateLessonInSchedule(@PathVariable Long id, @RequestBody Schedule schedule) {
        if (!Objects.nonNull(schedule)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(!Objects.nonNull(scheduleService.showSchedule(id))){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        scheduleService.updateLessonInSchedule(schedule, id);
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }
}
