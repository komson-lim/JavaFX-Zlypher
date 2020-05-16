package entity;

import javafx.scene.canvas.GraphicsContext;
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
    private int direction;
    private EnemySprite enemySprite;
    private boolean isAttack;
    private int attackFrame;
    private int directionCooldown;
    public Enemy(double posX, double posY, int damage){
        super(posX, posY);
        Rectangle r = new Rectangle(75,120);
        r.setFill(Color.ORANGE);
        setHitbox(r);
        health = 6;
        isAttacked = false;
        this.damage = damage;
        hurtDirection = 0;
        cooldown = 0;
        direction = 1;
        enemySprite = new EnemySprite(this);
        isAttack = false;
        attackFrame = 0;
        directionCooldown = 0;

    }
    public void update(Player player, GamePane gamePane, ArrayList<Enemy> enemies, ArrayList<Interactable>interactables){
        changeDirection(player);
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
//                LifeUp lifeUp = new LifeUp(this.posX, this.posY);
//                interactables.add(lifeUp);
//                lifeUp.render(gamePane);
//                lifeUp.updatePos();
            }
        }
        if (engageBattle(player) && cooldown==0){
            isAttack = true;
            if (attackFrame == 0) {
                this.attack(interactables, gamePane);
                cooldown = 120;
            }
        }
        if (attackFrame <= 11 && isAttack){
            attackFrame++;
            if (attackFrame==12){
                attackFrame = 0;
                isAttack = false;
            }
        }
        if (isAttacked){
            cooldown = 120;
            attackFrame = 0;
            isAttack = false;
            if (!engageBattle(player)){
                direction = -direction;
            }
        }
        if (cooldown != 0){
            cooldown --;
        }


    }
    public boolean engageBattle(Player player){
        if (player.posY <= posY +200 && player.posY >= posY -200 )
        if (direction == -1){
            return player.posX <= posX && player.posX >= posX-300;
        }else if (direction == 1){
            return player.posX <= posX+300 && player.posX >= posX;
        }
        return false;
    }
    public void changeDirection(Player player){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                if (!engageBattle(player)&&cooldown==0&&directionCooldown==0){
                    if (Math.random() <= 1){
                        direction = -direction;
                    }
                    directionCooldown = 300;
                }
                if (directionCooldown != 0){
                    directionCooldown --;
                }
            }
        });
        t.start();

    }
    public void renderSprite(GraphicsContext gc, double offsetX, double offsetY){
        enemySprite.updateImage(this);
        enemySprite.render(gc, offsetX, offsetY);
    }
    public void attack(ArrayList<Interactable> interactables, GamePane gamePane){
        EnemyAttack ea = new EnemyAttack(posX,posY,-7,3);
        if (direction==-1){
            ea.setPosX(posX-50);
            ea.setPosY(posY+20);
            ea.setDirection(-1);
        }else if (direction == 1){
            ea.setPosX(posX+50);
            ea.setPosY(posY +20);
            ea.setSpeedX(7);
            ea.setDirection(1);
        }
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

    public int getDirection() {
        return direction;
    }
    public boolean getIsAttack(){
        return isAttack;
    }

    public int getAttackFrame() {
        return attackFrame;
    }
}
