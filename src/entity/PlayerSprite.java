package entity;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class PlayerSprite extends Sprite {
    private ImageView[] idles;
    private ImageView[] idlesLeft;
    private ImageView[] walks;
    private ImageView[] walksLeft;
    private ImageView[] jumpStarts;
    private ImageView[] jumps;
    private ImageView[] jumpStartsLeft;
    private ImageView[] jumpsLeft;
    private ImageView[] dashes;
    private ImageView[] dashStarts;
    private ImageView[] dashesLeft;
    private ImageView[] dashStartsLeft;
    private ImageView[] falls;
    private ImageView[] fallsLeft;
    private ImageView[] atks;
    private ImageView[] atksLeft;
    private ImageView[] atkAirs;
    private ImageView[] atkAirsLeft;
    private int idleFrame;
    private int walkFrame;
    private int jumpFrame;
    private int fallFrame;
    public PlayerSprite(Player player){
        super(player.getPosX(), player.getPosY());
        ImageView idle1 = new ImageView(new Image(ClassLoader.getSystemResource("idle1.png").toString(), 500,260,true,true));
        ImageView idle2 = new ImageView(new Image(ClassLoader.getSystemResource("idle2.png").toString(), 500,260,true,true));
        ImageView idle3 = new ImageView(new Image(ClassLoader.getSystemResource("idle3.png").toString(), 500,260,true,true));
        ImageView idle4 = new ImageView(new Image(ClassLoader.getSystemResource("idle4.png").toString(), 500,260,true,true));
        ImageView idleL1 = new ImageView(new Image(ClassLoader.getSystemResource("idleL1.png").toString(), 500,260,true,true));
        ImageView idleL2 = new ImageView(new Image(ClassLoader.getSystemResource("idleL2.png").toString(), 500,260,true,true));
        ImageView idleL3 = new ImageView(new Image(ClassLoader.getSystemResource("idleL3.png").toString(), 500,260,true,true));
        ImageView idleL4 = new ImageView(new Image(ClassLoader.getSystemResource("idleL4.png").toString(), 500,260,true,true));
        ImageView walk1 = new ImageView(new Image(ClassLoader.getSystemResource("walk1.png").toString(), 500, 260, true, true));
        ImageView walk2 = new ImageView(new Image(ClassLoader.getSystemResource("walk2.png").toString(), 500, 260, true, true));
        ImageView walk3 = new ImageView(new Image(ClassLoader.getSystemResource("walk3.png").toString(), 500, 260, true, true));
        ImageView walk4 = new ImageView(new Image(ClassLoader.getSystemResource("walk4.png").toString(), 500, 260, true, true));
        ImageView walk5 = new ImageView(new Image(ClassLoader.getSystemResource("walk5.png").toString(), 500, 260, true, true));
        ImageView walk6 = new ImageView(new Image(ClassLoader.getSystemResource("walk6.png").toString(), 500, 260, true, true));
        ImageView walkL1 = new ImageView(new Image(ClassLoader.getSystemResource("walkL1.png").toString(), 500, 260, true, true));
        ImageView walkL2 = new ImageView(new Image(ClassLoader.getSystemResource("walkL2.png").toString(), 500, 260, true, true));
        ImageView walkL3 = new ImageView(new Image(ClassLoader.getSystemResource("walkL3.png").toString(), 500, 260, true, true));
        ImageView walkL4 = new ImageView(new Image(ClassLoader.getSystemResource("walkL4.png").toString(), 500, 260, true, true));
        ImageView walkL5 = new ImageView(new Image(ClassLoader.getSystemResource("walkL5.png").toString(), 500, 260, true, true));
        ImageView walkL6 = new ImageView(new Image(ClassLoader.getSystemResource("walkL6.png").toString(), 500, 260, true, true));
        ImageView jumpS1 = new ImageView(new Image(ClassLoader.getSystemResource("jumpS1.png").toString(), 500, 260, true, true));
        ImageView jumpS2 = new ImageView(new Image(ClassLoader.getSystemResource("jumpS2.png").toString(), 500, 260, true, true));
        ImageView jump1 = new ImageView(new Image(ClassLoader.getSystemResource("jump1.png").toString(), 500, 260, true, true));
        ImageView jump2 = new ImageView(new Image(ClassLoader.getSystemResource("jump2.png").toString(), 500, 260, true, true));
        ImageView jump3 = new ImageView(new Image(ClassLoader.getSystemResource("jump3.png").toString(), 500, 260, true, true));
        ImageView jumpSL1 = new ImageView(new Image(ClassLoader.getSystemResource("jumpSL1.png").toString(), 500, 260, true, true));
        ImageView jumpSL2 = new ImageView(new Image(ClassLoader.getSystemResource("jumpSL2.png").toString(), 500, 260, true, true));
        ImageView jumpL1 = new ImageView(new Image(ClassLoader.getSystemResource("jumpL1.png").toString(), 500, 260, true, true));
        ImageView jumpL2 = new ImageView(new Image(ClassLoader.getSystemResource("jumpL2.png").toString(), 500, 260, true, true));
        ImageView jumpL3 = new ImageView(new Image(ClassLoader.getSystemResource("jumpL3.png").toString(), 500, 260, true, true));
        ImageView dashS1 = new ImageView(new Image(ClassLoader.getSystemResource("dashS1.png").toString(), 500,260,true,true));
        ImageView dashS2 = new ImageView(new Image(ClassLoader.getSystemResource("dashS2.png").toString(), 500,260,true,true));
        ImageView dash1 = new ImageView(new Image(ClassLoader.getSystemResource("dash1.png").toString(), 500,260,true,true));
        ImageView dash2 = new ImageView(new Image(ClassLoader.getSystemResource("dash2.png").toString(), 500,260,true,true));
        ImageView dash3 = new ImageView(new Image(ClassLoader.getSystemResource("dash3.png").toString(), 500,260,true,true));
        ImageView dashSL1 = new ImageView(new Image(ClassLoader.getSystemResource("dashSL1.png").toString(), 500,260,true,true));
        ImageView dashSL2 = new ImageView(new Image(ClassLoader.getSystemResource("dashSL2.png").toString(), 500,260,true,true));
        ImageView dashL1 = new ImageView(new Image(ClassLoader.getSystemResource("dashL1.png").toString(), 500,260,true,true));
        ImageView dashL2 = new ImageView(new Image(ClassLoader.getSystemResource("dashL2.png").toString(), 500,260,true,true));
        ImageView dashL3 = new ImageView(new Image(ClassLoader.getSystemResource("dashL3.png").toString(), 500,260,true,true));
        ImageView fall1 = new ImageView(new Image(ClassLoader.getSystemResource("fall1.png").toString(), 500,260,true, true));
        ImageView fall2 = new ImageView(new Image(ClassLoader.getSystemResource("fall2.png").toString(), 500,260,true, true));
        ImageView fall3 = new ImageView(new Image(ClassLoader.getSystemResource("fall3.png").toString(), 500,260,true, true));
        ImageView fallL1 = new ImageView(new Image(ClassLoader.getSystemResource("fallL1.png").toString(), 500,260,true, true));
        ImageView fallL2 = new ImageView(new Image(ClassLoader.getSystemResource("fallL2.png").toString(), 500,260,true, true));
        ImageView fallL3 = new ImageView(new Image(ClassLoader.getSystemResource("fallL3.png").toString(), 500,260,true, true));
        ImageView atk1 = new ImageView(new Image(ClassLoader.getSystemResource("atk1.png").toString(), 500,260,true,true));
        ImageView atk2 = new ImageView(new Image(ClassLoader.getSystemResource("atk2.png").toString(), 500,260,true,true));
        ImageView atk3 = new ImageView(new Image(ClassLoader.getSystemResource("atk3.png").toString(), 500,260,true,true));
        ImageView atk4 = new ImageView(new Image(ClassLoader.getSystemResource("atk4.png").toString(), 500,260,true,true));
        ImageView atk5 = new ImageView(new Image(ClassLoader.getSystemResource("atk5.png").toString(), 500,260,true,true));
        ImageView atk6 = new ImageView(new Image(ClassLoader.getSystemResource("atk6.png").toString(), 500,260,true,true));
        ImageView atk7 = new ImageView(new Image(ClassLoader.getSystemResource("atk7.png").toString(), 500,260,true,true));
        ImageView atk8 = new ImageView(new Image(ClassLoader.getSystemResource("atk8.png").toString(), 500,260,true,true));
        ImageView atk9 = new ImageView(new Image(ClassLoader.getSystemResource("atk9.png").toString(), 500,260,true,true));
        ImageView atk10 = new ImageView(new Image(ClassLoader.getSystemResource("atk10.png").toString(), 500,260,true,true));
        ImageView atkL1 = new ImageView(new Image(ClassLoader.getSystemResource("atkL1.png").toString(), 500,260,true,true));
        ImageView atkL2 = new ImageView(new Image(ClassLoader.getSystemResource("atkL2.png").toString(), 500,260,true,true));
        ImageView atkL3 = new ImageView(new Image(ClassLoader.getSystemResource("atkL3.png").toString(), 500,260,true,true));
        ImageView atkL4 = new ImageView(new Image(ClassLoader.getSystemResource("atkL4.png").toString(), 500,260,true,true));
        ImageView atkL5 = new ImageView(new Image(ClassLoader.getSystemResource("atkL5.png").toString(), 500,260,true,true));
        ImageView atkL6 = new ImageView(new Image(ClassLoader.getSystemResource("atkL6.png").toString(), 500,260,true,true));
        ImageView atkL7 = new ImageView(new Image(ClassLoader.getSystemResource("atkL7.png").toString(), 500,260,true,true));
        ImageView atkL8 = new ImageView(new Image(ClassLoader.getSystemResource("atkL8.png").toString(), 500,260,true,true));
        ImageView atkL9 = new ImageView(new Image(ClassLoader.getSystemResource("atkL9.png").toString(), 500,260,true,true));
        ImageView atkL10 = new ImageView(new Image(ClassLoader.getSystemResource("atkL10.png").toString(), 500,260,true,true));
        ImageView atkAir1 = new ImageView(new Image(ClassLoader.getSystemResource("atkAir1.png").toString(), 500,260,true,true));
        ImageView atkAir2 = new ImageView(new Image(ClassLoader.getSystemResource("atkAir2.png").toString(), 500,260,true,true));
        ImageView atkAir3 = new ImageView(new Image(ClassLoader.getSystemResource("atkAir3.png").toString(), 500,260,true,true));
        ImageView atkAir4 = new ImageView(new Image(ClassLoader.getSystemResource("atkAir4.png").toString(), 500,260,true,true));
        ImageView atkAir5 = new ImageView(new Image(ClassLoader.getSystemResource("atkAir5.png").toString(), 500,260,true,true));
        ImageView atkAir6 = new ImageView(new Image(ClassLoader.getSystemResource("atkAir6.png").toString(), 500,260,true,true));
        ImageView atkAir7 = new ImageView(new Image(ClassLoader.getSystemResource("atkAir7.png").toString(), 500,260,true,true));
        ImageView atkAir8 = new ImageView(new Image(ClassLoader.getSystemResource("atkAir8.png").toString(), 500,260,true,true));
        ImageView atkAir9 = new ImageView(new Image(ClassLoader.getSystemResource("atkAir9.png").toString(), 500,260,true,true));
        ImageView atkAir10 = new ImageView(new Image(ClassLoader.getSystemResource("atkAir10.png").toString(), 500,260,true,true));
        ImageView atkAirL1 = new ImageView(new Image(ClassLoader.getSystemResource("atkAirL1.png").toString(), 500,260,true,true));
        ImageView atkAirL2 = new ImageView(new Image(ClassLoader.getSystemResource("atkAirL2.png").toString(), 500,260,true,true));
        ImageView atkAirL3 = new ImageView(new Image(ClassLoader.getSystemResource("atkAirL3.png").toString(), 500,260,true,true));
        ImageView atkAirL4 = new ImageView(new Image(ClassLoader.getSystemResource("atkAirL4.png").toString(), 500,260,true,true));
        ImageView atkAirL5 = new ImageView(new Image(ClassLoader.getSystemResource("atkAirL5.png").toString(), 500,260,true,true));
        ImageView atkAirL6 = new ImageView(new Image(ClassLoader.getSystemResource("atkAirL6.png").toString(), 500,260,true,true));
        ImageView atkAirL7 = new ImageView(new Image(ClassLoader.getSystemResource("atkAirL7.png").toString(), 500,260,true,true));
        ImageView atkAirL8 = new ImageView(new Image(ClassLoader.getSystemResource("atkAirL8.png").toString(), 500,260,true,true));
        ImageView atkAirL9 = new ImageView(new Image(ClassLoader.getSystemResource("atkAirL9.png").toString(), 500,260,true,true));
        ImageView atkAirL10 = new ImageView(new Image(ClassLoader.getSystemResource("atkAirL10.png").toString(), 500,260,true,true));

        idles = new ImageView[]{idle1, idle2, idle3, idle4};
        idlesLeft = new ImageView[]{idleL1, idleL2, idleL3, idleL4};
        walks = new ImageView[]{walk1,walk2,walk3,walk4,walk5,walk6};
        walksLeft = new ImageView[]{walkL1,walkL2,walkL3,walkL4,walkL5,walkL6};
        jumpStarts = new ImageView[]{jumpS1, jumpS2};
        jumps = new ImageView[]{jump1, jump2, jump3};
        jumpStartsLeft = new ImageView[]{jumpSL1, jumpSL2};
        jumpsLeft = new ImageView[]{jumpL1, jumpL2, jumpL3};
        dashes = new ImageView[]{dash1,dash2,dash3};
        dashStarts = new ImageView[]{dashS1, dashS2};
        dashesLeft = new ImageView[]{dashL1,dashL2,dashL3};
        dashStartsLeft = new ImageView[]{dashSL1, dashSL2};
        falls = new ImageView[]{fall1,fall2,fall3};
        fallsLeft = new ImageView[]{fallL1,fallL2,fallL3};
        atks = new ImageView[]{atk1,atk2,atk3,atk4,atk5,atk6,atk7,atk8,atk9,atk10};
        atksLeft = new ImageView[]{atkL1,atkL2,atkL3,atkL4,atkL5,atkL6,atkL7,atkL8,atkL9,atkL10};
        atkAirs = new ImageView[]{atkAir1,atkAir2,atkAir3,atkAir4,atkAir5,atkAir6,atkAir7,atkAir8,atkAir9,atkAir10};
        atkAirsLeft = new ImageView[]{atkAirL1,atkAirL2,atkAirL3,atkAirL4,atkAirL5,atkAirL6,atkAirL7,atkAirL8,atkAirL9,atkAirL10};
        idleFrame = 0;
        walkFrame = 0;
        jumpFrame = 0;
        fallFrame = 0;
    }
    public void updateImage(Player player){
        var movement = player.getMovement();
       if (player.getAttack().getIsAttack()){
            if (!movement.getIsJump()){
                if (movement.getDirection()==1){
                    setImage(atks[player.getAttack().getAttackFrame()/2]);
                    setPosX(player.getPosX() - 105);
                    setPosY(player.getPosY() - 80);
                }else if (movement.getDirection()==-1){
                    setImage(atksLeft[player.getAttack().getAttackFrame()/2]);
                    setPosX(player.getPosX() - 93);
                    setPosY(player.getPosY() - 80);
                }
            }
            if (player.getAttack().getIsAttackAir()){
                if (movement.getDirection()==1){
                    setImage(atkAirs[player.getAttack().getAttackFrame()/2]);
//                    setImage(atkAirs[9]);
                    setPosX(player.getPosX() - 105);
                    setPosY(player.getPosY() - 80);
                }else if (movement.getDirection()==-1){
                    setImage(atkAirsLeft[player.getAttack().getAttackFrame()/2]);
//                    setImage(atkAirsLeft[9]);
                    setPosX(player.getPosX() - 93);
                    setPosY(player.getPosY() - 80);
                }
            }
        }
        else if (movement.getIsIdle()){
            if(movement.getDirection()==1) {
                setImage(idles[idleFrame / 6]);
                setPosX(player.getPosX()-105);
            }else{
                ImageView i = idlesLeft[idleFrame/6];
                setImage(i);
                setPosX(player.getPosX()-93);
            }
            setPosY(player.getPosY()-80);
            idleFrame ++;
            if (idleFrame == 24){
                idleFrame = 0;
            }
        }else if (!movement.getIsJump()&&!movement.getIsDash()&&!movement.isMoveDown()){
            if (movement.getIsMoveRight()) {
                setImage(walks[walkFrame / 7]);
                setPosX(player.getPosX() - 105);
            }else if(movement.getIsMoveLeft()) {
                setImage(walksLeft[walkFrame/7]);
                setPosX(player.getPosX()-93);
            }
            setPosY(player.getPosY() - 80);
            walkFrame ++;
            if (walkFrame == 42){
                walkFrame = 0;
            }
        }
        else if (movement.getIsJump()&&!movement.isMoveDown()){
            if (movement.getDirection()==1) {
                if (jumpFrame < 6) {
                    setImage(jumpStarts[jumpFrame / 3]);
                } else {
                    setImage(jumps[(jumpFrame - 6) / 3]);
                }
                setPosX(player.getPosX() - 105);
                setPosY(player.getPosY() - 80);
            }
            else if (movement.getDirection()==-1){
                if (jumpFrame < 6) {
                    setImage(jumpStartsLeft[jumpFrame / 3]);
                } else {
                    setImage(jumpsLeft[(jumpFrame - 6) / 3]);
                }
                setPosX(player.getPosX() - 93);
                setPosY(player.getPosY() - 80);
            }
            jumpFrame ++;
            if (jumpFrame == 15){
                jumpFrame = 6;
            }
        }
        else if (movement.isMoveDown()){
            if (movement.getDirection()==1){
                setImage(falls[fallFrame/4]);
                setPosX(player.getPosX() - 105);
                setPosY(player.getPosY() - 80);
            }else if (movement.getDirection()==-1){
                setImage(fallsLeft[fallFrame/4]);
                setPosX(player.getPosX() - 93);
                setPosY(player.getPosY() - 80);
            }
            fallFrame++;
            if (fallFrame == 11){
                fallFrame = 0;
            }
        }
        else if (movement.getIsDash() && !movement.getIsJump()){
            var dashFrame = movement.getDashFrame();
            while (dashFrame >= 15){
                dashFrame -= 9;
            }
            if (movement.getDirection()==1) {
                if (dashFrame < 6) {
                    setImage(dashStarts[dashFrame/3]);
                }else {
                    setImage(dashes[(dashFrame-6)/3]);
                }
                setPosX(player.getPosX() - 105);
                setPosY(player.getPosY() - 80);
            } else if (movement.getDirection() == -1){
                if (dashFrame < 6) {
                    setImage(dashStartsLeft[dashFrame/3]);
                }else {
                    setImage(dashesLeft[(dashFrame-6)/3]);
                }
                setPosX(player.getPosX() - 93);
                setPosY(player.getPosY() - 80);
            }
        }
        else{
            walkFrame = 0;
            jumpFrame = 0;
            setImage("player.png");
            setPosX(player.getPosX());
            setPosY(player.getPosY());
        }

    }
}
