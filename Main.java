import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n=== TaskFlow Menu ===");
            System.out.println("1. Show all tasks");
            System.out.println("2. Add a new task");
            System.out.println("3. Mark task as completed");
            System.out.println("4. Delete a task");
            System.out.println("5. View Productivity Statistics"); 
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    manager.listTasks();
                    break;
                case "2":
                    System.out.print("Enter task description: ");
                    String desc = scanner.nextLine();
                    System.out.print("Enter priority (High/Medium/Low): ");
                    String priority = scanner.nextLine();
                    manager.addTask(desc, priority);
                    break;
                case "3":
                    System.out.print("Enter task ID to complete: ");
                    try {
                        int idToComplete = Integer.parseInt(scanner.nextLine());
                        manager.markAsCompleted(idToComplete);
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid numeric ID.");
                    }
                    break;
                case "4":
                    System.out.print("Enter task ID to delete: ");
                    try {
                        int idToDelete = Integer.parseInt(scanner.nextLine());
                        manager.deleteTask(idToDelete);
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid numeric ID.");
                    }
                    break;
                case "5":
                    manager.displayProductivity(); 
                    break;
                case "6":
                    System.out.println("Program closed. Have a great day!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}