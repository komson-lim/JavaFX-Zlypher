package pane;

import entity.Player;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameUI extends Pane {
    private int health;
    private Text text;
    public GameUI(Player player){
        this.health = player.getHealth();
        text = new Text();
        text.setText(String.valueOf(health));
        text.setFill(Color.BLACK);
        text.setFont(new Font("arial", 25));
        text.setX(500);
        text.setY(500);
        this.getChildren().add(text);
    }
    public void update(Player player){
        this.health = player.getHealth();
        text.setText(String.valueOf(health));
    }
}
