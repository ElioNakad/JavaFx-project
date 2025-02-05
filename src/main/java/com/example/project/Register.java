package com.example.project;
import com.example.project.Clients;

import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static com.example.project.LogIn.allClientsArrayList;

public class Register  {
    public     DataBase db=new DataBase();

    private TextField name = new TextField();
    private TextField lname = new TextField();
    private TextField email = new TextField();
    private TextField pass = new PasswordField(); // Start as a PasswordField

    private TextField checkPass = new TextField();
    private Button create = new Button("Create Account");
    Button hide = new Button();
    private boolean passwordVisible;
    private  boolean canSave = false;

    public Register() {
        Stage stage = new Stage();

        GridPane gp = new GridPane();
        GridPane gp2 = new GridPane();

        Label label = new Label("Create Account");


        HBox passwordBox = new HBox();
        passwordBox.setAlignment(Pos.CENTER_LEFT);
        passwordBox.setSpacing(5);
        passwordBox.getChildren().addAll(pass, hide);

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(100);
        gp.getColumnConstraints().add(column1);

        RowConstraints row1 = new RowConstraints();
        row1.setMinHeight(50);
        gp.getRowConstraints().add(row1);

        // Set GridPane alignment and spacing
        gp.setAlignment(Pos.CENTER);
        gp.setVgap(20);
        gp.setHgap(10);
        gp.setPadding(new Insets(230, 0, 100, 180));

        gp2.setHgap(50);
        gp2.add(name, 0, 0);
        gp2.add(lname, 1, 0);

        Label passLabel = new Label("Password should contain at least 1 uppercase, 1 special character, over 8");

        gp.add(label, 0, 0);
        gp.add(gp2, 0, 1);
        gp.add(email, 0, 2);
        gp.add(passwordBox, 0, 3);
        gp.add(passLabel, 0, 4);
        gp.add(checkPass, 0, 5);
        gp.add(create, 0, 6);

        label.setStyle("-fx-font-size: 50px; -fx-text-fill: white;");
        passLabel.setStyle("-fx-text-fill: white;");
        create.setStyle("-fx-background-color: #ef355b; -fx-font-size: 18px; -fx-text-fill: white;");
        hide.setStyle("-fx-background-color: #ef355b; -fx-font-size: 18px; -fx-text-fill: white;");

        name.setPromptText("First name");
        lname.setPromptText("Last name");
        email.setPromptText("email");
        pass.setPromptText("Password");
        checkPass.setPromptText("Check Password");

        name.setPrefWidth(150);
        name.setPrefHeight(40);
        lname.setPrefWidth(150);
        lname.setPrefHeight(40);
        email.setPrefWidth(300);
        email.setPrefHeight(40);
        pass.setPrefWidth(300);
        pass.setPrefHeight(40);
        checkPass.setPrefWidth(300);
        checkPass.setPrefHeight(40);
        create.setPrefWidth(150);
        create.setPrefHeight(35);

        email.setBorder(new Border(new BorderStroke(Color.VIOLET, BorderStrokeStyle.SOLID, null, null)));
        name.setBorder(new Border(new BorderStroke(Color.VIOLET, BorderStrokeStyle.SOLID, null, null)));
        lname.setBorder(new Border(new BorderStroke(Color.VIOLET, BorderStrokeStyle.SOLID, null, null)));
        pass.setBorder(new Border(new BorderStroke(Color.VIOLET, BorderStrokeStyle.SOLID, null, null)));
        checkPass.setBorder(new Border(new BorderStroke(Color.VIOLET, BorderStrokeStyle.SOLID, null, null)));

        Pane mainContent = new Pane();
        mainContent.setStyle("-fx-background-image: url('file:C:/Users/Administrator/Downloads/logInFinal.jpg'); "
                + "-fx-background-size: cover; "
                + "-fx-background-position: center center;");
        mainContent.getChildren().add(gp);


        Pane overlayPane = new Pane();
        overlayPane.getChildren().add(gp);

        overlayPane.setOnMouseClicked(e->{
            if(e.getClickCount() == 2){
                stage.close();
            }
        });

        // Combine the main content and the overlay
        StackPane root = new StackPane();
        root.getChildren().addAll(mainContent, overlayPane);

        // Set actions for buttons
        hide.setOnAction(this::showPass);
        create.setOnAction(e -> createAccount());

        Scene scene = new Scene(root);


        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
    }



