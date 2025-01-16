package com.example.blushbloom_b;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class _adminDashboardController {
    @FXML
    private Button backButton;

    @FXML
    private Button editClients;

    @FXML
    private Button editProducts;

    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminDashBoard.fxml"));


    @FXML
    public void openBack(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        openNewScreen("adminLogin.fxml");
    }

    @FXML
    void openClients(ActionEvent event) {
        try {
            openNewScreen("ClientManagment.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void openProducts(ActionEvent event) {
        try {
            openNewScreen("ProductManagement.fxml");
        } catch (IOException e) {
            e.printStackTrace();
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
