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

public class _brushMakeUpController {

    @FXML
    private Button backButton;

    @FXML
    private ImageView baseView;

    @FXML
    private Button beautyblenderaddButton;

    @FXML
    private Button beautyblenderaddCart;

    @FXML
    private Button brush1addButton;

    @FXML
    private Button brush1addCart;

    @FXML
    private Button brush2addButton;

    @FXML
    private Button brush2addCart;

    @FXML
    private Button brush3addButton;

    @FXML
    private Button brush3addCart;

    @FXML
    private Button brush4addButton;

    @FXML
    private Button brush4addCart;

    @FXML
    private Button cleanseraddButton;

    @FXML
    private Button cleanseraddCart;

    @FXML
    private ImageView cocealerView;

    @FXML
    private ImageView contourView;

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
    private ImageView skintintView;

    @FXML
    void openSeefav(ActionEvent event) throws IOException {
        navigateToPage(event, "favourites.fxml");
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

        if (clickedButton.equals(beautyblenderaddButton)) {
            productName = "Beauty Blender";
        } else if (clickedButton.equals(brush1addButton)) {
            productName = "Brush 1";
        } else if (clickedButton.equals(brush2addButton)) {
            productName = "Brush 2";
        } else if (clickedButton.equals(brush3addButton)) {
            productName = "Brush 3";
        } else if (clickedButton.equals(brush4addButton)) {
            productName = "Brush 4";
        } else if (clickedButton.equals(cleanseraddButton)) {
            productName = "Cleanser";
        }

        if (!productName.isEmpty()) {
            Product product = new Product(productName);
            _favouritesController.addToFavourites(product);
            System.out.println(productName + " added to favourites!");
        }

    }

    @FXML
    void handleAddToCart(ActionEvent event) throws IOException {
        Button clickedButton = (Button) event.getSource();
        String productName = "";

        if (clickedButton.equals(beautyblenderaddCart)) {
            productName = "Beauty Blender";
        } else if (clickedButton.equals(brush1addCart)) {
            productName = "Brush 1";
        } else if (clickedButton.equals(brush2addCart)) {
            productName = "Brush 2";
        } else if (clickedButton.equals(brush3addCart)) {
            productName = "Brush 3";
        } else if (clickedButton.equals(brush4addCart)) {
            productName = "Brush 4";
        } else if (clickedButton.equals(cleanseraddCart)) {
            productName = "Cleanser";
        }

        if (!productName.isEmpty()) {
            Product product = new Product(productName);
            _cartController.addToCart(product);

            System.out.println(productName + " added to cart!");
            try {
                navigateToPage(event, "Cart.fxml");
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error navigating to the cart page.");
            }
        }
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
