package entity;

import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import pane.GamePane;
import pane.GameUI;

public abstract class Hitbox {
    protected Rectangle hitbox;
    protected double posX;
    protected double posY;
    protected double width;
    protected double height;
    protected ImageView image;
    protected boolean hasImage;
    public Hitbox(double posX, double posY){
        this.posX = posX;
        this.posY = posY;
        this.width = 0;
        this.height = 0;
        hasImage = false;
    }
    public void setHitbox(Rectangle hitbox){
        this.hitbox = hitbox;
        this.height = hitbox.getHeight();
        this.width = hitbox.getWidth();
    }
    public void setHitbox(ImageView image){
        this.image = image;
        hasImage = true;
    }
    public void render(GamePane gamePane){
        if (hasImage){
            gamePane.getChildren().add(image);
        }else {
            gamePane.getChildren().add(hitbox);
        }
    }
    public void kill(GamePane gamePane){
        if (hasImage){
            gamePane.getChildren().remove(image);
        }else {
            gamePane.getChildren().remove(hitbox);
        }
    }
    public void updatePos(){
        if (hasImage){
            image.setTranslateX(posX);
            image.setTranslateY(posY);
        }else {
            hitbox.setTranslateX(posX);
            hitbox.setTranslateY(posY);
        }
    }
    public Bounds getBoundary(){
        if (hasImage){
            return image.getBoundsInParent();
        }
        return hitbox.getBoundsInParent();
    }
    public boolean intersects(Hitbox h){
        if (isEmpty()||h.isEmpty()){
            return false;
        }
        return this.getBoundary().intersects(h.getBoundary());
    }
    public void setPosX(double posX) {
        this.posX = posX;
        this.updatePos();
    }
    public double getPosX() {
        return posX;
    }
    public void setPosY(double posY) {
        this.posY = posY;
        this.updatePos();
    }
    public double getPosY() {
        return posY;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
    public boolean isEmpty(){
        if (hasImage){
            return false;
        }
        return (hitbox.getWidth()==0)&&(hitbox.getHeight()==0);
    }
}
