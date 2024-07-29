// TaskManager.java
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TaskManager {
    private List<TaskComponent> taskComponents = new ArrayList<>();
    private static TaskManager instance;
    private static final Logger logger = Logger.getLogger(TaskManager.class.getName());

    private TaskManager() {}

    public static TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }

    public void addTask(TaskComponent task) {
        taskComponents.add(task);
        logger.info("Task added: " + task.getDescription());
    }

    public void removeTask(TaskComponent task) {
        if (taskComponents.remove(task)) {
            logger.info("Task removed: " + task.getDescription());
        } else {
            logger.warning("Task not found for removal: " + task.getDescription());
        }
    }

    public List<TaskComponent> getTaskComponents() {
        return new ArrayList<>(taskComponents);
    }

    public void displayTasks() {
        for (TaskComponent task : taskComponents) {
            task.display();
        }
    }
}
