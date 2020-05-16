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
    private String[] barColorStyleClasses = { "red-bar", "yellow-bar", "orange-bar", "green-bar" };
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
//        pb.setStyle("-fx-accent: green;");
        pb.getStyleClass().add("green-bar");
        this.lives = player.getLives();
        livesText = new Text();
        livesText.setText(String.valueOf(lives));
        livesText.setFill(Color.BLACK);
        livesText.setFont(new Font("arial", 25));
        livesText.setX(1000);
        livesText.setY(50);
        getStylesheets().add(ClassLoader.getSystemResource("InGameUIStyle.css").toString());
        this.getChildren().addAll(healthText,pb,livesText);
    }
    public void update(Player player){
        this.health = player.getHealth();
        this.lives = player.getLives();
        double progress = (double)health/20;
        if (progress < 0.2) {
            setBarStyleClass(pb, "red-bar");
        } else if (progress < 0.4) {
            setBarStyleClass(pb, "orange-bar");
        } else if (progress < 0.6) {
            setBarStyleClass(pb, "yellow-bar");
        } else {
            setBarStyleClass(pb, "green-bar");
        }
        healthText.setText(String.valueOf(health));
        livesText.setText(String.valueOf(lives));
        pb.setProgress(progress);
    }
    public void setBarStyleClass(ProgressBar pb, String color) {
        pb.getStyleClass().removeAll(barColorStyleClasses);
        pb.getStyleClass().add(color);
    }
}
