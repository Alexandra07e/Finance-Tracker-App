package com.example.financetracker.controller;

import com.example.financetracker.database.DatabaseManager;
import com.example.financetracker.database.TransactionsLoader;
import com.example.financetracker.model.CategoryType;
import com.example.financetracker.model.Transaction;
import com.example.financetracker.model.TransactionType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class ModifyTransactionController {
    @FXML private TableView<Transaction> modifyTable;
    @FXML private TableColumn<Transaction,Double> amountColumn;
    @FXML private TableColumn<Transaction, LocalDate> dateColumn;
    @FXML private TableColumn<Transaction, CategoryType> categoryColumn;
    @FXML private TableColumn<Transaction, TransactionType> typeColumn;
    @FXML private TableColumn<Transaction,String> messageColumn;

    @FXML private VBox editForm;
    @FXML private TextField amountField;
    @FXML private DatePicker datePicker;
    @FXML private ComboBox<CategoryType> categoryBox;
    @FXML private ComboBox<TransactionType> typeBox;
    @FXML private TextArea messageArea;

    @FXML private StackPane background_update;

    private ObservableList<Transaction> transactionList= FXCollections.observableArrayList();
    private Transaction selectedTransaction;

    public void setTransactionList(){
        modifyTable.setItems(transactionList);
        TransactionsLoader.loadFromDatabase(transactionList);
    }

    @FXML
    public void initialize(){
        categoryBox.getItems().addAll(CategoryType.INCOME,CategoryType.EXPENSES,CategoryType.DEBTS,CategoryType.HEALTH,CategoryType.EDUCATION,CategoryType.OCCASIONS,CategoryType.RECREATION,CategoryType.SAVINGS);
        typeBox.getItems().addAll(TransactionType.EXPENSE,TransactionType.INCOME);
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        messageColumn.setCellValueFactory(new PropertyValueFactory<>("message"));

        String imgURL=getClass().getResource("/media/template2.jpg").toExternalForm();
        background_update.setStyle("-fx-background-image: url('"+imgURL+"');" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-size: cover;" +
                "-fx-background-position: center;");
    }

    @FXML
    private void handleSelectTransaction(){
        Transaction selected=modifyTable.getSelectionModel().getSelectedItem();
        if(selected==null){
            showAlert("Please select a transaction!");
            return;
        }

        editForm.setVisible(true);
        editForm.setManaged(true);
        amountField.setText(String.valueOf(selected.getAmount()));
        datePicker.setValue(selected.getDate());
        categoryBox.setValue(selected.getCategory());
        typeBox.setValue(selected.getType());
        messageArea.setText(selected.getMessage());

        selectedTransaction=selected;
    }

    @FXML
    private void handleUpdateTransaction() {
        if (selectedTransaction == null)
            return;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
                getClass().getResource("/style/alert.css").toExternalForm()
        );
        alert.setTitle("Confirm Update");
        alert.setHeaderText("Are you sure you want to update this transaction?");
        alert.setContentText("This action cannot be undone!");

        ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (selectedTransaction != null && result.isPresent() && result.get() == yesButton) {
            try {
                Transaction oldTransaction=new Transaction(selectedTransaction.getAmount(),selectedTransaction.getDate(),selectedTransaction.getMessage(),selectedTransaction.getType(),selectedTransaction.getCategory());
                selectedTransaction.setAmount(Double.parseDouble(amountField.getText()));
                selectedTransaction.setDate(datePicker.getValue());
                selectedTransaction.setCategory(categoryBox.getValue());
                selectedTransaction.setType(typeBox.getValue());
                selectedTransaction.setMessage(messageArea.getText());

                DatabaseManager.updateTransaction(oldTransaction,selectedTransaction);
                modifyTable.refresh();
                showAlert("Transaction Updated Successfully!");
            } catch (SQLException e) {
                System.out.println("Error updating the database: " + e.getMessage());
            }
        }
    }

    @FXML
    private void handleBacktoMenu(){
        SceneManager.BackToMenu(modifyTable.getScene());
    }

    private void showAlert(String message){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
                getClass().getResource("/style/alert.css").toExternalForm()
        );
        alert.showAndWait();
    }

}
