package com.example.project;

import javafx.animation.*;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class ShopitLogoAnimation {

    Stage primaryStage = new Stage();

    public ShopitLogoAnimation() {
        // Create the logo image and view
        Image logoImage = new Image("C:\\Users\\Administrator\\Desktop\\Prog3Images\\shopitLogo.jpg");
        ImageView logoView = new ImageView(logoImage);

        // Set the logo size
        logoView.setFitWidth(200);
        logoView.setFitHeight(200);

        // Center the logo within the Pane
        logoView.setLayoutX((700 - logoView.getFitWidth()) / 2);
        logoView.setLayoutY((350 - logoView.getFitHeight()) / 2);

        // Create the fade and scale animation
        FadeTransition fade = new FadeTransition(Duration.seconds(2.5), logoView);
        fade.setFromValue(1.0);
        fade.setToValue(0.0);

        ScaleTransition scale = new ScaleTransition(Duration.seconds(2.5), logoView);
        scale.setFromX(1.0);
        scale.setFromY(1.0);
        scale.setToX(0.5);
        scale.setToY(0.5);

        // Create the sequence of animations
        SequentialTransition sequence = new SequentialTransition(logoView,
                new ParallelTransition(fade, scale),
                new ParallelTransition(
                        new FadeTransition(Duration.seconds(0.5), logoView),
                        new ScaleTransition(Duration.seconds(0.5), logoView)
                ));
        sequence.setCycleCount(Animation.INDEFINITE); // Only play the animation once

        // Create the root Pane and add the logo
        Pane root = new Pane(logoView);
        root.setStyle("-fx-background-color: #ef355b;"); // Set the background color to pink

        // Start the animation
        sequence.play();

        // Set up the stage for the logo animation
        primaryStage.initStyle(StageStyle.UNDECORATED); // Remove the title bar
        primaryStage.setScene(new Scene(root, 700, 350));
        primaryStage.show();

        // Close the logo animation window after the animation finishes (5 seconds)
        Timeline closeStage = new Timeline(
                new javafx.animation.KeyFrame(Duration.seconds(5), e -> {
                    primaryStage.close();
                    LogIn l = new LogIn();
                })
        );

        closeStage.play();
}
}
