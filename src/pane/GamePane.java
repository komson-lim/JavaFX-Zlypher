package pane;

import entity.Enemy;
import entity.Platform;
import entity.Player;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;

public class GamePane extends Pane {
    private Canvas canvas;
    private GraphicsContext gc;
    private ArrayList<Platform> platforms;
    private ArrayList<Enemy>enemies;
    private Player player;
    private MediaPlayer mediaPlayer;
    public GamePane(int width, int height){
        canvas = new Canvas(width, height);
        gc = canvas.getGraphicsContext2D();
        player = new Player(30, height - 600, 5,18);
        platforms = new ArrayList<>();
        enemies = new ArrayList<>();
        mediaPlayer = new MediaPlayer(new Media(ClassLoader.getSystemResource("stage.mp3").toString()));
        mediaPlayer.setVolume(0.3);
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
                mediaPlayer.play();
            }
        });
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

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
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
