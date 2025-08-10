package com.example.financetracker.controller;

import com.example.financetracker.database.DatabaseManager;
import com.example.financetracker.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import com.example.financetracker.database.TransactionsLoader;
import javafx.scene.layout.StackPane;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class DeleteTransactionController {
    @FXML private ChoiceBox<String> fieldChoiceBox;
    @FXML private TextField valueField;
    @FXML private TableView<Transaction> resultTable;
    @FXML private TableColumn<Transaction,Double> amountColumn;
    @FXML private TableColumn<Transaction, LocalDate> dateColumn;
    @FXML private TableColumn<Transaction, CategoryType> categoryColumn;
    @FXML private TableColumn<Transaction, TransactionType> typeColumn;
    @FXML private TableColumn<Transaction,String> messageColumn;

    @FXML private StackPane background_delete;

    private ObservableList<Transaction> transactionList=FXCollections.observableArrayList();
    private ObservableList<Transaction> filteredList= FXCollections.observableArrayList();

    public void setTransactionList(){
        TransactionsLoader.loadFromDatabase(transactionList);
        resultTable.setItems(filteredList);
    }

    public void initialize(){
        fieldChoiceBox.setItems(FXCollections.observableArrayList("Amount","Date","Category","Type"));
        fieldChoiceBox.setValue("Amount");

        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        messageColumn.setCellValueFactory(new PropertyValueFactory<>("message"));

        resultTable.setItems(transactionList);

        String imgURL=getClass().getResource("/media/template2.jpg").toExternalForm();
        background_delete.setStyle("-fx-background-image: url('"+imgURL+"');" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-size: cover;" +
                "-fx-background-position: center;");
    }

    @FXML
    private void handleSearchTransaction(){
        int ok=0;
        String field=fieldChoiceBox.getValue();
        String value= valueField.getText().trim();

        filteredList.clear();
        for(Transaction t: transactionList){
            switch(field){
                case "Amount": {
                    if (Double.toString(t.getAmount()).equals(value)){
                        ok=1;
                        filteredList.add(t);
                    }
                    break;
                }
                case "Date":{
                    if(t.getDate().equals(LocalDate.parse(value))) {
                        ok = 1;
                        filteredList.add(t);
                    }
                    break;
                }
                case "Category":{
                    try {
                        CategoryType categoryType = CategoryType.valueOf(value.trim().toUpperCase());
                        if (t.getCategory().equals(categoryType)) {
                            ok = 1;
                            filteredList.add(t);
                        }
                    }catch(IllegalArgumentException e){
                        showAlert("Invalid transaction category");
                    }
                    break;
                }
                case "Type":{
                    try{
                        TransactionType transactionType=TransactionType.valueOf(value.trim().toUpperCase());
                        if(t.getType().equals(transactionType)){
                            ok = 1;
                            filteredList.add(t);
                        }
                    }catch(IllegalArgumentException e){
                        showAlert("Invalid transaction type");
                    }
                    break;
                }
            }
        }
        if(ok==0)
            showAlert("Transaction Not Found!");
    }

    @FXML
    private void handleDelete(){
        Transaction selected=resultTable.getSelectionModel().getSelectedItem();
        if(selected==null){
            showAlert("Please select a transaction!");
            return;
        }

        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
                getClass().getResource("/style/alert.css").toExternalForm()
        );
        alert.setTitle("Confirm Delete");
        alert.setHeaderText("Are you sure you want to delete this transaction?");
        alert.setContentText("This action cannot be undone!");

        ButtonType yesButton=new ButtonType("Yes",ButtonBar.ButtonData.YES);
        ButtonType noButton=new ButtonType("No",ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(yesButton,noButton);

        Optional <ButtonType> result=alert.showAndWait();
        if(selected!=null && result.isPresent() && result.get()==yesButton){
            try {
                DatabaseManager.deleteTransaction(selected);
                transactionList.remove(selected);
                filteredList.remove(selected);
                showAlert("Transaction deleted successfully!");
            } catch(SQLException e){
                showAlert("Error deleting from database: "+e.getMessage());
            }
        } else showAlert("Action cancelled!");
    }

    private void showAlert(String message){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/style/alert.css").toExternalForm());
        alert.showAndWait();
    }

    @FXML
    private void handleBacktoMenu() {
        SceneManager.BackToMenu(resultTable.getScene());
    }
}
