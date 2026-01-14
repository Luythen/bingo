package com.luythen.github.bingo.render;

import com.luythen.github.bingo.Bingo;
import com.luythen.github.bingo.random.random;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapPalette;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import static com.luythen.github.bingo.grid.UpdatePlayerGrid;
import static com.luythen.github.bingo.grid.getPlayergrid;

public class BingoUpdateRender extends MapRenderer {

    public BingoUpdateRender (int[] points, Player player) {
        UpdatePlayerGrid(points, player);
    }

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

        for (int[] value: getPlayergrid().get(player.getUniqueId())) {
            for (int x = value[0]; x < value[1]; x++) {
                for (int y = value[2]; y < value[3]; y++) {
                    canvas.setPixelColor(x, y, Color.GREEN);
                }
            }
        }

        for (int i = 0; i < random.generateBingoItem(player).size(); i++) {
            int[] points = Bingo.getInstance().grid.getItemGridByIndex(i);
            canvas.drawImage(points[0], points[1], random.generateBingoItem(player).get(i).getImage());
        }
    }
}
