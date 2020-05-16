package logic;


import entity.*;
import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import pane.GameBackgroundParallax;
import pane.GamePane;
import stage.LevelData;
import stage.NoLevelDataException;

import java.util.ArrayList;

public class GameController {
    private static double lastOffsetX = 0;
    private static double lastOffsetY;
    private static boolean isFinish = false;
    private static String[] currentLevel;
    private static int currentLevelNumber;
    private static ArrayList<Platform> platforms;
    private static ArrayList<Enemy> enemies;
    private static ArrayList<Interactable> interactables;
    public static GameMap initializeMap(String[] level, GamePane gamePane, Player player){
        GameMap gameMap = new GameMap(level);
        gameMap.createMap(gamePane, player);
        lastOffsetY = level.length*60;
        return gameMap;
    }
    public static void camera(Player player, String[] level, GamePane gamePane, Canvas canvas, GraphicsContext gc, Object[] enemies, GameBackgroundParallax gameBackgroundParallax){
        double offsetX = player.getPosX()+player.getWidth();
        double offsetY = player.getPosY()+player.getHeight();
        if (offsetX > 600 && offsetX < level[0].length()*60-600){
            gamePane.setTranslateX(-(offsetX-600));
            canvas.setTranslateX(offsetX-600);
            if (player.getMovement().getIsMoveRight()&&offsetX!=-lastOffsetX+600){
                gameBackgroundParallax.update(-player.getMovement().getSpeedX());
            }else if (player.getMovement().getIsMoveLeft()&&offsetX!=-lastOffsetX+600){
                gameBackgroundParallax.update(-player.getMovement().getSpeedX());
            }
            lastOffsetX = -offsetX+600;

        }
        if (offsetY > 100 && offsetY < level.length*60-450){
            gamePane.setTranslateY(-(offsetY-450));
            canvas.setTranslateY(offsetY-450);
            lastOffsetY = -offsetY+450;
        }
        if (lastOffsetY == level.length*60){
            offsetY = level.length*60-450;
            gamePane.setTranslateY(-(offsetY-450));
            canvas.setTranslateY(offsetY-450);
            lastOffsetY = -offsetY+450;
        }
        player.renderSprite(gc,lastOffsetX,lastOffsetY);
        for (Object o : enemies){
            var e = (Enemy)o;
            e.renderSprite(gc, lastOffsetX, lastOffsetY);
        }

    }
    public static void resetOffset(String[] level){
        lastOffsetX = 0;
        lastOffsetY = level.length*60;

    }
    public static ArrayList<Interactable> clearOutOfBound(ArrayList<Interactable> interactables, int width, int height , Canvas canvas){
        double posX = canvas.getTranslateX();
        double posY = canvas.getTranslateY();
        for (Interactable i : interactables){
            var h = (Hitbox)i;
            if (h.getPosX() >= posX-100 && h.getPosX() <= posX+width+100){
                if (h.getPosY() >= posY-100 && h.getPosY() <= posY+height+100){
                    if (!interactables.contains(h)) {
                        interactables.add(i);

                    }
                }else{
                    interactables.add(i);
                }
            }else{
                interactables.add(i);
            }
        }
        return interactables;
    }
    public static void initializedLevel(int levelNumber, GamePane gamePane, Player player){
        GameController.setIsFinish(false);
        currentLevelNumber = levelNumber;
        try {
            currentLevel = LevelData.getLevel(levelNumber);
        } catch (NoLevelDataException e) {
            e.printStackTrace();
        }
        GameMap gameMap = GameController.initializeMap(currentLevel, gamePane, player);
        platforms = gameMap.getPlatforms();
        enemies = gameMap.getEnemies();
        interactables = gameMap.getInteractables();
    }

    public static ArrayList<Platform> getPlatforms() {
        return platforms;
    }

    public static ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public static ArrayList<Interactable> getInteractables() {
        return interactables;
    }

    public static boolean getIsFinish(){
        return isFinish;
    }
    public static void setIsFinish(boolean b){
        isFinish = b;
    }

}
