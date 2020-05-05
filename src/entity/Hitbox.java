package entity;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public abstract class Hitbox {
    protected Rectangle hitbox;
    protected double posX;
    protected double posY;
    protected double width;
    protected double height;
    public Hitbox(double posX, double posY){
        this.posX = posX;
        this.posY = posY;
        this.width = 0;
        this.height = 0;
    }
    public void setHitbox(Rectangle hitbox){
        this.hitbox = hitbox;
        this.height = hitbox.getHeight();
        this.width = hitbox.getWidth();
    }
    public void render(Pane root){
        root.getChildren().add(hitbox);
    }
    public void kill(Pane root){
        root.getChildren().remove(hitbox);
    }
    public void updatePos(){
        hitbox.setTranslateX(posX);
        hitbox.setTranslateY(posY);
    }
    public Bounds getBoundary(){
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
        return (hitbox.getWidth()==0)&&(hitbox.getHeight()==0);
    }
}
