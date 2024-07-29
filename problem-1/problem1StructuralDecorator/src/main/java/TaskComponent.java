// TaskComponent.java
public interface TaskComponent {
    String getDescription();
    String getStartTime();
    String getEndTime();
    String getPriority();
    String getStatus();
    void markAsCompleted();
    void add(TaskComponent task) throws UnsupportedOperationException;
    void remove(TaskComponent task) throws UnsupportedOperationException;
    void display();
}
