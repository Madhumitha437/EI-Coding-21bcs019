
// TaskGroup.java
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TaskGroup implements TaskComponent {
    private String groupName;
    private List<TaskComponent> tasks = new ArrayList<>();
    private static final Logger logger = Logger.getLogger(TaskGroup.class.getName());

    public TaskGroup(String groupName) {
        if (groupName == null || groupName.isEmpty()) {
            logger.severe("Group name cannot be empty");
            throw new IllegalArgumentException("Group name cannot be empty");
        }
        this.groupName = groupName;
    }

    @Override
    public String getDescription() {
        return groupName;
    }

    @Override
    public String getStartTime() {
        return null; // Not applicable for group
    }

    @Override
    public String getEndTime() {
        return null; // Not applicable for group
    }

    @Override
    public String getPriority() {
        return null; // Not applicable for group
    }

    @Override
    public String getStatus() {
        return null; // Not applicable for group
    }

    @Override
    public void markAsCompleted() {
        for (TaskComponent task : tasks) {
            task.markAsCompleted();
        }
        logger.info("All tasks in group " + groupName + " marked as completed");
    }

    @Override
    public void add(TaskComponent task) {
        tasks.add(task);
        logger.info("Task added to group " + groupName + ": " + task.getDescription());
    }

    @Override
    public void remove(TaskComponent task) {
        tasks.remove(task);
        logger.info("Task removed from group " + groupName + ": " + task.getDescription());
    }

    @Override
    public void display() {
        System.out.println("Task Group: " + groupName);
        for (TaskComponent task : tasks) {
            task.display();
        }
    }
}
