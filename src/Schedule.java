
import exceptions.NoTaskException;
import task.Task;

import java.time.LocalDate;
import java.util.*;

public class Schedule {
    private final static HashMap<Integer, Task> schedule = new HashMap<>();
    private final static HashMap<Integer, Task> deletedTasks = new HashMap<>();

    public void addTask(Task task) throws NoTaskException {
        if (task != null) {
            schedule.put(task.getID(), task);
        } else {
            throw new NoTaskException("Переданная задача пуста!");
        }
    }

    public List<Task> getTasksOnDate(LocalDate date) {
        List<Task> taskList = new ArrayList<>();
        for (Map.Entry<Integer, Task> task :
                schedule.entrySet()) {
            if (task.getValue().getRepeatable().getNextDate(date, task.getValue())) {
                taskList.add(task.getValue());
            }
        }
        return taskList;
    }


    public void deleteTask(int id) throws NoTaskException {
        Task task = schedule.remove(id);
        if (task != null) {
            deletedTasks.put(task.getID(), task);
        } else {
            throw new NoTaskException("Такой задачи нет в ежедневнике - нечего удалять!");
        }
    }

    public static HashMap<Integer, Task> getSchedule() {
        return schedule;
    }


}