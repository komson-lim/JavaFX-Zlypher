package pane;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class GameBackgroundParallax extends Pane {
    ImageView bg1;
    ImageView bg2;
    ImageView bg3;
    ImageView bg4;
    public GameBackgroundParallax(int width, int height){
        setPrefHeight(height);
        setPrefWidth(width);
        bg1 = new ImageView(new Image(ClassLoader.getSystemResource("bgP1.png").toString(), 4800,900,true,true));
        bg2 = new ImageView(new Image(ClassLoader.getSystemResource("bgP2.png").toString(), 4800,900,true,true));
        bg3 = new ImageView(new Image(ClassLoader.getSystemResource("bgP3.png").toString(), 4800,900,true,true));
        bg4 = new ImageView(new Image(ClassLoader.getSystemResource("bgP4.png").toString(), 4800,900,true,true));
        bg1.setTranslateX(0);
        bg2.setTranslateX(0);
        bg3.setTranslateX(0);
        bg4.setTranslateX(0);
        this.getChildren().addAll(bg1,bg2,bg3,bg4);
    }
    public void update(double speedX){
        bg1.setTranslateX(Math.min(bg1.getTranslateX()+speedX/5, 0));
        bg2.setTranslateX(Math.min(bg2.getTranslateX()+speedX/6, 0));
        bg3.setTranslateX(Math.min(bg3.getTranslateX()+speedX/7, 0));
        bg4.setTranslateX(Math.min(bg4.getTranslateX()+speedX/8, 0));
    }
    public void reset(){
        bg1.setTranslateX(0);
        bg2.setTranslateX(0);
        bg3.setTranslateX(0);
        bg4.setTranslateX(0);
    }
}
