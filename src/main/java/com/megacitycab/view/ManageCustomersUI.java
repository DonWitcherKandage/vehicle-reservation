//package com.megacitycab.view;
//
//import com.megacitycab.controller.CustomerController;
//import com.megacitycab.model.Customer;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.geometry.Insets;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//public class ManageCustomersUI {
//    private final Stage previousStage;
//    private final CustomerController customerController = new CustomerController();
//    private final ObservableList<Customer> customers = FXCollections.observableArrayList();
//    private TableView<Customer> customerTable;
//
//    public ManageCustomersUI(Stage previousStage) {
//        this.previousStage = previousStage;
//    }
//
//    public void start(Stage primaryStage) {
//        primaryStage.setTitle("Manage Customers");
//
//
//        Button backBtn = new Button("⬅ Back");
//        backBtn.setStyle("-fx-background-color: #666; -fx-text-fill: white;");
//        backBtn.setOnAction(e -> {
//            primaryStage.close();
//            previousStage.show(); // Return to manager dashboard
//        });
//
//        // Table for displaying customers
//        customerTable = new TableView<>();
//        customerTable.setItems(customers);
//
//        TableColumn<Customer, Integer> idColumn = new TableColumn<>("ID");
//        idColumn.setCellValueFactory(cellData -> cellData.getValue().userIdProperty().asObject());
//
//        TableColumn<Customer, String> usernameColumn = new TableColumn<>("Username");
//        usernameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
//
//        TableColumn<Customer, String> addressColumn = new TableColumn<>("Address");
//        addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
//
//        TableColumn<Customer, String> nicColumn = new TableColumn<>("NIC");
//        nicColumn.setCellValueFactory(cellData -> cellData.getValue().nicProperty());
//
//        TableColumn<Customer, String> phoneNumberColumn = new TableColumn<>("Phone Number");
//        phoneNumberColumn.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
//
//        customerTable.getColumns().addAll(idColumn, usernameColumn, addressColumn, nicColumn, phoneNumberColumn);
//
//        // Form for adding and updating customers
//        GridPane form = new GridPane();
//        form.setPadding(new Insets(10));
//        form.setHgap(10);
//        form.setVgap(10);
//
//        TextField idField = new TextField();
//        idField.setPromptText("Customer ID");
//
//        TextField usernameField = new TextField();
//        usernameField.setPromptText("Username");
//
//        TextField passwordField = new TextField();
//        passwordField.setPromptText("Password");
//
//        TextField addressField = new TextField();
//        addressField.setPromptText("Address");
//
//        TextField nicField = new TextField();
//        nicField.setPromptText("NIC");
//
//        TextField phoneNumberField = new TextField();
//        phoneNumberField.setPromptText("Phone Number");
//
//        Button addBtn = new Button("Add Customer");
//        addBtn.setOnAction(e -> {
//            if (idField.getText().isEmpty() || usernameField.getText().isEmpty() || passwordField.getText().isEmpty() ||
//                    addressField.getText().isEmpty() || nicField.getText().isEmpty() || phoneNumberField.getText().isEmpty()) {
//                showAlert(Alert.AlertType.WARNING, "Warning", "Please fill in all fields.");
//                return;
//            }
//
//            int userId = Integer.parseInt(idField.getText());
//            String username = usernameField.getText();
//            String password = passwordField.getText();
//            String address = addressField.getText();
//            String nic = nicField.getText();
//            String phoneNumber = phoneNumberField.getText();
//            if (customerController.addCustomer(userId, username, password, address, nic, phoneNumber)) {
//                showAlert(Alert.AlertType.INFORMATION, "Success", "Customer added successfully!");
//                refreshCustomers();
//                clearForm(idField, usernameField, passwordField, addressField, nicField, phoneNumberField);
//            } else {
//                showAlert(Alert.AlertType.ERROR, "Error", "Failed to add customer.");
//            }
//        });
//
//        Button updateBtn = new Button("Update Customer");
//        updateBtn.setOnAction(e -> {
//            Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
//            if (selectedCustomer == null) {
//                showAlert(Alert.AlertType.WARNING, "Warning", "Select customer first.");
//                return;
//            }
//
//            if (idField.getText().isEmpty() || usernameField.getText().isEmpty() || passwordField.getText().isEmpty() ||
//                    addressField.getText().isEmpty() || nicField.getText().isEmpty() || phoneNumberField.getText().isEmpty()) {
//                showAlert(Alert.AlertType.WARNING, "Warning", "Please fill in all fields.");
//                return;
//            }
//
//            int userId = Integer.parseInt(idField.getText());
//            String username = usernameField.getText();
//            String password = passwordField.getText();
//            String address = addressField.getText();
//            String nic = nicField.getText();
//            String phoneNumber = phoneNumberField.getText();
//            if (customerController.updateCustomer(userId, username, password, address, nic, phoneNumber)) {
//                showAlert(Alert.AlertType.INFORMATION, "Success", "Customer updated successfully!");
//                refreshCustomers();
//                clearForm(idField, usernameField, passwordField, addressField, nicField, phoneNumberField);
//            } else {
//                showAlert(Alert.AlertType.ERROR, "Error", "Failed to update customer.");
//            }
//        });
//
//        Button deleteBtn = new Button("Delete Customer");
//        deleteBtn.setOnAction(e -> {
//            Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
//
//            if (selectedCustomer == null && idField.getText().isEmpty()) {
//                showAlert(Alert.AlertType.WARNING, "Warning", "Select customer first or enter Customer ID.");
//                return;
//            }
//
//            int userId;
//            if (selectedCustomer != null) {
//                userId = selectedCustomer.getUserId();
//            } else {
//                try {
//                    userId = Integer.parseInt(idField.getText());
//                } catch (NumberFormatException ex) {
//                    showAlert(Alert.AlertType.WARNING, "Warning", "Invalid Customer ID.");
//                    return;
//                }
//            }
//
//            if (customerController.deleteCustomer(userId)) {
//                showAlert(Alert.AlertType.INFORMATION, "Success", "Customer deleted successfully!");
//                refreshCustomers();
//                clearForm(idField, usernameField, passwordField, addressField, nicField, phoneNumberField);
//            } else {
//                showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete customer.");
//            }
//        });
//
//        form.add(new Label("Customer ID:"), 0, 0);
//        form.add(idField, 1, 0);
//        form.add(new Label("Username:"), 0, 1);
//        form.add(usernameField, 1, 1);
//        form.add(new Label("Password:"), 0, 2);
//        form.add(passwordField, 1, 2);
//        form.add(new Label("Address:"), 0, 3);
//        form.add(addressField, 1, 3);
//        form.add(new Label("NIC:"), 0, 4);
//        form.add(nicField, 1, 4);
//        form.add(new Label("Phone Number:"), 0, 5);
//        form.add(phoneNumberField, 1, 5);
//        form.add(addBtn, 0, 6);
//        form.add(updateBtn, 1, 6);
//        form.add(deleteBtn, 2, 6);
//
//        VBox layout = new VBox(10, backBtn, customerTable, form);
//        layout.setPadding(new Insets(20));
//
//        primaryStage.setScene(new Scene(layout, 800, 600));
//        primaryStage.show();
//
//        refreshCustomers();
//    }
//
//    private void refreshCustomers() {
//        customers.setAll(customerController.getAllCustomers());
//    }
//
//    private void clearForm(TextField idField, TextField usernameField, TextField passwordField, TextField addressField, TextField nicField, TextField phoneNumberField) {
//        idField.clear();
//        usernameField.clear();
//        passwordField.clear();
//        addressField.clear();
//        nicField.clear();
//        phoneNumberField.clear();
//    }
//
//    private void showAlert(Alert.AlertType alertType, String title, String message) {
//        Alert alert = new Alert(alertType);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
//}