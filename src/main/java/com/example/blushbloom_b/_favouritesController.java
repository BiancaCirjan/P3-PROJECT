package com.example.blushbloom_b;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class _favouritesController {

    @FXML
    private Button backButton;

    @FXML
    private VBox favouritesVBox;

    @FXML
    private Pane makeupTitle;

    private static final List<Product> favouritesList = new ArrayList<>();

    public static void addToFavourites(Product product) {
        favouritesList.add(product);
        System.out.println("Added to favouritesList: " + product);
    }

    public static List<Product> getFavourites() {
        return favouritesList;
    }

    private HBox createProductRow(Product product) {
        System.out.println("Creating row for product: " + product);

        HBox row = new HBox(10);
        row.setStyle("-fx-alignment: top-left; -fx-padding: 5; -fx-spacing: 10;");

        Label productNameLabel = new Label(product.getName());
        productNameLabel.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 14px; -fx-font-weight: bold;");

        Label priceLabel = new Label("Price: 19.99$");
        priceLabel.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 12px;");

        Label stockLabel = new Label("Stock: 1 ");
        stockLabel.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 12px;");

        Button deleteButton = new Button("Delete");
        deleteButton.setStyle(
                "-fx-font-family: 'Georgia';" +
                        "-fx-font-size: 12px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #ff89f1;" +
                        "-fx-border-color: #ff89f1;" +
                        "-fx-border-width: 1px;" +
                        "-fx-background-color: #fff1f9;" +
                        "-fx-border-radius: 5px;" +
                        "-fx-background-radius: 5px;" +
                        "-fx-padding: 3px 7px;"
        );
        deleteButton.setOnAction(e -> handleDelete(product, row));

        row.getChildren().addAll(productNameLabel, priceLabel, stockLabel, deleteButton);
        return row;
    }

    private void handleDelete(Product product, HBox row) {
        favouritesList.remove(product);
        favouritesVBox.getChildren().remove(row);
        System.out.println(product.getName() + " removed from favourites!");
    }

    @FXML
    public void initialize() {
        try {
            System.out.println("Initializing favourites section...");
            favouritesVBox.setSpacing(10);
            favouritesVBox.getChildren().clear();

            for (Product product : _favouritesController.getFavourites()) {
                HBox productRow = createProductRow(product);
                favouritesVBox.getChildren().add(productRow);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error occurred during initialize()");
        }
    }


    @FXML
    public void openBack(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        openNewScreen(event, "ClientHomepage.fxml");
    }

    public void openNewScreen(ActionEvent event, String fxmlFileName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
