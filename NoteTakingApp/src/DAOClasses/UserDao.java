package DAOClasses;

import GetterSetter.User;

import java.sql.*;

public class UserDao {
    private Connection connection;

    public UserDao() {

    }

    public void addUser(User user) {
        connection = Database.getConnection();
        try{
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO users (id, username, password) VALUES (?, ?, ?)");
            stmt.setInt(1, user.getId());
            stmt.setString(2, user.getUser());
            stmt.setString(3, user.getPass());
            stmt.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public void updateUser(User user) {

        try{
            PreparedStatement stmt = connection.prepareStatement("UPDATE users SET password = ? WHERE id = ?");
            stmt.setString(2, user.getPass());
            stmt.setInt(2, Math.toIntExact(user.getId()));
            stmt.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public void deleteUser(int userId) {
        try{
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public User getUserById(int userId) {
        User user = new User();
        try{
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
            }
            }catch(SQLException sqle) {

        }
        return user;
        }


    public User getUserByUsername(String username) {
        User user = new User();
        connection = Database.getConnection();
        try{
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                user = new User(rs.getLong("id"), rs.getString("username"), rs.getString("password"));
            }
        }catch(SQLException sqle) {

        }
        return user;
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User user = null;

        try {
            conn = Database.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUser(rs.getString("username"));
                user.setPass(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
