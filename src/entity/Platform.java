package entity;


import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Platform extends Hitbox{
    public Platform(double posX, double posY){
        super(posX, posY);
        Rectangle r = new Rectangle(60,60);
        r.setFill(Color.RED);
        setHitbox(r);
    }
    public void changeColor(Paint paint){
        Rectangle r = new Rectangle(60,60);
        r.setFill(paint);
        setHitbox(r);
    }
}
