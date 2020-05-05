package logic;

import entity.Enemy;
import entity.Platform;
import entity.Player;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class GameMap {
    private String[] level;
    private ArrayList<Platform> platforms;
    private ArrayList<Enemy> enemies;
    public GameMap(String[] level){
        this.level = level;
        platforms = new ArrayList<>();
        enemies = new ArrayList<>();
    }
    public void createMap(Pane root, Player player){
        for (int i = 0; i < level.length;i++){
            String line = level[i];
            for (int j = 0; j< line.length(); j++){
                switch (line.charAt(j)){
                    case '0':
                        break;
                    case '1':
                        Platform platform = new Platform(j*60, i*60);
                        platforms.add(platform);
                        platform.render(root);
                        platform.updatePos();
                        break;
                    case '2':
                        Platform wall = new Platform(j*60, i*60);
                        wall.changeColor(Color.rgb(0,0,0,0));
                        platforms.add(wall);
                        wall.render(root);
                        wall.updatePos();
                        break;
                    case 'x':
                        player.setPosX(j*60);
                        player.setPosY(i*60);
                        break;
                    case 'e':
                        Enemy enemy = new Enemy(j*60, i*60, 1);
                        enemies.add(enemy);
                        enemy.render(root);
                        enemy.updatePos();
                        break;
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
}
