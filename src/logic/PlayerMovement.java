package logic;

import entity.Player;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PlayerMovement {
    private double maxSpeedX;
    private double jumpSpeed;
    private double dashSpeed;
    private double speedX;
    private double speedY;
    private int direction;
    private boolean isMoveRight;
    private boolean isMoveLeft;
    private boolean isJump;
    private boolean canJump;
    private boolean isJumpRelease;
    private boolean isMoveDown;
    private boolean isJumpStart;
    private boolean isDash;
    private int dashFrame;
    private boolean isDashRelease;
    private boolean isDashHold;
    private boolean isPushDashInAir;
    private boolean isDashInAir;
    private boolean isIdle;
    private int hurtFrame;
    private AudioClip dashSFX;
    public PlayerMovement(double maxSpeedX, double jumpSpeed, double dashSpeed){
        this.maxSpeedX = maxSpeedX;
        this.jumpSpeed = jumpSpeed;
        this.dashSpeed = dashSpeed;
        this.direction = 1;
        this.isJumpStart = false;
        this.isJumpRelease = true;
        this.isDash = false;
        this.isDashRelease = true;
        this.isDashHold = false;
        this.isPushDashInAir = false;
        dashSFX = new AudioClip(ClassLoader.getSystemResource("dash.WAV").toString());
        dashSFX.setVolume(0.1);
    }
    public void update(Player player){
        if (player.getIsHurt()){
            hurt(player.getHurtDirection());
            hurtFrame++;
            if (hurtFrame > 5){
                hurtFrame = 0;
                player.setIsHurt(false);
            }
        }
        else if (player.getAttack().getIsAttack()&&!player.getAttack().getIsAttackAir()){
            speedX = 0;

        }
        else {
            if (isMoveLeft && !isDash && !isMoveRight) {
                speedX = moveLeft();
            } else if (isMoveRight && !isDash && !isMoveLeft) {
                speedX = moveRight();
            } else if (isDash) {
                updateDash();
            } else {
                speedX = 0;
            }
        }
        player.setPosX(player.getPosX()+speedX);
        player.setPosY(player.getPosY()+speedY);
        isIdle = speedX==0&&speedY==0&&!isJump&&!player.getAttack().getIsAttack();
    }
    public void stopMovement(){
        speedX = 0;
        speedY = 0;
    }
    public void hurt(int hurtDirection){
        if (hurtDirection==0){
            speedX = -10*direction;
        }else{
            speedX = 10*hurtDirection;
        }
    }

    // X direction
    public double moveRight(){
        direction = 1;
        speedX = maxSpeedX;
        return speedX;
    }
    public double moveLeft(){
        direction = -1;
        speedX = -maxSpeedX;
        return speedX;
    }

    public void setMoveLeft(boolean moveLeft) {
        isMoveLeft = moveLeft;
    }

    public void setMoveRight(boolean moveRight) {
        isMoveRight = moveRight;
    }
    public void updateDash(){
        if (!isDashRelease&&!isDashHold&&!isPushDashInAir){
            speedX = dash();
        }else if (isDashRelease&&(isJump||isMoveDown)){
            speedX = dash();
        }
        else {
            dashEnd();
        }
        if (dashFrame > 25 &&!(isJump||isMoveDown)){
            dashEnd();
            isDashHold = true;
        }
        if (isDash&&(isJump||isMoveDown)){
            isDashInAir = true;
        }

    }
    public double dash(){
        isDash = true;
        if (isMoveRight){
            direction=1;
        }else if (isMoveLeft) {
            direction=-1;
        }
        speedX = dashSpeed*direction;
        if ((isJump||isMoveDown)&&!isMoveRight&&!isMoveLeft){
            speedX = 0;
        }
        if (dashFrame <= 6){
            speedX = (maxSpeedX+dashFrame+dashSpeed)/2*direction;
        }
        if (dashFrame == 1){
            dashSFX.play();
        }
        dashFrame++;
        return speedX;
    }
    public void dashInit(){
        if(!isDash&&!isDashHold&&!isMoveDown){
            dashFrame = 0;
            isDash = true;
            if (isJump){
                isPushDashInAir = true;
            }
        }
        isDashRelease = false;

    }
    public void dashRelease(){
        isDashRelease = true;
        isDashHold = false;
    }
    public void dashEnd(){
        speedX = 0;
        isDash = false;
        dashFrame = 0;
        isPushDashInAir = false;
        isDashInAir = false;
//        dashSFX.stop();
    }

    // Y direction
    public void jump(){
        speedY = -jumpSpeed;
        canJump = false;
        isJump = true;
        isJumpRelease = false;
    }
    public void jumpInit(){
        isJumpStart = true;
    }
    public void jumpRelease(){
        isJumpRelease = true;
        if (!isMoveDown){
            speedY = 0;
        }
        canJump = false;
        isJumpStart = false;
    }
    public void jumpUpdate(Player player){
        if (player.getIsOnPlatforms()){
            canJump = true;
            speedY = 0;
            isJump = false;
            if (isDashInAir) {
                dashEnd();
                if (!isDashRelease) {
                    isDashHold = true;
                }
            }
            if (player.getAttack().getIsAttackAir()) {
                player.getAttack().attackEnd();
            }
        }
        if (player.getIsHitAbovePlatforms()){
            speedY = 0;
        }
        isMoveDown = speedY > 0;
        if (isMoveDown){
            canJump = false;
        }
        if (isJumpStart &&canJump&&isJumpRelease&&!player.getAttack().getIsAttack()){
            jump();
        }
    }
    public void gravity(){
        if (speedY < 12){
            speedY += 1;
        }
    }

    public boolean isMoveDown() {
        return isMoveDown;
    }
    public boolean getIsIdle(){
        return this.isIdle;
    }

    public int getDirection() {
        return direction;
    }
    public boolean getIsJump(){
        return isJump;
    }
    public boolean getIsMoveLeft(){
        return isMoveLeft;
    }
    public boolean getIsMoveRight(){
        return isMoveRight;
    }
    public boolean getIsDash(){
        return isDash;
    }
    public int getDashFrame() {
        return dashFrame;
    }
    public void setDirection(int direction) {
        this.direction = direction;
    }

    public double getSpeedX() {
        return speedX;
    }
}

