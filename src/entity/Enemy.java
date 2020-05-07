package entity;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import pane.GamePane;
import pane.GameUI;

import java.util.ArrayList;
import java.util.Random;

public class Enemy extends Hitbox {
    private int health;
    private boolean isAttacked;
    private int damage;
    private int hurtDirection;
    private int cooldown;
    public Enemy(double posX, double posY, int damage){
        super(posX, posY);
        Rectangle r = new Rectangle(64,115);
        r.setFill(Color.ORANGE);
        setHitbox(r);
        health = 6;
        isAttacked = false;
        this.damage = damage;
        hurtDirection = 0;
        cooldown = 0;
    }
    public void update(Player player, GamePane gamePane, ArrayList<Enemy> enemies, ArrayList<Interactable>interactables){
        if (this.intersects(player)&&!player.getIsInvincible()){
            player.setHealth(player.getHealth()-2);
            player.setIsHurt(true);
            player.setIsInvincible(true);
        }
        if (health<=0){
            this.kill(gamePane);
            enemies.remove(this);
            if (Math.random() <= 1){
                HealthDrop hd = new HealthDrop(this.posX, this.posY);
                interactables.add(hd);
                hd.render(gamePane);
                hd.updatePos();
            }
        }
        if (player.posX <= posX && player.posX >= posX-300 && cooldown==0){
            this.attack(interactables,gamePane);
            cooldown = 120;
        }
        if (isAttacked){
            cooldown = 120;
        }
        if (cooldown != 0){
            cooldown --;
        }
    }
    public void attack(ArrayList<Interactable> interactables, GamePane gamePane){
        EnemyAttack ea = new EnemyAttack(posX,posY,-6,3);
        interactables.add(ea);
        ea.render(gamePane);
        ea.updatePos();
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
