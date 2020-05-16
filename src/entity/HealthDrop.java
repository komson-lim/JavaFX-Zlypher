package entity;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import pane.GamePane;
import pane.GameUI;

public class HealthDrop extends Hitbox implements Interactable,Consumable {
    private int healthGain;
    private AudioClip healthSFX;
    public HealthDrop(double posX, double posY){
        super(posX, posY);
//        Rectangle r = new Rectangle(10,10);
//        r.setFill(Color.PURPLE);
//        setHitbox(r);
        ImageView image = new ImageView(new Image(ClassLoader.getSystemResource("health.png").toString(),50,50,true,true));
        setHitbox(image);
        healthGain = 3;
        healthSFX = new AudioClip(ClassLoader.getSystemResource("health.WAV").toString());
        healthSFX.setVolume(0.1);
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
        healthSFX.play();
    }
    public void consume(GamePane gamePane){
        this.kill(gamePane);
    }
}

