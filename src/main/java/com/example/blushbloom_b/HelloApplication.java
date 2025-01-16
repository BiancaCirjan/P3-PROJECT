package com.example.blushbloom_b;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 602, 401);
        stage.setTitle("Hello Blush&Bloomer!");
        stage.setScene(scene);
        stage.show();


        DatabaseOperations.syncStockData();
    }

    public static void main(String[] args) {
        launch();
    }
}
