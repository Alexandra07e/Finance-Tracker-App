package com.example.financetracker;

import com.example.financetracker.database.DatabaseManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/start-view.fxml"));
        Scene scene = new Scene(loader.load());

        stage.setTitle("Finance Tracker");
        stage.getIcons().add(new javafx.scene.image.Image(
                getClass().getResourceAsStream("/media/icon.jpg")));

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        DatabaseManager.initializeDatabase();
        launch();
    }
}