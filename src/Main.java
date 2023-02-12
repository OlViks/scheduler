import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner);
                            break;
                        case 2:
                            // todo: обрабатываем пункт меню 2
                            break;
                        case 3:
                            // todo: обрабатываем пункт меню 3
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }

    private static void inputTask(Scanner scanner) {
        System.out.print("Введите название задачи: ");
        String taskName = scanner.useDelimiter("\n").next();
        System.out.println(" ");
        System.out.print("Введите описание задачи: ");
        String taskDescription = scanner.useDelimiter("\n").next();
        System.out.println(" ");
        System.out.print("Выберите тип задачи:  \n 1 - личная \n 2 - рабочая   \n");
        String typeTask = scanner.useDelimiter("\n").next();
        System.out.println(" ");
        System.out.print("Выберите повторяемость задачи:  \n 1 - Одннократная \n" +
                "2 - Ежедневная  \n" +
                "3 - Еженедельная  \n" +
                "4 - Ежемесечная  \n" +
                "5 - Ежегодная  \n");
        String repeatTask = scanner.useDelimiter("\n").next();
        System.out.println(" ");

        // todo
    }

    private static void getTheTaskData(Scanner scanner) {
    }

    private static void printMenu() {
        System.out.println(
                """
                        1. Добавить задачу
                        2. Удалить задачу
                        3. Получить задачу на указанный день
                        0. Выход
                        """
        );
    }
}
