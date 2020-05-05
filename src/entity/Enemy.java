package entity;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Enemy extends Hitbox {
    private int health;
    private boolean isAttacked;
    private int damage;
    private int hurtDirection;
    public Enemy(double posX, double posY, int damage){
        super(posX, posY);
        Rectangle r = new Rectangle(64,115);
        r.setFill(Color.ORANGE);
        setHitbox(r);
        health = 3;
        isAttacked = false;
        this.damage = damage;
        hurtDirection = 0;
    }
    public void update(Player player, Pane root, ArrayList<Enemy> enemies){
        if (this.intersects(player)&&!player.getIsInvincible()){
            player.setHealth(player.getHealth()-1);
            player.setIsHurt(true);
            player.setIsInvincible(true);
        }
        if (health<=0){
            this.kill(root);
            enemies.remove(this);
        }
    }
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    public void setIsAttacked(boolean isAttacked){
        this.isAttacked = isAttacked;
    }
    public boolean getIsAttacked(){
        return this.isAttacked;
    }
    public int getDamage() {
        return damage;
    }

    public int getHurtDirection() {
        return hurtDirection;
    }
}
