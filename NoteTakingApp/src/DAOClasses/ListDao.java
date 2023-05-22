package DAOClasses;

import GetterSetter.ToDoList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListDao {
    private Connection connection = Database.getConnection();

    public ListDao() {

    }

    public void addList(ToDoList list) {
        try{
            // Check if user exists
            String checkUserQuery = "SELECT id FROM users WHERE id = ?";
            try (PreparedStatement checkUserStatement = connection.prepareStatement(checkUserQuery)) {
                checkUserStatement.setInt(1, list.getUserId());
                try (ResultSet resultSet = checkUserStatement.executeQuery()) {
                    if (!resultSet.next()) {
                        System.out.println("User with id " + list.getUserId() + " does not exist.");
                        return;
                    }
                }
            }

            // Insert list
            String addListQuery = "INSERT INTO lists (name, user_id) VALUES (?, ?)";
            try (PreparedStatement addListStatement = connection.prepareStatement(addListQuery)) {
                addListStatement.setString(1, list.getName());
                addListStatement.setInt(2, list.getUserId());
                int rowsAffected = addListStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("List added successfully.");
                } else {
                    System.out.println("List not added.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error adding list: " + e.getMessage());
        }
    }

    public List<ToDoList> getAllListsForUser(int userId) {
        List<ToDoList> lists = new ArrayList<>();
        try {
            String query = "SELECT * FROM lists WHERE user_id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, userId);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        ToDoList list = new ToDoList(id, name, userId);
                        lists.add(list);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving lists for user: " + e.getMessage());
        }
        return lists;
    }

}
