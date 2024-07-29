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
                System.out.println("2. Add Task Group");
                System.out.println("3. Remove Task");
                System.out.println("4. View Tasks");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                option = scanner.nextLine();

                switch (option) {
                    case "1":
                        addTask(scanner, taskManager);
                        break;
                    case "2":
                        addTaskGroup(scanner, taskManager);
                        break;
                    case "3":
                        removeTask(scanner, taskManager);
                        break;
                    case "4":
                        taskManager.displayTasks();
                        break;
                    case "5":
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

    private static void addTaskGroup(Scanner scanner, TaskManager taskManager) {
        System.out.print("Enter group name: ");
        String groupName = scanner.nextLine();
        try {
            TaskGroup taskGroup = new TaskGroup(groupName);

            String addMoreTasks;
            do {
                System.out.print("Add task to the group (y/n)? ");
                addMoreTasks = scanner.nextLine();

                if (addMoreTasks.equalsIgnoreCase("y")) {
                    addTask(scanner, taskGroup);
                }
            } while (addMoreTasks.equalsIgnoreCase("y"));

            taskManager.addTask(taskGroup);
        } catch (IllegalArgumentException e) {
            System.err.println("Error adding task group: " + e.getMessage());
        }
    }

    private static void addTask(Scanner scanner, TaskGroup taskGroup) {
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
            taskGroup.add(task);
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
