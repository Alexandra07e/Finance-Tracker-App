package com.example.financetracker.controller;

import com.example.financetracker.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.*;
import java.sql.*;
import java.time.LocalDate;
import com.example.financetracker.database.*;
import javafx.scene.layout.StackPane;

public class AddTransactionController {

    @FXML private TextField amountField;
    @FXML private TextArea messageArea;
    @FXML private ComboBox<CategoryType> categoryBox;
    @FXML private ComboBox<TransactionType> typeBox;
    @FXML private DatePicker datePicker;
    @FXML private Button addButton;
    @FXML private StackPane background_add;

    private ObservableList<Transaction> transactionList=FXCollections.observableArrayList();

    public void initialize(){
        categoryBox.getItems().addAll(CategoryType.EXPENSES,CategoryType.DEBTS,CategoryType.SAVINGS,CategoryType.EDUCATION,CategoryType.RECREATION,CategoryType.OCCASIONS,CategoryType.HEALTH,CategoryType.INCOME);
        typeBox.getItems().addAll(TransactionType.EXPENSE,TransactionType.INCOME);
        String imgURL=getClass().getResource("/media/template2.jpg").toExternalForm();
        background_add.setStyle("-fx-background-image: url('"+imgURL+"');" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-size: cover;" +
                "-fx-background-position: center;");
    }

    @FXML
    private void handleAddTransaction(){
        try {
            double amount = Double.parseDouble(amountField.getText());
            LocalDate date = datePicker.getValue();
            CategoryType category = categoryBox.getValue();
            TransactionType type = typeBox.getValue();
            String message = messageArea.getText();

            if (date == null || category == null || type == null || message.isEmpty()) {
                showAlert("All fields should be completed!");
                return;
            }

            Transaction transaction = new Transaction(amount, date, message, type, category);
            TransactionsLoader.loadFromDatabase(transactionList);
            for (Transaction t : transactionList) {
                if (t.getAmount() == transaction.getAmount() &&
                        t.getDate().equals(transaction.getDate()) &&
                        t.getCategory() == transaction.getCategory() &&
                        t.getType() == transaction.getType() &&
                        t.getMessage().trim().equalsIgnoreCase(transaction.getMessage().trim())) {
                    showAlert("Transaction already exists!");
                    return;
                }
            }

            String insertSQL="INSERT INTO transactions (amount, date, message, category, type) VALUES (?, ?, ?, ?, ?)";
            try(
                    Connection conn=DatabaseManager.getConnection();
                    PreparedStatement stmt=conn.prepareStatement(insertSQL)
            ){
                stmt.setDouble(1,amount);
                stmt.setString(2,date.toString());
                stmt.setString(3,message);
                stmt.setString(4,category.name());
                stmt.setString(5,type.name());
                showAlert("Transaction added successfully!");
                stmt.executeUpdate();
            } catch(SQLException e){
                showAlert("Error saving in database: "+e.getMessage());
            }
            amountField.clear();
            datePicker.setValue(null);
            categoryBox.setValue(null);
            typeBox.setValue(null);
            messageArea.clear();
        } catch(NumberFormatException e){
            showAlert("Invalid sum!");
        }
    }

    private void showAlert(String message){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/style/alert.css").toExternalForm());
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleBacktoMenu(){
        SceneManager.BackToMenu(addButton.getScene());
    }
}