package pane;

import application.Main;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MenuPane extends VBox {
    private Button start;
    private Button howTo;
    private Button quit;
    private Media media;
    private MediaPlayer mediaPlayer;
    public MenuPane(int width, int height){
        super();
        setPrefHeight(height);
        setPrefWidth(width);
        start = new Button();
        start.setPrefSize(200,70);
        start.setText("Start");
        start.setFont(new Font("arial",20));
        howTo = new Button();
        howTo.setPrefSize(200,70);
        howTo.setText("How to Play");
        howTo.setFont(new Font("arial",20));
        quit = new Button();
        quit.setPrefSize(200,70);
        quit.setText("Quit");
        quit.setFont(new Font("arial",20));
        setAlignment(Pos.CENTER);
        setSpacing(25);
        media = new Media(ClassLoader.getSystemResource("menu.mp3").toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.1);
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
                mediaPlayer.play();
            }
        });
        getChildren().addAll(start,howTo,quit);
    }
    public void addListener(Scene scene, Stage primaryStage,Pane root ,Pane pane){
        start.setOnAction(e -> {
            primaryStage.setScene(scene);
            Main.initializeLevel(1);
            mediaPlayer.stop();
        });
        howTo.setOnAction(e -> {
            root.getChildren().add(pane);
        });
        quit.setOnAction(e -> {
            Platform.exit();
        });
    }
    public void play(){
        mediaPlayer.play();
    }

}
