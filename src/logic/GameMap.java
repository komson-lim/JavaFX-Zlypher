package logic;

import entity.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import pane.GamePane;

import java.util.ArrayList;

public class GameMap {
    private String[] level;
    private ArrayList<Platform> platforms;
    private ArrayList<Enemy> enemies;
    private ArrayList<Interactable> interactables;
    public GameMap(String[] level){
        this.level = level;
        platforms = new ArrayList<>();
        enemies = new ArrayList<>();
        interactables = new ArrayList<>();

    }
    public void createMap(GamePane gamePane, Player player){
        gamePane.setTranslateX(0);
        gamePane.setTranslateY(0);
        gamePane.getCanvas().setTranslateX(0);
        gamePane.getCanvas().setTranslateY(0);
            for (int i = 0; i < level.length;i++){
            String line = level[i];
            for (int j = 0; j< line.length(); j++){
                switch (line.charAt(j)){
                    case '0':
                        break;
                    case '1':
                        Platform platform = new Platform(j*60, i*60);
                        platforms.add(platform);
                        platform.render(gamePane);
                        platform.updatePos();
                        break;
                    case '2':
                        Platform wall = new Platform(j*60, i*60);
                        wall.changeColor(Color.rgb(0,0,0,0));
                        platforms.add(wall);
                        wall.render(gamePane);
                        wall.updatePos();
                        break;
                    case 'x':
                        player.setPosX(j*60);
                        player.setPosY(i*60);
                        player.updatePos();
                        break;
                    case 'e':
                        Enemy enemy = new Enemy(j*60, i*60, 1);
                        enemies.add(enemy);
                        enemy.render(gamePane);
                        enemy.updatePos();
                        break;
                    case 'f':
                        FinishPoint finishPoint = new FinishPoint(j*60, i*60);
                        interactables.add(finishPoint);
                        finishPoint.render(gamePane);
                        finishPoint.updatePos();
                }
            }
        }
    }

    public ArrayList<Platform> getPlatforms() {
        return platforms;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<Interactable> getInteractables() {
        return interactables;
    }
}
