package com.example.blushbloom_b;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class _lipsMakeUpController {

    @FXML
    private Button backButton;

    @FXML
    private Text favouriteText;

    @FXML
    private ImageView glossView;

    @FXML
    private Button glossaddButton;

    @FXML
    private Button glossaddCart;

    @FXML
    private GridPane gridPane;

    @FXML
    private ImageView lipbalmView;

    @FXML
    private Button lipbalmaddButton;

    @FXML
    private Button lipbalmaddCart;

    @FXML
    private Button lippenciladdButton;

    @FXML
    private Button lippenciladdCart;

    @FXML
    private ImageView lippenciltView;

    @FXML
    private ImageView lipstainView;

    @FXML
    private Button lipstainaddCart;

    @FXML
    private Button lipstainraddButton;

    @FXML
    private ImageView lipstickView;

    @FXML
    private Button lipstickaddButton;

    @FXML
    private Button lipstickaddCart;

    @FXML
    private ImageView rougeView;

    @FXML
    private Button rougeaddButton;

    @FXML
    private Button rougeaddCart;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button seefavButton;

    @FXML
    void openSeefav(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        openNewScreen("favourites.fxml");
    }

    @FXML
    public void openBack(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        openNewScreen("MakeUpPage.fxml");
    }

    @FXML
    void openCart(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        openNewScreen("Cart.fxml");
    }


    public void openNewScreen(String fxmlFileName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void openFavourites(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String productName = "";

        if (clickedButton.equals(glossaddButton)) {
            productName = "Gloss";
        } else if (clickedButton.equals(lipbalmaddButton)) {
            productName = "Lip Balm";
        } else if (clickedButton.equals(lippenciladdButton)) {
            productName = "Lip Pencil";
        } else if (clickedButton.equals(lipstainraddButton)) {
            productName = "Lip Stain";
        } else if (clickedButton.equals(lipstickaddButton)) {
            productName = "Lipstick";
        } else if (clickedButton.equals(rougeaddButton)) {
            productName = "Rouge";
        }

        if (!productName.isEmpty()) {

            Product product = new Product(productName);
            _favouritesController.addToFavourites(product);
            System.out.println(productName + " added to favourites!");
        }

    }

    @FXML
    void handleAddToCart(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String productName = "";

        if (clickedButton.equals(glossaddCart)) {
            productName = "Gloss";
        } else if (clickedButton.equals(lipbalmaddCart)) {
            productName = "Lip Balm";
        } else if (clickedButton.equals(lippenciladdCart)) {
            productName = "Lip Pencil";
        } else if (clickedButton.equals(lipstainaddCart)) {
            productName = "Lip Stain";
        } else if (clickedButton.equals(lipstickaddCart)) {
            productName = "Lipstick";
        } else if (clickedButton.equals(rougeaddCart)) {
            productName = "Rouge";
        }

        if (!productName.isEmpty()) {
            Product product = new Product(productName);

            _cartController.addToCart(product);

            System.out.println(productName + " added to cart!");
            try {
                openCart(event);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error navigating to the cart page.");
            }
        }


    }
}
