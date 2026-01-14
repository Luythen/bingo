package com.luythen.github.bingo;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class grid {

    private final HashMap<Integer, int[]> grid = new HashMap<>();
    private final ArrayList<int[]> iGrid = new ArrayList<>();
    private static final HashMap<UUID, ArrayList<int[]>> playergrid = new HashMap<>();

    private int itemGridX = 8;
    private int itemGirdY = 8;

    public void loadgrid () {

        grid.put(1, new int[]{0, 32, 0, 32});
        grid.put(2, new int[]{33, 64, 0, 32});
        grid.put(3, new int[]{65, 96, 0, 32});
        grid.put(4, new int[]{97, 128, 0, 32});
        grid.put(5, new int[]{0, 32, 33, 64});
        grid.put(6, new int[]{33, 64, 33, 64});
        grid.put(7, new int[]{65, 96, 33, 64});
        grid.put(8, new int[]{97, 128, 33, 64});
        grid.put(9, new int[]{0, 32, 65, 96});
        grid.put(10, new int[]{33, 64, 65, 96});
        grid.put(11, new int[]{65, 96, 65, 96});
        grid.put(12, new int[]{97, 128, 65, 96});
        grid.put(13, new int[]{0, 32, 97, 128});
        grid.put(14, new int[]{33, 64, 97, 128});
        grid.put(15, new int[]{65, 96, 97, 128});
        grid.put(16, new int[]{97, 128, 97, 128});

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                iGrid.add(new int[]{itemGridX, itemGirdY});
                itemGridX+= 32;
            }
            itemGridX = 8;
            itemGirdY+= 32;
        }
    }

    public int[] getGridByIndex (int index) {
        return grid.get(index);
    }

    public int[] getItemGridByIndex (int index) {
        return iGrid.get(index);
    }

    public static void UpdatePlayerGrid (int[] grid, Player p) {
        if (!(playergrid.containsKey(p.getUniqueId()))) {
            ArrayList<int[]> list = new ArrayList<>();
            playergrid.put(p.getUniqueId(), list);
        }
        playergrid.get(p.getUniqueId()).add(grid);
    }

    public static HashMap<UUID, ArrayList<int[]>> getPlayergrid() {
        return playergrid;
    }
}
