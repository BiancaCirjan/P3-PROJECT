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

public class _faceMakeUpController {

    @FXML
    private Button backButton;

    @FXML
    private ImageView baseView;

    @FXML
    private ImageView cocealerView;

    @FXML
    private Button concealeraddButton;

    @FXML
    private Button concealeraddCart;

    @FXML
    private ImageView contourView;

    @FXML
    private Button contouraddButton;

    @FXML
    private Button contouraddCart;

    @FXML
    private Text favouriteText;

    @FXML
    private ImageView fonddetenView;

    @FXML
    private Button foundationaddButton;

    @FXML
    private Button foundationaddCart;

    @FXML
    private Button glowaddButton;

    @FXML
    private Button glowaddCart;

    @FXML
    private GridPane gridPane;

    @FXML
    private ImageView highlighterView;

    @FXML
    private Button highlighteraddButton;

    @FXML
    private Button highlighteraddCart;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button seefavButton;

    @FXML
    private Button serumaddButton;

    @FXML
    private Button serumaddCart;

    @FXML
    private ImageView skintintView;

    @FXML
    void handleAddToCart(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String productName = "";

        if (clickedButton.equals(foundationaddCart)) {
            productName = "Foundation";
        } else if (clickedButton.equals(highlighteraddCart)) {
            productName = "Highlighter";
        } else if (clickedButton.equals(serumaddCart)) {
            productName = "Serum";
        } else if (clickedButton.equals(glowaddCart)) {
            productName = "Glow";
        } else if (clickedButton.equals(contouraddCart)) {
            productName = "Contour";
        } else if (clickedButton.equals(concealeraddCart)) {
            productName = "Concealer";
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

        if (clickedButton.equals(foundationaddButton)) {
            productName = "Foundation";
        } else if (clickedButton.equals(highlighteraddButton)) {
            productName = "Highlighter";
        } else if (clickedButton.equals(serumaddButton)) {
            productName = "Serum";
        } else if (clickedButton.equals(glowaddButton)) {
            productName = "Glow";
        } else if (clickedButton.equals(contouraddButton)) {
            productName = "Contour";
        } else if (clickedButton.equals(concealeraddButton)) {
            productName = "Concealer";
        }

        if (!productName.isEmpty()) {

            Product product = DatabaseConnection.getProductByName(productName);

            if (product != null) {
                _favouritesController.addToFavourites(product);
                System.out.println(product.getName() + " added to favourites!");
            } else {
                System.err.println("Product not found in the database: " + productName);
            }
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
