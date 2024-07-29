// TaskManagerApp.java
import java.util.Scanner;

public class TaskManagerApp {
    private static Logger logger = Logger.getInstance("application.log");
    private static TaskManager taskManager;

    public static void main(String[] args) {
        taskManager = new TaskManager(new LoggingTaskManagementStrategy(logger));

        Scanner scanner = new Scanner(System.in);
        runMenu(scanner);
        scanner.close();
    }

    private static void runMenu(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("Task Manager Application");
            System.out.println("1. Add Task");
            System.out.println("2. Mark Task as Completed");
            System.out.println("3. View Tasks");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();
            try {
                handleUserChoice(choice, scanner);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void handleUserChoice(String choice, Scanner scanner) {
        switch (choice) {
            case "1":
                addTask(scanner);
                break;
            case "2":
                markTaskAsCompleted(scanner);
                break;
            case "3":
                viewTasks();
                break;
            case "4":
                System.out.println("Exiting the application.");
                System.exit(0); // Graceful exit
                break;
            default:
                throw new IllegalArgumentException("Invalid choice. Please try again.");
        }
    }

    private static void addTask(Scanner scanner) {
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
    }

    private static void markTaskAsCompleted(Scanner scanner) {
        System.out.print("Enter task description to mark as completed: ");
        String description = scanner.nextLine();
        taskManager.markTaskAsCompleted(description);
    }

    private static void viewTasks() {
        for (Task task : taskManager.getTasks()) {
            System.out.println(task);
        }
    }
}
