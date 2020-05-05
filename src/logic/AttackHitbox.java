package logic;

import entity.Hitbox;
import entity.Sprite;
import javafx.scene.shape.Rectangle;

public class AttackHitbox extends Hitbox {
    private int damage;
    private int priority;
    public AttackHitbox(double posX, double posY, int damage, int priority){
        super(posX,posY);
        setHitbox(new Rectangle(100,30));
        this.damage = damage;
        this.priority = priority;
    }
    public void setSize(double width, double height){
        hitbox.setWidth(width);
        hitbox.setHeight(height);
    }
    public void updatePos(double posX, double posY){
        this.posX = posX;
        this.posY = posY;
        super.updatePos();
    }
    public void killHitbox(){
        setSize(0,0);
    }

    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage){
        this.damage = damage;
    }

    public int getPriority() {
        return priority;
    }
}