    private void createAccount(){
        if(setName(name) && setLname(lname) && setPass(pass) && setCheckPass(checkPass)){
            String namee , lnamee , emaile , passs;
            passs = pass.getText();
            namee = name.getText();
            lnamee = lname.getText();
            emaile = email.getText();
            db.insertClientIntoDatabase(namee,lnamee,emaile,passs);

            canSave = true;

            allClientsArrayList.add(new Clients(namee , lnamee , emaile , passs));


        }else{
            callAlert("Something Wrong!");
        }
    }
    private void showPass(ActionEvent e) {
        if (!passwordVisible) {
            // Show password as plain text
            TextField textField = new TextField(pass.getText());
            textField.setPrefWidth(pass.getPrefWidth());
            textField.setPrefHeight(pass.getPrefHeight());
            textField.setPromptText(pass.getPromptText());
            textField.setBorder(pass.getBorder());

            HBox passwordBox = (HBox) pass.getParent(); // Get the parent HBox
            int index = passwordBox.getChildren().indexOf(pass);
            passwordBox.getChildren().set(index, textField); // Replace PasswordField with TextField

            pass =  textField; // Update the reference
        } else {
            // Hide password as asterisks
            PasswordField passwordField = new PasswordField();
            passwordField.setText(pass.getText());
            passwordField.setPrefWidth(pass.getPrefWidth());
            passwordField.setPrefHeight(pass.getPrefHeight());
            passwordField.setPromptText(pass.getPromptText());
            passwordField.setBorder(pass.getBorder());

            HBox passwordBox = (HBox) pass.getParent(); // Get the parent HBox
            int index = passwordBox.getChildren().indexOf(pass);
            passwordBox.getChildren().set(index, passwordField); // Replace TextField with PasswordField

            pass = passwordField; // Update the reference
        }
        passwordVisible = !passwordVisible;
    }



    private boolean setName(TextField name) {
        for(int i = 0 ; i< name.getText().length() ; i++){
            if(name.getText().charAt(i) == ' '){
                callAlert("Invalid name");
                return false;
            }
        }
        this.name = name;
        return true;
    }

    public TextField getName() {
        return name;
    }

    private boolean setLname(TextField lname) {
        for(int i = 0 ; i< lname.getText().length() ; i++){
            if(lname.getText().charAt(i) == ' '){
                callAlert("Invalid name");
                return false;
            }
        }
        this.lname = lname;
        return true;
    }

    private TextField getLname() {
        return lname;
    }

    private void setEmail(TextField email) {
        this.email = email;
    }

    private TextField getEmail() {
        return email;
    }

    private boolean setPass(TextField pass) {
        String password = pass.getText();
//        if (password.length() < 8) {
//            callAlert("Password must be at least 8 characters long");
//            return false;
//        }
        if (!isUppercase(password)) {
            callAlert("Password must contain at least one uppercase letter");
            return false;
        }
//        if (!isSpecialCharacter(password)) {
//            callAlert("Password must contain at least one special character");
//            return false;
//        }
        this.pass = pass;
        return true;
    }

    private TextField getPass(){
        return pass;
    }

    private boolean isUppercase(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))) {
                return true;
            }
        }
        return false;
    }
    private boolean isSpecialCharacter(String password) {
        return password.matches(".[!@#$%^&()\\-_=+{}\\[\\]:;\"'<>,.?/].*");
    }



    private boolean setCheckPass(TextField checkPass) {
        if(checkPass.getText().equals(pass.getText())){
            this.checkPass = checkPass;
            return true;
        }else{
            callAlert("Invalid Password");
            return false;
        }
    }

    private TextField getCheckPass() {
        return checkPass;
    }


    private void callAlert(String details) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(details);
        alert.showAndWait();
    }





}
