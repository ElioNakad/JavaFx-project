package com.example.project;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;

import java.sql.*;

import static com.example.project.LogIn.allClientsArrayList;
import static com.example.project.Main.allphonesArrayList;

public class DataBase {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/project_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public DataBase() {

    }

    protected void insertClientIntoDatabase(String firstName, String lastName, String email, String password) {
        String query = "INSERT INTO client (fname , lname , email , pass) VALUES (?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);


            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                showAlert("Success", "Client added successfully!");
            } else {
                System.out.println("\nnot acc");
            }
        } catch (Exception e) {
            showAlert("Database Error", "Error inserting data nayyik");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    protected void selectClientsFromDatabase() {
        String query = "SELECT * FROM client";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("Email\tFirstName\tLastName\tPassword");
            System.out.println("------------------------------------------");

            while (resultSet.next()) {
                String email = resultSet.getString("email");
                String name = resultSet.getString("fname");
                String lname = resultSet.getString("lname");
                String password = resultSet.getString("pass");

                System.out.printf("%s\t%s\t\t%s\t\t%s%n", email, name, lname, password);

                Clients Cl = new Clients(name, lname, email, password);
                allClientsArrayList.add(Cl);

            }

        } catch (Exception e) {
            showAlert("Database Error", "Error fetching data: " + e.getMessage());
        }
    }


    protected void selectPhonesFromDatabase() {
        String query = "SELECT i.*, p.storage, p.RAM " +
                "FROM items i " +
                "INNER JOIN phones p ON i.id = p.id";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Double price = resultSet.getDouble("price");
                String name = resultSet.getString("name");
                int quantityInStock = resultSet.getInt("quantityInStock");
                String brand = resultSet.getString("brand");
                String img = resultSet.getString("img");
                int storage = resultSet.getInt("storage");
                int RAM = resultSet.getInt("RAM");

                ImageView imageView = new ImageView("C:\\Users\\Administrator\\Desktop\\Prog3Images\\" + img);

                Phones p = new Phones(price, name, quantityInStock, brand, imageView, storage, RAM);
                p.setId(id);
                allphonesArrayList.add(p);
            }

        } catch (Exception e) {
            showAlert("Database Error", "Error fetching data: " + e.getMessage());
        }
    }


    protected void insertPhonesToDB(Double price, String name, int quantityInStock, String brand, String img,
                                    int storage, int RAM) {
        String query = "INSERT INTO items (price,name,quantityInStock,brand,img) VALUES (?,?,?,?,?)";
        String query2 = "INSERT INTO phones (id,storage,RAM) VALUES (?,?,?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            // First insert into items
            preparedStatement.setDouble(1, price);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, quantityInStock);
            preparedStatement.setString(4, brand);
            preparedStatement.setString(5, img);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                // Get the ID that was just generated for the items table
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);

                    // Now insert into phones using the same ID
                    try (PreparedStatement phoneStatement = connection.prepareStatement(query2)) {
                        phoneStatement.setInt(1, generatedId);
                        phoneStatement.setInt(2, storage);
                        phoneStatement.setInt(3, RAM);

                        phoneStatement.executeUpdate();
                        showAlert("Success", "Phone added successfully!");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // This will help with debugging
            showAlert("Database Error", "Error inserting phone: " + e.getMessage());
        }
    }


    protected void updateItemPriceAdmin(double newPrice, String itemName) {
        String query = "UPDATE items SET price = ? WHERE name = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set parameters for the query
            preparedStatement.setDouble(1, newPrice);  // Set the new price
            preparedStatement.setString(2, itemName);  // Match the item by name

            // Execute the update query
            int rowsAffected = preparedStatement.executeUpdate();

            // Check if the item was found and updated
            if (rowsAffected > 0) {
                System.out.println("Price updated successfully for item: " + itemName);
            } else {
                System.out.println("No item found with the name: " + itemName);
            }

        } catch (SQLException e) {
            showAlert("Database Error", "Error updating price: " + e.getMessage());
        }
    }

    protected void deleteClient(String email) {
        String query = "DELETE FROM client WHERE email=?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);

            // Execute the update query
            int rowsAffected = preparedStatement.executeUpdate();

            // Check if the item was found and updated
            if (rowsAffected > 0) {
                System.out.println("Client who has been deleted: " + email);
            } else {
                System.out.println("No client found with the name: " + email);
            }

        } catch (SQLException e) {
            showAlert("Database Error", "Error updating price: " + e.getMessage());
        }
    }

    protected void deletePhone(String name) {
        String queryFindId = "SELECT id FROM items WHERE name=?";
        String queryDeletePhone = "DELETE FROM phones WHERE id=?";
        String queryDeleteItem = "DELETE FROM items WHERE id=?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // First find the ID of the phone
            try (PreparedStatement findStatement = connection.prepareStatement(queryFindId)) {
                findStatement.setString(1, name);
                ResultSet rs = findStatement.executeQuery();

                if (rs.next()) {
                    int id = rs.getInt("id");

                    // Delete from phones first (due to foreign key constraint)
                    try (PreparedStatement phoneStatement = connection.prepareStatement(queryDeletePhone)) {
                        phoneStatement.setInt(1, id);
                        phoneStatement.executeUpdate();

                        // Then delete from items
                        try (PreparedStatement itemStatement = connection.prepareStatement(queryDeleteItem)) {
                            itemStatement.setInt(1, id);
                            int rowsAffected = itemStatement.executeUpdate();

                            if (rowsAffected > 0) {
                                System.out.println("Phone deleted successfully: " + name);
                            }
                        }
                    }
                } else {
                    System.out.println("No phone found with name: " + name);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Database Error", "Error deleting phone: " + e.getMessage());
        }
    }

    protected void updateStockItems(int quantityToDeduct, String itemName) {
        String query = "UPDATE items SET quantityInStock = quantityInStock - ? WHERE name = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set parameters for the query
            preparedStatement.setInt(1, quantityToDeduct); // Deduct this quantity
            preparedStatement.setString(2, itemName);     // Match this item name

            // Execute the update query
            int rowsAffected = preparedStatement.executeUpdate();

            // Check if the item was found and updated
            if (rowsAffected > 0) {
                System.out.println("Stock updated successfully for item: " + itemName);
            } else {
                System.out.println("No item found with the name: " + itemName);
            }

        } catch (SQLException e) {
            showAlert("Database Error", "Error u");

        }
    }
}
