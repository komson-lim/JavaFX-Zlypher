package entity;

import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Sprite {
    protected ImageView image;
    protected double posX;
    protected double posY;
    protected double width;
    protected double height;
    public Sprite(double posX, double posY){
        this.posX = posX;
        this.posY = posY;
        this.width = 0;
        this.height = 0;
    }
    public void setImage(ImageView image){
        this.image = image;
        width = image.getImage().getWidth();
        height = image.getImage().getHeight();
    }
    public void setImage(String path){
        ImageView image = new ImageView(path);
        setImage(image);
    }

    public void render(GraphicsContext gc, double offsetX, double offsetY){
        gc.drawImage(image.getImage(), posX+offsetX, posY+offsetY);
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double getPosY() {
        return posY;
    }
}

