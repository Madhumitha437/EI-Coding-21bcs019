import java.util.*;
import java.util.stream.Collectors;

public class ScheduleManager {
    private static ScheduleManager instance;
    private List<Task> tasks;

    private ScheduleManager() {
        tasks = new ArrayList<>();
    }

    public static ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }

    public void addTask(Task task) throws TaskConflictException {
        if (isTaskConflict(task)) {
            throw new TaskConflictException("Task conflicts with existing tasks.");
        }
        tasks.add(task);
    }

    public void removeTask(String description) throws TaskNotFoundException {
        boolean removed = tasks.removeIf(task -> task.getDescription().equals(description));
        if (!removed) {
            throw new TaskNotFoundException("Task not found.");
        }
    }

    public List<Task> viewTasks() {
        tasks.sort(Comparator.comparing(Task::getStartTime));
        return tasks;
    }

    public List<Task> viewTasksByPriority(String priority) {
        return tasks.stream()
                .filter(task -> task.getPriority().equalsIgnoreCase(priority))
                .collect(Collectors.toList());
    }

    public void editTask(String oldDescription, String newDescription, String newStartTime, String newEndTime, String newPriority) 
            throws TaskNotFoundException, TaskConflictException {
        Task existingTask = tasks.stream()
                .filter(task -> task.getDescription().equals(oldDescription))
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException("Task not found."));

        Task updatedTask = TaskFactory.createTask(newDescription, newStartTime, newEndTime, newPriority);
        if (isTaskConflict(updatedTask)) {
            throw new TaskConflictException("Task conflicts with existing tasks.");
        }

        existingTask.setDescription(newDescription);
        existingTask.setStartTime(newStartTime);
        existingTask.setEndTime(newEndTime);
        existingTask.setPriority(newPriority);
    }

    private boolean isTaskConflict(Task task) {
        return tasks.stream().anyMatch(t -> t.overlaps(task));
    }
}
