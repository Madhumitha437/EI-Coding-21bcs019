// TaskManagerApp.java
import java.util.Scanner;

public class TaskManagerApp {
    private static Logger logger = Logger.getInstance("application.log");

    public static void main(String[] args) {
        TaskManager taskManager = TaskManager.getInstance();

        Scanner scanner = new Scanner(System.in);
        runMenu(scanner, taskManager);
        scanner.close();
    }

    private static void runMenu(Scanner scanner, TaskManager taskManager) {
        boolean running = true;
        while (running) {
            System.out.println("Task Manager Application");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. View Tasks");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();
            try {
                handleUserChoice(choice, scanner, taskManager);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void handleUserChoice(String choice, Scanner scanner, TaskManager taskManager) {
        switch (choice) {
            case "1":
                addTask(scanner, taskManager);
                break;
            case "2":
                removeTask(scanner, taskManager);
                break;
            case "3":
                viewTasks(taskManager);
                break;
            case "4":
                System.out.println("Exiting the application.");
                System.exit(0); // Graceful exit
                break;
            default:
                throw new IllegalArgumentException("Invalid choice. Please try again.");
        }
    }

    private static void addTask(Scanner scanner, TaskManager taskManager) {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        System.out.print("Enter start time (HH:MM): ");
        String startTime = scanner.nextLine();
        System.out.print("Enter end time (HH:MM): ");
        String endTime = scanner.nextLine();
        System.out.print("Enter priority level (Low, Medium, High): ");
        String priority = scanner.nextLine();

        Task task = new Task(description, startTime, endTime, priority);
        taskManager.addTask(task);
        logger.logInfo("Task added: " + description);
    }

    private static void removeTask(Scanner scanner, TaskManager taskManager) {
        System.out.print("Enter task description to remove: ");
        String description = scanner.nextLine();
        taskManager.removeTask(description);
        logger.logInfo("Task removed: " + description);
    }

    private static void viewTasks(TaskManager taskManager) {
        for (Task task : taskManager.getTasks()) {
            System.out.println(task);
        }
    }
}

