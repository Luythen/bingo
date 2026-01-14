package com.luythen.github.bingo.render;

import com.luythen.github.bingo.Bingo;
import com.luythen.github.bingo.random.random;

import java.awt.Color;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;


public class PreBingoRender extends MapRenderer {
    @Override
    public void render(MapView map, MapCanvas canvas, Player player) {
        for (int x = 0; x < 128; x++) {
            for (int y = 0; y < 128; y++) {
                canvas.setPixelColor(x, y, Color.WHITE);
                canvas.setPixelColor(32, y, Color.DARK_GRAY);
                canvas.setPixelColor(64, y, Color.DARK_GRAY);
                canvas.setPixelColor(96, y, Color.DARK_GRAY);
            }
            canvas.setPixelColor(x, 32, Color.DARK_GRAY);
            canvas.setPixelColor(x, 64, Color.DARK_GRAY);
            canvas.setPixelColor(x, 96, Color.DARK_GRAY);
        }

        for (int i = 0; i < random.generateBingoItem(player).size(); i++) {
            int[] points = Bingo.getInstance().grid.getItemGridByIndex(i);
            canvas.drawImage(points[0], points[1], random.generateBingoItem(player).get(i).getImage());
        }
    }
}
