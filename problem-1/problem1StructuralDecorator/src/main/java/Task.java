
// Task.java
import java.util.logging.Logger;

public class Task implements TaskComponent {
    private String description;
    private String startTime;
    private String endTime;
    private String priority;
    private String status;
    private static final Logger logger = Logger.getLogger(Task.class.getName());

    public Task(String description, String startTime, String endTime, String priority) {
        if (description == null || description.isEmpty()) {
            logger.severe("Task description cannot be empty");
            throw new IllegalArgumentException("Task description cannot be empty");
        }
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
        this.status = "Pending";
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getStartTime() {
        return startTime;
    }

    @Override
    public String getEndTime() {
        return endTime;
    }

    @Override
    public String getPriority() {
        return priority;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void markAsCompleted() {
        if (!status.equals("Completed")) {
            this.status = "Completed";
            logger.info("Task marked as completed: " + description);
        }
    }

    @Override
    public void add(TaskComponent task) {
        throw new UnsupportedOperationException("Cannot add to a leaf task");
    }

    @Override
    public void remove(TaskComponent task) {
        throw new UnsupportedOperationException("Cannot remove from a leaf task");
    }

    @Override
    public void display() {
        System.out.println(startTime + " - " + endTime + ": " + description + " [" + priority + "] (" + status + ")");
    }
}
