Expt. No. : 16	Car Maintenance Scheduler

PO-PSO Mapping Table :


	Relevance	Justification
PO1 – Engineering Knowledge	3	Applies core computer science principles to design a real-time reminder and scheduling system.
PO2 – Problem Analysis	2	Identifies user needs, such as service reminders and maintenance tracking, and designs solutions accordingly.
PO3 – Design/Development of Solutions	3	Designs a full-stack application integrating frontend, backend, and database for vehicle service management.
PO4 – Conduct Investigations of Complex Problems	2	Analyzes user behavior, maintenance frequency, and performance logs to improve accuracy of alerts.
PO5 – Modern Tool Usage	2	Uses modern tools like Flask/Spring Boot, MySQL, and cloud platforms (Render/Firebase) for deployment.
PO6 – The Engineer and Society	1	Helps vehicle owners maintain road safety and reduces breakdowns through timely reminders.
PO8 – Ethics	3	Ensures responsible handling of user data and promotes digital responsibility.
PO9 – Individual and Team Work	1	Can be built individually or collaboratively with UI and backend developers.
PO10 – Communication	2	Promotes structured communication through documentation and UI feedback.
PSO1 – Apply fundamental computing knowledge	3	Implements scheduling logic, database handling, and backend–frontend communication.
PSO2 – Design and implement solutions	2	Creates an efficient, user-friendly application with CRUD operations for vehicles and maintenance logs.
PSO3 – Use modern tools and technologies	1	Uses APIs, notifications, and cloud-based deployment for modern usability.
 
 
Introduction :

Vehicle maintenance plays a critical role in ensuring safety, performance, and longevity. However, many users forget regular service dates, oil changes, or insurance renewals, leading to costly breakdowns.
The Car Maintenance Scheduler is a smart web-based system that automates service scheduling, tracks maintenance history, and notifies users of upcoming tasks.
It provides personalized reminders via email or app notifications and maintains a complete log of every vehicle’s service record.
The system eliminates manual tracking and reduces the chances of missed maintenance activities by offering an intelligent, automated solution suitable for individuals, car rental companies, and workshops.

Objectives
•	To automate the process of scheduling car maintenance and sending timely reminders.
•	To maintain digital records of each vehicle’s service, parts replaced, and last inspection date.
•	To improve vehicle life and reduce repair costs through regular maintenance.
•	To design a user-friendly dashboard for owners to monitor all their cars.
•	To integrate email/SMS alerts for upcoming service due dates.
•	To provide admin control for garages to manage customer reminders.

Project Distribution :

Module	Name	Concept (Unit)	Description
1	Vehicle & User Registration	Core OOP Principles & Inheritance	Handles user sign-up, vehicle profile creation (make, model, VIN), and basic data storage.
2	Service Scheduling	Exception Handling & Control Structures	Allows users to log past services and set future reminders based on date or mileage.
3	Real-Time Notification	Multithreading & Networking	Implements a background process to check for due dates and sends alerts via email or push notifications.
4	Backend & Database	JDBC & Database Connectivity	Manages CRUD operations for service logs, vehicle details, and user accounts in the SQL database.
5	Frontend & Reporting	Java GUI (JavaFX/Swing)	Provides an interactive dashboard for viewing service history, upcoming due dates, and generating maintenance reports.
 

System Architecture :

 
 
Coding :
Module 1:
package project;

// UNIT I: OOP Concepts – Classes, Objects, Inheritance, Polymorphism, etc.
abstract class Vehicle {
    protected String model;
    protected int year;

    Vehicle(String model, int year) {
        this.model = model;
        this.year = year;
    }

    abstract void displayInfo();
}

class Car extends Vehicle {
    private String owner;

    Car(String model, int year, String owner) {
        super(model, year);
        this.owner = owner;
    }

    @Override
    void displayInfo() {
        System.out.println("Car Model: " + model + ", Year: " + year + ", Owner: " + owner);
    }
}

public class mod1 {
    public static void main(String[] args) {
        Car c1 = new Car("Honda City", 2022, "Chanakyan");
        c1.displayInfo();
    }
}

MODULE 2:
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
MODULE 3:
package project;

// UNIT III: Generics and Multi-threading
class ServiceTask<T> extends Thread {
    private T taskName;

    ServiceTask(T taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println("Performing maintenance task: " + taskName);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Task interrupted");
        }
    }
}

public class mod3 {
    public static void main(String[] args) {
        ServiceTask<String> t1 = new ServiceTask<>("Oil Change");
        ServiceTask<String> t2 = new ServiceTask<>("Tyre Check");

        t1.start();
        t2.start();
    }
}
MODULE 4:(Backend and Database connection) package project;
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
 
MODULE 5(Frontend and User Interface):
package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.sql.*;

public class mod7 extends Application {

    // Database details
    private static final String URL = "jdbc:mysql://localhost:3306/sql_file_4";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    // Input fields
    private TextField ownerNameField;
    private TextField carModelField;
    private DatePicker serviceDatePicker;
    private TextField serviceTypeField;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Car Service Entry Form");

