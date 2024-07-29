// SimpleTaskManagementStrategy.java
public class SimpleTaskManagementStrategy implements TaskManagementStrategy {
    @Override
    public void addTask(TaskManager taskManager, Task task) {
        taskManager.addTask(task);
    }

    @Override
    public void markTaskAsCompleted(TaskManager taskManager, String description) {
        taskManager.markTaskAsCompleted(description);
    }
}

