package by.task.scheduleapp.service.impl;

import by.task.scheduleapp.model.Schedule;
import by.task.scheduleapp.repository.ScheduleRepo;
import by.task.scheduleapp.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepo scheduleRepo;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepo scheduleRepo) {
        this.scheduleRepo = scheduleRepo;
    }

    @Override
    public Optional<Schedule> showSchedule(Long id) {
        return scheduleRepo.findById(id);
    }

    @Override
    public List<Schedule> showStudentSchedule(Long id) {
        return scheduleRepo.findByStudentId(id);
    }

    @Override
    public List<Schedule> showAllSchedule() {
        return scheduleRepo.findAll();
    }

    @Override
    public void saveSchedule(Schedule schedule) {
        scheduleRepo.save(schedule);
    }

    @Override
    public void updateSchedule(Schedule schedule, Long id) {
        scheduleRepo.update(schedule, id);
    }

    @Override
    public void deleteSchedule(Long id) {
        scheduleRepo.deleteById(id);
    }

    @Override
    public void saveLessonInSchedule(Schedule schedule, Long id) {
        scheduleRepo.saveLessonInSchedule(schedule, id);
    }

    @Override
    public void updateLessonInSchedule(Schedule schedule, Long id) {
        scheduleRepo.updateLessonInSchedule(schedule, id);
    }
}
