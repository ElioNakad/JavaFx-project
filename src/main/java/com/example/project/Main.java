package com.example.project;

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
import javafx.scene.text.Font;
import javafx.scene.transform.Shear;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {



    public static ArrayList<Phones> allphonesArrayList = new ArrayList<>();
   /*static {
        allphonesArrayList.add(new Phones(1100, "iphone 16 pro max", 100, "Apple",
                new ImageView("C:\\Users\\Administrator\\Desktop\\Prog3Images\\Iphonephoto.jpg"),
                256, 16));
        allphonesArrayList.add(new Phones(1100, "iphone 16", 100, "Apple",
                new ImageView("C:\\Users\\Administrator\\Desktop\\Prog3Images\\iphone16.jpg"),
                256, 16));
        allphonesArrayList.add(new Phones(1100, "Samsung A55", 100, "Android",
                new ImageView("C:\\Users\\Administrator\\Desktop\\Prog3Images\\A55black.jpg"),
                256, 16));
        allphonesArrayList.add(new Phones(1100, "Samsung S24", 100, "Android",
                new ImageView("C:\\Users\\Administrator\\Desktop\\Prog3Images\\s2444.jpg"),
                256, 16));
        allphonesArrayList.add(new Phones(1100, "iphone 15 pro max", 100, "Apple",
                new ImageView("C:\\Users\\Administrator\\Desktop\\Prog3Images\\iphone-15-pro-max-titanium-black-v0-xxelckc24d1c1.jpg"),
                256, 16));
        allphonesArrayList.add(new Phones(1100, "iphone 13 pro max", 100, "Apple",
                new ImageView("C:\\Users\\Administrator\\Desktop\\Prog3Images\\iphone 13 pro max.jpg"),
                256, 16));
        allphonesArrayList.add(new Phones(1100, "Samsung S23", 100, "Android",
                new ImageView("C:\\Users\\Administrator\\Desktop\\Prog3Images\\s23.jfif"),
                256, 16));
        allphonesArrayList.add(new Phones(1100, "Samsung S24", 100, "Android",
                new ImageView("C:\\Users\\Administrator\\Desktop\\Prog3Images\\s2444.jpg"),
                256, 16));
    }*/

    public static ArrayList<Laptops> alllaptopsArrayList = new ArrayList<>();

    static {
        alllaptopsArrayList.add(new Laptops(500, "HP notebook", 100, "HP",
                new ImageView("C:\\Users\\Administrator\\Desktop\\Prog3Images\\hp.jpg"),
                128, 16, "awee"));

        alllaptopsArrayList.add(new Laptops(1100, "Lenovo Ideapad", 100, "Lenovo",
                new ImageView("C:\\Users\\Administrator\\Desktop\\Prog3Images\\Lenovoblack.jfif"),
                256, 32, "awee"));
        alllaptopsArrayList.add(new Laptops(1100, "Lenovo thinkPad", 100, "Lenovo",
                new ImageView("C:\\Users\\Administrator\\Desktop\\Prog3Images\\thinkpad.jpg"),
                256, 32, "awee"));
    }


    public static ArrayList<Accessories> allaccessoriesArrayList = new ArrayList<>();

    static {
        allaccessoriesArrayList.add(new Accessories(1100, "HP w10", 100, "HP",
                new ImageView("C:\\Users\\Administrator\\Desktop\\Prog3Images\\mouseeeeee.jfif")));

        allaccessoriesArrayList.add(new Accessories(1100, "Lenovo wireless keyboard", 100, "Lenovo",
                new ImageView("C:\\Users\\Administrator\\Desktop\\Prog3Images\\keyboard.jpg")));
    }


    private Clients c;
    public Main(Clients c) throws FileNotFoundException {
        this.c=c;
        ArrayList<Phones> phonesArrayList = new ArrayList<>();
        ArrayList<Laptops> laptopsArrayList = new ArrayList<>();
        ArrayList<Accessories> accessoriesArrayList = new ArrayList<>();

        Phones p1 = null, p2 = null;
        Laptops l1 = null, l2 = null;
        Accessories ac1 = null, ac2 = null;

// File path
        File file = new File("C:\\Users\\Administrator\\Desktop\\mainstage.txt");

        try (Scanner input = new Scanner(file)) {
            // Match the first two lines for Phones
            if (input.hasNextLine()) {
                String phone1Name = input.nextLine().trim();
                p1 = allphonesArrayList.stream()
                        .filter(p -> p.getName().equalsIgnoreCase(phone1Name))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Phone not found: " + phone1Name));
                phonesArrayList.add(p1);
            }

            if (input.hasNextLine()) {
                String phone2Name = input.nextLine().trim();
                p2 = allphonesArrayList.stream()
                        .filter(p -> p.getName().equalsIgnoreCase(phone2Name))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Phone not found: " + phone2Name));
                phonesArrayList.add(p2);
            }

            // Match the next two lines for Laptops
            if (input.hasNextLine()) {
                String laptop1Name = input.nextLine().trim();
                l1 = alllaptopsArrayList.stream()
                        .filter(l -> l.getName().equalsIgnoreCase(laptop1Name))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Laptop not found: " + laptop1Name));
                laptopsArrayList.add(l1);
            }

            if (input.hasNextLine()) {
                String laptop2Name = input.nextLine().trim();
                l2 = alllaptopsArrayList.stream()
                        .filter(l -> l.getName().equalsIgnoreCase(laptop2Name))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Laptop not found: " + laptop2Name));
                laptopsArrayList.add(l2);
            }

            // Match the last two lines for Accessories
            if (input.hasNextLine()) {
                String accessory1Name = input.nextLine().trim();
                ac1 = allaccessoriesArrayList.stream()
                        .filter(ac -> ac.getName().equalsIgnoreCase(accessory1Name))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Accessory not found: " + accessory1Name));
                accessoriesArrayList.add(ac1);
            }

            if (input.hasNextLine()) {
                String accessory2Name = input.nextLine().trim();
                ac2 = allaccessoriesArrayList.stream()
                        .filter(ac -> ac.getName().equalsIgnoreCase(accessory2Name))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Accessory not found: " + accessory2Name));
                accessoriesArrayList.add(ac2);
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        //the pain that contains all the stage
        Stage stage=new Stage();

        BorderPane mainBp=new BorderPane();
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


        Button btPhones=new Button("Phones");
        btPhones.setPrefSize(200,50);
        btPhones.setAlignment(Pos.CENTER);
        btPhones.setStyle("-fx-background-color: #ef355b; -fx-font-size: 18px; -fx-text-fill: white;");
        btPhones.setOnAction(e -> {
            try {
                PhonesStage phonesStage = new PhonesStage(c);
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        Button btLaptops=new Button("Laptops");
        btLaptops.setPrefSize(200,50);
        btLaptops.setAlignment(Pos.CENTER);
        btLaptops.setStyle("-fx-background-color: #ef355b; -fx-font-size: 18px; -fx-text-fill: white;");
        btLaptops.setOnAction(e->{
            LaptopsStage laptopsStage=new LaptopsStage(c);
            stage.close();
        });

        Button btAccessories=new Button("Accessories");
        btAccessories.setPrefSize(200,50);
        btAccessories.setAlignment(Pos.CENTER);
        btAccessories.setStyle("-fx-background-color: #ef355b; -fx-font-size: 18px; -fx-text-fill: white;");
        btAccessories.setOnAction(e->{
            AccessoriesStage a=new AccessoriesStage(c);
            stage.close();
        });

        Button btLogOut=new Button();
        btLogOut.setPrefSize(200,50);
        btLogOut.setAlignment(Pos.CENTER);
        btLogOut.setStyle("-fx-background-color: #ef355b; ");
        ImageView logOutIcon=new ImageView(new Image("C:\\Users\\Administrator\\Downloads\\logout.png"));
        logOutIcon.setFitHeight(30);
        logOutIcon.setFitWidth(30);
        btLogOut.setGraphic(logOutIcon);
        btLogOut.setPadding(new Insets(0,0,125,200));

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

        //now the second part
        VBox vb3=new VBox();
        vb3.setStyle( "-fx-background-color: white;" +      // Background color
                "-fx-background-radius: 70;" +       // Rounded corners
                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.25), 10, 0, 0, 4);" + // Shadow
                "-fx-padding: 20;" +                 // Padding inside the VBox
                "-fx-border-radius: 20;" );

        HBox hbForLabel2 = new HBox();
        Label lbl2 = new Label(" TAKE A LOOK AT OUR PRODUCTS");
        lbl2.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");

        Rectangle rectangle2 = new Rectangle();
        rectangle2.setFill(new LinearGradient(
                0, 0, 0, 1, // Start (0,0) and end (0,1) points for vertical gradient
                true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#ef355b")), // Start color
                new Stop(1, Color.web("#ff6f91"))  // End color
        ));

        rectangle2.setArcWidth(10); // Rounded corners
        rectangle2.setArcHeight(10); // Rounded corners

        rectangle2.widthProperty().bind(lbl2.widthProperty().add(20)); // Add padding
        rectangle2.heightProperty().bind(lbl2.heightProperty().add(10)); // Add padding
        rectangle2.getTransforms().add(new Shear(-0.3, 0)); // Adjust the X-shear value for the slant


        StackPane stackPane2 = new StackPane(rectangle2, lbl2);
        stackPane2.setPadding(new Insets(5)); // Optional padding around the label

        hbForLabel2.getChildren().add(stackPane2);
        vb3.getChildren().add(hbForLabel2);

        GridPane gpForProducts=new GridPane();
        gpForProducts.setAlignment(Pos.CENTER);
        gpForProducts.setHgap(150);
        gpForProducts.setVgap(80);
        gpForProducts.setStyle("-fx-border-color: lightgray; -fx-padding: 10px;");


        int j=0;
        for(Phones p:phonesArrayList){
            p.getImgage().setSmooth(true);
            p.getImgage().setFitWidth(300);
            p.getImgage().setFitHeight(300);
            p.getImgage().setPreserveRatio(true);
            p.getImgage().setEffect(new DropShadow(20, Color.web("#000000")));
            Label labelImg = new Label();

            labelImg.setText(p.getName());
            labelImg.setContentDisplay(ContentDisplay.TOP);
            labelImg.setStyle("-fx-font-size: 14px; -fx-text-fill: black;" +
                    " -fx-font-style: italic; -fx-font-weight: bold;");
            labelImg.setGraphic(p.getImgage());


            Rectangle r = new Rectangle();
            r.setWidth(400);
            r.setHeight(300);
            r.setArcWidth(20);
            r.setArcHeight(20);
            r.setFill(Color.TRANSPARENT);
            r.setStyle("-fx-stroke: linear-gradient(to right, lightgrey, black, lightgrey);"
                    + " -fx-stroke-width: 2;"+
                    "-fx-background-radius: 70;");
            r.setStrokeWidth(2);

            StackPane sp5 = new StackPane();
            sp5.setPrefSize(300, 300);
            sp5.getChildren().addAll(r, p.getImgage(), labelImg);

            ScaleTransition hoverEffect = new ScaleTransition(Duration.millis(200), p.getImgage());
            hoverEffect.setFromX(1.0);
            hoverEffect.setFromY(1.0);
            hoverEffect.setToX(1.05);
            hoverEffect.setToY(1.05);

            sp5.setOnMouseEntered(e -> {
                hoverEffect.setRate(1.0);
                hoverEffect.play();
            });

            sp5.setOnMouseExited(e -> {
                hoverEffect.setRate(-1.0);
                hoverEffect.play();
            });

            gpForProducts.add(sp5,0,j);
            j++;



            //here comes the descriptionPane
            p.getImgage().setMouseTransparent(true);
            labelImg.setMouseTransparent(true);
            r.setOnMouseClicked(e->{
                DescriptionPane dp=new DescriptionPane(p,c);
            });
        }

        int k=0;
        for(Laptops l:laptopsArrayList){
            l.getImgage().setFitWidth(300);
            l.getImgage().setFitHeight(300);
            l.getImgage().setPreserveRatio(true);
            l.getImgage().setEffect(new DropShadow(20, Color.web("#000000")));
            Label labelImg = new Label();

            labelImg.setText(l.getName());
            labelImg.setContentDisplay(ContentDisplay.TOP);
            labelImg.setStyle("-fx-font-size: 14px; -fx-text-fill: black;" +
                    " -fx-font-style: italic; -fx-font-weight: bold;");
            labelImg.setGraphic(l.getImgage());

            Rectangle r = new Rectangle();
            r.setWidth(400);
            r.setHeight(300);
            r.setArcWidth(20);
            r.setArcHeight(20);
            r.setFill(Color.TRANSPARENT);
            r.setStyle("-fx-stroke: linear-gradient(to right, lightgrey, black, lightgrey);"
                    + " -fx-stroke-width: 2;");
            r.setStrokeWidth(2);

            StackPane sp5 = new StackPane();
            sp5.setPrefSize(300, 300);
            sp5.getChildren().addAll(r, l.getImgage(), labelImg);
            ScaleTransition hoverEffect = new ScaleTransition(Duration.millis(200), l.getImgage());
            hoverEffect.setFromX(1.0);
            hoverEffect.setFromY(1.0);
            hoverEffect.setToX(1.05);
            hoverEffect.setToY(1.05);

            sp5.setOnMouseEntered(e -> {
                hoverEffect.setRate(1.0);
                hoverEffect.play();
            });

            sp5.setOnMouseExited(e -> {
                hoverEffect.setRate(-1.0);
                hoverEffect.play();
            });

            gpForProducts.add(sp5,1,k);
            k++;
            r.setOnMouseClicked(e->{
                DescriptionPane dp=new DescriptionPane(l,c);
            });
        }

        int d=0;
        for(Accessories ac:accessoriesArrayList){
            ac.getImgage().setFitWidth(300);
            ac.getImgage().setFitHeight(300);
            ac.getImgage().setPreserveRatio(true);
            ac.getImgage().setEffect(new DropShadow(20, Color.web("#000000")));

            Label labelImg = new Label();

            labelImg.setText(ac.getName());
            labelImg.setContentDisplay(ContentDisplay.TOP);
            labelImg.setStyle("-fx-font-size: 14px; -fx-text-fill: black;" +
                    " -fx-font-style: italic; -fx-font-weight: bold;");
            labelImg.setGraphic(ac.getImgage());

            Rectangle r = new Rectangle();
            r.setWidth(400);
            r.setHeight(300);
            r.setArcWidth(20);
            r.setArcHeight(20);
            r.setFill(Color.TRANSPARENT);
            r.setStyle("-fx-stroke: linear-gradient(to right, lightgrey, black, lightgrey);"
                    + " -fx-stroke-width: 2;");
            r.setStrokeWidth(2);

            StackPane sp5 = new StackPane();
            sp5.setPrefSize(300, 300);
            sp5.getChildren().addAll(r, ac.getImgage(), labelImg);
            ScaleTransition hoverEffect = new ScaleTransition(Duration.millis(200), ac.getImgage());
            hoverEffect.setFromX(1.0);
            hoverEffect.setFromY(1.0);
            hoverEffect.setToX(1.05);
            hoverEffect.setToY(1.05);

            sp5.setOnMouseEntered(e -> {
                hoverEffect.setRate(1.0);
                hoverEffect.play();
            });

            sp5.setOnMouseExited(e -> {
                hoverEffect.setRate(-1.0);
                hoverEffect.play();
            });
            gpForProducts.add(sp5,2,d);
            d++;
            r.setOnMouseClicked(e->{
                DescriptionPane dp=new DescriptionPane(ac,c);
            });
        }
        vb3.getChildren().add(gpForProducts);





        mainBp.setCenter(vb3);

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
        btLogOut.setOnAction(e->{
            LogIn logIn=new LogIn();
            stage.close();
        });
    }


}
