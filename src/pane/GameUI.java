package pane;

import entity.Player;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameUI extends Pane {
    private int health;
    private Text healthText;
    private ProgressBar pb;
    private int lives;
    private Text livesText;
    public GameUI(Player player){
        this.health = player.getHealth();
        healthText = new Text();
        healthText.setText(String.valueOf(health));
        healthText.setFill(Color.BLACK);
        healthText.setFont(new Font("arial", 25));
        healthText.setX(500);
        healthText.setY(50);
        pb = new ProgressBar(0);
        pb.setPrefSize(200,25);
        pb.setTranslateX(10);
        pb.setTranslateY(30);
        pb.setStyle("-fx-accent: green;");
        this.lives = player.getLives();
        livesText = new Text();
        livesText.setText(String.valueOf(lives));
        livesText.setFill(Color.BLACK);
        livesText.setFont(new Font("arial", 25));
        livesText.setX(1000);
        livesText.setY(50);
        this.getChildren().addAll(healthText,pb,livesText);
    }
    public void update(Player player){
        this.health = player.getHealth();
        this.lives = player.getLives();
        healthText.setText(String.valueOf(health));
        livesText.setText(String.valueOf(lives));
        pb.setProgress((double)health/20);
    }
}
