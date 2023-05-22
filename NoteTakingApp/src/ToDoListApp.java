import DAOClasses.ListDao;
import DAOClasses.TaskDao;
import DAOClasses.UserDao;
import GetterSetter.TaskStatus;
import GetterSetter.Tasks;
import GetterSetter.ToDoList;
import GetterSetter.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ToDoListApp {
    private UserDao userDAO;
    private ListDao listDAO;
    private TaskDao taskDAO;
    private User user;
    private Tasks task;
    private Scanner scanner;
    String username;
    String password;
    int id;
    int list_id;

    public ToDoListApp() {
        user = new User();
        task = new Tasks();
        userDAO = new UserDao();
        listDAO = new ListDao();
        taskDAO = new TaskDao();
        scanner = new Scanner(System.in);
    }
    public static void main(String []args){
        ToDoListApp toDoListApp = new ToDoListApp();
        toDoListApp.start();
    }
    public void start() {
        System.out.println("Welcome to your To Do list app!");
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.println("1. Login");
            System.out.println("2. Create account");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Username: ");
                    username = scanner.nextLine();
                    System.out.print("Password: ");
                    password = scanner.nextLine();
                    User user = userDAO.getUserByUsernameAndPassword(username, password);

                    if (user == null) {
                        System.out.println("Invalid username or password. Please try again.");
                    } else {
                        System.out.println("Welcome, " + user.getUser() + "!");
                        id = user.getId();
                        username = user.getUser();
                        loggedIn = true;
                    }
                    break;
                case 2:
                    System.out.print("Enter a username: ");
                    String newUsername = scanner.nextLine();
                    System.out.print("Enter a password: ");
                    String newPassword = scanner.nextLine();
                    User newUser = new User(newUsername, newPassword);

                    userDAO.addUser(newUser);
                    System.out.println("Account created successfully.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        // Display main menu and handle user input
        boolean quit = false;
        while (!quit) {
            System.out.println(id);
            System.out.println("1. View all lists");
            System.out.println("2. Create a new list");
            System.out.println("3. Create a new task");
            System.out.println("4. View tasks in a list");
            System.out.println("5. Mark as Done");
            System.out.println("6. Quit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (option) {
                case 1:
                    viewLists();
                    break;
                case 2:
                    createList();
                    break;
                case 3:
                    createTasks();
                break;
                case 4:
                    viewTasksInAList();
                    break;
                case 5:
                    // handle quitting the program
                    System.out.println("Goodbye!");
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
    public void viewLists() {
        List<ToDoList> lists = listDAO.getAllListsForUser(id);
        if (lists.isEmpty()) {
            System.out.println("You have no ToDo lists.");
        } else {
            System.out.println("Your ToDo lists:");
            for (ToDoList list : lists) {
                System.out.println("- " + list.getName() + " (id: " + list.getId() + ")");
            }
        }
    }
    private void createList (){
        System.out.print("Enter a name for the new list: ");
        String newListName = scanner.nextLine();
        ToDoList newList = new ToDoList(newListName, id);
        listDAO.addList(newList);
        System.out.println("List created successfully.");
    }

    private void createTasks() {
        System.out.println("Enter task name:");
        String name = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("List ID: ");
        int listId = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        System.out.print("Due Date and Time (YYYY-MM-DD HH:MM): ");
        String dueDateString = scanner.nextLine();
        task.setStatus(TaskStatus.IN_PROGRESS);
        LocalDateTime dueDate = null;
        try {
            dueDate = LocalDateTime.parse(dueDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date format. Please use the format YYYY-MM-DD HH:MM.");
            return;
        }

        taskDAO.addTask(name, description, listId, dueDate);
    }

    private void viewTasksInAList() {
        System.out.println("Enter list id: ");
        int listId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        List<Tasks> tasks = taskDAO.viewTasksInAList(listId);

        if (tasks.isEmpty()) {
            System.out.println("No tasks found in list.");
        } else {
            System.out.println("Tasks in list:");
            for (Tasks task : tasks) {
                System.out.println(task.toString());
            }
        }
    }

/*    public void markTaskAsComplete() {
        System.out.println("Enter task ID");
        int taskId = scanner.nextInt();
        Tasks task = taskDAO.getTaskById(taskId);
        if (task == null) {
            System.out.println("Task not found.");
            return;
        }
        task.setStatus(TaskStatus.COMPLETED);
        task.setCompleteDate(LocalDateTime.now());

        boolean result = taskDAO.updateTask(task);
        if (result) {
            System.out.println("Task marked as done.");
        } else {
            System.out.println("Error marking task as done.");
        }
    }*/



}

