package com.luythen.github.bingo.game;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.entity.Player;

public class BingoPlayer {
    
    private Player player;
    private int score;
    private ArrayList<GridItem> gridItems;

    public BingoPlayer (Player player, ArrayList<GridItem> gridItems) {
        this.player = player;
        this.score = 0;
        this.gridItems = gridItems;
    }

    public Player getPlayer() {
        return player;
    }

    public UUID getPlayerUUID () {
        return player.getUniqueId();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ArrayList<GridItem> getGridItems() {
        return gridItems;
    }

    public void addGridItem (GridItem gridItem) {
        gridItems.add(gridItem);
    }

}
