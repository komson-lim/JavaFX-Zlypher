package pane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameOverPane extends VBox {
    private Button button;
    public GameOverPane(int width, int height){
        super();
        setPrefSize(width, height);
        var bg = new BackgroundFill(Color.BLACK,new CornerRadii(0), new Insets(0));
        setBackground(new Background(bg));
        Text text = new Text();
        text.setText("Game Over");
        text.setFill(Color.WHITE);
        text.setFont(new Font("arial", 100));
        text.setStyle("-fx-font-weight: bold");
        button = new Button();
        button.setPrefSize(300,80);
        button.setText("Return to Main Menu");
        button.setFont(new Font("arial", 25));
        setAlignment(Pos.CENTER);
        getChildren().addAll(text,button);
        setSpacing(75);
        getStylesheets().add(ClassLoader.getSystemResource("GameOverStyle.css").toString());
    }
    public void addListener(Scene scene, Stage primaryStage, Pane root, MenuPane menuPane){
        button.setOnAction(e->{
            primaryStage.setScene(scene);
            root.getChildren().remove(this);
            menuPane.play();
        });
    }
}
