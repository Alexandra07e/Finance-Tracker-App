package com.example.financetracker.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SceneManager {
    public static void BackToMenu(Scene currentScene) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/fxml/menu-view.fxml"));
            Parent root=loader.load();
            Scene scene=new Scene(root);
            Stage stage=(Stage) currentScene.getWindow();
            stage.setTitle("Finance Tracker Menu");
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
