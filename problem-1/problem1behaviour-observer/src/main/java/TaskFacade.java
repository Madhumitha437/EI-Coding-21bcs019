
// TaskFacade.java
public class TaskFacade {
    private TaskManager taskManager;
    private Logger logger;

    public TaskFacade() {
        taskManager = new TaskManager();
        logger = Logger.getInstance("application.log");
    }

    public void addTask(String description, String startTime, String endTime, String priority) {
        try {
            Task task = new Task(description, startTime, endTime, priority);
            taskManager.addTask(task);
            logger.logInfo("Task added: " + description);
        } catch (Exception e) {
            logger.logError("Failed to add task: " + e.getMessage());
        }
    }

    public void markTaskAsCompleted(String description) {
        try {
            taskManager.markTaskAsCompleted(description);
            logger.logInfo("Marked task as completed: " + description);
        } catch (Exception e) {
            logger.logError("Failed to mark task as completed: " + e.getMessage());
        }
    }

    public void viewTasks() {
        for (Task task : taskManager.getTasks()) {
            System.out.println(task);
        }
    }

    public TaskManager getTaskManager() {
        return taskManager;
    }
}
