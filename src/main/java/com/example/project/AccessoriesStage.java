package com.example.project;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Shear;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static com.example.project.Main.*;

public class AccessoriesStage {
    private ComboBox<String> cbo=new ComboBox<>();
    private BorderPane mainBp=new BorderPane();
    private Clients c;

    public AccessoriesStage(Clients c){
        this.c=c;
        Stage stage=new Stage();
        mainBp.setStyle("-fx-background-color: #ef355b;");

        mainBp.setPrefWidth(20000);
        ScrollPane sp = new ScrollPane();
        sp.setContent(mainBp);
        sp.setFitToWidth(true);  // Adjust width to viewport
        sp.setFitToHeight(false);
        sp.setFitToWidth(true);
        sp.setPannable(true);

        //from here we start with the first part of the page
        VBox vb1=new VBox();
        mainBp.setTop(vb1);
        vb1.setAlignment(Pos.CENTER);
        vb1.setStyle("-fx-background-color: #ef355b;");
        vb1.setPrefHeight(100);

        HBox hb1=new HBox();
        vb1.getChildren().add(hb1);

        Button btAll=new Button("All");
        btAll.setPrefSize(200,50);
        btAll.setAlignment(Pos.CENTER);
        btAll.setStyle("-fx-background-color: #ef355b; -fx-font-size: 18px; -fx-text-fill: white;");
        btAll.setOnAction(e->{
            try {
                Main main=new Main(c);
                stage.close();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

        Button btPhones=new Button("Phones");
        btPhones.setPrefSize(200,50);
        btPhones.setAlignment(Pos.CENTER);
        btPhones.setStyle("-fx-background-color: #ef355b; -fx-font-size: 18px; -fx-text-fill: white;");
        btPhones.setOnAction(e->{
            PhonesStage pStage=new PhonesStage(c);
            stage.close();;
        });


        Button btLaptops=new Button("Laptops");
        btLaptops.setPrefSize(200,50);
        btLaptops.setAlignment(Pos.CENTER);
        btLaptops.setStyle("-fx-background-color: #ef355b; -fx-font-size: 18px; -fx-text-fill: white;");
        btLaptops.setOnAction(e->{
            LaptopsStage laptopsStage=new LaptopsStage(c);
            stage.close();;
        });

        Button btAccessories=new Button("Accessories");
        btAccessories.setPrefSize(200,50);
        btAccessories.setAlignment(Pos.CENTER);
        btAccessories.setStyle("-fx-background-color: #ef355b; -fx-font-size: 18px; -fx-text-fill: white;");

        Button btLogOut=new Button();
        btLogOut.setPrefSize(200,50);
        btLogOut.setAlignment(Pos.CENTER);
        btLogOut.setStyle("-fx-background-color: #ef355b; ");
        ImageView logOutIcon=new ImageView(new Image("C:\\Users\\Administrator\\Downloads\\logout.png"));
        logOutIcon.setFitHeight(30);
        logOutIcon.setFitWidth(30);
        btLogOut.setGraphic(logOutIcon);
        btLogOut.setPadding(new Insets(0,0,125,200));
        btLogOut.setOnAction(e->{
            LogIn logIn=new LogIn();
            stage.close();
        });
        Button btShoppingCart=new Button("Shopping Cart");
        btShoppingCart.setPrefSize(200,50);
        btShoppingCart.setAlignment(Pos.CENTER);
        btShoppingCart.setStyle("-fx-background-color: #ef355b; -fx-font-size: 18px; -fx-text-fill: white;");
        btShoppingCart.setOnAction(e->{
            // ShoppingCartStage shoppingCartStage=new ShoppingCartStage(c);
            c.ShoppingCartStage();
            stage.close();
        });

        hb1.getChildren().addAll(btAll,btPhones,btLaptops,btAccessories,btShoppingCart,btLogOut);
        hb1.setAlignment(Pos.CENTER);
        hb1.setSpacing(100);



        //the second part
        ArrayList<String> brandsList=new ArrayList<>();
        for(Accessories p:allaccessoriesArrayList){
            if(!brandsList.contains(p.getBrand())){
                brandsList.add(p.getBrand());
            }
        }
        cbo.getItems().addAll(brandsList);
        HBox paneForCbo=new HBox();
        Text txt=new Text("Choose a brand:");
        paneForCbo.getChildren().addAll(txt,cbo);
        paneForCbo.setSpacing(50);
        txt.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-fill: white;");
        paneForCbo.setStyle("-fx-padding: 10; -fx-background-color: #ef355b; -fx-spacing: 20;");

        cbo.setStyle("-fx-font-size: 14px; -fx-background-color: #ef355b; -fx-border-color: white; -fx-border-width: 2; -fx-border-radius: 5; -fx-text-fill: white;");

// Customize ListCell rendering for the ComboBox
        cbo.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item);
                }
                setStyle("-fx-text-fill: white; -fx-background-color: #ef355b; -fx-font-size: 14px;");
            }
        });

        cbo.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item);
                }
                setStyle("-fx-text-fill: white; -fx-background-color: #ef355b; -fx-font-size: 14px;");
            }
        });


        vb1.getChildren().add(paneForCbo);

        cbo.setValue("Lenovo");
        setSecondPart();
        cbo.setOnAction(e->setSecondPart());


        //now it's the last part which are the partners
        VBox vb2=new VBox();
        vb2.setStyle( "-fx-background-color: white;" +      // Background color
                "-fx-background-radius: 90;" +       // Rounded corners
                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.25), 10, 0, 0, 4);" + // Shadow
                "-fx-padding: 20;" +                 // Padding inside the VBox
                "-fx-border-radius: 20;");

        ImageView[] images1={new ImageView("C:\\Users\\Administrator\\Downloads\\Appleee.png")
                ,new ImageView("C:\\Users\\Administrator\\Downloads\\android-logo-0.png")
                ,new ImageView("C:\\Users\\Administrator\\Downloads\\hplogooooooooooooooo.jpeg"),
                new ImageView("C:\\Users\\Administrator\\Downloads\\lenovoo.png")};

        HBox hbForLabel = new HBox();
        Label lbl = new Label("OUR PARTNERS");
        lbl.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");

        Rectangle rectangle = new Rectangle();
        rectangle.setFill(new LinearGradient(
                0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#ef355b")),
                new Stop(1, Color.web("#ff6f91"))
        ));
        rectangle.setArcWidth(10); // Rounded corners
        rectangle.setArcHeight(10); // Rounded corners

        rectangle.widthProperty().bind(lbl.widthProperty().add(20)); // Add padding
        rectangle.heightProperty().bind(lbl.heightProperty().add(10)); // Add padding
        rectangle.getTransforms().add(new Shear(-0.3, 0)); // Adjust the X-shear value for the slant


        StackPane stackPane = new StackPane(rectangle, lbl);
        stackPane.setPadding(new Insets(5)); // Optional padding around the label

        hbForLabel.getChildren().add(stackPane);

        vb2.getChildren().add(hbForLabel);



        for(int i=1;i<3;i++){
            images1[i].setFitWidth(200);
            images1[i].setFitHeight(200);
        }
        images1[0].setFitHeight(150);
        images1[0].setFitWidth(150);
        images1[3].setFitHeight(150);
        images1[3].setFitWidth(150);

        HBox hb2=new HBox();
        hb2.setSpacing(200);
        hb2.setPadding(new Insets(0,0,0,100));
        hb2.getChildren().addAll(images1[0],images1[1],images1[2],images1[3]);
        vb2.getChildren().add(hb2);
        mainBp.setBottom(vb2);



        Scene scene = new Scene(sp, 800, 600);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setTitle("Login");
        stage.show();
    }


    private void setSecondPart() {
        String selectedBrand = cbo.getValue();

        // Create a modern, responsive grid layout
        GridPane phoneGrid = new GridPane();
        phoneGrid.setHgap(200);
        phoneGrid.setVgap(80);
        phoneGrid.setPadding(new Insets(40));
        phoneGrid.setAlignment(Pos.CENTER);

        // Set phoneGrid to fill the entire width
        phoneGrid.prefWidthProperty().bind(mainBp.widthProperty());

        // Enhanced styling with a card-like design
        phoneGrid.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 80;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.2), 15, 0, 0, 5);"
        );

        int column = 0;
        int row = 0;

        for (Accessories p : allaccessoriesArrayList) {
            if (p.getBrand().equalsIgnoreCase(selectedBrand)) {
                // Create a card-like container for each laptop
                VBox laptopCard = new VBox(10);
                laptopCard.setAlignment(Pos.CENTER);
                laptopCard.setStyle(
                        "-fx-background-color: white;" +
                                "-fx-background-radius: 15;" +
                                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.1), 10, 0, 0, 3);" +
                                "-fx-padding: 20;"
                );

                StackPane imageContainer = new StackPane();
                imageContainer.setStyle(
                        "-fx-background-color: #f0f0f0;" +
                                "-fx-background-radius: 10;"
                );

                // Configure the image
                p.getImgage().setFitWidth(250);
                p.getImgage().setFitHeight(500);
                p.getImgage().setPreserveRatio(true);
                p.getImgage().setSmooth(true);

                ScaleTransition hoverEffect = new ScaleTransition(Duration.millis(200), p.getImgage());
                hoverEffect.setFromX(1.0);
                hoverEffect.setFromY(1.0);
                hoverEffect.setToX(1.05);
                hoverEffect.setToY(1.05);

                laptopCard.setOnMouseEntered(e -> {
                    laptopCard.setStyle(
                            "-fx-background-color: white;" +
                                    "-fx-background-radius: 15;" +
                                    "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.2), 15, 0, 0, 5);" +
                                    "-fx-padding: 20;"
                    );
                    hoverEffect.setRate(1.0);
                    hoverEffect.play();
                });

                laptopCard.setOnMouseExited(e -> {
                    laptopCard.setStyle(
                            "-fx-background-color: white;" +
                                    "-fx-background-radius: 15;" +
                                    "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.1), 10, 0, 0, 3);" +
                                    "-fx-padding: 20;"
                    );
                    hoverEffect.setRate(-1.0);
                    hoverEffect.play();
                });

                imageContainer.getChildren().add(p.getImgage());

                Label laptopNameLabel = new Label(p.getName());
                laptopNameLabel.setStyle(
                        "-fx-font-size: 16px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-text-fill: #333;"
                );

                laptopCard.getChildren().addAll(imageContainer, laptopNameLabel);
                laptopCard.setOnMouseClicked(e -> new DescriptionPane(p,c));

                FadeTransition fadeIn = new FadeTransition(Duration.millis(500), laptopCard);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.play();

                phoneGrid.add(laptopCard, column, row);

                column++;
                if (column > 2) {
                    column = 0;
                    row++;
                }
            }
        }

        // Wrap the grid in a container
        VBox gridContainer = new VBox(phoneGrid);
        gridContainer.setStyle("-fx-background-color: #ef355b;"); // Set background color for the second part
        gridContainer.setPadding(new Insets(0)); // No gaps between sections

        // Wrap the container in a scrollable pane
        ScrollPane scrollPane = new ScrollPane(gridContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        // Remove borders or gaps in the ScrollPane
        scrollPane.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        mainBp.setCenter(scrollPane);
    }

}
