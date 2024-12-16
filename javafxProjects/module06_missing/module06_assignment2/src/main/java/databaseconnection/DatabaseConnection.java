package main.java.databaseconnection;

import java.sql.*;

public class DatabaseConnection {
    public static void main(String[] args) {
        try {
            // MySQL database credentials
            String url = "jdbc:mysql://localhost:3306/my database";
            String user = "root";
            String password = "";

            Connection conn = DriverManager.getConnection(url, user, password);

            // database operations here

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}