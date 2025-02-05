//package com.example.project;
//
//import javafx.beans.property.SimpleDoubleProperty;
//import javafx.beans.property.SimpleIntegerProperty;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.*;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//import javafx.scene.control.Alert;
//
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//import java.util.stream.Stream;
//
//import static com.example.project.LogIn.adminArrayList;
//import static com.example.project.LogIn.allClientsArrayList;
//import static com.example.project.Main.*;
//
//public class Admin extends Person {
//
//    DataBase db=new DataBase();
//    private String[] commands = {"Edit Admin" ,"Access Clients", "Add Item" , "Delete Item", "Change Item Price" ,"Edit quantity", "Show Inventory"};
//    private TableView<Clients> table = new TableView<Clients>();
//    private VBox tableVbox = new VBox();
//
//    private HBox radioPane = new HBox();
//
//
//    Label p = new Label("Price");
//    TextField pt = new TextField();
//    Label nl = new Label("Name");
//    TextField nt = new TextField();
//    Label q = new Label("Quantity");
//    TextField qt = new TextField();
//    Label b = new Label("Brand");
//    TextField bt = new TextField();
//    Label s = new Label("Storage (GB)");
//    TextField st = new TextField();
//    Label r = new Label("RAM (GB)");
//    TextField rt = new TextField();
//    Label il = new Label("Image URL");
//    TextField it = new TextField();
//    Label cl = new Label("CPU");
//    TextField ct = new TextField();
//
//
//
//    public Admin(String name, String lname, String email, String pass) {
//        super(name, lname, email, pass);
//
//    }
//
//    public Admin() {
//
//        BorderPane bp = new BorderPane();
//
//        // Create and configure the ListView
//        ListView<String> lv = new ListView<>(FXCollections.observableArrayList(commands));
//        lv.setPrefSize(200, 300);
//        lv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//        lv.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//            if ("Access Clients".equals(newValue)) {
//                accessDatabase();
//            } else if ("Change Item Price".equals(newValue)) {
//                editPrice();
//            } else if ("Add Item".equals(newValue)) {
//
//                addItems();
//
//            } else if ("Show Inventory".equals(newValue)) {
//                showInv();
//            }else if("Edit Admin".equals(newValue)){
//                editAdmin();
//            }else if("Delete Item".equals(newValue)){
//                deleteItem();
//            }else if("Edit quantity".equals(newValue)){
//                editQty();
//            }
//        });
//
//        // Internal inline styling for ListView
//        lv.setStyle(
//                "-fx-background-color: #f4f4f4; " + /* ListView background */
//                        "-fx-border-color: #ef355b; " + /* Border color */
//                        "-fx-border-width: 2px; " + /* Border thickness */
//                        "-fx-padding: 10px;" /* Padding inside the ListView */
//        );
//
//        // Styling for list cells (items inside the ListView)
//        lv.setCellFactory(param -> {
//            javafx.scene.control.ListCell<String> cell = new javafx.scene.control.ListCell<String>() {
//                @Override
//                protected void updateItem(String item, boolean empty) {
//                    super.updateItem(item, empty);
//                    if (!empty) {
//                        setText(item);
//                        setStyle("-fx-font-size: 16px; " + /* Font size */
//                                "-fx-padding: 5px 10px; " + /* Padding inside each item */
//                                "-fx-text-fill: #333333;"); /* Text color */
//                    } else {
//                        setText(null);
//                    }
//                }
//            };
//            return cell;
//        });
//
//        // VBox layout
//        VBox vb = new VBox();
//        vb.getChildren().addAll(lv, radioPane);
//        vb.setPadding(new Insets(12)); // Add 12px padding for left section
//        bp.setLeft(vb);
//
//        // Set the top text
//        Text topText = new Text("Admin Panel");
//        HBox topContainer = new HBox(topText);
//        topContainer.setAlignment(javafx.geometry.Pos.CENTER);
//        topContainer.setPadding(new Insets(12, 0, 12, 0)); // Add 12px padding at the top of the top container
//        bp.setTop(topContainer);
//
//        topText.setStyle("-fx-font-size: 24px; -fx-font-family: 'Arial';");
//        topText.setFill(Color.WHITE);
//
//        // Add padding to the center layout (tableVbox)
//        tableVbox.setPadding(new Insets(12)); // Add 12px padding for center section
//        bp.setCenter(tableVbox);
//
//        // Set background for the BorderPane
//        bp.setBackground(new javafx.scene.layout.Background(new javafx.scene.layout.BackgroundFill(
//                Color.WHITE, // Background color for the main layout
//                new javafx.scene.layout.CornerRadii(10),
//                null
//        )));
//
//        // Set background for the top section
//        topContainer.setBackground(new javafx.scene.layout.Background(new javafx.scene.layout.BackgroundFill(
//                Color.web("#ef355b"), // Background color for the top section
//                new javafx.scene.layout.CornerRadii(0),
//                null
//        )));
//
//        topContainer.setPrefHeight(100);
//
//        // Configure the scene and stage
//        Scene scene = new Scene(bp, 800, 600);
//        Stage stage = new Stage();
//        stage.setScene(scene);
//        stage.setTitle("Admin Panel");
//        stage.setFullScreen(true);
//        stage.show();
//    }
//
//
//    private void editAdmin() {
//        // Clear existing content
//        tableVbox.getChildren().clear();
//
//        // Create GridPane with improved styling
//        GridPane gp = new GridPane();
//        gp.setStyle(
//                "-fx-background-color: #f4f4f4; " +
//                        "-fx-padding: 20px; " +
//                        "-fx-background-radius: 10px;"
//        );
//        gp.setHgap(15);
//        gp.setVgap(15);
//        gp.setAlignment(Pos.CENTER);
//
//        // Style labels
//        Label l3 = new Label("Last Name");
//        Label l2 = new Label("First Name");
//        Label l = new Label("Email");
//        Label l1 = new Label("Password");
//
//        // Consistent label styling
//        Stream.of(l3, l2, l, l1).forEach(label -> {
//            label.setStyle(
//                    "-fx-text-fill: #333; " +
//                            "-fx-font-size: 14px;"
//            );
//        });
//
//        // Create TextFields
//        TextField lname = new TextField();
//        TextField fname = new TextField();
//        TextField email = new TextField();
//        PasswordField pass = new PasswordField(); // Use PasswordField for password
//
//        // Style TextFields
//        Stream.of(lname, fname, email, pass).forEach(textField -> {
//            textField.setStyle(
//                    "-fx-background-color: white; " +
//                            "-fx-border-color: #ef355b; " +
//                            "-fx-border-width: 1px; " +
//                            "-fx-border-radius: 5px; " +
//                            "-fx-padding: 8px;"
//            );
//            textField.setPrefHeight(35);
//        });
//
//        // Set Prompts
//        lname.setPromptText("Enter Last Name");
//        fname.setPromptText("Enter First Name");
//        email.setPromptText("Enter Email");
//        pass.setPromptText("Enter Password");
//
//        // Add validation button
//        Button submitButton = new Button("Update Admin");
//        submitButton.setStyle(
//                "-fx-background-color: #ef355b; " +
//                        "-fx-text-fill: white; " +
//                        "-fx-font-size: 14px; " +
//                        "-fx-padding: 10px; " +
//                        "-fx-background-radius: 5px;"
//        );
//
//        // Layout components
//        gp.add(l3, 0, 0);
//        gp.add(lname, 1, 0);
//        gp.add(l2, 2, 0);
//        gp.add(fname, 3, 0);
//        gp.add(l, 0, 1);
//        gp.add(email, 1, 1);
//        gp.add(l1, 2, 1);
//        gp.add(pass, 3, 1);
//        gp.add(submitButton, 1, 2, 2, 1); // Span submit button across two columns
//
//        // Add validation and submission logic
//        submitButton.setOnAction(e -> {
//            // Trim and validate inputs
//            String trimmedFname = fname.getText().trim();
//            String trimmedLname = lname.getText().trim();
//            String trimmedEmail = email.getText().trim();
//            String trimmedPass = pass.getText().trim();
//
//            Admin newAdmin = new Admin(trimmedFname, trimmedLname, trimmedEmail, trimmedPass);
//            adminArrayList.add(newAdmin);
//
//            // Clear fields after submission
//            fname.clear();
//            lname.clear();
//            email.clear();
//            pass.clear();
//
//        });
//
//        // Add to VBox
//        tableVbox.getChildren().addAll(gp);
//    }
//
//    private void editQty(){
//        // Clear previous content in the VBox
//        tableVbox.getChildren().clear();
//        radioPane.getChildren().clear();
//
//        // Create RadioButtons for each category
//        RadioButton phones = new RadioButton("Phones");
//        RadioButton laptops = new RadioButton("Laptops");
//        RadioButton accessories = new RadioButton("Accessories");
//
//        // Group the radio buttons
//        ToggleGroup categoryGroup = new ToggleGroup();
//        phones.setToggleGroup(categoryGroup);
//        laptops.setToggleGroup(categoryGroup);
//        accessories.setToggleGroup(categoryGroup);
//
//        // Create TextFields and Labels for quantity editing
//        Label newQuantityLabel = new Label("New Quantity:");
//        TextField newQuantityField = new TextField();
//        newQuantityField.setPromptText("Enter new quantity");
//
//        // Create a TableView for Phones
//        TableView<Phones> phoneTable = new TableView<>();
//        TableColumn<Phones, String> phoneNameCol = new TableColumn<>("Phone Name");
//        phoneNameCol.setCellValueFactory(cellData ->
//                new SimpleStringProperty(cellData.getValue().getName()));
//
//        TableColumn<Phones, Integer> phoneQuantityCol = new TableColumn<>("Quantity");
//        phoneQuantityCol.setCellValueFactory(cellData ->
//                new SimpleIntegerProperty(cellData.getValue().getQuantityInStock()).asObject());
//
//        phoneTable.getColumns().addAll(phoneNameCol, phoneQuantityCol);
//
//        // Create a TableView for Laptops
//        TableView<Laptops> laptopTable = new TableView<>();
//        TableColumn<Laptops, String> laptopNameCol = new TableColumn<>("Laptop Name");
//        laptopNameCol.setCellValueFactory(cellData ->
//                new SimpleStringProperty(cellData.getValue().getName()));
//
//        TableColumn<Laptops, Integer> laptopQuantityCol = new TableColumn<>("Quantity");
//        laptopQuantityCol.setCellValueFactory(cellData ->
//                new SimpleIntegerProperty(cellData.getValue().getQuantityInStock()).asObject());
//
//        laptopTable.getColumns().addAll(laptopNameCol, laptopQuantityCol);
//
//        // Create a TableView for Accessories
//        TableView<Accessories> accessoryTable = new TableView<>();
//        TableColumn<Accessories, String> accessoryNameCol = new TableColumn<>("Accessory Name");
//        accessoryNameCol.setCellValueFactory(cellData ->
//                new SimpleStringProperty(cellData.getValue().getName()));
//
//        TableColumn<Accessories, Integer> accessoryQuantityCol = new TableColumn<>("Quantity");
//        accessoryQuantityCol.setCellValueFactory(cellData ->
//                new SimpleIntegerProperty(cellData.getValue().getQuantityInStock()).asObject());
//
//        accessoryTable.getColumns().addAll(accessoryNameCol, accessoryQuantityCol);
//
//        // Initially hide all tables
//        phoneTable.setVisible(false);
//        laptopTable.setVisible(false);
//        accessoryTable.setVisible(false);
//
//        // Set up action for radio buttons to show/populate the appropriate table
//        phones.setOnAction(e -> {
//            phoneTable.setItems(FXCollections.observableArrayList(Main.allphonesArrayList));
//            phoneTable.setVisible(true);
//            laptopTable.setVisible(false);
//            accessoryTable.setVisible(false);
//        });
//
//        laptops.setOnAction(e -> {
//            laptopTable.setItems(FXCollections.observableArrayList(Main.alllaptopsArrayList));
//            phoneTable.setVisible(false);
//            laptopTable.setVisible(true);
//            accessoryTable.setVisible(false);
//        });
//
//        accessories.setOnAction(e -> {
//            accessoryTable.setItems(FXCollections.observableArrayList(Main.allaccessoriesArrayList));
//            phoneTable.setVisible(false);
//            laptopTable.setVisible(false);
//            accessoryTable.setVisible(true);
//        });
//
//        // Create a button to change the quantity
//        Button changeQtyButton = new Button("Change Quantity");
//        changeQtyButton.setStyle("-fx-background-color: #ef355b; -fx-text-fill: white;");
//        changeQtyButton.setOnAction(e -> {
//            // Determine which table is currently visible
//            Object selectedItem = null;
//            if (phoneTable.isVisible()) {
//                selectedItem = phoneTable.getSelectionModel().getSelectedItem();
//            } else if (laptopTable.isVisible()) {
//                selectedItem = laptopTable.getSelectionModel().getSelectedItem();
//            } else if (accessoryTable.isVisible()) {
//                selectedItem = accessoryTable.getSelectionModel().getSelectedItem();
//            }
//
//            if (selectedItem == null) {
//                showAlert("Please select an item to change its quantity.");
//                return;
//            }
//
//            try {
//                int newQty = Integer.parseInt(newQuantityField.getText());
//                String itemName = null;
//                int oldQty = 0;
//
//                if (selectedItem instanceof Phones) {
//                    Phones phone = (Phones) selectedItem;
//                    oldQty = phone.getQuantityInStock();
//                    phone.setQuantityInStock(newQty);
//                    itemName = phone.getName();
//                    phoneTable.refresh();
//                } else if (selectedItem instanceof Laptops) {
//                    Laptops laptop = (Laptops) selectedItem;
//                    oldQty = laptop.getQuantityInStock();
//                    laptop.setQuantityInStock(newQty);
//                    itemName = laptop.getName();
//                    laptopTable.refresh();
//                } else if (selectedItem instanceof Accessories) {
//                    Accessories accessory = (Accessories) selectedItem;
//                    oldQty = accessory.getQuantityInStock();
//                    accessory.setQuantityInStock(newQty);
//                    itemName = accessory.getName();
//                    accessoryTable.refresh();
//                }
//
//                // Calculate the quantity difference
//                int quantityToDeduct = newQty - oldQty;
//
//                // Call the updateStockItemsAdmin function to update the database
//                db.updateStockItemsAdmin(quantityToDeduct, itemName);
//
//                showAlert("Quantity updated successfully!");
//            } catch (NumberFormatException ex) {
//                showAlert("Invalid quantity. Please enter a valid number.");
//            }
//        });
//
//        // Create a HBox for radio buttons and quantity editing
//        HBox controlPane = new HBox(20);
//        controlPane.setAlignment(Pos.CENTER);
//        controlPane.setPadding(new Insets(10));
//        controlPane.getChildren().addAll(phones, laptops, accessories, newQuantityLabel, newQuantityField, changeQtyButton);
//
//        // Add components to the layout
//        tableVbox.getChildren().addAll(controlPane, phoneTable, laptopTable, accessoryTable);
//    }
//
//
//    private void accessDatabase() {
//        // Clear previous content in the VBox
//        tableVbox.getChildren().clear();
//        radioPane.getChildren().clear();
//
//        // Define columns
//        TableColumn<Clients, String> nameCol = new TableColumn<>("First Name");
//        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
//
//        TableColumn<Clients, String> lnameCol = new TableColumn<>("Last Name");
//        lnameCol.setCellValueFactory(cellData -> cellData.getValue().lnameProperty());
//
//        TableColumn<Clients, String> emailCol = new TableColumn<>("Email");
//        emailCol.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
//
//        TableColumn<Clients, String> passCol = new TableColumn<>("Password");
//        passCol.setCellValueFactory(cellData -> cellData.getValue().passProperty());
//
//        // Add columns to the table
//        table.getColumns().setAll(nameCol, lnameCol, emailCol, passCol);
//
//        // Add sample data to the table
//        ObservableList<Clients> data = FXCollections.observableArrayList(
//                allClientsArrayList
//        );
//
//        table.setItems(data);
//
//        // Add the table to the VBox
//        tableVbox.getChildren().add(table);
//    }
//
//    private void showInv(){
//        tableVbox.getChildren().clear();
//        radioPane.getChildren().clear();
//
//        // Create RadioButtons for each category
//        RadioButton phones = new RadioButton("Phones");
//        RadioButton laptops = new RadioButton("Laptops");
//        RadioButton accessories = new RadioButton("Accessories");
//
//        // Group the radio buttons
//        ToggleGroup group = new ToggleGroup();
//        phones.setToggleGroup(group);
//        laptops.setToggleGroup(group);
//        accessories.setToggleGroup(group);
//
//        // Set actions for the radio buttons
//        phones.setOnAction(this::paneForPhones);
//        laptops.setOnAction(this::paneForLaptops);
//        accessories.setOnAction(this::paneForAccessories);
//
//
//        // Create a VBox to hold the radio buttons
//        VBox radioButtonContainer = new VBox(10, phones, laptops, accessories);
//        radioPane.setAlignment(Pos.CENTER);
//        radioPane.setPadding(new Insets(20));
//        radioPane.getChildren().addAll(phones, laptops, accessories);
//
//
//
//    }
//
//
//    private void paneForPhones(ActionEvent e) {
//        // Clear previous contents if any
//        tableVbox.getChildren().clear();
//
//        // Create a TableView for Phones
//        TableView<Phones> phoneTable = new TableView<>();
//
//        // Create columns for Phone properties
//        TableColumn<Phones, String> nameCol = new TableColumn<>("Phone Name");
//        nameCol.setCellValueFactory(cellData -> {
//            Phones phone = cellData.getValue();
//            return new javafx.beans.property.SimpleStringProperty(phone.getName());
//        });
//
//        TableColumn<Phones, Double> priceCol = new TableColumn<>("Price");
//        priceCol.setCellValueFactory(cellData -> {
//            Phones phone = cellData.getValue();
//            return new javafx.beans.property.SimpleDoubleProperty(phone.getPrice()).asObject();
//        });
//
//        TableColumn<Phones, Integer> qtyCol = new TableColumn<>("Quantity");
//        qtyCol.setCellValueFactory(cellData -> {
//            Phones phone = cellData.getValue();
//            return new javafx.beans.property.SimpleIntegerProperty(phone.getQuantityInStock()).asObject();
//        });
//
//        TableColumn<Phones, String> brandCol = new TableColumn<>("Brand");
//        brandCol.setCellValueFactory(cellData -> {
//            Phones phone = cellData.getValue();
//            return new javafx.beans.property.SimpleStringProperty(phone.getBrand());
//        });
//
////        TableColumn<Phones , String>imageCol = new TableColumn<>("Image");
////        imageCol.setCellValueFactory(cellData -> {
////            Phones phone = cellData.getValue();
////            return new javafx.beans.property.SimpleStringProperty(phone.getImgage());
////        });
//
//
//        // Add columns to the table
//        phoneTable.getColumns().addAll(nameCol, priceCol, qtyCol, brandCol);
//
//        // Populate the table with the static allphonesArrayList
//        phoneTable.setItems(FXCollections.observableArrayList(Main.allphonesArrayList));
//
//        // Add the table to the VBox
//        tableVbox.getChildren().add(phoneTable);
//    }
//
//    private void paneForLaptops(ActionEvent e) {
//        // Clear previous contents if any
//        tableVbox.getChildren().clear();
//
//        // Create a TableView for Laptops
//        TableView<Laptops> laptopTable = new TableView<>();
//
//        // Create columns for Laptop properties
//        TableColumn<Laptops, String> nameCol = new TableColumn<>("Laptop Name");
//        nameCol.setCellValueFactory(cellData -> {
//            Laptops laptop = cellData.getValue();
//            return new javafx.beans.property.SimpleStringProperty(laptop.getName());
//        });
//
//        TableColumn<Laptops, Double> priceCol = new TableColumn<>("Price");
//        priceCol.setCellValueFactory(cellData -> {
//            Laptops laptop = cellData.getValue();
//            return new javafx.beans.property.SimpleDoubleProperty(laptop.getPrice()).asObject();
//        });
//
//        TableColumn<Laptops, Integer> qtyCol = new TableColumn<>("Quantity");
//        qtyCol.setCellValueFactory(cellData -> {
//            Laptops laptop = cellData.getValue();
//            return new javafx.beans.property.SimpleIntegerProperty(laptop.getQuantityInStock()).asObject();
//        });
//
//        TableColumn<Laptops, String> brandCol = new TableColumn<>("Brand");
//        brandCol.setCellValueFactory(cellData -> {
//            Laptops laptop = cellData.getValue();
//            return new javafx.beans.property.SimpleStringProperty(laptop.getBrand());
//        });
//
//        // Add columns to the table
//        laptopTable.getColumns().addAll(nameCol, priceCol, qtyCol, brandCol);
//
//        // Populate the table with the static allLaptopsArrayList
//        laptopTable.setItems(FXCollections.observableArrayList(Main.alllaptopsArrayList));  // Use the correct list for laptops
//
//        // Add the table to the VBox
//        tableVbox.getChildren().add(laptopTable);
//    }
//
//    private void paneForAccessories(ActionEvent e) {
//        // Clear previous contents if any
//        tableVbox.getChildren().clear();
//
//        // Create a TableView for Accessories
//        TableView<Accessories> accessoryTable = new TableView<>();
//
//        // Create columns for Accessory properties
//        TableColumn<Accessories, String> nameCol = new TableColumn<>("Accessory Name");
//        nameCol.setCellValueFactory(cellData -> {
//            Accessories accessory = cellData.getValue();
//            return new javafx.beans.property.SimpleStringProperty(accessory.getName());
//        });
//
//        TableColumn<Accessories, Double> priceCol = new TableColumn<>("Price");
//        priceCol.setCellValueFactory(cellData -> {
//            Accessories accessory = cellData.getValue();
//            return new javafx.beans.property.SimpleDoubleProperty(accessory.getPrice()).asObject();
//        });
//
//        TableColumn<Accessories, Integer> qtyCol = new TableColumn<>("Quantity");
//        qtyCol.setCellValueFactory(cellData -> {
//            Accessories accessory = cellData.getValue();
//            return new javafx.beans.property.SimpleIntegerProperty(accessory.getQuantityInStock()).asObject();
//        });
//
//        TableColumn<Accessories, String> brandCol = new TableColumn<>("Brand");
//        brandCol.setCellValueFactory(cellData -> {
//            Accessories accessory = cellData.getValue();
//            return new javafx.beans.property.SimpleStringProperty(accessory.getBrand());
//        });
//
//        // Add columns to the table
//        accessoryTable.getColumns().addAll(nameCol, priceCol, qtyCol, brandCol);
//
//        // Populate the table with the static allAccessoriesArrayList
//        accessoryTable.setItems(FXCollections.observableArrayList(Main.allaccessoriesArrayList));  // Use the correct list for accessories
//
//        // Add the table to the VBox
//        tableVbox.getChildren().add(accessoryTable);
//    }
//
//    private void addItems(){
//        Items i = new Items();
//        i.generateId();
//        int idd = i.getId();
//        // Clear the VBox to set up a new layout
//        tableVbox.getChildren().clear();
//        radioPane.getChildren().clear();
//
//        GridPane gp = new GridPane();
//        gp.setPadding(new Insets(10));
//        gp.setHgap(10);
//        gp.setVgap(10);
//        gp.setStyle("-fx-background-color: #f4f4f4;"); // Add a background color to the GridPane
//
//        // Radio buttons for categories
//        RadioButton phones = new RadioButton("Phone");
//        RadioButton laptops = new RadioButton("Laptop");
//        RadioButton accessories = new RadioButton("Accessories");
//
//        ToggleGroup group = new ToggleGroup();
//        phones.setToggleGroup(group);
//        laptops.setToggleGroup(group);
//        accessories.setToggleGroup(group);
//
//        // Put radio buttons in a HBox container
//        HBox radioButtonContainer = new HBox(20, phones, laptops, accessories);
//        radioButtonContainer.setAlignment(Pos.CENTER);
//        radioButtonContainer.setStyle(
//                "-fx-background-color: white; " +
//                        "-fx-padding: 10px; " +
//                        "-fx-border-color: #ef355b; " +
//                        "-fx-border-width: 2px; " +
//                        "-fx-border-radius: 10px;"
//        );
//
//        pt.setPromptText("Enter Price");
//        nt.setPromptText("Enter Item Name");
//        qt.setPromptText("Enter Quantity");
//        bt.setPromptText("Enter Brand");
//        st.setPromptText("Enter Storage");
//        rt.setPromptText("Enter RAM");
//        it.setPromptText("Enter Image URL");
//        ct.setPromptText("Enter CPU (For Laptop)");
//
//        // Corrected file extensions list
//        String[] comboList = {"png", "jpeg", "jpg", "gif"};
//        ComboBox<String> combo = new ComboBox<>();
//
//        // Set the placeholder text
//        combo.setPromptText("File Type");
//
//        ObservableList<String> items = FXCollections.observableArrayList(comboList);
//        combo.getItems().addAll(items);
//
//        // Optional: Set a default selection
//        combo.setValue("png");
//
//        // Create GridPane for image URL and file type
//        GridPane gp2 = new GridPane();
//        gp2.setHgap(10); // Add some horizontal spacing between components
//        gp2.setAlignment(Pos.CENTER_LEFT); // Align components to the left
//
//        // Adjust the column constraints to make components more uniform
//        ColumnConstraints col1 = new ColumnConstraints();
//        col1.setHgrow(Priority.SOMETIMES);
//        ColumnConstraints col2 = new ColumnConstraints();
//        col2.setHgrow(Priority.ALWAYS);
//        ColumnConstraints col3 = new ColumnConstraints();
//        col3.setHgrow(Priority.SOMETIMES);
//        gp2.getColumnConstraints().addAll(col1, col2, col3);
//
//        // Style the ComboBox to match TextField height
//        combo.setStyle(
//                "-fx-background-color: white; " +
//                        "-fx-border-color: #ef355b; " +
//                        "-fx-border-width: 1px; " +
//                        "-fx-border-radius: 5px; " +
//                        "-fx-font-size: 12px; " +
//                        "-fx-pref-height: 25px;" // Match typical TextField height
//        );
//
//        // Make TextField and other properties consistent
//        it.setPrefHeight(25);
//
//        // Add components to gp2
//        gp2.add(il, 0, 0);
//        gp2.add(it, 1, 0);
//        gp2.add(combo, 2, 0);
//
//        // Add fields to the grid pane
//        gp.add(nl, 0, 1);
//        gp.add(nt, 1, 1);
//        gp.add(p, 0, 2);
//        gp.add(pt, 1, 2);
//        gp.add(q, 0, 3);
//        gp.add(qt, 1, 3);
//        gp.add(b, 0, 4);
//        gp.add(bt, 1, 4);
//        gp.add(s, 0, 5);
//        gp.add(st, 1, 5);
//        gp.add(r, 0, 6);
//        gp.add(rt, 1, 6);
//        gp.add(gp2, 0, 7, 2, 1); // Span across two columns
//
//        // Add CPU field for laptops
//        gp.add(cl, 0, 8);
//        gp.add(ct, 1, 8);
//        cl.setVisible(false);
//        ct.setVisible(false);
//
//        // Visibility handling for category-specific fields
//        phones.setOnAction(e -> {
//            s.setVisible(true);
//            st.setVisible(true);
//            r.setVisible(true);
//            rt.setVisible(true);
//            cl.setVisible(false);
//            ct.setVisible(false);
//
//            // Remove and re-add gp2 (image URL row) to its original position
//            gp.getChildren().remove(gp2);
//            gp.add(gp2, 0, 7, 2, 1);
//        });
//
//        laptops.setOnAction(e -> {
//            s.setVisible(true);
//            st.setVisible(true);
//            r.setVisible(true);
//            rt.setVisible(true);
//            cl.setVisible(true);
//            ct.setVisible(true);
//
//            // Remove and re-add gp2 (image URL row) after CPU field
//            gp.getChildren().remove(gp2);
//            gp.add(gp2, 0, 9, 2, 1);
//        });
//
//        accessories.setOnAction(e -> {
//            s.setVisible(false);
//            st.setVisible(false);
//            r.setVisible(false);
//            rt.setVisible(false);
//            cl.setVisible(false);
//            ct.setVisible(false);
//
//            // Remove and re-add gp2 (image URL row) directly under brand
//            gp.getChildren().remove(gp2);
//            gp.add(gp2, 0, 5, 2, 1);
//        });
//
//        // Add Button to submit items
//        Button submitButton = new Button("Add Item");
//        submitButton.setStyle("-fx-background-color: #ef355b; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px;");
//
//        submitButton.setOnAction(e -> {
//            try {
//                String name = nt.getText().trim();
//                String brand = bt.getText().trim();
//                String imageUrl = it.getText().trim();
//                int price = Integer.parseInt(pt.getText().trim());
//                int quantity = Integer.parseInt(qt.getText().trim());
//
//                String fileExtension = combo.getValue();
//                String path = "file:C:\\Users\\USER\\Desktop\\mainphoto\\" + imageUrl + "." + fileExtension;
//                Image image = new Image(path);
//
//                // First, insert the main item details
//                db.insertItemsIntoDatabase(idd, name, price, brand, imageUrl, quantity);
//
//                // Then insert specific product details based on selection
//                if (phones.isSelected()) {
//                    int storage = Integer.parseInt(st.getText().trim());
//                    int ram = Integer.parseInt(rt.getText().trim());
//                    db.insertPhonesIntoDatabase(idd, storage, ram);
//                } else if (laptops.isSelected()) {
//                    int storage = Integer.parseInt(st.getText().trim());
//                    int ram = Integer.parseInt(rt.getText().trim());
//                    String cpu = ct.getText().trim();
//                    db.insertLaptopsIntoDatabase(idd, storage, ram, cpu);
//                } else if (accessories.isSelected()) {
//                    db.insertAccIntoDatabase(idd);
//                }
//
//                // Clear all input fields
//                clearInputFields();
//
//                // Optional: Show success message
//                showSuccessAlert("Item added successfully!");
//
//            } catch (NumberFormatException ex) {
//                showErrorAlert("Invalid input: " + ex.getMessage());
//            } catch (Exception ex) {
//                showErrorAlert("Error: " + ex.getMessage());
//            }
//        });
//
//        // Add everything to the main VBox
//        tableVbox.getChildren().addAll(radioButtonContainer, gp, submitButton);
//    }
//
//
//
//    private void clearInputFields() {
//        nt.clear();
//        bt.clear();
//        it.clear();
//        pt.clear();
//        qt.clear();
//        st.clear();
//        rt.clear();
//        ct.clear();
//    }
//
//    private void showSuccessAlert(String message) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Success");
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
//
//    // Helper method to show error alert
//    private void showErrorAlert(String message) {
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("Error");
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
//
//    private void editPrice() {
//        // Clear previous content in the VBox
//        tableVbox.getChildren().clear();
//        radioPane.getChildren().clear();
//
//        // Create RadioButtons for each category
//        RadioButton phones = new RadioButton("Phones");
//        RadioButton laptops = new RadioButton("Laptops");
//        RadioButton accessories = new RadioButton("Accessories");
//
//        // Group the radio buttons
//        ToggleGroup categoryGroup = new ToggleGroup();
//        phones.setToggleGroup(categoryGroup);
//        laptops.setToggleGroup(categoryGroup);
//        accessories.setToggleGroup(categoryGroup);
//
//        // Create TextFields and Labels for price editing
//        Label newPriceLabel = new Label("New Price:");
//        TextField newPriceField = new TextField();
//        newPriceField.setPromptText("Enter new price");
//
//        // Create a TableView for Phones
//        TableView<Phones> phoneTable = new TableView<>();
//        TableColumn<Phones, String> phoneNameCol = new TableColumn<>("Phone Name");
//        phoneNameCol.setCellValueFactory(cellData ->
//                new SimpleStringProperty(cellData.getValue().getName()));
//
//        TableColumn<Phones, Double> phonePriceCol = new TableColumn<>("Price");
//        phonePriceCol.setCellValueFactory(cellData ->
//                new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
//
//        phoneTable.getColumns().addAll(phoneNameCol, phonePriceCol);
//
//        // Create a TableView for Laptops
//        TableView<Laptops> laptopTable = new TableView<>();
//        TableColumn<Laptops, String> laptopNameCol = new TableColumn<>("Laptop Name");
//        laptopNameCol.setCellValueFactory(cellData ->
//                new SimpleStringProperty(cellData.getValue().getName()));
//
//        TableColumn<Laptops, Double> laptopPriceCol = new TableColumn<>("Price");
//        laptopPriceCol.setCellValueFactory(cellData ->
//                new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
//
//        laptopTable.getColumns().addAll(laptopNameCol, laptopPriceCol);
//
//        // Create a TableView for Accessories
//        TableView<Accessories> accessoryTable = new TableView<>();
//        TableColumn<Accessories, String> accessoryNameCol = new TableColumn<>("Accessory Name");
//        accessoryNameCol.setCellValueFactory(cellData ->
//                new SimpleStringProperty(cellData.getValue().getName()));
//
//        TableColumn<Accessories, Double> accessoryPriceCol = new TableColumn<>("Price");
//        accessoryPriceCol.setCellValueFactory(cellData ->
//                new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
//
//        accessoryTable.getColumns().addAll(accessoryNameCol, accessoryPriceCol);
//
//        // Initially hide all tables
//        phoneTable.setVisible(false);
//        laptopTable.setVisible(false);
//        accessoryTable.setVisible(false);
//
//        // Set up action for radio buttons to show/populate the appropriate table
//        phones.setOnAction(e -> {
//            phoneTable.setItems(FXCollections.observableArrayList(Main.allphonesArrayList));
//            phoneTable.setVisible(true);
//            laptopTable.setVisible(false);
//            accessoryTable.setVisible(false);
//        });
//
//        laptops.setOnAction(e -> {
//            laptopTable.setItems(FXCollections.observableArrayList(Main.alllaptopsArrayList));
//            phoneTable.setVisible(false);
//            laptopTable.setVisible(true);
//            accessoryTable.setVisible(false);
//        });
//
//        accessories.setOnAction(e -> {
//            accessoryTable.setItems(FXCollections.observableArrayList(Main.allaccessoriesArrayList));
//            phoneTable.setVisible(false);
//            laptopTable.setVisible(false);
//            accessoryTable.setVisible(true);
//        });
//
//        // Create a button to change the price
//        Button changePriceButton = new Button("Change Price");
//        changePriceButton.setStyle("-fx-background-color: #ef355b; -fx-text-fill: white;");
//        changePriceButton.setOnAction(e -> {
//            // Determine which table is currently visible
//            Object selectedItem = null;
//            if (phoneTable.isVisible()) {
//                selectedItem = phoneTable.getSelectionModel().getSelectedItem();
//            } else if (laptopTable.isVisible()) {
//                selectedItem = laptopTable.getSelectionModel().getSelectedItem();
//            } else if (accessoryTable.isVisible()) {
//                selectedItem = accessoryTable.getSelectionModel().getSelectedItem();
//            }
//
//            if (selectedItem == null) {
//                showAlert("Please select an item to change its price.");
//                return;
//            }
//
//            try {
//                double newPrice = Double.parseDouble(newPriceField.getText());
//
//                if (selectedItem instanceof Phones) {
//                    ((Phones) selectedItem).setPrice(newPrice);
//                    phoneTable.refresh();
//                    // Call updatePrice function to update the price in the database
//                    db.updateItemPriceAdmin(newPrice, ((Phones) selectedItem).getName());
//                } else if (selectedItem instanceof Laptops) {
//                    ((Laptops) selectedItem).setPrice(newPrice);
//                    laptopTable.refresh();
//                    // Call updatePrice function to update the price in the database
//                    db.updateItemPriceAdmin(newPrice, ((Laptops) selectedItem).getName());
//                } else if (selectedItem instanceof Accessories) {
//                    ((Accessories) selectedItem).setPrice(newPrice);
//                    accessoryTable.refresh();
//                    // Call updatePrice function to update the price in the database
//                    db.updateItemPriceAdmin(newPrice, ((Accessories) selectedItem).getName());
//                }
//
//                showAlert("Price updated successfully!");
//            } catch (NumberFormatException ex) {
//                showAlert("Invalid price. Please enter a valid number.");
//            }
//        });
//
//        // Create a HBox for radio buttons and price editing
//        HBox controlPane = new HBox(20);
//        controlPane.setAlignment(Pos.CENTER);
//        controlPane.setPadding(new Insets(10));
//        controlPane.getChildren().addAll(phones, laptops, accessories, newPriceLabel, newPriceField, changePriceButton);
//
//        // Add components to the layout
//        tableVbox.getChildren().addAll(controlPane, phoneTable, laptopTable, accessoryTable);
//    }
//
//
//    private void deleteItem() {
//        tableVbox.getChildren().clear();
//        radioPane.getChildren().clear();
//
//        // Create RadioButtons for each category
//        RadioButton phones = new RadioButton("Phones");
//        RadioButton laptops = new RadioButton("Laptops");
//        RadioButton accessories = new RadioButton("Accessories");
//
//        // Group the radio buttons
//        ToggleGroup categoryGroup = new ToggleGroup();
//        phones.setToggleGroup(categoryGroup);
//        laptops.setToggleGroup(categoryGroup);
//        accessories.setToggleGroup(categoryGroup);
//
//        // Create Delete Button
//        Button deleteButton = new Button("Delete Selected Item");
//        deleteButton.setDisable(true); // Initially disabled
//
//        // Create a TableView for Phones
//        TableView<Phones> phoneTable = new TableView<>();
//        TableColumn<Phones, String> phoneNameCol = new TableColumn<>("Phone Name");
//        phoneNameCol.setCellValueFactory(cellData ->
//                new SimpleStringProperty(cellData.getValue().getName()));
//
//        TableColumn<Phones, Double> phonePriceCol = new TableColumn<>("Price");
//        phonePriceCol.setCellValueFactory(cellData ->
//                new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
//
//        phoneTable.getColumns().addAll(phoneNameCol, phonePriceCol);
//
//        // Create a TableView for Laptops
//        TableView<Laptops> laptopTable = new TableView<>();
//        TableColumn<Laptops, String> laptopNameCol = new TableColumn<>("Laptop Name");
//        laptopNameCol.setCellValueFactory(cellData ->
//                new SimpleStringProperty(cellData.getValue().getName()));
//
//        TableColumn<Laptops, Double> laptopPriceCol = new TableColumn<>("Price");
//        laptopPriceCol.setCellValueFactory(cellData ->
//                new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
//
//        laptopTable.getColumns().addAll(laptopNameCol, laptopPriceCol);
//
//        // Create a TableView for Accessories
//        TableView<Accessories> accessoryTable = new TableView<>();
//        TableColumn<Accessories, String> accessoryNameCol = new TableColumn<>("Accessory Name");
//        accessoryNameCol.setCellValueFactory(cellData ->
//                new SimpleStringProperty(cellData.getValue().getName()));
//
//        TableColumn<Accessories, Double> accessoryPriceCol = new TableColumn<>("Price");
//        accessoryPriceCol.setCellValueFactory(cellData ->
//                new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
//
//        accessoryTable.getColumns().addAll(accessoryNameCol, accessoryPriceCol);
//
//        // Initially hide all tables
//        phoneTable.setVisible(false);
//        laptopTable.setVisible(false);
//        accessoryTable.setVisible(false);
//
//        // Set up selection listeners to enable/disable delete button
//        phoneTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
//            deleteButton.setDisable(newSelection == null);
//        });
//
//        laptopTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
//            deleteButton.setDisable(newSelection == null);
//        });
//
//        accessoryTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
//            deleteButton.setDisable(newSelection == null);
//        });
//
//        // Set up action for radio buttons to show/populate the appropriate table
//        phones.setOnAction(e -> {
//            phoneTable.setItems(FXCollections.observableArrayList(Main.allphonesArrayList));
//            phoneTable.setVisible(true);
//            laptopTable.setVisible(false);
//            accessoryTable.setVisible(false);
//            deleteButton.setDisable(true);
//        });
//
//        laptops.setOnAction(e -> {
//            laptopTable.setItems(FXCollections.observableArrayList(Main.alllaptopsArrayList));
//            phoneTable.setVisible(false);
//            laptopTable.setVisible(true);
//            accessoryTable.setVisible(false);
//            deleteButton.setDisable(true);
//        });
//
//        accessories.setOnAction(e -> {
//            accessoryTable.setItems(FXCollections.observableArrayList(Main.allaccessoriesArrayList));
//            phoneTable.setVisible(false);
//            laptopTable.setVisible(false);
//            accessoryTable.setVisible(true);
//            deleteButton.setDisable(true);
//        });
//
//        // Set up delete button action
//        deleteButton.setOnAction(e -> {
//            if (phones.isSelected()) {
//                Phones selectedPhone = phoneTable.getSelectionModel().getSelectedItem();
//                if (selectedPhone != null) {
//                    db.deleteItemFromDatabase(selectedPhone.getId(), "Phones"); // Call delete function
//                    Main.allphonesArrayList.remove(selectedPhone);
//                    phoneTable.setItems(FXCollections.observableArrayList(Main.allphonesArrayList));
//                }
//            } else if (laptops.isSelected()) {
//                Laptops selectedLaptop = laptopTable.getSelectionModel().getSelectedItem();
//                if (selectedLaptop != null) {
//                    db.deleteItemFromDatabase(selectedLaptop.getId(), "Laptops"); // Call delete function
//                    Main.alllaptopsArrayList.remove(selectedLaptop);
//                    laptopTable.setItems(FXCollections.observableArrayList(Main.alllaptopsArrayList));
//                }
//            } else if (accessories.isSelected()) {
//                Accessories selectedAccessory = accessoryTable.getSelectionModel().getSelectedItem();
//                if (selectedAccessory != null) {
//                    db.deleteItemFromDatabase(selectedAccessory.getId(), "Accessories"); // Call delete function
//                    Main.allaccessoriesArrayList.remove(selectedAccessory);
//                    accessoryTable.setItems(FXCollections.observableArrayList(Main.allaccessoriesArrayList));
//                }
//            }
//        });
//
//        // Add components to radioPane
//        radioPane.getChildren().addAll(phones, laptops, accessories, deleteButton);
//
//        // Add tables to tableVbox
//        tableVbox.getChildren().addAll(phoneTable, laptopTable, accessoryTable);
//    }
//
//
//
//
//    // Helper method to show alerts
//    private void showAlert(String message) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Price Change");
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
//
//}
