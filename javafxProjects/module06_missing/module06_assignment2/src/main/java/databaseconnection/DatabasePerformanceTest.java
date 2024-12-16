package main.java.databaseconnection;

import java.sql.*;

public class DatabasePerformanceTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/my database";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {
            // Create the Temp table if it doesn't exist
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Temp (num1 DOUBLE, num2 DOUBLE, num3 DOUBLE)");

            // Single-row insert performance test
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < 1000; i++) {
                double num1 = Math.random();
                double num2 = Math.random();
                double num3 = Math.random();
                String sql = "INSERT INTO Temp VALUES (" + num1 + ", " + num2 + ", " + num3 + ")";
                stmt.executeUpdate(sql);
            }
            long endTime = System.currentTimeMillis();
            long singleRowTime = endTime - startTime;
            System.out.println("Single-row insert time: " + singleRowTime + " milliseconds");

            // Batch update performance test
            startTime = System.currentTimeMillis();
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Temp VALUES (?, ?, ?)");
            for (int i = 0; i < 1000; i++) {
                double num1 = Math.random();
                double num2 = Math.random();
                double num3 = Math.random();
                pstmt.setDouble(1, num1);
                pstmt.setDouble(2, num2);
                pstmt.setDouble(3, num3);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            endTime = System.currentTimeMillis();
            long batchUpdateTime = endTime - startTime;
            System.out.println("Batch update time: " + batchUpdateTime + " milliseconds");

            // Compare performance
            System.out.println("Batch update is " + (singleRowTime / batchUpdateTime) + " times faster than single-row insert");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}