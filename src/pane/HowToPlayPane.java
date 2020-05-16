package pane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class HowToPlayPane extends VBox {
    private Button exit;
    public HowToPlayPane(){
        super();
        setMaxSize(900,500);
        var bg = new BackgroundFill(Color.LIGHTGOLDENRODYELLOW,new CornerRadii(50), new Insets(0));
        setBackground(new Background(bg));
        exit = new Button();
        exit.setText("OK");
        exit.setFont(new Font("arial",20));
        exit.setPrefSize(100,20);
        Text text = new Text();
        text.setText("Press x to Dash.\nPress c to Jump.\nPress v to Attack.\nYour goal is to get to the finish point.\nGood luck!");
        text.setFont(new Font("arial", 45));
        text.setTextAlignment(TextAlignment.CENTER);
        setAlignment(Pos.CENTER);
        setSpacing(50);
        getChildren().addAll(text,exit);
        text.getStyleClass().add("text1");
    }
    public void addListener(Pane pane){
        exit.setOnAction(e -> {
            pane.getChildren().remove(this);
        });
    }
}
