package com.example.financetracker.controller;

import com.example.financetracker.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;
import com.example.financetracker.database.TransactionsLoader;
import javafx.scene.layout.StackPane;

public class ViewTransactionsController {
    @FXML private TableView<Transaction> transactionTable;
    @FXML private TableColumn<Transaction,Double> amountColumn;
    @FXML private TableColumn<Transaction, LocalDate> dateColumn;
    @FXML private TableColumn<Transaction, CategoryType> categoryColumn;
    @FXML private TableColumn<Transaction,TransactionType> typeColumn;
    @FXML private TableColumn<Transaction,String> messageColumn;

    @FXML private StackPane background_view;

    private ObservableList<Transaction> transactionList=FXCollections.observableArrayList();

    public void setTransactionList(){
        transactionTable.setItems(transactionList);
        TransactionsLoader.loadFromDatabase(transactionList);
    }

    @FXML
    public void initialize(){
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        messageColumn.setCellValueFactory(new PropertyValueFactory<>("message"));

        String imgURL=getClass().getResource("/media/template2.jpg").toExternalForm();
        background_view.setStyle("-fx-background-image: url('"+imgURL+"');" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-size: cover;" +
                "-fx-background-position: center;");
    }

    @FXML
    private void handleBacktoMenu(){
        SceneManager.BackToMenu(transactionTable.getScene());
    }
}
