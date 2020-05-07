package sample;

import entity.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import logic.GameController;
import logic.GameMap;
import logic.GameUpdateHelper;
import pane.GameBackground;
import pane.GamePane;
import pane.GameUI;
import stage.LevelData;

import java.util.ArrayList;
import java.util.HashMap;


public class Main extends Application {
    private static int width = 1200;
    private static int height = 900;
    private HashMap<KeyCode, Boolean> keys = new HashMap<>();
    private Pane root = new StackPane();
    private GamePane gamePane = new GamePane(width,height);
    private Canvas canvas = gamePane.getCanvas();
    private GraphicsContext gc = canvas.getGraphicsContext2D();
    private Player player = gamePane.getPlayer();
    private GameBackground gameBackground = new GameBackground(width, height);
//    private Canvas canvas = new Canvas(width, height);
//    private GraphicsContext gc = canvas.getGraphicsContext2D();
    private ArrayList<Platform>platforms;
    private ArrayList<Enemy>enemies;
    private static String[] currentLevel;
    private static int currentLevelNumber;
    private ArrayList<Interactable>interactables = new ArrayList<>();
    private ArrayList<Platform> nearPlatforms = new ArrayList<>();
    private ObservableList<Platform> list = FXCollections.observableArrayList();
    private ArrayList<Platform> toAdd = new ArrayList<>();
    private ArrayList<Platform> toRemove = new ArrayList<>();
//    private Platform floor = new Platform(10,height-20);
//    private Platform floor2 = new Platform(100,height-180);
//    private Platform floor3 =  new Platform(200, height-180);
//    private Player player = new Player(30, height - 600, 5,18);
    private GameUI gameUI = new GameUI(player);
    private AnimationTimer timer;
//    private void eventListener(Scene scene){
//        scene.setOnKeyPressed(e -> {
//            switch (e.getCode()){
//                case LEFT:
//                    player.getMovement().setMoveLeft(true);
//                    break;
//                case RIGHT:
//                    player.getMovement().setMoveRight(true);
//                    break;
//                case C:
//                    player.getMovement().jumpInit();
//                    break;
//                case X:
//                    player.getMovement().dashInit();
//                    break;
//                case V:
//                    player.getAttack().attackInit();
//            }
//        });
//        scene.setOnKeyReleased(e -> {
//            switch (e.getCode()){
//                case LEFT:
//                    player.getMovement().setMoveLeft(false);
//                    break;
//                case RIGHT:
//                    player.getMovement().setMoveRight(false);
//                    break;
//                case C:
//                    player.getMovement().jumpRelease();
//                    break;
//                case X:
//                    player.getMovement().dashRelease();
//                    break;
//                case V:
//                    player.getAttack().attackRelease();
//            }
//        });
//    }
    public void initializeLevel(int levelNumber){
        currentLevelNumber = levelNumber;
        currentLevel = LevelData.getLevel(levelNumber);
        GameMap gameMap = GameController.initializeMap(currentLevel, gamePane, player);
        platforms = gameMap.getPlatforms();
        enemies = gameMap.getEnemies();
        interactables = gameMap.getInteractables();
    }
    private void reset(int levelNumber){
        player.setHealth(20);
        player.setIsInvincible(false);
        gamePane.getChildren().clear();
        player.getMovement().stopMovement();
        player.getAttack().attackEnd();
        GameController.resetOffset(LevelData.getLevel(levelNumber));
        initializeLevel(levelNumber);
        gamePane.getChildren().add(canvas);

    }
    private Parent createContent(){
        root.getChildren().add(gameBackground);
        initializeLevel(1);
//        player.render(gamePane);
//        for (Platform f : platforms){
//            f.render(root);
//            f.updatePos();
//        }
        player.updatePos();
        player.getAttack().getAtkHB().render(gamePane);
//        root.getChildren().add(canvas);
        root.setPrefSize(width, height);
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update(gc);
            }
        };
        gamePane.setPrefSize(width,height);
        root.getChildren().add(gamePane);
        gamePane.getChildren().add(canvas);
        gameUI.setPrefSize(width,height);
        root.getChildren().add(gameUI);
        timer.start();
