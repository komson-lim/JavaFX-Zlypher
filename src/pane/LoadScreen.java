package pane;

import entity.Player;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class LoadScreen extends Pane {
    private Text lives;
    public LoadScreen(int width, int height, Player player){
        super();
        var bg = new BackgroundFill(Color.BLACK,new CornerRadii(0), new Insets(0));
        setBackground(new Background(bg));
        setPrefHeight(height);
        setPrefWidth(width);
        Text load = new Text();
        load.setText("Now Loading . . .");
        load.setFill(Color.WHITE);
        load.setFont(new Font("arial", 50));
        load.setX(750);
        load.setY(800);
        lives = new Text();
        lives.setText("Lives: " + player.getLives());
        lives.setFill(Color.WHITE);
        lives.setFont(new Font("arial", 75));
        lives.setX((double) width/2 - lives.getBoundsInParent().getWidth()/2);
        lives.setY((double)height/2 + lives.getBoundsInParent().getHeight()/2);
        getChildren().addAll(load,lives);
    }
    public void setLives(int lives){
        this.lives.setText("Lives: " + lives);
    }
}
