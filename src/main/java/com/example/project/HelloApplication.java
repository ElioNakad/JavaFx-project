package com.example.project;
import javafx.animation.Animation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class HelloApplication extends Application {

    public void start(Stage primaryStage) throws IOException {
        ShopitLogoAnimation  s=new ShopitLogoAnimation();
        // Create the scene and set the stage
      
    }

    public static void main(String[] args)  {
        launch(args);

    }
}

