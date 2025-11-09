package project;

import java.sql.*;

public class mod4 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/car_service?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String user = "root";
        String password = "123456"; // your MySQL password

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to your existing database
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Connected to MySQL Database: car_service");

            Statement stmt = conn.createStatement();

            // Insert new record
            String insertQuery = "INSERT INTO MaintenanceRecords (car_model, owner_name, maintenance_task, service_date, status) VALUES " +
                    "('Toyota Innova', 'Manoj', 'Engine Check', '2025-11-08', 'In Progress')";
            stmt.executeUpdate(insertQuery);
            System.out.println("✅ New record inserted successfully.");

            // Retrieve and display all records
            String selectQuery = "SELECT * FROM MaintenanceRecords";
            ResultSet rs = stmt.executeQuery(selectQuery);

            System.out.println("\n=== Maintenance Records ===");
            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("record_id") +
                        ", Car Model: " + rs.getString("car_model") +
                        ", Owner: " + rs.getString("owner_name") +
                        ", Task: " + rs.getString("maintenance_task") +
                        ", Date: " + rs.getDate("service_date") +
                        ", Status: " + rs.getString("status")
                );
            }

            // Close connections
            rs.close();
            stmt.close();
            conn.close();
            System.out.println("\n✅ Connection closed successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
