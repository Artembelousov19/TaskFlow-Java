import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks;
    private int nextId;

    public TaskManager() {
        this.tasks = FileHandler.loadTasks();
        this.nextId = tasks.stream().mapToInt(Task::getId).max().orElse(0) + 1;
    }

    public void addTask(String description, String priority) {
        Task newTask = new Task(nextId++, description, priority);
        tasks.add(newTask);
        FileHandler.saveTasks(tasks);
        System.out.println("Task added successfully!");
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Your task list is empty.");
            return;
        }
        System.out.println("\n--- YOUR TASKS ---");
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public void markAsCompleted(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setCompleted(true);
                FileHandler.saveTasks(tasks);
                System.out.println("Task marked as completed!");
                return;
            }
        }
        System.out.println("Task with this ID was not found.");
    }

    public void deleteTask(int id) {
        boolean removed = tasks.removeIf(task -> task.getId() == id);
        if (removed) {
            FileHandler.saveTasks(tasks);
            System.out.println("Task deleted successfully.");
        } else {
            System.out.println("Task with this ID was not found.");
        }
    }

    // УНИКАЛЬНАЯ ФИЧА: Статистика продуктивности
    public void displayProductivity() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available to calculate productivity. Add some tasks first!");
            return;
        }

        int totalTasks = tasks.size();
        long completedTasks = tasks.stream().filter(Task::isCompleted).count();
        double completionRate = ((double) completedTasks / totalTasks) * 100;

        // Определяем ранг пользователя на основе его успехов
        String rank;
        if (completionRate == 100) {
            rank = "Productivity God 👑";
        } else if (completionRate >= 75) {
            rank = "High Achiever 🎯";
        } else if (completionRate >= 50) {
            rank = "Steady Worker ☕";
        } else if (completionRate > 0) {
            rank = "Procrastinator Starter 🌱";
        } else {
            rank = " Couch Potato 🥔";
        }

        System.out.println("\n=================================");
        System.out.println("      PRODUCTIVITY REPORT        ");
        System.out.println("=================================");
        System.out.printf("Total Tasks:       %d\n", totalTasks);
        System.out.printf("Completed Tasks:   %d\n", completedTasks);
        System.out.printf("Completion Rate:   %.1f%%\n", completionRate);
        System.out.println("---------------------------------");
        System.out.println("Your Rank:         " + rank);
        System.out.println("=================================");
    }
}