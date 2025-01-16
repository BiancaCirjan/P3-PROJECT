package com.example.blushbloom_b;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class _makeupController {


    @FXML
    private Button BrushesButton;

    @FXML
    private Button backButton;

    @FXML
    private Button browsButton;

    @FXML
    private Button eyesButton;

    @FXML
    private Button faceButton;

    @FXML
    private Button lipsButton;

    @FXML
    public void openBack(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        openNewScreen("ClientHomepage.fxml");

    }


    @FXML
    void openBrows(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        openNewScreen("BrowsMakeup.fxml");
    }

    @FXML
    void openBrushes(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        openNewScreen("BrushesMakeup.fxml");

    }


    @FXML
    void openLips(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        openNewScreen("LipsMakeup.fxml");

    }

    public void openNewScreen(String fxmlFileName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void openFace(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        openNewScreen("FaceMakeup.fxml");
    }

    @FXML
    public void openEyes(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        openNewScreen("EyesMakeup.fxml");
    }

}
