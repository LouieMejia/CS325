package DAOClasses;

import GetterSetter.Tasks;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskDao {
    private Connection connection = Database.getConnection();

    public void addTask(String name, String description , int listId, LocalDateTime dueDate) {
            // Check if list exists
            String checkListQuery = "SELECT id FROM lists WHERE id = ?";
            try (PreparedStatement checkListStatement = connection.prepareStatement(checkListQuery)) {
                checkListStatement.setInt(1, listId);
                try (ResultSet resultSet = checkListStatement.executeQuery()) {
                    if (!resultSet.next()) {
                        System.out.println("List with id " + listId + " does not exist.");
                        return;
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Error creating task: " + ex.getMessage());
            }

        // Insert task
            String addTaskQuery = "INSERT INTO tasks (name, description, list_id, due_date) VALUES (?, ?, ?, ?)";
            try (PreparedStatement addTaskStatement = connection.prepareStatement(addTaskQuery)) {
                addTaskStatement.setString(1, name);
                addTaskStatement.setString(2, description);
                addTaskStatement.setInt(3, listId);
                addTaskStatement.setObject(4, dueDate);
                int rowsAffected = addTaskStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Task added successfully.");
                } else {
                    System.out.println("Task not added.");
                }
            } catch (SQLException ex) {
                System.out.println("Error creating task: " + ex.getMessage());
            }
    }
    public List<Tasks> viewTasksInAList(int listId) {
        List<Tasks> tasks = new ArrayList<>();
            String query = "SELECT id, description, due_date FROM tasks WHERE list_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, listId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int taskId = resultSet.getInt("id");
                        String description = resultSet.getString("description");
                        LocalDateTime dueDate = resultSet.getObject("due_date", LocalDateTime.class);
                        tasks.add(new Tasks(taskId, description, dueDate));
                    }
                }
            } catch (SQLException e) {
            System.out.println("Error viewing tasks in list: " + e.getMessage());
        }
        return tasks;
    }

/*    public boolean updateTask(Tasks task) {
        try {
            PreparedStatement stmt = connection.prepareStatement("UPDATE tasks SET name = ?, description = ?, list_id = ?, due_date = ?, status = ?, complete_date = ? WHERE id = ?");
            stmt.setString(1, task.getName());
            stmt.setString(2, task.getDescription());
            stmt.setInt(3, task.getListId());
            stmt.setTimestamp(4, Timestamp.valueOf(task.getDueDate()));
            stmt.setString(5, task.getStatus().getStatus().toUpperCase());
            stmt.setTimestamp(6, Timestamp.valueOf(task.getCompleteDate()));
            stmt.setInt(7, task.getId());
            int affectedRows = stmt.executeUpdate();
            return affectedRows == 1;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return true;
        }
    }*/


/*    public List<Tasks> getTasksByListId(int listId) {
        List<Tasks> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks WHERE list_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, listId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Tasks task = new Tasks();
                task.setId(resultSet.getInt("id"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setListId(resultSet.getInt("list_id"));
                task.setDueDate(resultSet.getTimestamp("due_date").toLocalDateTime());
                task.setCompleteDate(resultSet.getTimestamp("completion_date") != null ? resultSet.getTimestamp("completion_date").toLocalDateTime() : null);
                task.setDateCreated(resultSet.getTimestamp("created_at").toLocalDateTime());
                task.setStatus(TaskStatus.valueOf(resultSet.getString("status")));
                tasks.add(task);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tasks;
    }


    public Tasks getTaskById(int taskId) {
        Tasks task = null;
        String sql = "SELECT * FROM tasks WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, taskId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                task = new Tasks();
                task.setId(resultSet.getInt("id"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setListId(resultSet.getInt("list_id"));
                task.setDueDate(resultSet.getTimestamp("due_date").toLocalDateTime());
                task.setCompleteDate(resultSet.getTimestamp("completion_date") != null ? resultSet.getTimestamp("completion_date").toLocalDateTime() : null);
                task.setDateCreated(resultSet.getTimestamp("created_at").toLocalDateTime());
                task.setStatus(TaskStatus.valueOf(resultSet.getString("status")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return task;
    }*/

}
