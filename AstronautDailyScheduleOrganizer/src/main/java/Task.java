public class Task {
    private String description;
    private String startTime;
    private String endTime;
    private String priority;
    private TaskStatus status;

    // Enum for Task Status
    public enum TaskStatus {
        PENDING,
        COMPLETED
    }

    // Constructor
    public Task(String description, String startTime, String endTime, String priority) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
        this.status = TaskStatus.PENDING; // Default status is PENDING
    }

    // Getters
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

    public TaskStatus getStatus() {
        return status;
    }

    // Setters
    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    // Method to check if two tasks overlap
    public boolean overlaps(Task other) {
        return (this.startTime.compareTo(other.getEndTime()) < 0) &&
               (this.endTime.compareTo(other.getStartTime()) > 0);
    }

    @Override
    public String toString() {
        // Display task status based on the current state
        String completionStatus = (status == TaskStatus.COMPLETED) ? "" : " (Pending)";
        return startTime + " - " + endTime + ": " + description + " [" + priority + "]" + completionStatus;
    }
}
