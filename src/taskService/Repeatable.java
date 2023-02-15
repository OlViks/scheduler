package taskService;


import task.Task;

import java.time.LocalDate;

public interface Repeatable {
    boolean getNextDate(LocalDate date, Task task);
}
