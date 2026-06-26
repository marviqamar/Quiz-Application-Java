package quiz.application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectDb {

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/quiz_application?useSSL=false&serverTimezone=UTC";

            String username = "root";
            String password = "12345";

            con = DriverManager.getConnection(url, username, password);


        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database!");
            e.printStackTrace();
        }

        return con;
    }

    public static void main(String[] args) {
        getConnection();
    }
}