        // Labels and input fields
        Label nameLabel = new Label("Owner Name:");
        ownerNameField = new TextField();

        Label modelLabel = new Label("Car Model:");
        carModelField = new TextField();

        Label dateLabel = new Label("Service Date:");
        serviceDatePicker = new DatePicker();

        Label typeLabel = new Label("Service Type:");
        serviceTypeField = new TextField();

        Button addButton = new Button("Add to Database");
        addButton.setOnAction(e -> insertData());

        // Layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        grid.add(nameLabel, 0, 0);
        grid.add(ownerNameField, 1, 0);
        grid.add(modelLabel, 0, 1);
        grid.add(carModelField, 1, 1);
        grid.add(dateLabel, 0, 2);
        grid.add(serviceDatePicker, 1, 2);
        grid.add(typeLabel, 0, 3);
        grid.add(serviceTypeField, 1, 3);
        grid.add(addButton, 1, 4);

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void insertData() {
        String name = ownerNameField.getText();
        String model = carModelField.getText();
        String date = (serviceDatePicker.getValue() != null) ? serviceDatePicker.getValue().toString() : null;
        String type = serviceTypeField.getText();

        if (name.isEmpty() || model.isEmpty() || date == null || type.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Please fill all fields!");
            return;
        }

        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to DB
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "INSERT INTO car_service (owner_name, car_model, service_date, service_type) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, model);
            pstmt.setString(3, date);
            pstmt.setString(4, type);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {


                showAlert(Alert.AlertType.INFORMATION, "Record added successfully!");
                clearFields();
            }

            conn.close();
        } catch (Exception ex) {
            showAlert(Alert.AlertType.ERROR, "Database Error: " + ex.getMessage());
        }
    }

    private void clearFields() {
        ownerNameField.clear();
        carModelField.clear();
        serviceDatePicker.setValue(null);
        serviceTypeField.clear();
    }

    private void showAlert(Alert.AlertType type, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
Database(MySql):
CREATE DATABASE IF NOT EXISTS car_service;
USE car_service;

CREATE TABLE IF NOT EXISTS MaintenanceRecords (
    record_id INT AUTO_INCREMENT PRIMARY KEY,
    car_model VARCHAR(100),
    owner_name VARCHAR(100),
    maintenance_task VARCHAR(100),
    service_date DATE,
    status VARCHAR(50)

);

INSERT INTO MaintenanceRecords (car_model, owner_name, maintenance_task, service_date, status)
VALUES 
('Honda City', 'Chanakyan', 'Oil Change', '2025-11-05', 'Completed'),
('Hyundai i20', 'Rahul', 'Tyre Replacement', '2025-11-06', 'Pending'),
('Maruti Swift', 'Arun', 'Brake Inspection', '2025-11-07', 'Scheduled');

SELECT * FROM MaintenanceRecords;

UPDATE MaintenanceRecords
SET status = 'Completed'
WHERE record_id = 2;

DELETE FROM MaintenanceRecords
WHERE record_id = 3;

SELECT * FROM MaintenanceRecords
WHERE service_date >= CURDATE()
ORDER BY service_date ASC;


GITHUB Link for Full Code :
CODE: https://github.com/Amudieshwar-AG/Java-mini-project
README: https://github.com/Amudieshwar-AG/Java-mini-project/blob/main/README.md

Screenshots :
Frontend Structure :
 
 


Description:
The Car Maintenance Scheduler is a web-based application designed to help users manage their vehicle maintenance efficiently. It reminds owners of important tasks like oil changes, battery checks, insurance renewals, and other periodic services. The system automatically calculates upcoming service dates and sends timely notifications to ensure no maintenance is missed.
The project consists of three main parts:
•	Frontend: Built using HTML, CSS, and JavaScript for an easy-to-use interface where users can add car details and view reminders.
•	Backend: Developed with Spring Boot or Flask to handle calculations, reminders, and data communication through RESTful APIs.
•	Database: Uses MySQL or Firebase to store vehicle details, user information, and service history securely.
The system also supports email or app-based reminders before each service date. An admin panel can be used by garages to manage multiple customers and their vehicles. The project is deployed using Render for backend hosting and GitHub Pages for frontend access.
Overall, the Car Maintenance Scheduler provides a simple, automated, and reliable solution for tracking vehicle maintenance, helping users save time, reduce repair costs, and improve vehicle life.
 
Database Structure :





Description:

The backend of the Online Voting System is developed using core Java with JDBC (Java Database Connectivity) for seamless integration with the MySQL database. It handles user authentication, candidate retrieval, and vote recording operations securely and efficiently.

When a voter logs in and casts a vote through the JavaFX interface, the backend connects to the MySQL database and updates the corresponding candidate’s vote count in real time. All database transactions are managed through prepared SQL statements to ensure data accuracy and prevent SQL injection vulnerabilities.

The backend logic ensures that each vote is counted only once, maintaining the integrity of the election process. This setup provides a robust bridge between the user interface and the database, making the system reliable, scalable, and secure for electronic voting operations.


Database Storage Image :
 
 
 
Description:

The database is the backbone of the Car Maintenance Scheduler, used to store and manage all essential information such as user details, vehicle information, service dates, and maintenance history. It ensures that all data entered by the user is saved securely and can be retrieved easily whenever needed. Each record includes details like the car’s model, registration number, last service date, next service date, and service type. This helps maintain a complete digital record of all vehicle-related activities in an organized way.
 
The system uses MySQL or Firebase Realtime Database for data storage. MySQL provides structured tables and strong query support, making it ideal for relational data handling, while Firebase offers cloud-based synchronization for real-time updates. The database is linked to the backend through RESTful APIs, ensuring smooth data flow between the user interface and server. This setup guarantees reliability, quick access, and easy scalability for future enhancements.
 
Github Pages :



Description:

The GitHub Repository for the Car Maintenance Scheduler project is used to store, organize, and manage all the source code files related to the system. It contains separate folders for the frontend, backend, and database scripts, making it easier to identify and modify specific parts of the application. The repository also includes important files like README.md, configuration files, and deployment setup details, which describe how to run and host the project. This structure helps maintain clarity and ensures that all project-related data is available in one centralized location.

The repository serves as an effective version control system, allowing developers to record every change made during the development process. Each commit stores information about updates, bug fixes, and improvements, enabling the team to track progress or roll back to a previous version if required. Branching and merging features make it simple to experiment with new ideas without affecting the main project. This also helps when multiple contributors are involved, ensuring smooth teamwork and avoiding code conflicts.

Additionally, the GitHub repository is linked to the Render and GitHub Pages platforms for automated deployment. Whenever the project files are updated and pushed to the main branch, the changes are automatically reflected in the hosted version of the application. This integration ensures continuous deployment (CI/CD) and easy accessibility of the project online. Overall, the GitHub repository plays a vital role in maintaining version control, enabling collaboration, and ensuring that the Car Maintenance Scheduler remains reliable, updated, and efficiently managed.
 
Website Output Pages:

The application displays a clean and interactive JavaFX interface that serves as the home page of the Online Voting System. The login window allows users to securely enter their credentials before accessing the voting page. Upon successful login, the system presents a neatly designed voting screen where voters can view a list of candidates and their respective parties through a drop-down menu.
When a user selects a candidate and clicks the “Vote” button, the system immediately records the vote in the MySQL database and updates the vote count in real time. If a voter attempts to proceed without selecting a candidate, the interface provides instant feedback with a clear message such as “Please select a candidate!”
The GUI ensures a smooth and error-free user experience by validating inputs, preventing duplicate submissions, and confirming successful voting actions with status messages like “Vote recorded successfully!” This interactive design demonstrates effective synchronization between the frontend (JavaFX), backend (Java logic), and database (MySQL) for a seamless and reliable online voting experience.

 
 
 





 
 
 
 

Conclusion:

The Car Maintenance Scheduler project successfully demonstrates how modern web technologies can be used to automate and simplify everyday tasks like vehicle maintenance tracking. By integrating an intelligent scheduling and reminder system, it reduces the possibility of missed services and ensures that vehicles are maintained on time. The project effectively combines frontend design, backend logic, and database connectivity to create a user-friendly and reliable application. It also highlights the importance of cloud deployment and real-time data handling in building efficient, accessible systems.

From a technical perspective, the system showcases a practical implementation of full-stack development, involving technologies such as HTML, CSS, JavaScript, Spring Boot/Flask, and MySQL/Firebase. The use of RESTful APIs enables smooth communication between client and server, while cloud platforms like Render and GitHub Pages make deployment fast and scalable. The project structure encourages modular coding and follows good software engineering principles such as maintainability, security, and usability. Through proper design and testing, the application achieves accuracy, performance, and stability.

In conclusion, the Car Maintenance Scheduler not only meets its functional objectives but also provides a real-world solution that can benefit both individual car owners and service centers. It promotes safety, cost-efficiency, and convenience by ensuring timely vehicle care. Future improvements like AI-based predictive maintenance, mobile app integration, and IoT sensor connectivity can make it even more powerful and intelligent. Overall, the project serves as a strong example of how technology can be applied to make daily life smarter, safer, and more organized.

Future Work:

      •  AI-Based Predictive Maintenance:
Use artificial intelligence to predict future maintenance needs based on mileage, driving habits, and sensor data.
      •  Mobile Application Development:
Create a mobile app version using Flutter or React Native for better accessibility and real-time notifications.
      •  Expense and Report Tracking:
Add a feature to track maintenance costs and generate summary reports for each vehicle.
      •  Google Maps Integration:
Connect with Google Maps to locate nearby service centers and garages for quick booking.
      •  IoT Integration:
Integrate IoT sensors to monitor real-time vehicle health data like oil level, battery, and engine temperature.
      •  QR Code System:
Implement QR codes for each vehicle to instantly access maintenance history and details.
      •  Cloud Backup and Multi-User Access:
Enable automatic cloud backups and allow multiple users (like owners and mechanics) to access and update records securely.

