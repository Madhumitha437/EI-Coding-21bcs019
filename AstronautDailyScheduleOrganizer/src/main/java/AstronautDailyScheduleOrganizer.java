import java.util.*;

public class AstronautDailyScheduleOrganizer {
    private static ScheduleManager scheduleManager = ScheduleManager.getInstance();
    private static List<Observer> observers = new ArrayList<>();

    public static void main(String[] args) {
        Observer consoleObserver = new ConsoleObserver();
        addObserver(consoleObserver);

        Scanner scanner = new Scanner(System.in);
        runMenu(scanner);
        scanner.close();
    }

    private static void runMenu(Scanner scanner) {
        System.out.println("Astronaut Daily Schedule Organizer");
        System.out.println("1. Add Task");
        System.out.println("2. Remove Task");
        System.out.println("3. View Tasks (sorted by start time)");
        System.out.println("4. View Tasks by Priority");
        System.out.println("5. Edit Task");
        System.out.println("6. Mark Task as Completed");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addTask(scanner);
                    break;
                case 2:
                    removeTask(scanner);
                    break;
                case 3:
                    viewTasks();
                    break;
                case 4:
                    viewTasksByPriority(scanner);
                    break;
                case 5:
                    editTask(scanner);
                    break;
                case 6:
                    markTaskAsCompleted(scanner);
                    break;
                case 7:
                    System.out.println("Exiting the application.");
                    AppLogger.logInfo("Application exited.");
                    return; // Exit the recursion
                default:
                    System.out.println("Invalid choice. Please try again.");
                    AppLogger.logWarning("Invalid menu choice: " + choice);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            AppLogger.logWarning("Invalid input: not a number");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred. Please try again.");
            AppLogger.logException("Unexpected error", e);
        }

        runMenu(scanner); // Recursively call the menu again
    }

    private static void addTask(Scanner scanner) {
        try {
            System.out.print("Enter task description: ");
            String description = scanner.nextLine();
            System.out.print("Enter start time (HH:MM): ");
            String startTime = scanner.nextLine();
            System.out.print("Enter end time (HH:MM): ");
            String endTime = scanner.nextLine();
            System.out.print("Enter priority level (Low, Medium, High): ");
            String priority = scanner.nextLine();

            Task task = TaskFactory.createTask(description, startTime, endTime, priority);
            scheduleManager.addTask(task);
            notifyObservers("Task added successfully. No conflicts.");
            AppLogger.logInfo("Task added: " + description);
        } catch (TaskConflictException e) {
            notifyObservers(e.getMessage());
            AppLogger.logWarning(e.getMessage());
        } catch (Exception e) {
            System.out.println("Failed to add task. Please check the input and try again.");
            AppLogger.logException("Failed to add task", e);
        }
    }

    private static void removeTask(Scanner scanner) {
        try {
            System.out.print("Enter task description to remove: ");
            String description = scanner.nextLine();
            scheduleManager.removeTask(description);
            notifyObservers("Task removed successfully.");
            AppLogger.logInfo("Task removed: " + description);
        } catch (TaskNotFoundException e) {
            notifyObservers(e.getMessage());
            AppLogger.logWarning(e.getMessage());
        } catch (Exception e) {
            System.out.println("Failed to remove task. Please try again.");
                    
            AppLogger.logException("Failed to remove task", e);
        }
    }

    private static void viewTasks() {
        try {
            List<Task> tasks = scheduleManager.viewTasks();
            if (tasks.isEmpty()) {
                System.out.println("No tasks scheduled for the day.");
                AppLogger.logInfo("No tasks scheduled for the day.");
            } else {
                for (Task t : tasks) {
                    System.out.println(t);
                }
                AppLogger.logInfo("Tasks viewed.");
            }
        } catch (Exception e) {
            System.out.println("Failed to view tasks. Please try again.");
            AppLogger.logException("Failed to view tasks", e);
        }
    }

    private static void viewTasksByPriority(Scanner scanner) {
        try {
            System.out.print("Enter priority level (Low, Medium, High): ");
            String priority = scanner.nextLine();
            List<Task> tasks = scheduleManager.viewTasksByPriority(priority);
            if (tasks.isEmpty()) {
                System.out.println("No tasks found with priority " + priority);
                AppLogger.logInfo("No tasks found with priority " + priority);
            } else {
                for (Task t : tasks) {
                    System.out.println(t);
                }
                AppLogger.logInfo("Tasks viewed by priority: " + priority);
            }
        } catch (Exception e) {
            System.out.println("Failed to view tasks by priority. Please try again.");
            AppLogger.logException("Failed to view tasks by priority", e);
        }
    }

    private static void editTask(Scanner scanner) {
        try {
            System.out.print("Enter existing task description to edit: ");
            String oldDescription = scanner.nextLine();
            System.out.print("Enter new task description: ");
            String newDescription = scanner.nextLine();
            System.out.print("Enter new start time (HH:MM): ");
            String newStartTime = scanner.nextLine();
            System.out.print("Enter new end time (HH:MM): ");
            String newEndTime = scanner.nextLine();
            System.out.print("Enter new priority level (Low, Medium, High): ");
            String newPriority = scanner.nextLine();

            scheduleManager.editTask(oldDescription, newDescription, newStartTime, newEndTime, newPriority);
            notifyObservers("Task edited successfully. No conflicts.");
            AppLogger.logInfo("Task edited: " + oldDescription + " to " + newDescription);
        } catch (TaskNotFoundException | TaskConflictException e) {
            notifyObservers(e.getMessage());
            AppLogger.logWarning(e.getMessage());
        } catch (Exception e) {
            System.out.println("Failed to edit task. Please check the input and try again.");
            AppLogger.logException("Failed to edit task", e);
        }
    }

private static void markTaskAsCompleted(Scanner scanner) {
    try {
        System.out.print("Enter task description to mark as completed: ");
        String description = scanner.nextLine();
        System.out.print("Enter start time (HH:MM): ");
        String startTime = scanner.nextLine();
        System.out.print("Enter end time (HH:MM): ");
        String endTime = scanner.nextLine();
        System.out.print("Enter priority level (Low, Medium, High): ");
        String priority = scanner.nextLine();

        Task taskToMark = scheduleManager.viewTasks().stream()
                .filter(task -> task.getDescription().replace(" (Completed)", "").equals(description) &&
                        task.getStartTime().equals(startTime) &&
                        task.getEndTime().equals(endTime) &&
                        task.getPriority().equals(priority))
                .findFirst()
                .orElse(null);

        if (taskToMark == null) {
            notifyObservers("Task not found.");
            AppLogger.logWarning("Task not found: " + description);
        } else if (taskToMark.getDescription().contains("(Completed)")) {
            notifyObservers("Task is already marked as completed.");
            AppLogger.logInfo("Task is already completed: " + description);
        } else {
            taskToMark.setDescription(description + " (Completed)");
            notifyObservers("Task marked as completed.");
            AppLogger.logInfo("Task marked as completed: " + description);
        }
    } catch (Exception e) {
        System.out.println("Failed to mark task as completed. Please try again.");
        AppLogger.logException("Failed to mark task as completed", e);
    }
}


    private static void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public static void addObserver(Observer observer) {
        observers.add(observer);
    }

    public static void removeObserver(Observer observer) {
        observers.remove(observer);
    }
}
