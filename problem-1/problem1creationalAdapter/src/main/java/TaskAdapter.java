// TaskAdapter.java
import java.util.List;

public interface TaskAdapter {
    void addTask(String description, String startTime, String endTime, String priority);
    List<Task> viewTasksByPriority(String priority);
}
