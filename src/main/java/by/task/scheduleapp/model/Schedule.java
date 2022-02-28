package by.task.scheduleapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Schedule {

    private Long id;
    private Student student;
    private List<Lesson> lessons;
}
