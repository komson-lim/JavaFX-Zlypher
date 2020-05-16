package entity;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import pane.GamePane;

public class LifeUp extends Hitbox implements Interactable, Consumable {
    private AudioClip lifeUpSFX;
    public LifeUp(double posX, double posY){
        super(posX, posY);
//        Rectangle r = new Rectangle(10,10);
//        r.setFill(Color.PURPLE);
//        setHitbox(r);
        ImageView image = new ImageView(new Image(ClassLoader.getSystemResource("lifeup.png").toString(),50,50,true,true));
        setHitbox(image);
        lifeUpSFX = new AudioClip(ClassLoader.getSystemResource("lifeup.wav").toString());
        lifeUpSFX.setVolume(0.1);
    }
    public void interact(Player player, GamePane gamePane){
        player.setLives(player.getLives()+1);
        consume(gamePane);
        lifeUpSFX.play();
    }
    public void consume(GamePane gamePane){
        this.kill(gamePane);
    }
}
