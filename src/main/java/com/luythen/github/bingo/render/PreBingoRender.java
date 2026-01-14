package com.luythen.github.bingo.render;

import com.luythen.github.bingo.Bingo;
import com.luythen.github.bingo.random.random;
import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapPalette;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;


public class PreBingoRender extends MapRenderer {
    @Override
    public void render(MapView map, MapCanvas canvas, Player player) {
        for (int x = 0; x < 128; x++) {
            for (int y = 0; y < 128; y++) {
                canvas.setPixel(x, y, MapPalette.WHITE);
                canvas.setPixel(32, y, MapPalette.DARK_GRAY);
                canvas.setPixel(64, y, MapPalette.DARK_GRAY);
                canvas.setPixel(96, y, MapPalette.DARK_GRAY);
            }
            canvas.setPixel(x, 32, MapPalette.DARK_GRAY);
            canvas.setPixel(x, 64, MapPalette.DARK_GRAY);
            canvas.setPixel(x, 96, MapPalette.DARK_GRAY);
        }

        for (int i = 0; i < random.generateBingoItem(player).size(); i++) {
            int[] points = Bingo.getInstance().grid.getItemGridByIndex(i);
            canvas.drawImage(points[0], points[1], random.generateBingoItem(player).get(i).getImage());
        }
    }
}
