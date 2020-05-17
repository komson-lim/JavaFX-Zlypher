package pane;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class GameBackground extends Pane {
    private ImageView bg;
    private ImageView bg2;
    public GameBackground(int width, int height){
        setPrefHeight(height);
        setPrefWidth(width);
        bg = new ImageView(new Image(ClassLoader.getSystemResource("bg.png").toString(), 1200,900,true,true));
        bg2 = new ImageView(new Image(ClassLoader.getSystemResource("bg2.png").toString(), 1200,900,true,true));
        this.getChildren().add(bg);
    }
    public void addBg(){
        this.getChildren().add(bg2);
    }
    public void removeBg(){
        this.getChildren().remove(bg2);
    }
}
