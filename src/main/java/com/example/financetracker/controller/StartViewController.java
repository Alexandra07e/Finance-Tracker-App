package com.example.financetracker.controller;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.net.URL;

public class StartViewController {
    @FXML private MediaView mediaView;
    @FXML private Text welcomeText;
    @FXML private Text about;
    @FXML private Button addStartButton;
    @FXML private HBox start_background;

    private MediaPlayer mediaPlayer;

    @FXML
    public void initialize(){
        URL videoURL=getClass().getResource("/media/money-falling.mp4");
        Media media=new Media(videoURL.toString());
        mediaPlayer=new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
    }

    @FXML
    public void handleScreenClick(){
        mediaPlayer.stop();
        welcomeText.setOpacity(0);
        welcomeText.setVisible(true);
        about.setVisible(false);
        addStartButton.setVisible(false);

        FadeTransition fadeIn=new FadeTransition(Duration.seconds(1.5),welcomeText);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);

        fadeIn.setOnFinished(e -> {
            // Wait two seconds after it completely pops up
            PauseTransition pause=new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(ev -> {
                // FADE-OUT: slowly fade away in two seconds
                FadeTransition fadeOut=new FadeTransition(Duration.seconds(1.5),welcomeText);
                fadeOut.setFromValue(1.0);
                fadeOut.setToValue(0.0);
                fadeOut.setOnFinished(f -> {
                    welcomeText.setVisible(false);
                    String imgURL=getClass().getResource("/media/template2.jpg").toExternalForm();
                    start_background.setStyle("-fx-background-image: url('"+imgURL+"');" +
                            "-fx-background-repeat: no-repeat;" +
                            "-fx-background-size: cover;" +
                            "-fx-background-position: center;");
                    about.setVisible(true);
                    addStartButton.setVisible(true);
                });
                fadeOut.play();
            });
            pause.play();
        });
        fadeIn.play();
    }

    @FXML
    public void handleStartApplication(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/menu-view.fxml"));
            Stage stage=new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Menu Finance Tracker");
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
            ((Stage)addStartButton.getScene().getWindow()).close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
