package com.luythen.github.bingo.game;

import java.awt.Color;
import java.util.UUID;

import org.bukkit.map.MapCanvas;

public class GridItem {

    private UUID uuid;

    private boolean isCompleted;
    private Item item;

    private int[] gridAxis;
    private int[] gridItemAxis;

    public GridItem (Item item, int[] gridAxis, int[] gridItemAxis) {
        this.isCompleted = false;
        this.item = item;
        this.gridAxis = gridAxis;
        this.gridItemAxis = gridItemAxis;
        this.uuid = UUID.randomUUID();
    }

    public void drawGrid (MapCanvas canvas) {
        for (int x = gridAxis[0]; x < gridAxis[1]; x++) {
            for (int y = gridAxis[2]; y < gridAxis[3]; y++) {
                canvas.setPixelColor(x, y, isCompleted() ? Color.GREEN : Color.WHITE);
            }
        }

        canvas.drawImage(gridItemAxis[0], gridItemAxis[1], getItem().getImage());
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
    
    public Item getItem() {
        return item;
    }

    public int[] getGridAxis() {
        return gridAxis;
    }

    public int[] getGridItemAxis() {
        return gridItemAxis;
    }

    public UUID getUuid () {
        return uuid;
    }

}
