package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DatabaseConnection {

    // JDBC URL, username, and password of the database
    private static final String URL = "jdbc:mysql://localhost:3306/file_hider_project";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // JDBC variables for opening and managing connection
    private static Connection connection = null;

    public static void main(String[] args) {
        try {
            // Establish the connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            if (connection != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the connection (always a good practice to close resources)
            try {
                if (connection != null) {
                    connection.close();
                    System.out.println("Connection closed!");
                }
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
