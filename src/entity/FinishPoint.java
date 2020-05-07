package entity;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.GameController;
import pane.GamePane;

public class FinishPoint extends Hitbox implements Interactable{
    public FinishPoint(double posX, double posY){
        super(posX, posY);
        Rectangle r = new Rectangle(50,50);
        r.setFill(Color.YELLOWGREEN);
        setHitbox(r);
    }
    public void interact(Player player, GamePane gamePane){
        GameController.setIsFinish(true);
    }
}
