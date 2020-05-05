package logic;

import entity.Enemy;
import entity.Player;

public class PlayerAttack {
    private boolean isAttack;
    private boolean isAttackHold;
    private boolean isAttackAir;
    private AttackHitbox atkHB;
    private int attackFrame;
    public PlayerAttack(Player player){
        atkHB = new AttackHitbox(player.getPosX()+30, player.getPosY()-30, 3, 0);
        atkHB.setSize(0,0);
        isAttack = false;
        isAttackHold = false;
        isAttackAir = false;
    }
    public void attackInit(){
        if (!isAttack&&!isAttackHold){
            attackFrame = 0;
            isAttack = true;
            isAttackHold = true;
        }

    }
    public void attackRelease(){
        isAttackHold = false;
    }
    public void attackEnd(){
        atkHB.killHitbox();
        attackFrame = 0;
        isAttack = false;
        isAttackAir = false;
    }
    public void update(Player player){
        if (isAttack) {
            if (player.getMovement().getIsJump()||player.getMovement().isMoveDown()){
                isAttackAir = true;
                attackAir(player);
                attackFrame++;
            }
            else {
                attack(player);
                attackFrame++;
            }
        }
        if (isAttackAir && attackFrame >= 12){
            attackEnd();
        }
        if (attackFrame >= 20) {
            attackEnd();
        }
    }
    public void updateAttack(Player player, Enemy enemy){
        if (enemy.intersects(atkHB)){
            if (!enemy.getIsAttacked()) {
                enemy.setHealth(enemy.getHealth() - atkHB.getDamage());
                enemy.setIsAttacked(true);
            }
        }else{
            enemy.setIsAttacked(false);
        }
    }
    public void attack(Player player){
        atkHB.setSize(100,30);
        atkHB.setDamage(3);
        if (player.getMovement().getDirection()==1) {
            atkHB.updatePos(player.getPosX() + 50, player.getPosY() + 75);
        }else if (player.getMovement().getDirection()==-1){
            atkHB.updatePos(player.getPosX() - 25-64, player.getPosY() + 75);
        }

    }
    public void attackAir(Player player){
//        if (attackFrame < 100){
//            atkHB.setSize(65,100);
//            atkHB.updatePos(player.getPosX()-40, player.getPosY()-50);
//        }
        atkHB.setDamage(1);
        if (attackFrame <= 1){
            atkHB.setSize(100,100);
            atkHB.updatePos(player.getPosX() + 50, player.getPosY()-10);
        }else if (attackFrame <= 3){
            atkHB.setSize(100,100);
            atkHB.updatePos(player.getPosX() + 25, player.getPosY()+75);
        }else if (attackFrame <= 5){
            atkHB.setSize(100,100);
            atkHB.updatePos(player.getPosX() -80, player.getPosY()+50);
        }else if (attackFrame <= 7){
            atkHB.setSize(100,100);
            atkHB.updatePos(player.getPosX()-40, player.getPosY()-50);
        }
//        atkHB.setSize(100,30);
//        if (player.getMovement().getDirection()==1) {
//            atkHB.updatePos(player.getPosX() + 25, player.getPosY() + 75);
//        }else if (player.getMovement().getDirection()==-1){
//            atkHB.updatePos(player.getPosX() - 25-64, player.getPosY() + 75);
//        }
    }
    public AttackHitbox getAtkHB(){
        return atkHB;
    }
    public boolean getIsAttack(){
        return this.isAttack;
    }

    public int getAttackFrame() {
        return attackFrame;
    }
    public void setIsAttackAir(boolean isAttackAir){
        this.isAttackAir = isAttackAir;
    }
    public boolean getIsAttackAir(){
        return isAttackAir;
    }
}

