import java.io.Serializable;

public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String description;
    private String priority;
    private boolean isCompleted;

    public Task(int id, String description, String priority) {
        this.id = id;
        this.description = description;
        this.priority = priority;
        this.isCompleted = false;
    }

    public int getId() { return id; }
    public String getDescription() { return description; }
    public String getPriority() { return priority; }
    public boolean isCompleted() { return isCompleted; }
    
    public void setCompleted(boolean completed) { this.isCompleted = completed; }

    @Override
    public String toString() {
        String status = isCompleted ? "[X]" : "[ ]";
        return String.format("%d. %s %s (Приоритет: %s)", id, status, description, priority);
    }
}
