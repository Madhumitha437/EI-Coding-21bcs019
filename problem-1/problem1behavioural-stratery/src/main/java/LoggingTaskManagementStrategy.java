// LoggingTaskManagementStrategy.java
public class LoggingTaskManagementStrategy implements TaskManagementStrategy {
    private Logger logger;

    public LoggingTaskManagementStrategy(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void addTask(TaskManager taskManager, Task task) {
        taskManager.addTask(task);
        logger.logInfo("Task added: " + task.getDescription());
    }

    @Override
    public void markTaskAsCompleted(TaskManager taskManager, String description) {
        taskManager.markTaskAsCompleted(description);
        logger.logInfo("Marked task as completed: " + description);
    }
}