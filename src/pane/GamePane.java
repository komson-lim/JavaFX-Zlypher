package pane;

import entity.Enemy;
import entity.Platform;
import entity.Player;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class GamePane extends Pane {
    private Canvas canvas;
    private GraphicsContext gc;
    private ArrayList<Platform> platforms;
    private ArrayList<Enemy>enemies;
    private String[] currentLevel;
    private Player player;
    public GamePane(int width, int height){
        canvas = new Canvas(width, height);
        gc = canvas.getGraphicsContext2D();
        player = new Player(30, height - 600, 5,18);
    }
    private void addListener(Scene scene, Player player){
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
}
