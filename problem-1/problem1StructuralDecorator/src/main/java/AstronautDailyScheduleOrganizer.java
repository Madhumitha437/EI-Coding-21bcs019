
// AstronautDailyScheduleOrganizer.java
import java.util.Scanner;

public class AstronautDailyScheduleOrganizer {
    public static void main(String[] args) {
        TaskManager taskManager = TaskManager.getInstance();
        
        try (Scanner scanner = new Scanner(System.in)) {
            String option;

            do {
                System.out.println("Astronaut Daily Schedule Organizer");
                System.out.println("1. Add Task");
                System.out.println("2. Remove Task");
                System.out.println("3. View Tasks");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                option = scanner.nextLine();

                switch (option) {
                    case "1":
                        addTask(scanner, taskManager);
                        break;
                    case "2":
                        removeTask(scanner, taskManager);
                        break;
                    case "3":
                        taskManager.displayTasks();
                        break;
                    case "4":
                        System.out.println("Exiting the application.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (!option.equals("5"));
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
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

        try {
            Task task = new Task(description, startTime, endTime, priority);
            taskManager.addTask(task);
        } catch (IllegalArgumentException e) {
            System.err.println("Error adding task: " + e.getMessage());
        }
    }


    private static void removeTask(Scanner scanner, TaskManager taskManager) {
        System.out.print("Enter task description to remove: ");
        String description = scanner.nextLine();
        // This is a simple implementation; ideally, you would search by full task details.
        for (TaskComponent task : taskManager.getTaskComponents()) {
            if (task.getDescription().equalsIgnoreCase(description)) {
                taskManager.removeTask(task);
                return;
            }
        }
        System.out.println("Task not found: " + description);
    }
}
