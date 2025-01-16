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

public class _browsMakeUpController {

    @FXML
    private Button backButton;

    @FXML
    private ImageView baseView;

    @FXML
    private ImageView cocealerView;

    @FXML
    private ImageView contourView;

    @FXML
    private Button eyebrow2addButton;

    @FXML
    private Button eyebrow2addCart;

    @FXML
    private Button eyebrow3addButton;

    @FXML
    private Button eyebrow3addCart;

    @FXML
    private Button eyebrow4addCart;

    @FXML
    private Button eyebrow5addButton;

    @FXML
    private Button eyebrow5addCart;

    @FXML
    private Button eyebrow6addButton;

    @FXML
    private Button eyebrow6addCart;

    @FXML
    private Button eyebrowaddButton;

    @FXML
    private Button eyebrowaddCart;

    @FXML
    private Text favouriteText;

    @FXML
    private ImageView fonddetenView;

    @FXML
    private GridPane gridPane;

    @FXML
    private ImageView highlighterView;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button seefavButton;

    @FXML
    private Button serumaddButton;

    @FXML
    private ImageView skintintView;

    @FXML
    void handleAddToCart(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String productName = "";

        if (clickedButton.equals(eyebrowaddCart)) {
            productName = "Eyebrow Product 1";
        } else if (clickedButton.equals(eyebrow2addCart)) {
            productName = "Eyebrow Product 2";
        } else if (clickedButton.equals(eyebrow3addCart)) {
            productName = "Eyebrow Product 3";
        } else if (clickedButton.equals(eyebrow4addCart)) {
            productName = "Eyebrow Product 4";
        } else if (clickedButton.equals(eyebrow5addCart)) {
            productName = "Eyebrow Product 5";
        } else if (clickedButton.equals(eyebrow6addCart)) {
            productName = "Eyebrow Product 6";
        }

        if (!productName.isEmpty()) {
            Product product = new Product(productName);

            _cartController.addToCart(product);

            System.out.println(productName + " added to cart!");
            try {
                navigateToPage(event, "Cart.fxml");
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error occurred while navigating to the cart page.");
            }
        }
    }

    @FXML
    public void openBack(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        openNewScreen("MakeUpPage.fxml");
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

        if (clickedButton.equals(eyebrowaddButton)) {
            productName = "Eyebrow Product 1";
        } else if (clickedButton.equals(eyebrow2addButton)) {
            productName = "Eyebrow Product 2";
        } else if (clickedButton.equals(eyebrow3addButton)) {
            productName = "Eyebrow Product 3";
        } else if (clickedButton.equals(eyebrow5addButton)) {
            productName = "Eyebrow Product 5";
        } else if (clickedButton.equals(eyebrow6addButton)) {
            productName = "Eyebrow Product 6";
        }

        if (!productName.isEmpty()) {

            Product product = new Product(productName);
            _favouritesController.addToFavourites(product);
            System.out.println(productName + " added to favourites!");
        }
    }

    @FXML
    void openSeefav(ActionEvent event) throws IOException {
        navigateToPage(event, "favourites.fxml");
    }

    private void navigateToPage(ActionEvent event, String fxmlFileName) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
