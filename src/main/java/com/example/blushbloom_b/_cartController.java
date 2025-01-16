package com.example.blushbloom_b;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class _cartController {

    @FXML
    private Button backButton;

    @FXML
    private VBox cartVBox;

    private static final List<Product> cartList = new ArrayList<>();

    public static void addToCart(Product product) {
        cartList.add(product);
    }

    public static List<Product> getCart() {
        return cartList;
    }

    @FXML
    public void initialize() {
        try {
            System.out.println("Initializing cart section...");
            cartVBox.setSpacing(10);
            cartVBox.getChildren().clear();

            for (Product product : _cartController.getCart()) {
                HBox productRow = createProductRow(product);
                cartVBox.getChildren().add(productRow);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error occurred during initialize()");
        }
    }


    private HBox createProductRow(Product product) {
        HBox row = new HBox(10);
        row.setStyle("-fx-alignment: top-left; -fx-padding: 5; -fx-spacing: 10;");

        Text productNameText = new Text(product.getName());
        productNameText.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 14px; -fx-font-weight: bold;");


        Text productPriceText = new Text("Price: 19.99$");
        productPriceText.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 14px;");


        Text productStockText = new Text("Stock: 1");
        productStockText.setStyle("-fx-font-family: 'Georgia'; -fx-font-size: 14px;");

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

        row.getChildren().addAll(productNameText, productPriceText, productStockText, deleteButton);
        return row;
    }

    private void handleDelete(Product product, HBox row) {
        cartList.remove(product);
        cartVBox.getChildren().remove(row);
        System.out.println(product.getName() + " removed from cart.");
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
