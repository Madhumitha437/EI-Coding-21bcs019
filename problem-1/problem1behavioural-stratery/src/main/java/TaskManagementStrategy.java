// TaskManagementStrategy.java
public interface TaskManagementStrategy {
    void addTask(TaskManager taskManager, Task task);
    void markTaskAsCompleted(TaskManager taskManager, String description);
}
