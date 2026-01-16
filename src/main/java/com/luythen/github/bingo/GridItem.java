package com.luythen.github.bingo;

import java.awt.Color;

import org.bukkit.map.MapCanvas;

import com.luythen.github.bingo.random.Item;

public class GridItem {

    private Color gridColor;
    private Item item;

    private int[] gridAxis;
    private int[] gridItemAxis;

    public GridItem (Color gridColor, Item item, int[] gridAxis, int[] gridItemAxis) {
        this.gridColor = gridColor;
        this.item = item;
        this.gridAxis = gridAxis;
        this.gridItemAxis = gridItemAxis;
    }

    public void drawGrid (MapCanvas canvas) {
        for (int x = gridAxis[0]; x < gridAxis[1]; x++) {
            for (int y = gridAxis[2]; y < gridAxis[3]; y++) {
                canvas.setPixelColor(x, y, gridColor);
            }
        }

        canvas.drawImage(gridItemAxis[0], gridItemAxis[1], getItem().getImage());
    }

    public Color getGridColor() {
        return gridColor;
    }

    public void setGridColor(Color gridColor) {
        this.gridColor = gridColor;
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

    

}
