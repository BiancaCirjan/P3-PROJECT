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

public class _eyesMakeUpController {

    @FXML
    private Button backButton;

    @FXML
    private ImageView eyelinerView;

    @FXML
    private Button eyelineraddButton;

    @FXML
    private Button eyelineraddCart;

    @FXML
    private ImageView eyeshadowView;

    @FXML
    private Button falselashesaddButton;

    @FXML
    private Button falselashesaddCart;

    @FXML
    private Text favouriteText;

    @FXML
    private GridPane gridPane;

    @FXML
    private ImageView lashesView;

    @FXML
    private Button liquideyeshadowButton;

    @FXML
    private Button liquideyeshadowaddCart;

    @FXML
    private ImageView mascaraView;

    @FXML
    private Button mascaraaddButton;

    @FXML
    private Button mascaraaddCart;

    @FXML
    private Button mascarabaseaddButton;

    @FXML
    private Button mascarabaseaddCart;

    @FXML
    private ImageView pencilView;

    @FXML
    private Button penciladdButton;

    @FXML
    private Button penciladdCart;

    @FXML
    private ImageView primerView;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button seefavButton;

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

        if (clickedButton.equals(eyelineraddButton)) {
            productName = "Eyeliner";
        } else if (clickedButton.equals(falselashesaddButton)) {
            productName = "False Lashes";
        } else if (clickedButton.equals(liquideyeshadowButton)) {
            productName = "Liquid Eyeshadow";
        } else if (clickedButton.equals(mascaraaddButton)) {
            productName = "Mascara";
        } else if (clickedButton.equals(mascarabaseaddButton)) {
            productName = "Mascara Base";
        } else if (clickedButton.equals(penciladdButton)) {
            productName = "Eye Pencil";
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

        if (clickedButton.equals(eyelineraddCart)) {
            productName = "Eyeliner";
        } else if (clickedButton.equals(falselashesaddCart)) {
            productName = "False Lashes";
        } else if (clickedButton.equals(liquideyeshadowaddCart)) {
            productName = "Liquid Eyeshadow";
        } else if (clickedButton.equals(mascaraaddCart)) {
            productName = "Mascara";
        } else if (clickedButton.equals(mascarabaseaddCart)) {
            productName = "Mascara Base";
        } else if (clickedButton.equals(penciladdCart)) {
            productName = "Eye Pencil";
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

    public void navigateToPage(ActionEvent event, String fxmlFileName) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
