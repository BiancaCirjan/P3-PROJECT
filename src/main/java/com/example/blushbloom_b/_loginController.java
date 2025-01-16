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
import java.util.ArrayList;
import java.util.List;

public class _loginController {

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    private Text errorText;
    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;


    @FXML
    private Button adminButton;

    @FXML
    void openAdmin(ActionEvent event)throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        openNewScreen("loginAdmin.fxml");
    }


    @FXML
    public void initialize() {
  //   pulated all @FXML annotated members.
        errorText.setVisible(false);
    }

    @FXML
    void login(ActionEvent event) {
        PasswordHasher passwordHasher = new PasswordHasher();
        String email = emailField.getText();
        String password = passwordField.getText();
        String hashedPassword = passwordHasher.hashPassword(password);

        List<User> dbUsers = new ArrayList<>();

        DatabaseConnection databaseConnection = new DatabaseConnection();

        try (Connection connection = databaseConnection.getConnection()) {
            String query = "SELECT id, email, password, role FROM users";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String dbEmail = resultSet.getString("email");
                String dbPassword = resultSet.getString("password");
                String dbRole = resultSet.getString("role");
                Integer dbId = resultSet.getInt("id");
                if(email.equals(dbEmail) && hashedPassword.equals(dbPassword)) {
                    LoggedUser.email = email;
                    LoggedUser.role = dbRole;
                    LoggedUser.id = dbId;
                    errorText.setVisible(false);
                    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    currentStage.close();

                    if(dbRole.equals("Admin")) {
                        openNewScreen("AdminDashBoard.fxml");
                    }
                    else if(dbRole.equals("Client")) {
                        openNewScreen("ClientHomepage.fxml");
                    }
                }
                else {
                   errorText.setVisible(true);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void register(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        openNewScreen("register.fxml");
    }

    public void openNewScreen(String fxmlFileName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

}
