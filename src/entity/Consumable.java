package entity;

import pane.GamePane;

public interface Consumable {
    void consume(GamePane gamePane);
    boolean getNotDespawn();
}
