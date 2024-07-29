// TaskManager.java
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;
    private TaskManagementStrategy strategy;

    public TaskManager(TaskManagementStrategy strategy) {
        this.tasks = new ArrayList<>();
        this.strategy = strategy;
    }

    public void setStrategy(TaskManagementStrategy strategy) {
        this.strategy = strategy;
    }

    public void addTask(Task task) {
        strategy.addTask(this, task);
    }

    public void markTaskAsCompleted(String description) {
        strategy.markTaskAsCompleted(this, description);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Task findTask(String description) {
        return tasks.stream()
                .filter(task -> task.getDescription().equals(description))
                .findFirst()
                .orElse(null);
    }
}
