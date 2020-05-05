package sample;

import entity.Enemy;
import entity.Platform;
import entity.Player;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.GameController;
import logic.GameMap;
import pane.GameUI;
import stage.LevelData;

import java.util.ArrayList;
import java.util.HashMap;

public class Main extends Application {
    private static int width = 1200;
    private static int height = 900;
    private HashMap<KeyCode, Boolean> keys = new HashMap<>();
    private Pane root = new Pane();
    private Canvas canvas = new Canvas(width, height);
    private GraphicsContext gc = canvas.getGraphicsContext2D();
    private ArrayList<Platform>platforms;
    private ArrayList<Enemy>enemies;
    private String[] currentLevel;
//    private Platform floor = new Platform(10,height-20);
//    private Platform floor2 = new Platform(100,height-180);
//    private Platform floor3 =  new Platform(200, height-180);
    private Player player = new Player(30, height - 600, 5,18);
    private Pane gameUI = new GameUI(player);
    private AnimationTimer timer;
    private void eventListener(Scene scene){
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
    private Parent createContent(){
        currentLevel = LevelData.LEVEL2;
        GameMap gameMap = GameController.initializeMap(currentLevel, root, player);
        platforms = gameMap.getPlatforms();
        enemies = gameMap.getEnemies();
//        player.render(root);
//        for (Platform f : platforms){
//            f.render(root);
//            f.updatePos();
//        }
        player.updatePos();
//        player.getAttack().getAtkHB().render(root);
        root.getChildren().add(canvas);
        root.setPrefSize(width, height);
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update(gc);
            }
        };
        timer.start();
        gameUI.setPrefSize(width,height);
        root.getChildren().add(gameUI);
        return root;
    }
    private void update(GraphicsContext gc){
        player.setOnPlatforms(false);
        player.setHitAbovePlatforms(false);
        player.setHitSidePlatforms(false);
        gc.clearRect(0,0,width,height);
        player.update();
        var tempEnemies = enemies.toArray();
        for (Object e : tempEnemies){
            var enemy = (Enemy)e;
            player.getAttack().updateAttack(player,enemy);
            enemy.update(player, root, enemies);
        }
        for (Platform f : platforms) {
            if(player.isOnPlatform(f)){
                player.setOnPlatforms(true);
            }else{
                if (player.hitSidePlatform(f)){
                    player.setHitSidePlatforms(true);
                }
            }
            if (player.hitAbovePlatform(f)){
                player.setHitAbovePlatforms(true);
            }
        }
        player.getMovement().jumpUpdate(player);
        if (!player.getIsOnPlatforms()){
            player.getMovement().gravity();
            player.updatePos();
        }
        GameController.camera(player, currentLevel, root, canvas,gc);
//        System.out.println(player.getHealth());

    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        Scene scene = new Scene(createContent());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Platformer");
        primaryStage.setResizable(false);
        eventListener(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        System.setProperty("prism.lcdtext", "false");
        launch(args);
    }
}
