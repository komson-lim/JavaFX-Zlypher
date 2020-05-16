package application;

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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import logic.GameController;
import pane.*;
import stage.LevelData;
import stage.NoLevelDataException;

import java.util.ArrayList;
import java.util.HashMap;


public class Main extends Application {
    private static int width = 1200;
    private static int height = 900;
    private HashMap<KeyCode, Boolean> keys = new HashMap<>();
    private static Pane root = new StackPane();
    private Pane rootMenu = new StackPane();
    private static GamePane gamePane = new GamePane(width,height);
    private static Canvas canvas = gamePane.getCanvas();
    private GraphicsContext gc = canvas.getGraphicsContext2D();
    private static Player player = gamePane.getPlayer();
    private GameBackground gameBackground = new GameBackground(width, height);
    private static LoadScreen loadScreen = new LoadScreen(width,height,player);
    private MenuPane menuPane = new MenuPane(width, height);
    private GameOverPane gameOverPane = new GameOverPane(width, height);
    private HowToPlayPane howToPlayPane = new HowToPlayPane();
    private WinPane winPane = new WinPane(width, height);
    private GameBackgroundParallax gameBackgroundParallax = new GameBackgroundParallax(width,height);
    private Scene scene;
    private Scene mainMenu;
    private static ArrayList<Platform>platforms;
    private static ArrayList<Enemy>enemies;
    private static String[] currentLevel;
    private static int currentLevelNumber;
    private static ArrayList<Interactable>interactables = new ArrayList<>();
    private ArrayList<Platform> nearPlatforms = new ArrayList<>();
    private ObservableList<Platform> list = FXCollections.observableArrayList();
    private ArrayList<Platform> toAdd = new ArrayList<>();
    private ArrayList<Platform> toRemove = new ArrayList<>();
    private GameUI gameUI = new GameUI(player);
    private static AnimationTimer timer;

//    public void initializeLevel(int levelNumber) throws NoLevelDataException {
//        GameController.setIsFinish(false);
//        currentLevelNumber = levelNumber;
//        currentLevel = LevelData.getLevel(levelNumber);
//        GameMap gameMap = GameController.initializeMap(currentLevel, gamePane, player);
//        platforms = gameMap.getPlatforms();
//        enemies = gameMap.getEnemies();
//        interactables = gameMap.getInteractables();
//
//    }
    private void reload(int levelNumber) throws NoLevelDataException{
        reset(levelNumber);
        initializeLevel(levelNumber);

    }
    private void reset(int levelNumber) throws NoLevelDataException{
        gamePane.getMediaPlayer().stop();
        player.setHealth(20);
        player.getMovement().setDirection(1);
        player.setIsInvincible(false);
        gamePane.getChildren().clear();
        player.getMovement().stopMovement();
        player.getAttack().attackEnd();
        GameController.resetOffset(LevelData.getLevel(levelNumber));
        gameBackgroundParallax.reset();
    }
    public static void initializeLevel(int levelNumber){
        try {
            currentLevel = LevelData.getLevel(levelNumber);
        } catch (NoLevelDataException e) {
            e.printStackTrace();
        }
        root.getChildren().add(loadScreen);
        Thread load = new Thread(new Runnable() {
            @Override
            public void run() {
                javafx.application.Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        GameController.initializedLevel(currentLevelNumber,gamePane,player);
                        platforms = GameController.getPlatforms();
                        enemies = GameController.getEnemies();
                        interactables = GameController.getInteractables();
                        root.getChildren().remove(loadScreen);
                        gamePane.getChildren().add(canvas);
                        timer.start();
                        gamePane.getMediaPlayer().play();
                    }
                });
            }
        });
        load.start();

    }
    private Parent createContent() throws NoLevelDataException{
        root.getChildren().add(gameBackground);
        root.getChildren().add(gameBackgroundParallax);
        currentLevelNumber = 1;
//        currentLevel = LevelData.getLevel(1);
//        GameController.initializedLevel(1,gamePane,player);
//        platforms = GameController.getPlatforms();
//        enemies = GameController.getEnemies();
//        interactables = GameController.getInteractables();
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
                try {
                    update(gc);
                } catch (NoLevelDataException e) {
                    e.printStackTrace();
                }
            }
        };
        gamePane.setPrefSize(width,height);
        root.getChildren().add(gamePane);
//        gamePane.getChildren().add(canvas);
        gameUI.setPrefSize(width,height);
        root.getChildren().add(gameUI);
//        timer.start();
        list.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                System.out.println(list.size());
            }
        });
        return root;
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

    private void update(GraphicsContext gc) throws NoLevelDataException {
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
        GameController.camera(player, currentLevel, gamePane, canvas,gc,tempEnemies,gameBackgroundParallax);
        player.getAttack().update(player);
        gameUI.update(player);
        if (player.getPosY() > canvas.getTranslateY()+width+player.getHeight()+10){
            player.setHealth(0);
        }
        if (player.getHealth() == 0){
            player.setLives(player.getLives()-2);
            if (player.getLives() < 0){
                root.getChildren().add(gameOverPane);
                player.setLives(3);
                currentLevelNumber=1;
                reset(currentLevelNumber);
                loadScreen.setLives(player.getLives());
                timer.stop();
            }else {
                reload(currentLevelNumber);
                loadScreen.setLives(player.getLives());
            }
        }
        if (GameController.getIsFinish()){
            currentLevelNumber++;
            if (currentLevelNumber == 3){
                root.getChildren().add(winPane);
                player.setLives(3);
                currentLevelNumber=1;
                reset(currentLevelNumber);
                loadScreen.setLives(player.getLives());
                timer.stop();
            }else {
                reload(currentLevelNumber);
                loadScreen.setLives(player.getLives());
                GameController.setIsFinish(false);
            }
        }
        clearOutOfBound();


    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        scene = new Scene(createContent());
        rootMenu.getChildren().add(menuPane);
        mainMenu = new Scene(rootMenu);
        mainMenu.getStylesheets().add(ClassLoader.getSystemResource("MenuStyle.css").toString());
        menuPane.addListener(scene,primaryStage,rootMenu,howToPlayPane);
        gamePane.addListener(scene,player);
        gameOverPane.addListener(mainMenu,primaryStage,root,menuPane);
        howToPlayPane.addListener(rootMenu);
        winPane.addListener(mainMenu,primaryStage,root,menuPane);
        primaryStage.setScene(mainMenu);
        primaryStage.setTitle("Platformer");
        primaryStage.setResizable(false);
        menuPane.play();
        primaryStage.show();
    }


    public static void main(String[] args) {
        System.setProperty("prism.lcdtext", "false");
        launch(args);
    }
}
