package com.luythen.github.bingo.render;

import com.luythen.github.bingo.game.BingoPlayer;
import com.luythen.github.bingo.game.GridItem;

import java.awt.Color;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;


public class BingoRender extends MapRenderer {

    private BingoPlayer bplayer;

    public BingoRender (BingoPlayer bplayer) {
        this.bplayer = bplayer;
    }

    @Override
    public void render(MapView map, MapCanvas canvas, Player player) {

        for (GridItem gridItem: bplayer.getGridItems()) {
            gridItem.drawGrid(canvas);
        }

        for (int x = 0; x < 128; x++) {
            for (int y = 0; y < 128; y++) {
                canvas.setPixelColor(32, y, Color.DARK_GRAY);
                canvas.setPixelColor(64, y, Color.DARK_GRAY);
                canvas.setPixelColor(96, y, Color.DARK_GRAY);
            }
            canvas.setPixelColor(x, 32, Color.DARK_GRAY);
            canvas.setPixelColor(x, 64, Color.DARK_GRAY);
            canvas.setPixelColor(x, 96, Color.DARK_GRAY);
        }
    }
}
