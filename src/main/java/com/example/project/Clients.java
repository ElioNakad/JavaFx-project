package com.example.project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Optional;
public class Clients extends Person {
    private TableView<CartItem> tableView;
    private ArrayList<CartItem> cartItems;
    private DataBase db;

    public Clients(String fname, String lname, String email, String pass) {
        super(fname, lname, email, pass);
        this.tableView = new TableView<>();
        this.cartItems = new ArrayList<>();
        this.db = new DataBase();
    }

    public TableView<CartItem> getTV() {
        return this.tableView;
    }

    public ArrayList<CartItem> getCartItems() {
        return this.cartItems;
    }

    public void ShoppingCartStage() {
        Stage stage = new Stage();
        BorderPane mainBp = new BorderPane();

        VBox vb1 = new VBox();
        mainBp.setTop(vb1);
        vb1.setAlignment(Pos.CENTER);
        vb1.setStyle("-fx-background-color: #ef355b;");
        vb1.setPrefHeight(100);

        HBox hb1 = new HBox();
        vb1.getChildren().add(hb1);

        Button btAll = new Button("All");
        btAll.setPrefSize(200, 50);
        btAll.setAlignment(Pos.CENTER);
        btAll.setStyle("-fx-background-color: #ef355b; -fx-font-size: 18px; -fx-text-fill: white;");
        btAll.setOnAction(e -> {
            try {
                Main main = new Main(this);
                stage.close();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

        Button btPhones = new Button("Phones");
        btPhones.setPrefSize(200, 50);
        btPhones.setAlignment(Pos.CENTER);
        btPhones.setStyle("-fx-background-color: #ef355b; -fx-font-size: 18px; -fx-text-fill: white;");
        btPhones.setOnAction(e -> {
            PhonesStage p = new PhonesStage(this);
            stage.close();
        });

        Button btLaptops = new Button("Laptops");
        btLaptops.setPrefSize(200, 50);
        btLaptops.setAlignment(Pos.CENTER);
        btLaptops.setStyle("-fx-background-color: #ef355b; -fx-font-size: 18px; -fx-text-fill: white;");
        btLaptops.setOnAction(e -> {
            LaptopsStage p = new LaptopsStage(this);
            stage.close();
        });

        Button btAccessories = new Button("Accessories");
        btAccessories.setPrefSize(200, 50);
        btAccessories.setAlignment(Pos.CENTER);
        btAccessories.setStyle("-fx-background-color: #ef355b; -fx-font-size: 18px; -fx-text-fill: white;");
        btAccessories.setOnAction(e -> {
            AccessoriesStage a = new AccessoriesStage(this);
            stage.close();
        });

        Button btPay = new Button("PAY");
        btPay.setPrefSize(200, 50);
        btPay.setAlignment(Pos.CENTER);
        btPay.setStyle("-fx-background-color: #ef355b; -fx-font-size: 18px; -fx-text-fill: white;");

        Button btLogOut = new Button();
        btLogOut.setPrefSize(200, 50);
        btLogOut.setAlignment(Pos.CENTER);
        btLogOut.setStyle("-fx-background-color: #ef355b; ");
        ImageView logOutIcon = new ImageView(new Image("C:\\Users\\Administrator\\Downloads\\logout.png"));
        logOutIcon.setFitHeight(30);
        logOutIcon.setFitWidth(30);
        btLogOut.setGraphic(logOutIcon);
        btLogOut.setPadding(new Insets(0, 0, 125, 200));
        btLogOut.setOnAction(e -> {
            LogIn logIn = new LogIn();
            stage.close();
        });

        hb1.getChildren().addAll(btAll, btPhones, btLaptops, btAccessories, btPay, btLogOut);
        hb1.setAlignment(Pos.CENTER);
        hb1.setSpacing(100);

        TableColumn<CartItem, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<CartItem, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<CartItem, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableView.getColumns().clear();
        tableView.getColumns().addAll(nameColumn, quantityColumn, priceColumn);

        tableView.getItems().clear();
        tableView.getItems().addAll(cartItems);

        double sum = cartItems.stream()
                .mapToDouble(CartItem::getPrice)
                .sum();

        CartItem totalItem = new CartItem("Total", 0, sum);
        tableView.getItems().add(totalItem);

        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                CartItem selectedItem = tableView.getSelectionModel().getSelectedItem();
                if (selectedItem != null && !selectedItem.getName().equals("Total")) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Delete Item");
                    alert.setHeaderText("Delete Item from Cart");
                    alert.setContentText("Are you sure you want to delete this item?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        tableView.getItems().remove(selectedItem);
                        cartItems.remove(selectedItem);

                        double newSum = cartItems.stream()
                                .mapToDouble(CartItem::getPrice)
                                .sum();

                        tableView.getItems().removeIf(item -> item.getName().equals("Total"));
                        CartItem newTotalItem = new CartItem("Total", 0, newSum);
                        tableView.getItems().add(newTotalItem);
                    }
                }
            }
        });

        btPay.setOnAction(e -> {
            ArrayList<CartItem> itemsToProcess = new ArrayList<>(tableView.getItems());
            for (CartItem item : itemsToProcess) {
                if (item.getName().equals("Total")) {
                    continue;
                }
                System.out.println("Name: " + item.getName() + ", Quantity: " + item.getQuantity());
                db.updateStockItems(item.getQuantity(), item.getName());
            }

            tableView.getItems().clear();
            cartItems.clear();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Payment Successful");
            alert.setHeaderText(null);
            alert.setContentText("Your payment was successful, and the cart has been cleared.");
            alert.showAndWait();
        });

        mainBp.setCenter(tableView);
        Scene scene = new Scene(mainBp, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Shopping Cart");
        stage.show();
    }
}


