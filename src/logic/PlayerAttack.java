package logic;

import entity.AttackHitbox;
import entity.Enemy;
import entity.Player;
import javafx.scene.media.AudioClip;

public class PlayerAttack {
    private boolean isAttack;
    private boolean isAttackHold;
    private boolean isAttackAir;
    private AttackHitbox atkHB;
    private int attackFrame;
    private AudioClip slashSFX;
    private AudioClip spinSlashSFX;
    public PlayerAttack(Player player){
        atkHB = new AttackHitbox(player.getPosX()+30, player.getPosY()-30, 3, 0);
        atkHB.setSize(0,0);
        isAttack = false;
        isAttackHold = false;
        isAttackAir = false;
        slashSFX = new AudioClip(ClassLoader.getSystemResource("slash.WAV").toString());
        slashSFX.setVolume(0.1);
        spinSlashSFX = new AudioClip(ClassLoader.getSystemResource("spinSlash.WAV").toString());
        spinSlashSFX.setVolume(0.1);
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
        if (isAttackAir && attackFrame >= 20){
            attackEnd();
        }
        if (attackFrame >= 20) {
            attackEnd();
        }
        if (attackFrame == 1) {
            if (isAttackAir) {
                spinSlashSFX.play();
            } else {
                slashSFX.play();
            }
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
        atkHB.setDamage(8);
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
        atkHB.setDamage(4);
        if (player.getMovement().getDirection()==1) {
//            atkHB.setSize(100, 100);
//            atkHB.updatePos(player.getPosX() + 50, player.getPosY() - 10);

            switch (attackFrame) {
                case 0:
                    atkHB.setSize(100, 100);
            atkHB.updatePos(player.getPosX() +10, player.getPosY() - 50);
                    break;
                case 1:
                    atkHB.setSize(100, 100);
                    atkHB.updatePos(player.getPosX() + 50, player.getPosY() - 50);
                    break;
                case 2:
                    atkHB.setSize(100, 100);
                    atkHB.updatePos(player.getPosX() + 50, player.getPosY() +35);
                    break;
                case 3:
                    atkHB.setSize(100, 100);
                    atkHB.updatePos(player.getPosX() + 25, player.getPosY() + 75);
                    break;
                case 4:
                    atkHB.setSize(100, 100);
                    atkHB.updatePos(player.getPosX()-50, player.getPosY() + 75);
                    break;
                case 5:
                    atkHB.setSize(100, 100);
                    atkHB.updatePos(player.getPosX() - 80, player.getPosY() + 50);
                    break;
                case 6:
                    atkHB.setSize(100, 100);
                    atkHB.updatePos(player.getPosX() - 80, player.getPosY()-25);
                    break;
                case 7:
                    atkHB.setSize(100, 100);
                    atkHB.updatePos(player.getPosX()-75, player.getPosY() - 50);
                    break;
                case 8:
                    atkHB.setSize(100, 100);
                    atkHB.updatePos(player.getPosX()+15, player.getPosY() - 50);
                    break;
                case 9:
                    atkHB.updatePos(player.getPosX() + 50, player.getPosY() - 10);
                    break;
            }
        }
        else if (player.getMovement().getDirection()==-1){

//            atkHB.setSize(100, 100);
//            atkHB.updatePos(player.getPosX() - 80, player.getPosY()-10);
            switch (attackFrame){
                case 0:
                    atkHB.setSize(100, 100);
                    atkHB.updatePos(player.getPosX() - 50, player.getPosY() - 50);
                    break;
                case 1:
                    atkHB.setSize(100, 100);
                    atkHB.updatePos(player.getPosX() - 80, player.getPosY()-50);
                    break;
                case 2:
                    atkHB.setSize(100, 100);
                    atkHB.updatePos(player.getPosX()-80, player.getPosY()+30);
                    break;
                case 3:
                    atkHB.setSize(100, 100);
                    atkHB.updatePos(player.getPosX() - 60, player.getPosY()+75);
                    break;
                case 4:
                    atkHB.setSize(100, 100);
                    atkHB.updatePos(player.getPosX() +25, player.getPosY()+75);
                    break;
                case 5:
                    atkHB.setSize(100, 100);
                    atkHB.updatePos(player.getPosX() + 50, player.getPosY() + 50);
                    break;
                case 6:
                    atkHB.setSize(100, 100);
                    atkHB.updatePos(player.getPosX() + 50, player.getPosY());
                    break;
                case 7:
                    atkHB.setSize(100, 100);
                    atkHB.updatePos(player.getPosX() + 50, player.getPosY() - 50);
                    break;
                case 8:
                    atkHB.setSize(100, 100);
                    atkHB.updatePos(player.getPosX()-45, player.getPosY() - 45);
                    break;
                case 9:
                    atkHB.updatePos(player.getPosX() - 80, player.getPosY()-10);
                    break;
            }

//
//
//            } else if (attackFrame <= 10) {
//                atkHB.setSize(100, 100);
//                atkHB.updatePos(player.getPosX() - 60, player.getPosY()+75);
//            }
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

