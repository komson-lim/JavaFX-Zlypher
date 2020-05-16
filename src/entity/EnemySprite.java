package entity;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EnemySprite extends Sprite{
    ImageView[] enemyPic;
    ImageView[] enemyLPic;
    public EnemySprite(Enemy enemy){
        super(enemy.getPosX(),enemy.getPosY());
        ImageView enemy1 = new ImageView(new Image(ClassLoader.getSystemResource("enemy1.png").toString(), 500,260,true,true));
        ImageView enemy2 = new ImageView(new Image(ClassLoader.getSystemResource("enemy2.png").toString(), 500,260,true,true));
        ImageView enemy3 = new ImageView(new Image(ClassLoader.getSystemResource("enemy3.png").toString(), 500,260,true,true));
        ImageView enemy4 = new ImageView(new Image(ClassLoader.getSystemResource("enemy4.png").toString(), 500,260,true,true));
        ImageView enemyL1 = new ImageView(new Image(ClassLoader.getSystemResource("enemyL1.png").toString(), 500,260,true,true));
        ImageView enemyL2 = new ImageView(new Image(ClassLoader.getSystemResource("enemyL2.png").toString(), 500,260,true,true));
        ImageView enemyL3 = new ImageView(new Image(ClassLoader.getSystemResource("enemyL3.png").toString(), 500,260,true,true));
        ImageView enemyL4 = new ImageView(new Image(ClassLoader.getSystemResource("enemyL4.png").toString(), 500,260,true,true));
        enemyPic = new ImageView[]{enemy1,enemy2,enemy3,enemy4};
        enemyLPic = new ImageView[]{enemyL1,enemyL2,enemyL3,enemyL4};
    }
    public void updateImage(Enemy enemy){
        if (enemy.getIsAttack()){
            if (enemy.getDirection()==-1) {
                setImage(enemyLPic[enemy.getAttackFrame()/4+1]);
                setPosX(enemy.getPosX() - 93);
                setPosY(enemy.getPosY() - 100);
            }
            else if (enemy.getDirection()==1){
                setImage((enemyPic[enemy.getAttackFrame()/4+1]));
                setPosX(enemy.getPosX() - 93);
                setPosY(enemy.getPosY() - 100);
            }
        }
        else{
            if (enemy.getDirection()==-1){
                setImage(enemyLPic[0]);
                setPosX(enemy.getPosX() - 93);
                setPosY(enemy.getPosY() - 100);
            }else if (enemy.getDirection()==1){
                setImage(enemyPic[0]);
                setPosX(enemy.getPosX() -93);
                setPosY(enemy.getPosY() -100);

            }
        }
    }
}
