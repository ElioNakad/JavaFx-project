package com.example.project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import static com.example.project.ShoppingCartStage.*;
import static com.example.project.Main.*;


public class DescriptionPane extends StackPane {

    public DescriptionPane(Phones item, Clients c) {
        Stage stage = new Stage();
        this.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                try {
                    Main main = new Main(c);
                    stage.close();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        ImageView backgroundImage = new ImageView(item.getImgage().getImage());
        backgroundImage.setFitWidth(800);
        backgroundImage.setFitHeight(600);
        backgroundImage.setPreserveRatio(false);
        backgroundImage.setOpacity(0.5);
        backgroundImage.setEffect(new DropShadow(100000, Color.web("#000000")));

        Label lblName = new Label(item.getName());
        lblName.setStyle("-fx-font-size: 28px; -fx-text-fill: white; -fx-font-weight: bold;");

        TextFlow descriptionFlow = new TextFlow();
        descriptionFlow.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-padding: 15px; "
                + "-fx-border-radius: 20px; -fx-background-radius: 20px;");

        Text descriptionHeader = new Text("Product Details\n\n");
        descriptionHeader.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-fill: #333333;");

        Text descriptionBody = new Text(item.getDesc());
        descriptionBody.setStyle("-fx-font-size: 16px; -fx-fill: #666666;");

        descriptionFlow.getChildren().addAll(descriptionHeader, descriptionBody);
        descriptionFlow.setTextAlignment(TextAlignment.JUSTIFY);

        VBox descriptionContainer = new VBox(descriptionFlow);
        descriptionContainer.setAlignment(Pos.CENTER);
        descriptionContainer.setPadding(new Insets(20));
        descriptionContainer.setMaxWidth(600);

        Button btCTA = new Button("ADD TO CART");
        btCTA.setOnAction(e -> showQuantityDialog(item, c, stage));
        btCTA.setStyle("-fx-font-size: 18px; -fx-text-fill: white; -fx-font-weight: bold; "
                + "-fx-padding: 10px 20px; -fx-background-color: #ef355b; "
                + "-fx-border-radius: 20px; -fx-cursor: hand;");

        VBox ctaContainer = new VBox(btCTA);
        ctaContainer.setAlignment(Pos.BOTTOM_CENTER);
        ctaContainer.setPadding(new Insets(20));

        VBox contentContainer = new VBox(lblName, descriptionContainer, ctaContainer);
        contentContainer.setAlignment(Pos.CENTER);
        contentContainer.setSpacing(30);
        contentContainer.setPadding(new Insets(30));

        this.getChildren().addAll(backgroundImage, contentContainer);

        Scene scene = new Scene(this, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Product Details");
        stage.show();
    }

    private void showQuantityDialog(Phones item, Clients c, Stage parentStage) {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.initOwner(parentStage);
        dialogStage.setTitle("Add Quantity");

        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setHgap(10);
        gp.setVgap(5);

        TextField txtf = new TextField();
        gp.add(new Text("Add quantity:"), 0, 0);
        gp.add(txtf, 1, 0);

        Button btOk = new Button("Ok");
        Button btCancel = new Button("Cancel");

        HBox buttons = new HBox(10, btOk, btCancel);
        buttons.setAlignment(Pos.CENTER);
        gp.add(buttons, 0, 1, 2, 1);

        btOk.setOnAction(ov -> handleQuantityInput(txtf, item, c, dialogStage));
        btCancel.setOnAction(ov -> dialogStage.close());

        Scene dialogScene = new Scene(gp, 300, 150);
        dialogStage.setScene(dialogScene);
        dialogStage.setOnCloseRequest(event -> dialogStage.close());
        dialogStage.showAndWait();
    }

    private void handleQuantityInput(TextField txtf, Phones item, Clients c, Stage dialogStage) {
        try {
            int quantity = Integer.parseInt(txtf.getText());

            if (quantity > item.getQuantityInStock()) {
                txtf.setText("Unavailable quantity");
                return;
            }

            if (quantity <= 0) {
                txtf.setText("Quantity must be positive.");
                return;
            }

            boolean itemExists = false;
            for (CartItem existingItem : c.getCartItems()) {
                if (existingItem.getName().equals(item.getName())) {
                    int newQuantity = existingItem.getQuantity() + quantity;
                    double newPrice = item.getPrice() * newQuantity;

                    existingItem.setQuantity(newQuantity);
                    existingItem.setPrice(newPrice);

                    c.getTV().refresh();
                    itemExists = true;
                    break;
                }
            }

            if (!itemExists) {
                CartItem cartItem = new CartItem(
                        item.getName(),
                        quantity,
                        item.getPrice() * quantity
                );
                c.getCartItems().add(cartItem);
                c.getTV().getItems().add(cartItem);
            }

            dialogStage.close();

        } catch (NumberFormatException ex) {
            txtf.setText("Quantity must be a number.");
        }
    }

    public DescriptionPane(Laptops item,Clients c) {
        Stage stage = new Stage();
        this.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                try {
                    Main main = new Main(c);
                    stage.close();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        ImageView backgroundImage = new ImageView(item.getImgage().getImage());
        backgroundImage.setFitWidth(800);
        backgroundImage.setFitHeight(600);
        backgroundImage.setPreserveRatio(false);
        backgroundImage.setOpacity(0.5);
        backgroundImage.setEffect(new DropShadow(100000, Color.web("#000000")));

        Label lblName = new Label(item.getName());
        lblName.setStyle("-fx-font-size: 28px; -fx-text-fill: white; -fx-font-weight: bold;");

        TextFlow descriptionFlow = new TextFlow();
        descriptionFlow.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-padding: 15px; "
                + "-fx-border-radius: 20px; -fx-background-radius: 20px;");

        Text descriptionHeader = new Text("Product Details\n\n");
        descriptionHeader.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-fill: #333333;");

        Text descriptionBody = new Text(item.getDesc());
        descriptionBody.setStyle("-fx-font-size: 16px; -fx-fill: #666666;");

        descriptionFlow.getChildren().addAll(descriptionHeader, descriptionBody);
        descriptionFlow.setTextAlignment(TextAlignment.JUSTIFY);

        VBox descriptionContainer = new VBox(descriptionFlow);
        descriptionContainer.setAlignment(Pos.CENTER);
        descriptionContainer.setPadding(new Insets(20));
        descriptionContainer.setMaxWidth(600);

        Button btCTA = new Button("ADD TO CART");
        btCTA.setOnAction(e -> showQuantityDialog(item, c, stage));
        btCTA.setStyle("-fx-font-size: 18px; -fx-text-fill: white; -fx-font-weight: bold; "
                + "-fx-padding: 10px 20px; -fx-background-color: #ef355b; "
                + "-fx-border-radius: 20px; -fx-cursor: hand;");

        VBox ctaContainer = new VBox(btCTA);
        ctaContainer.setAlignment(Pos.BOTTOM_CENTER);
        ctaContainer.setPadding(new Insets(20));

        VBox contentContainer = new VBox(lblName, descriptionContainer, ctaContainer);
        contentContainer.setAlignment(Pos.CENTER);
        contentContainer.setSpacing(30);
        contentContainer.setPadding(new Insets(30));

        this.getChildren().addAll(backgroundImage, contentContainer);

        Scene scene = new Scene(this, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Product Details");
        stage.show();
    }

    private void showQuantityDialog(Laptops item, Clients c, Stage parentStage) {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.initOwner(parentStage);
        dialogStage.setTitle("Add Quantity");

        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setHgap(10);
        gp.setVgap(5);

        TextField txtf = new TextField();
        gp.add(new Text("Add quantity:"), 0, 0);
        gp.add(txtf, 1, 0);

        Button btOk = new Button("Ok");
        Button btCancel = new Button("Cancel");

        HBox buttons = new HBox(10, btOk, btCancel);
        buttons.setAlignment(Pos.CENTER);
        gp.add(buttons, 0, 1, 2, 1);

        btOk.setOnAction(ov -> handleQuantityInput(txtf, item, c, dialogStage));
        btCancel.setOnAction(ov -> dialogStage.close());

        Scene dialogScene = new Scene(gp, 300, 150);
        dialogStage.setScene(dialogScene);
        dialogStage.setOnCloseRequest(event -> dialogStage.close());
        dialogStage.showAndWait();
    }

    private void handleQuantityInput(TextField txtf, Laptops item, Clients c, Stage dialogStage) {
        try {
            int quantity = Integer.parseInt(txtf.getText());

            if (quantity > item.getQuantityInStock()) {
                txtf.setText("Unavailable quantity");
                return;
            }

            if (quantity <= 0) {
                txtf.setText("Quantity must be positive.");
                return;
            }

            boolean itemExists = false;
            for (CartItem existingItem : c.getCartItems()) {
                if (existingItem.getName().equals(item.getName())) {
                    int newQuantity = existingItem.getQuantity() + quantity;
                    double newPrice = item.getPrice() * newQuantity;

                    existingItem.setQuantity(newQuantity);
                    existingItem.setPrice(newPrice);

                    c.getTV().refresh();
                    itemExists = true;
                    break;
                }
            }

            if (!itemExists) {
                CartItem cartItem = new CartItem(
                        item.getName(),
                        quantity,
                        item.getPrice() * quantity
                );
                c.getCartItems().add(cartItem);
                c.getTV().getItems().add(cartItem);
            }

            dialogStage.close();

        } catch (NumberFormatException ex) {
            txtf.setText("Quantity must be a number.");
        }
    }


    public DescriptionPane(Accessories item,Clients c) {
        Stage stage = new Stage();
        this.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                try {
                    Main main = new Main(c);
                    stage.close();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        ImageView backgroundImage = new ImageView(item.getImgage().getImage());
        backgroundImage.setFitWidth(800);
        backgroundImage.setFitHeight(600);
        backgroundImage.setPreserveRatio(false);
        backgroundImage.setOpacity(0.5);
        backgroundImage.setEffect(new DropShadow(100000, Color.web("#000000")));

        Label lblName = new Label(item.getName());
        lblName.setStyle("-fx-font-size: 28px; -fx-text-fill: white; -fx-font-weight: bold;");

        TextFlow descriptionFlow = new TextFlow();
        descriptionFlow.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-padding: 15px; "
                + "-fx-border-radius: 20px; -fx-background-radius: 20px;");

        Text descriptionHeader = new Text("Product Details\n\n");
        descriptionHeader.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-fill: #333333;");

        Text descriptionBody = new Text(item.getDesc());
        descriptionBody.setStyle("-fx-font-size: 16px; -fx-fill: #666666;");

        descriptionFlow.getChildren().addAll(descriptionHeader, descriptionBody);
        descriptionFlow.setTextAlignment(TextAlignment.JUSTIFY);

        VBox descriptionContainer = new VBox(descriptionFlow);
        descriptionContainer.setAlignment(Pos.CENTER);
        descriptionContainer.setPadding(new Insets(20));
        descriptionContainer.setMaxWidth(600);

        Button btCTA = new Button("ADD TO CART");
        btCTA.setOnAction(e -> showQuantityDialog(item, c, stage));
        btCTA.setStyle("-fx-font-size: 18px; -fx-text-fill: white; -fx-font-weight: bold; "
                + "-fx-padding: 10px 20px; -fx-background-color: #ef355b; "
                + "-fx-border-radius: 20px; -fx-cursor: hand;");

        VBox ctaContainer = new VBox(btCTA);
        ctaContainer.setAlignment(Pos.BOTTOM_CENTER);
        ctaContainer.setPadding(new Insets(20));

        VBox contentContainer = new VBox(lblName, descriptionContainer, ctaContainer);
        contentContainer.setAlignment(Pos.CENTER);
        contentContainer.setSpacing(30);
        contentContainer.setPadding(new Insets(30));

        this.getChildren().addAll(backgroundImage, contentContainer);

        Scene scene = new Scene(this, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Product Details");
        stage.show();
    }

    private void showQuantityDialog(Accessories item, Clients c, Stage parentStage) {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.initOwner(parentStage);
        dialogStage.setTitle("Add Quantity");

        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setHgap(10);
        gp.setVgap(5);

        TextField txtf = new TextField();
        gp.add(new Text("Add quantity:"), 0, 0);
        gp.add(txtf, 1, 0);

        Button btOk = new Button("Ok");
        Button btCancel = new Button("Cancel");

        HBox buttons = new HBox(10, btOk, btCancel);
        buttons.setAlignment(Pos.CENTER);
        gp.add(buttons, 0, 1, 2, 1);

        btOk.setOnAction(ov -> handleQuantityInput(txtf, item, c, dialogStage));
        btCancel.setOnAction(ov -> dialogStage.close());

        Scene dialogScene = new Scene(gp, 300, 150);
        dialogStage.setScene(dialogScene);
        dialogStage.setOnCloseRequest(event -> dialogStage.close());
        dialogStage.showAndWait();
    }

    private void handleQuantityInput(TextField txtf, Accessories item, Clients c, Stage dialogStage) {
        try {
            int quantity = Integer.parseInt(txtf.getText());

            if (quantity > item.getQuantityInStock()) {
                txtf.setText("Unavailable quantity");
                return;
            }

            if (quantity <= 0) {
                txtf.setText("Quantity must be positive.");
                return;
            }

            boolean itemExists = false;
            for (CartItem existingItem : c.getCartItems()) {
                if (existingItem.getName().equals(item.getName())) {
                    int newQuantity = existingItem.getQuantity() + quantity;
                    double newPrice = item.getPrice() * newQuantity;

                    existingItem.setQuantity(newQuantity);
                    existingItem.setPrice(newPrice);

                    c.getTV().refresh();
                    itemExists = true;
                    break;
                }
            }

            if (!itemExists) {
                CartItem cartItem = new CartItem(
                        item.getName(),
                        quantity,
                        item.getPrice() * quantity
                );
                c.getCartItems().add(cartItem);
                c.getTV().getItems().add(cartItem);
            }

            dialogStage.close();

        } catch (NumberFormatException ex) {
            txtf.setText("Quantity must be a number.");
        }
    }



}
