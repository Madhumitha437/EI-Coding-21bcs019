// TaskManagerApp.java
import java.util.List;
import java.util.Scanner;

public class TaskManagerApp {
    public static void main(String[] args) {
        TaskManagerAdapter taskManagerAdapter = new TaskManagerAdapter();
        Scanner scanner = new Scanner(System.in);
        runMenu(scanner, taskManagerAdapter);
        scanner.close();
    }

    private static void runMenu(Scanner scanner, TaskManagerAdapter taskManagerAdapter) {
        boolean running = true;
        while (running) {
            System.out.println("Task Manager Application");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks by Priority");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addTask(scanner, taskManagerAdapter);
                    break;
                case "2":
                    viewTasksByPriority(scanner, taskManagerAdapter);
                    break;
                case "3":
                    System.out.println("Exiting the application.");
                    running = false; // Change the running flag to exit the loop
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addTask(Scanner scanner, TaskManagerAdapter taskManagerAdapter) {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        System.out.print("Enter start time (HH:MM): ");
        String startTime = scanner.nextLine();
        System.out.print("Enter end time (HH:MM): ");
        String endTime = scanner.nextLine();
        System.out.print("Enter priority level (Low, Medium, High): ");
        String priority = scanner.nextLine();

        taskManagerAdapter.addTask(description, startTime, endTime, priority);
    }

    private static void viewTasksByPriority(Scanner scanner, TaskManagerAdapter taskManagerAdapter) {
        System.out.print("Enter priority level to filter tasks (Low, Medium, High): ");
        String priority = scanner.nextLine();
        List<Task> tasks = taskManagerAdapter.viewTasksByPriority(priority);
        if (tasks.isEmpty()) {
            System.out.println("No tasks found with priority: " + priority);
        } else {
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }
}
