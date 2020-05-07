package entity;

import com.sun.javafx.sg.prism.NGAmbientLight;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import pane.GamePane;

public class EnemyAttack extends Hitbox implements Interactable,Updatable,Consumable{
    private int damage;
    private int speedX;
    public EnemyAttack(double posX, double posY,int speedX ,int damage){
        super(posX, posY);
        Rectangle r = new Rectangle(10,10);
        r.setFill(Color.BLUEVIOLET);
        setHitbox(r);
        this.damage = damage;
        this.speedX = speedX;
    }
    public void interact(Player player, GamePane gamePane){
        if (!player.getIsInvincible()) {
            player.setHealth(player.getHealth() - damage);
            player.setIsHurt(true);
            player.setIsInvincible(true);
        }
        consume(gamePane);
    }
    public void consume(GamePane gamePane){
        this.kill(gamePane);
    }
    public void update(){
        this.posX += speedX;
        updatePos();
    }
}
