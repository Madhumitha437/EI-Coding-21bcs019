// TaskManager.java
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;
    private static TaskManager instance;

    // Private constructor to prevent instantiation
    private TaskManager() {
        tasks = new ArrayList<>();
    }

    // Singleton instance method
    public static TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }

    // Method to add a task
    public void addTask(Task task) {
        tasks.add(task);
    }

    // Method to view all tasks
    public List<Task> getTasks() {
        return tasks;
    }
}
