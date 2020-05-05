package logic;

import entity.Player;

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
    private boolean isDashInAir;
    private boolean isIdle;
    private int hurtFrame;
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
        this.isDashInAir = false;
    }
    public void update(Player player){
        if (player.getIsHurt()){
            hurt(player.getHurtDirection());
            hurtFrame++;
            if (hurtFrame > 5){
                hurtFrame = 0;
                player.setIsHurt(false);
            }
        }else if (player.getAttack().getIsAttack()){
            if (!isJump){
                speedX = 0;
            }
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
        if (!isDashRelease&&!isDashHold&&!isDashInAir){
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
        dashFrame++;
        return speedX;
    }
    public void dashInit(){
        if(!isDash&&!isDashHold){
            dashFrame = 0;
            isDash = true;
            if (isJump){
                isDashInAir = true;
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
        isDashInAir = false;
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
            isDashInAir = false;
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
}

