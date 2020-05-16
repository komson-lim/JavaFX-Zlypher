package entity;



import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;


public class Platform extends Hitbox{
    private ImageView block;
    public Platform(double posX, double posY){
        super(posX, posY);
//        Rectangle r = new Rectangle(60,60);
//        r.setFill(Color.RED);
//        setHitbox(r);
    }
    public Platform(double posX, double posY, int number){
        super(posX,posY);
        block = new ImageView(new Image(ClassLoader.getSystemResource("block"+number+".png").toString(), 60,60,true,true));
        setHitbox(block);
        width = 60;
        height = 60;
    }
    public void changeColor(Paint paint){
        Rectangle r = new Rectangle(60,60);
        r.setFill(paint);
        setHitbox(r);
    }
//    public Bounds getBoundary(){
//        return block.getBoundsInParent();
//    }
//    public boolean intersects(Hitbox h){
//        if (block==null) {
//            return super.intersects(h);
//        }
//        return block.getBoundsInParent().intersects(h.getBoundary());
//    }
//    public void render(GamePane gamePane){
//        if (block==null){
//            super.render(gamePane);
//        }
//        else {
//            gamePane.getChildren().add(block);
//        }
//    }
//    public void kill(GamePane gamePane){
//        if (block==null){
//            super.kill(gamePane);
//        }else {
//            gamePane.getChildren().remove(block);
//        }
//    }
//    public void updatePos(){
//        if (block==null){
//            super.updatePos();
//        }else {
//            block.setTranslateX(posX);
//            block.setTranslateY(posY);
//        }
//    }
}
