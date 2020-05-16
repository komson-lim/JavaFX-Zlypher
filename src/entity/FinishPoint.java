package entity;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.GameController;
import pane.GamePane;

public class FinishPoint extends Hitbox implements Interactable{
    public FinishPoint(double posX, double posY){
        super(posX, posY);
//        Rectangle r = new Rectangle(50,50);
//        r.setFill(Color.YELLOWGREEN);
//        setHitbox(r);
        ImageView image = new ImageView(new Image(ClassLoader.getSystemResource("finish.png").toString(),100,100,true,true));
        setHitbox(image);
    }
    public void interact(Player player, GamePane gamePane){
        GameController.setIsFinish(true);
    }
}
