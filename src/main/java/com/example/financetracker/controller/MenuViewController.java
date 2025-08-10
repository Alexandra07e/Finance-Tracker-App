package com.example.financetracker.controller;

import com.example.financetracker.model.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class MenuViewController {
    @FXML private Button addTransactionButton;
    @FXML private Button viewTransactionsButton;
    @FXML private Button deleteTransactionButton;
    @FXML private Button modifyTransactionButton;
    @FXML private ImageView sideImage;
    @FXML private StackPane menu_background;

    @FXML
    public void initialize(){
        Image img=new Image(getClass().getResourceAsStream("/media/side2.png"));
        sideImage.setImage(img);
        sideImage.setPreserveRatio(true);
        sideImage.setSmooth(true);
        String imgURL=getClass().getResource("/media/template2.jpg").toExternalForm();
        menu_background.setStyle("-fx-background-image: url('"+imgURL+"');" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-size: cover;" +
                "-fx-background-position: center;");
    }

    @FXML
    public void handleAddTransaction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/add-transaction.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Add Transaction");
            stage.setScene(scene);
            stage.setMaximized(true);  // AICI: fereastra pe tot ecranul
            stage.show();
            ((Stage) addTransactionButton.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleViewTransactions() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/view-transactions.fxml"));
            Parent root = loader.load();
            ViewTransactionsController controller = loader.getController();
            controller.setTransactionList();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("View All Transactions");
            stage.setScene(scene);
            stage.setMaximized(true);  // AICI: fereastra pe tot ecranul
            stage.show();
            ((Stage) viewTransactionsButton.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleDeleteTransaction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/delete-transaction.fxml"));
            Parent root = loader.load();
            DeleteTransactionController controller = loader.getController();
            controller.setTransactionList();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Delete Transaction");
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
            ((Stage) deleteTransactionButton.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleModifyTransaction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/update-transaction.fxml"));
            Parent root=loader.load();
            ModifyTransactionController controller=loader.getController();
            controller.setTransactionList();
            Stage stage=new Stage();
            Scene scene=new Scene(root);
            stage.setTitle("Update Transaction");
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
            ((Stage) modifyTransactionButton.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
