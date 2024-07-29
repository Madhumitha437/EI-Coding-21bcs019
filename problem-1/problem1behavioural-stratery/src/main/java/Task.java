// Task.java
public class Task {
    private String description;
    private String startTime;
    private String endTime;
    private String priority;
    private String status;

    public Task(String description, String startTime, String endTime, String priority) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
        this.status = "Pending";
    }

    public String getDescription() {
        return description;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }

    public void markAsCompleted() {
        this.status = "Completed";
    }

    @Override
    public String toString() {
        return startTime + " - " + endTime + ": " + description + " [" + priority + "] (" + status + ")";
    }
}
