package logic;


import entity.Platform;
import entity.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class GameController {
    private static double lastOffsetX = 0;
    private static double lastOffsetY;
    public static GameMap initializeMap(String[] level, Pane root, Player player){
        GameMap gameMap = new GameMap(level);
        gameMap.createMap(root, player);
        lastOffsetY = level.length*60;
        return gameMap;
    }
    public static void camera(Player player, String[] level, Pane root, Canvas canvas, GraphicsContext gc){
        double offsetX = player.getPosX()+player.getWidth();
        double offsetY = player.getPosY()+player.getHeight();
        if (offsetX > 600 && offsetX < level[0].length()*60-600){
            root.setTranslateX(-(offsetX-600));
            canvas.setTranslateX(offsetX-600);
            lastOffsetX = -offsetX+600;
        }
        if (offsetY > 100 && offsetY < level.length*60-450){
            root.setTranslateY(-(offsetY-450));
            canvas.setTranslateY(offsetY-450);
            lastOffsetY = -offsetY+450;
        }
        if (lastOffsetY == level.length*60){
            offsetY = level.length*60-450;
            root.setTranslateY(-(offsetY-450));
            canvas.setTranslateY(offsetY-450);
            lastOffsetY = -offsetY+450;
        }
        player.renderSprite(gc,lastOffsetX,lastOffsetY);
    }
}
