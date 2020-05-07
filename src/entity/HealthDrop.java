package entity;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import pane.GamePane;
import pane.GameUI;

public class HealthDrop extends Hitbox implements Interactable,Consumable {
    private int healthGain;
    public HealthDrop(double posX, double posY){
        super(posX, posY);
        Rectangle r = new Rectangle(10,10);
        r.setFill(Color.PURPLE);
        setHitbox(r);
        healthGain = 3;
    }
    public HealthDrop(double posX, double posY,int healthGain){
        super(posX, posY);
        Rectangle r = new Rectangle(10,10);
        r.setFill(Color.PURPLE);
        setHitbox(r);
        this.healthGain = healthGain;
    }
    public void interact(Player player, GamePane gamePane){
        player.setHealth(player.getHealth()+healthGain);
        consume(gamePane);
    }
    public void consume(GamePane gamePane){
        this.kill(gamePane);
    }
}

