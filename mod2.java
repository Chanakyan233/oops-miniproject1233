package project;

import java.io.FileWriter;
import java.io.IOException;

// UNIT II: Exception Handling, I/O Streams, Class Hierarchy, etc.
public class mod2 {
    public static void logMaintenance(String carModel, String task) {
        try {
            FileWriter fw = new FileWriter("maintenance_log.txt", true);
            fw.write("Car: " + carModel + " | Task: " + task + "\n");
            fw.close();
            System.out.println("Maintenance logged successfully.");
        } catch (IOException e) {
            System.out.println("Error writing log: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            logMaintenance("Honda City", "Oil Change");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
