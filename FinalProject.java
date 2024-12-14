import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager 
{
    private ArrayList<String> pendingTasks;
    private String[] completedTasks;
    private static final String PENDING_FILE = "pendingTasks.txt";
    private static final String COMPLETED_FILE = "completedTasks.txt";

    public TaskManager() 
    {
        pendingTasks = new ArrayList<>();
        completedTasks = new String[100]; // Example size
        loadTasks();
    }

    public void addTask(String task) 
    {
        pendingTasks.add(task);
    }

    public void completeTask(int index) 
    {
        try {
            completedTasks[index] = pendingTasks.remove(index);
        } catch (IndexOutOfBoundsException e) 
        {
            System.out.println("Invalid task index.");
        }
    }

    private void loadTasks() 
    {
        try (BufferedReader br = new BufferedReader(new FileReader(PENDING_FILE))) 
        {
            String line;
            while ((line = br.readLine()) != null) 
            {
                pendingTasks.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error loading pending tasks.");
        }

        try (BufferedReader br = new BufferedReader(new FileReader(COMPLETED_FILE))) 
        {
            String line;
            int index = 0;
            while ((line = br.readLine()) != null) 
            {
                completedTasks[index++] = line;
            }
        } catch (IOException e) {
            System.out.println("Error loading completed tasks.");
        }
    }

    private void saveTasks() 
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PENDING_FILE))) 
        {
            for (String task : pendingTasks) 
            {
                bw.write(task);
                bw.newLine();
            }
        } catch (IOException e) 
        {
            System.out.println("Error saving pending tasks.");
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(COMPLETED_FILE))) 
        {
            for (String task : completedTasks) 
            {
                if (task != null) 
                {
                    bw.write(task);
                    bw.newLine();
                }
            }
        } catch (IOException e) 
        {
            System.out.println("Error saving completed tasks.");
        }
    }

    private void displayMenu() 
    {
        System.out.println("Task Manager Menu:");
        System.out.println("1. Add Task");
        System.out.println("2. Complete Task");
        System.out.println("3. View Pending Tasks");
        System.out.println("4. View Completed Tasks");
        System.out.println("5. Exit");
    }

    private void handleUserInput() 
    {
        Scanner scanner = new Scanner(System.in);
        while (true) 
        {
            displayMenu();
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) 
            {
                case 1:
                    System.out.print("Enter task: ");
                    String task = scanner.nextLine();
                    addTask(task);
                    break;
                case 2:
                    System.out.print("Enter task index to complete: ");
                    int index = scanner.nextInt();
                    completeTask(index);
                    break;
                case 3:
                    System.out.println("Pending Tasks:");
                    for (int i = 0; i < pendingTasks.size(); i++) 
                    {
                        System.out.println(i + ": " + pendingTasks.get(i));
                    }
                    break;
                case 4:
                    System.out.println("Completed Tasks:");
                    for (int i = 0; i < completedTasks.length; i++) 
                    {
                        if (completedTasks[i] != null) 
                        {
                            System.out.println(i + ": " + completedTasks[i]);
                        }
                    }
                    break;
                case 5:
                    saveTasks();
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) 
    {
        TaskManager manager = new TaskManager();
        manager.handleUserInput();
    }
}
