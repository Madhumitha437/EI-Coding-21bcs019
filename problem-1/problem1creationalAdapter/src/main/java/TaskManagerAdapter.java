// TaskManagerAdapter.java
import java.util.ArrayList;
import java.util.List;

public class TaskManagerAdapter implements TaskAdapter {
    private TaskManager taskManager;

    public TaskManagerAdapter() {
        this.taskManager = TaskManager.getInstance();
    }

    @Override
    public void addTask(String description, String startTime, String endTime, String priority) {
        Task task = new Task(description, startTime, endTime, priority);
        taskManager.addTask(task);
        Logger.getInstance("application.log").logInfo("Task added: " + description);
    }

    @Override
    public List<Task> viewTasksByPriority(String priority) {
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : taskManager.getTasks()) {
            if (task.getPriority().equalsIgnoreCase(priority)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }
}
