package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Update the username and password as per your database setup
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/file_hider_project", "root", "Van#20world");
                System.out.println("Connected successfully!");
            } catch (ClassNotFoundException e) {
                System.err.println("MySQL JDBC Driver not found.");
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.println("Connection failed. Check the connection properties.");
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed successfully!");
            } catch (SQLException ex) {
                System.err.println("Failed to close the connection.");
                ex.printStackTrace();
            }
        }
    }
}
