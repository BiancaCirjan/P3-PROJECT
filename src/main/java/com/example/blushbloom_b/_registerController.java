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
import java.sql.SQLException;

public class _registerController {

    @FXML
    private TextField adressFIeld;

    @FXML
    private TextField emailField;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private Text errorText;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField phoneField;

    @FXML
    private Button registerButton;

    @FXML
    private void initialize() {
        errorText.setVisible(false);
    }

    @FXML
    void register(ActionEvent event) throws SQLException {

        String firstName = this.firstName.getText();
        String lastName = this.lastName.getText();
        String adress = adressFIeld.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String phone = phoneField.getText();

        Boolean emailOk = false;
        if (!email.contains("@")) {
            errorText.setVisible(true);
            errorText.setText("Please enter a valid email address ‼️");
        } else emailOk = true;

        boolean passwordOk = false;
        if (password.length() < 4) {
            errorText.setVisible(true);
            errorText.setText("Password must be at least 4 characters ‼️");
        } else passwordOk = true;

        PasswordHasher passwordHasher = new PasswordHasher();
        String hashedPassword = passwordHasher.hashPassword(password);

        if (emailOk && passwordOk) {
            DatabaseConnection databaseConnection = new DatabaseConnection();

            try (Connection connection = databaseConnection.getConnection()) {
                String SQL = "INSERT INTO users (name, surname, email, password, address, phone) VALUES (?, ?, ?, ?, ?, ?)";

                try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
                    preparedStatement.setString(1, firstName);
                    preparedStatement.setString(2, lastName);
                    preparedStatement.setString(3, email);
                    preparedStatement.setString(4, hashedPassword);
                    preparedStatement.setString(5, adress);
                    preparedStatement.setString(6, phone);

                    int rowsInserted = preparedStatement.executeUpdate();
                    if (rowsInserted > 0) {
                        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        currentStage.close();

                        openNewScreen("login.fxml");
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (SQLException e) {
                System.err.println("Error occurred during user registration: " + e.getMessage());
                e.printStackTrace();
            }


        }
    }

    public void openNewScreen(String fxmlFileName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
