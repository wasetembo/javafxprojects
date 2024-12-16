package staffdatabaseapp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.*;

public class StaffDatabaseApp extends Application {

    // Database connection credentials
    private final String DB_URL = "jdbc:mysql://localhost:3306/yourDatabase"; // Update this
    private final String USER = "yourUsername"; // Update this
    private final String PASS = "yourPassword"; // Update this

    // UI Components
    private TextField tfID = new TextField();
    private TextField tfLastName = new TextField();
    private TextField tfFirstName = new TextField();
    private TextField tfMI = new TextField();
    private TextField tfAddress = new TextField();
    private TextField tfCity = new TextField();
    private TextField tfState = new TextField();
    private TextField tfTelephone = new TextField();
    private TextField tfEmail = new TextField();

    private Label lblMessage = new Label();

    public void start(Stage primaryStage) {
        // Create GridPane for layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Add UI components to the grid
        gridPane.add(new Label("ID"), 0, 0);
        gridPane.add(tfID, 1, 0);
        gridPane.add(new Label("Last Name"), 0, 1);
        gridPane.add(tfLastName, 1, 1);
        gridPane.add(new Label("First Name"), 0, 2);
        gridPane.add(tfFirstName, 1, 2);
        gridPane.add(new Label("MI"), 0, 3);
        gridPane.add(tfMI, 1, 3);
        gridPane.add(new Label("Address"), 0, 4);
        gridPane.add(tfAddress, 1, 4);
        gridPane.add(new Label("City"), 0, 5);
        gridPane.add(tfCity, 1, 5);
        gridPane.add(new Label("State"), 0, 6);
        gridPane.add(tfState, 1, 6);
        gridPane.add(new Label("Telephone"), 0, 7);
        gridPane.add(tfTelephone, 1, 7);
        gridPane.add(new Label("Email"), 0, 8);
        gridPane.add(tfEmail, 1, 8);

        gridPane.add(lblMessage, 1, 9);

        Button btnView = new Button("View");
        Button btnInsert = new Button("Insert");
        Button btnUpdate = new Button("Update");
        Button btnClear = new Button("Clear");

        gridPane.add(btnView, 0, 10);
        gridPane.add(btnInsert, 1, 10);
        gridPane.add(btnUpdate, 2, 10);
        gridPane.add(btnClear, 3, 10);

        // Button actions
        btnView.setOnAction(e -> viewRecord());
        btnInsert.setOnAction(e -> insertRecord());
        btnUpdate.setOnAction(e -> updateRecord());
        btnClear.setOnAction(e -> clearFields());

        // Create scene and set stage
        Scene scene = new Scene(gridPane, 600, 400);
        primaryStage.setTitle("Staff Database Management");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // View a record
    private void viewRecord() {
        String id = tfID.getText();
        if (id.isEmpty()) {
            lblMessage.setText("Please enter ID to view.");
            return;
        }
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "SELECT * FROM Staff WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                tfLastName.setText(resultSet.getString("lastName"));
                tfFirstName.setText(resultSet.getString("firstName"));
                tfMI.setText(resultSet.getString("mi"));
                tfAddress.setText(resultSet.getString("address"));
                tfCity.setText(resultSet.getString("city"));
                tfState.setText(resultSet.getString("state"));
                tfTelephone.setText(resultSet.getString("telephone"));
                tfEmail.setText(resultSet.getString("email"));
                lblMessage.setText("Record found.");
            } else {
                lblMessage.setText("Record not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            lblMessage.setText("Error: " + ex.getMessage());
        }
    }

    // Insert a record
    private void insertRecord() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "INSERT INTO Staff (id, lastName, firstName, mi, address, city, state, telephone, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, tfID.getText());
            statement.setString(2, tfLastName.getText());
            statement.setString(3, tfFirstName.getText());
            statement.setString(4, tfMI.getText());
            statement.setString(5, tfAddress.getText());
            statement.setString(6, tfCity.getText());
            statement.setString(7, tfState.getText());
            statement.setString(8, tfTelephone.getText());
            statement.setString(9, tfEmail.getText());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                lblMessage.setText("Record inserted successfully.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            lblMessage.setText("Error: " + ex.getMessage());
        }
    }

    // Update a record
    private void updateRecord() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "UPDATE Staff SET lastName = ?, firstName = ?, mi = ?, address = ?, city = ?, state = ?, telephone = ?, email = ? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, tfLastName.getText());
            statement.setString(2, tfFirstName.getText());
            statement.setString(3, tfMI.getText());
            statement.setString(4, tfAddress.getText());
            statement.setString(5, tfCity.getText());
            statement.setString(6, tfState.getText());
            statement.setString(7, tfTelephone.getText());
            statement.setString(8, tfEmail.getText());
            statement.setString(9, tfID.getText());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                lblMessage.setText("Record updated successfully.");
            } else {
                lblMessage.setText("ID not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            lblMessage.setText("Error: " + ex.getMessage());
        }
    }

    // Clear all text fields
    private void clearFields() {
        tfID.clear();
        tfLastName.clear();
        tfFirstName.clear();
        tfMI.clear();
        tfAddress.clear();
        tfCity.clear();
        tfState.clear();
        tfTelephone.clear();
        tfEmail.clear();
        lblMessage.setText("");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
