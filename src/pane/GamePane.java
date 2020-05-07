package pane;

import entity.Enemy;
import entity.Platform;
import entity.Player;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Arrays;

public class GamePane extends Pane {
    private Canvas canvas;
    private GraphicsContext gc;
    private ArrayList<Platform> platforms;
    private ArrayList<Enemy>enemies;
    private Player player;
    public GamePane(int width, int height){
        canvas = new Canvas(width, height);
        gc = canvas.getGraphicsContext2D();
        player = new Player(30, height - 600, 5,18);
        platforms = new ArrayList<>();
        enemies = new ArrayList<>();
//        this.getChildren().add(canvas);

    }
    public void addListener(Scene scene,Player player){
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()){
                case LEFT:
                    player.getMovement().setMoveLeft(true);
                    break;
                case RIGHT:
                    player.getMovement().setMoveRight(true);
                    break;
                case C:
                    player.getMovement().jumpInit();
                    break;
                case X:
                    player.getMovement().dashInit();
                    break;
                case V:
                    player.getAttack().attackInit();
            }
        });
        scene.setOnKeyReleased(e -> {
            switch (e.getCode()){
                case LEFT:
                    player.getMovement().setMoveLeft(false);
                    break;
                case RIGHT:
                    player.getMovement().setMoveRight(false);
                    break;
                case C:
                    player.getMovement().jumpRelease();
                    break;
                case X:
                    player.getMovement().dashRelease();
                    break;
                case V:
                    player.getAttack().attackRelease();
            }
        });
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public ArrayList<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(ArrayList<Platform> platforms) {
        this.platforms = platforms;
    }
}
