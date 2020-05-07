package pane;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class GameBackground extends Pane {
    private ImageView bg;
    public GameBackground(double width, double height){
        setPrefHeight(height);
        setPrefWidth(width);
        bg = new ImageView(new Image(ClassLoader.getSystemResource("bg.png").toString(), 1200,900,true,true));
        this.getChildren().add(bg);
    }
}
