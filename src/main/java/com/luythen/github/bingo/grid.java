package com.luythen.github.bingo;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class grid {

    private final ArrayList<int[]> grid = new ArrayList<>();
    private final ArrayList<int[]> iGrid = new ArrayList<>();
    private static final HashMap<UUID, ArrayList<int[]>> playergrid = new HashMap<>();

    private int itemGridX = 8;
    private int itemGirdY = 8;

    private int[] gridPointA = new int[]{0, 32, 64, 96};
    private int gridPointB = 32;
    private int girdPointC = 0;
    private int gridPointD = 32;

    public void loadgrid () {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //Item grid
                iGrid.add(new int[]{itemGridX, itemGirdY});
                itemGridX+= 32;

                //Map grid
                grid.add(new int[]{gridPointA[i], gridPointB, girdPointC, gridPointD});
                gridPointB += 32;
            }
            itemGridX = 8;
            itemGirdY+= 32;
            
            gridPointB = 32;
            girdPointC += 32;
            gridPointD += 32;
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
