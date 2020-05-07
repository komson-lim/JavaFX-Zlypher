package logic;

import entity.Platform;
import entity.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pane.GamePane;

import java.util.ArrayList;

public class GameUpdateHelper {
    public static ObservableList<Platform> checkNear(ArrayList<Platform> platforms,int width, int height ,GamePane gamePane){
        double posX = gamePane.getTranslateX();
        double posY = gamePane.getTranslateY();
//        ArrayList<Platform> nearPlatforms = new ArrayList<>();
        ObservableList<Platform> list = FXCollections.observableArrayList();
        for (Platform p : platforms){
            if (p.getPosX() >= posX-500 && p.getPosX() <= posX+width+500){
                if (p.getPosY() >= posY-500 && p.getPosY() <= posY+height+500){
                    if (!list.contains(p)) {
                        list.add(p);

                    }
                }else{
                    list.remove(p);
                }
            }else{
                list.remove(p);
            }
        }
        return list;


    }
}
