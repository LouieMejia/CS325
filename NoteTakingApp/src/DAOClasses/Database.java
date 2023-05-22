package DAOClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Database {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/noteappdb";

    static final String USER = "root";
    static final String PASS = "";

    static Connection con;
    private Database() {}
    public static Connection getConnection(){
        if(con == null){
            try{
                con = DriverManager.getConnection(DB_URL, USER, PASS);
            }catch(SQLException sqle){
                System.out.println("Error connection to database: " + sqle);
            }
        }
        return con;
    }
}
