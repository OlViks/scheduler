import task.*;
import taskService.Repeatable;
import taskService.TypeOfTasks;
import exceptions.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    private final static Schedule schedule = new Schedule();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            printMenu();
            System.out.println("Введите соответствующую цифру пункта меню:");
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        inputTask(sc);
                        break;
                    case 2:
                        deleteTask(sc);
                        break;
                    case 3:
                        getTasksOnDay(sc);
                        break;
                    case 4:
                        printAllTasks();
                        break;
                    case 0:
                        System.exit(0);
                }
            } else {
                sc.next();
                System.out.println("Выберите пункт меню из списка!");
            }
        }

    }

    private static void printMenu() {
        System.out.println(
                        "1. Добавить задачу,\n" +
                        "2. Удалить задачу,\n" +
                        "3. Получить задачи на день,\n" +
                        "4. Распечатать список всех задач,\n" +
                        "0. Выход."
        );
    }

    private static void inputTask(Scanner scanner) {
        System.out.println("Введите название задачи: ");
        String taskName = scanner.useDelimiter("\n").next();
        System.out.println(" ");
        System.out.println("Введите описание задачи: ");
        String taskDescribe = scanner.useDelimiter("\n").next();
        System.out.println(" ");
        TypeOfTasks type = getTypeOfTask(scanner);
        Repeatable repeatable = getRepeatable(scanner);
        try {
            Task task = new Task(taskName, taskDescribe, type, repeatable);
            schedule.addTask(task);
            System.out.println(Schedule.getSchedule().size());
        } catch (NoNameException | NoTypeException | NoDescException | NoRepeatException | NoTaskException e) {
            System.out.println(e.getMessage() + Arrays.toString(e.getStackTrace()));
        }

    }

    private static TypeOfTasks getTypeOfTask(Scanner scanner) {
        while (true) {
            System.out.println("Введите категорию задачи:\n" +
                    "1. Личная,\n" +
                    "2. Рабочая.");
            if (scanner.hasNextInt()) {
                int i = scanner.nextInt();
                if (i == 1) {
                    return TypeOfTasks.PERSONAL;
                } else if (i == 2) {
                    return TypeOfTasks.WORK;
                } else {
                    System.out.println("введено иное число, выберите из списка!");
                }
            } else {
                System.out.println("Введено не число, введите число из списка!");
            }
        }
    }

    private static Repeatable getRepeatable(Scanner scanner) {
        while (true) {
            System.out.println("Введите повторяемость задачи:\n" +
                    "1. Однократная,\n" +
                    "2. Ежедневная,\n" +
                    "3. Еженедельная,\n" +
                    "4. Ежемесячная,\n" +
                    "5. Ежегодная.");
            if (scanner.hasNextInt()) {
                int i = scanner.nextInt();
                switch (i) {
                    case 1:
                        return new Once();
                    case 2:
                        return new Daily();
                    case 3:
                        return new Weekly();
                    case 4:
                        return new Monthly();
                    case 5:
                        return new Yearly();
                    default:
                        System.out.println("Введено иное число, выберите из списка!");
                }
            } else {
                System.out.println("Введено не число, введите число из списка!");
            }
        }
    }

    private static void getTasksOnDay(Scanner scanner) {
        List<Task> tasksOnDate = schedule.getTasksOnDate(getDate(scanner));
        for (Task task : tasksOnDate) {
            System.out.println(task);
        }
    }
    private static void printAllTasks() {
        HashMap<Integer, Task> dairy = Schedule.getSchedule();
        for (Map.Entry<Integer, Task> task :
                dairy.entrySet()) {
            System.out.println(task.getValue());
        }
    }
    private static void deleteTask(Scanner sc) {
        while (true) {
            System.out.println("Введите ID задачи для удаления: \n" +
                    "0. Выйти в предыдущее меню");
            int taskID;
            try {
                taskID = sc.nextInt();
                if (taskID == 0) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Введено не число!");
                continue;
            }

            try {
                schedule.deleteTask(taskID);
                System.out.println("Задача удалена!\n");
            } catch (NoTaskException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static LocalDate getDate(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Введите дату в формате DD-MM-YYYY:");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                return LocalDate.parse(scanner.next(), formatter);
            } catch (Exception e) {
                System.out.println("Неверный формат ввода");
            }
        }
    }
}