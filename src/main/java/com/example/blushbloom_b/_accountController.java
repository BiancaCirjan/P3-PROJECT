package com.example.blushbloom_b;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class _accountController {

    @FXML
    private TextField adressFIeld;

    @FXML
    private TextField emailField;

    @FXML
    private Text errorText;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField phoneField;

    @FXML
    private Button updateButton;

    @FXML
    public void initialize(){
        updateButton.setText("Update");
        errorText.setVisible(false);
         String userEmail = LoggedUser.email;

         DatabaseConnection databaseConnection = new DatabaseConnection();

        try (Connection connection = databaseConnection.getConnection()) {
            String query = "SELECT * FROM users";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String dbEmail = resultSet.getString("email");
                String dbFirstName = resultSet.getString("name");
                String dbLastName = resultSet.getString("surname");
                String dbPhone = resultSet.getString("phone");
                String dbAddress = resultSet.getString("address");

                emailField.setText(dbEmail);
                firstName.setText(dbFirstName);
                lastName.setText(dbLastName);
                phoneField.setText(dbPhone);
                adressFIeld.setText(dbAddress);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void update(ActionEvent event) {
        String firstName = this.firstName.getText();
        String lastName = this.lastName.getText();
        String address = adressFIeld.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String phone = phoneField.getText();

        boolean emailOk = email.contains("@");
        if (!emailOk) {
            errorText.setVisible(true);
            errorText.setText("Please enter a valid email address ‼️");
            return;
        }


        boolean passwordOk = password.length() >= 4;
        if (!passwordOk) {
            errorText.setVisible(true);
            errorText.setText("Password must be at least 4 characters ‼️");
            return;
        }


        PasswordHasher passwordHasher = new PasswordHasher();
        String hashedPassword = passwordHasher.hashPassword(password);


        DatabaseConnection databaseConnection = new DatabaseConnection();
        try (Connection connection = databaseConnection.getConnection()) {
            String SQL = "UPDATE users SET name = ?, surname = ?, email = ?, password = ?, address = ?, phone = ?, role = ? WHERE id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, hashedPassword);
                preparedStatement.setString(5, address);
                preparedStatement.setString(6, phone);
                preparedStatement.setString(7, "Customer");
                preparedStatement.setInt(8, LoggedUser.id);

                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    currentStage.close();
                    openNewScreen("account.fxml");
                } else {
                    errorText.setVisible(true);
                    errorText.setText("Update failed. Please try again.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error occurred during user update: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException("Error opening new screen: " + e.getMessage(), e);
        }
    }

    @FXML
    public void back(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        openNewScreen("ClientHomepage.fxml");

    }

    public void openNewScreen(String fxmlFileName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