//        nearPlatforms = GameUpdateHelper.checkNear(platforms,width,height,gamePane);
//        list = GameUpdateHelper.checkNear(platforms,width,height,gamePane);
        checkNear(platforms,width,height,canvas,list,toAdd,toRemove);
        list.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                System.out.println(list.size());
            }
        });
        return root;
    }
    public void checkNear(ArrayList<Platform> platforms,int width, int height ,Canvas canvas,ObservableList<Platform> list,ArrayList<Platform>toAdd,ArrayList<Platform>toRemove){
        double posX = canvas.getTranslateX();
        double posY = canvas.getTranslateY();
//        ArrayList<Platform> nearPlatforms = new ArrayList<>();
//        ObservableList<Platform> list = FXCollections.observableArrayList();
        for (Platform p : platforms){
            if (p.getPosX() >= posX-500 && p.getPosX() <= posX+width+500){
                if (p.getPosY() >= posY-500 && p.getPosY() <= posY+height+500){
                    if (!list.contains(p)) {
//                        list.add(p);
                        toAdd.add(p);

                    }
                }else{
//                    list.remove(p);
                    toRemove.add(p);
                }
            }else{
//                list.remove(p);
                toRemove.add(p);
            }
        }
        list.addAll(toAdd);
        list.removeAll(toRemove);
        toAdd.clear();
        toRemove.clear();
    }
    private void clearOutOfBound() {
        double posX = canvas.getTranslateX();
        double posY = canvas.getTranslateY();
        var tempInteractable = interactables.toArray();
        for (Object o : tempInteractable) {
            var i = (Interactable)o;
            if (i instanceof Consumable) {
                var h = (Hitbox) i;
                if (h.getPosX() >= posX - 100 && h.getPosX() <= posX + width + 100) {
                    if (h.getPosY() >= posY - 100 && h.getPosY() <= posY + height + 100) {
                        if (!interactables.contains(h)) {
                            interactables.add(i);
                        }
                    } else {
                        interactables.remove(i);
                        ((Consumable) i).consume(gamePane);
                    }
                } else {
                    interactables.remove(i);
                    ((Consumable) i).consume(gamePane);
                }
            }
        }
    }

    private void update(GraphicsContext gc){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
//                GameUpdateHelper.checkNear(platforms,width,height,gamePane);
//                list = GameUpdateHelper.checkNear(platforms,width,height,gamePane);
                checkNear(platforms,width,height,canvas,list,toAdd,toRemove);

            }
        });
//        thread.start();
        player.setOnPlatforms(false);
        player.setHitAbovePlatforms(false);
        player.setHitSidePlatforms(false);
        gc.clearRect(0,0,width,height);
        player.update();
        var tempEnemies = enemies.toArray();
        for (Object e : tempEnemies){
            var enemy = (Enemy)e;
            player.getAttack().updateAttack(player,enemy);
            enemy.update(player, gamePane, enemies,interactables);
        }
//        var temp = list.toArray();
        var temp = platforms.toArray();
        for (Object o : temp) {
            var f = (Platform)o;
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
        var tempInteractables = interactables.toArray();
        for (Object o : tempInteractables){
            var i = (Interactable)o;
            if (player.intersects((Hitbox) i)) {
                i.interact(player, gamePane);
                interactables.remove(i);
            }
            if (i instanceof Updatable){
                ((Updatable) i).update();
            }
        }
        player.getMovement().jumpUpdate(player);
        if (!player.getIsOnPlatforms()){
            player.getMovement().gravity();
            player.updatePos();
        }
        GameController.camera(player, currentLevel, gamePane, canvas,gc);
//        System.out.println(player.getHealth());
        player.getAttack().update(player);
        gameUI.update(player);
        if (GameController.getIsFinish()){
            currentLevelNumber++;
            reset(currentLevelNumber);
            GameController.setIsFinish(false);
            try {
                thread.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        if (player.getPosY() > canvas.getTranslateY()+width+player.getHeight()+10){
            player.setHealth(0);
        }
        if (player.getHealth() == 0){
            reset(currentLevelNumber);
            player.setLives(player.getLives()-1);
        }
        clearOutOfBound();


    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        Scene scene = new Scene(createContent());
        gamePane.addListener(scene,player);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Platformer");
        primaryStage.setResizable(false);
//        eventListener(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        System.setProperty("prism.lcdtext", "false");
        launch(args);
    }
}
