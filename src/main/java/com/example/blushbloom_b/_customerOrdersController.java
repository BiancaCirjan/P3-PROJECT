package com.example.blushbloom_b;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class _customerOrdersController {

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<Order, Integer> orderId;

    @FXML
    private TableColumn<Order, String> date;

    @FXML
    private TableColumn<Order, Void> details;

    @FXML
    private TableView<Order> ordersTable;

    private ObservableList<Order> ordersList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        orderId.setCellValueFactory(new PropertyValueFactory<>("id"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));


        addDetailsButtonToTable();

        loadOrdersFromDatabase();
    }

    private void loadOrdersFromDatabase() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        String query = "SELECT id, userId, date FROM orders";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int userId = resultSet.getInt("userId");
                Date date = resultSet.getDate("date");

               if(userId == LoggedUser.id)
                ordersList.add(new Order(id, userId, date));
            }


            ordersTable.setItems(ordersList);

        } catch (SQLException e) {
            System.err.println("Error loading orders from database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void addDetailsButtonToTable() {
        Callback<TableColumn<Order, Void>, javafx.scene.control.TableCell<Order, Void>> cellFactory = new Callback<>() {
            @Override
            public javafx.scene.control.TableCell<Order, Void> call(final TableColumn<Order, Void> param) {
                return new javafx.scene.control.TableCell<>() {

                    private final Button detailsButton = new Button("Details");

                    {
                        detailsButton.setOnAction(event -> {

                            Order order = getTableView().getItems().get(getIndex());
                            int orderId = order.getId();

                            System.out.println("Details button clicked for Order ID: " + orderId);

                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(detailsButton);
                        }
                    }
                };
            }
        };

        details.setCellFactory(cellFactory);
    }

    @FXML
    public void back(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        openNewScreen("ClientHomepage.fxml");
    }

    public void openNewScreen(String fxmlFileName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
