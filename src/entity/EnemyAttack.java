package entity;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import pane.GamePane;

public class EnemyAttack extends Hitbox implements Interactable,Updatable,Consumable{
    private int damage;
    private int speedX;
    private ImageView kunai;
    private ImageView kunaiL;
    private int direction;
    public EnemyAttack(double posX, double posY,int speedX ,int damage){
        super(posX, posY);
//        Rectangle r = new Rectangle(10,10);
//        r.setFill(Color.BLUEVIOLET);
//        setHitbox(r);
        kunai = new ImageView(new Image(ClassLoader.getSystemResource("kunai.png").toString(), 75,75,true,true));
        kunaiL = new ImageView(new Image(ClassLoader.getSystemResource("kunaiL.png").toString(), 75,75,true,true));
        setHitbox(kunaiL);
        this.damage = damage;
        this.speedX = speedX;
        direction = -1;
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

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public void setDirection(int direction) {
        this.direction = direction;
        if (direction==1){
            setHitbox(kunai);
        }else if (direction == -1){
            setHitbox(kunaiL);
        }
    }

    public int getDirection() {
        return direction;
    }
}
