package logic;


import entity.Hitbox;
import entity.Interactable;
import entity.Platform;
import entity.Player;
import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import pane.GamePane;
import stage.LevelData;

import java.util.ArrayList;

public class GameController {
    private static double lastOffsetX = 0;
    private static double lastOffsetY;
    private static boolean isFinish = false;
    public static GameMap initializeMap(String[] level, GamePane gamePane, Player player){
        GameMap gameMap = new GameMap(level);
        gameMap.createMap(gamePane, player);
        lastOffsetY = level.length*60;
        return gameMap;
    }
    public static void camera(Player player, String[] level, GamePane gamePane, Canvas canvas, GraphicsContext gc){
        double offsetX = player.getPosX()+player.getWidth();
        double offsetY = player.getPosY()+player.getHeight();
        if (offsetX > 600 && offsetX < level[0].length()*60-600){
            gamePane.setTranslateX(-(offsetX-600));
            canvas.setTranslateX(offsetX-600);
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
    public static boolean getIsFinish(){
        return isFinish;
    }
    public static void setIsFinish(boolean b){
        isFinish = b;
    }

}
