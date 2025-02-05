package com.example.project;

import com.example.project.Clients;
import com.example.project.Main;
import com.example.project.Register;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class LogIn {

    public static ArrayList<Clients> allClientsArrayList=new ArrayList<>();
  /*  static {
        allClientsArrayList.add(new Clients("a ","b","chi","123"));
        allClientsArrayList.add(new Clients("Elio ","Nakad","elionakad05@gmail.com","pass123"));
    }
*/
public DataBase db=new DataBase();

    public static ArrayList<Admin> adminArrayList=new ArrayList<>();
    static {
        adminArrayList.add(new Admin("a","b","chi@gmail.com","pass123"));
    }

    public static Button btsignIn = new Button("Sign in");

    private TextField email = new TextField();
    private PasswordField password = new PasswordField();
    // private Button btsignIn = new Button("Sign in");
    private Text createtxt = new Text("Create account");

    public LogIn() {
        db.selectClientsFromDatabase();
        db.selectPhonesFromDatabase();
        Label intro = new Label("Welcome Back");
        Label or = new Label("or");


        GridPane gp = new GridPane();
        GridPane gp2 = new GridPane();


        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(100);  // Adjust width of columns
        gp.getColumnConstraints().add(column1);

        RowConstraints row1 = new RowConstraints();
        row1.setMinHeight(50);  // Increase row height for better spacing
        gp.getRowConstraints().add(row1);

        Pane textPane = new Pane();
        textPane.getChildren().add(createtxt);



        gp2.add(btsignIn , 0 , 0);
        //gp2.add(or , 0 , 1);
        //gp2.add(create , 0 , 2);
        // gp2.add(textPane , 0 , 2);

        // Add components to the GridPane
        gp.add(intro, 0, 0);

        gp.add(email, 0, 2);
        gp.add(password, 0, 3);
        gp.add(gp2, 0, 4);
        gp.add(textPane , 0 , 5);

        // Center the "or" label
        GridPane.setHalignment(or, HPos.CENTER);

        // Set GridPane alignment and spacing
        gp.setAlignment(Pos.CENTER);
        gp.setVgap(20);  // Vertical gap between rows
        gp.setHgap(10);  // Horizontal gap between columns
        gp.setPadding(new Insets(230, 0, 100, 180));


        intro.setStyle("-fx-font-size: 50px; -fx-text-fill: white;");

        email.setPromptText("Email:");
        password.setPromptText("Password:");

        // Style the "or" label
        or.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
        createtxt.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
        createtxt.setFill(Color.WHITE);

        // Set sizes for text fields
        //  fullName.setPrefWidth(300);
        email.setPrefWidth(300);
        password.setPrefWidth(300);
        //  fullName.setPrefHeight(40);
        email.setPrefHeight(40);
        password.setPrefHeight(40);

        // Set borders for the text fields
        //  fullName.setBorder(new Border(new BorderStroke(Color.VIOLET, BorderStrokeStyle.SOLID, null, null)));
        email.setBorder(new Border(new BorderStroke(Color.VIOLET, BorderStrokeStyle.SOLID, null, null)));
        password.setBorder(new Border(new BorderStroke(Color.VIOLET, BorderStrokeStyle.SOLID, null, null)));

        // Style buttons
        btsignIn.setStyle("-fx-background-color: #ef355b; -fx-font-size: 18px; -fx-text-fill: white;");
        btsignIn.setPrefWidth(320);
        btsignIn.setPrefHeight(35);

//        create.setStyle("-fx-background-color: #ef355b; -fx-font-size: 18px; -fx-text-fill: white;");
//        create.setPrefWidth(320);
//        create.setPrefHeight(35);

        Stage stage = new Stage();

        btsignIn.setOnAction(e -> {
            boolean adminFound=false;
            try {
                for(Admin a:adminArrayList){
                    if(a.getEmail().equals(email.getText())&&a.getPass().equals(password.getText())){
                        Admin a2=new Admin();
                        adminFound=true;
                    }
                }

                boolean userFound = false;
                for (Clients c : allClientsArrayList) {
                    if (
                            c.getEmail().equals(email.getText()) &&
                                    c.getPass().equals(password.getText())&&!adminFound) {
                        userFound = true;
                        Main main=new Main(c);  // Ensure Main's constructor is valid
                        break;
                    }
                }

                if (!userFound&&!adminFound) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid credentials. Please try again.");
                    alert.showAndWait();
                } else {
                    stage.close();
                }
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        // create.setOnAction(this:: nextScene);
        textPane.setOnMousePressed(this::nextScene);


        Pane root = new Pane();

        // Set the background image for the Pane using CSS
        root.setStyle("-fx-background-image: url('file:C:/Users/Administrator/Downloads/logInFinal.jpg'); "
                + "-fx-background-size: cover; "
                + "-fx-background-position: center center;");

        // Add the GridPane to the Pane
        root.getChildren().add(gp);

        // Create a new Stage

        stage.setFullScreen(true);


        // Set up the Scene and Stage
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }


    private void nextScene(MouseEvent e){
        Register rg = new Register();
}
}
