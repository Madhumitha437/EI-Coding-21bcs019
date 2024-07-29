// TaskManager.java
import java.util.ArrayList;
import java.util.List;

public class TaskManager implements Subject {
    private List<Task> tasks;
    private List<Observer> observers;

    public TaskManager() {
        tasks = new ArrayList<>();
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
        notifyObservers("Task added: " + task.getDescription());
    }

    public void markTaskAsCompleted(String description) {
        Task taskToMark = findTask(description);
        if (taskToMark != null) {
            if (!taskToMark.getStatus().equals("Completed")) {
                taskToMark.markAsCompleted();
                notifyObservers("Task marked as completed: " + description);
            } else {
                notifyObservers("Task is already completed: " + description);
            }
        } else {
            notifyObservers("Task not found: " + description);
        }
    }

    private Task findTask(String description) {
        return tasks.stream()
                .filter(task -> task.getDescription().equals(description))
                .findFirst()
                .orElse(null);
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
