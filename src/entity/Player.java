package entity;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.PlayerAttack;
import logic.PlayerMovement;

public class Player extends Hitbox {
    private boolean isOnPlatforms;
    private boolean isHitAbovePlatforms;
    private boolean isHitSidePlatforms;
    private PlayerMovement movement;
    private PlayerAttack attack;
    private PlayerSprite playerSprite;
    private int health;
    private boolean isHurt;
    private int hurtDirection;
    private boolean isInvincible;
    private int invincibleFrame;
    private int lives;
    private AudioClip hitSFX;
    public Player(double posX, double posY, double speedX, double jumpSpeed){
        super(posX, posY);
        this.isOnPlatforms = false;
        Rectangle r = new Rectangle(64,115);
        r.setFill(Color.BLACK);
        setHitbox(r);
        movement = new PlayerMovement(speedX, jumpSpeed, 14);
        attack = new PlayerAttack(this);
        playerSprite = new PlayerSprite(this);
        health = 20;
        isInvincible = false;
        invincibleFrame = 0;
        lives = 3;
        hitSFX = new AudioClip(ClassLoader.getSystemResource("hit.WAV").toString());
        hitSFX.setVolume(0.1);
    }
    public void update() {
        movement.update(this);
//        attack.update(this);
        if (isInvincible){
            invincibleFrame++;
            if (invincibleFrame == 60){
                isInvincible = false;
                invincibleFrame = 0;
            }
        }
    }
//    public void hurt(Enemy enemy){
//        health -= enemy.getDamage();
//        isHurt =  true;
//    }
    public void renderSprite(GraphicsContext gc, double offsetX, double offsetY){
        if (!isInvincible || invincibleFrame%5 == 0) {
            playerSprite.updateImage(this);
            playerSprite.render(gc, offsetX, offsetY);
        }
    }
    public boolean isOnPlatform(Platform floor){
        if (floor.intersects(this)&&(posY+height >= floor.posY) && (posY+height <= floor.posY+11)){
            if (movement.isMoveDown()||!movement.getIsJump()){
                posY = floor.posY - height;
                this.updatePos();
                return true;
            }

        }
        return false;
    }
    public boolean hitAbovePlatform(Platform floor){
        if (floor.intersects(this)&&(posY >= floor.posY+floor.height-15)&&(posY <= floor.posY+floor.height)){
            posY = floor.posY+floor.height;
            this.updatePos();
            return true;
        }
        return false;
    }
    public boolean hitSidePlatform(Platform floor){
        if (floor.intersects(this)){
            if ((posX+width >= floor.posX) && (posX+width <= floor.posX+18)){
                posX = floor.posX - width -0.1 ;
                this.updatePos();
                return true;
            }
            if ((posX >= floor.posX+floor.width-18) && (posX <= floor.posX+floor.width)) {
                posX = floor.posX + floor.width +0.1;
                this.updatePos();
                return false;
            }
        }
        return false;
    }

    public PlayerMovement getMovement() {
        return movement;
    }

    public void setOnPlatforms(boolean onPlatforms) {
        isOnPlatforms = onPlatforms;
    }
    public boolean getIsOnPlatforms(){
        return this.isOnPlatforms;
    }

    public void setHitAbovePlatforms(boolean hitAbovePlatforms) {
        isHitAbovePlatforms = hitAbovePlatforms;
    }

    public void setHitSidePlatforms(boolean hitSidePlatforms) {
        isHitSidePlatforms = hitSidePlatforms;
    }
    public boolean getIsHitSidePlatform(){
        return this.isHitSidePlatforms;
    }
    public boolean getIsHitAbovePlatforms(){
        return this.isHitAbovePlatforms;
    }
    public PlayerAttack getAttack(){
        return this.attack;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health <= 0){
            this.health = 0;
        }else this.health = Math.min(health, 20);
    }
    public boolean getIsHurt(){
        return  isHurt;
    }
    public void setIsHurt(boolean isHurt){
        this.isHurt = isHurt;
        if (isHurt){
            hitSFX.play();
        }
    }

    public int getHurtDirection() {
        return hurtDirection;
    }

    public void setHurtDirection(int hurtDirection) {
        this.hurtDirection = hurtDirection;
    }
    public void setIsInvincible(boolean isInvincible){
        this.isInvincible = isInvincible;
    }
    public boolean getIsInvincible(){
        return this.isInvincible;
    }

    public int getInvincibleFrame() {
        return invincibleFrame;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        if (lives <= 0){
            this.lives = 0;
        }
        this.lives = lives;
    }
}
