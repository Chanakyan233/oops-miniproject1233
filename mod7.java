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
