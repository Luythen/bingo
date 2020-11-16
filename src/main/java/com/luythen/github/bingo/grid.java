package com.luythen.github.bingo;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.bukkit.entity.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class grid {

    private HashMap<Integer, int[]> grid = new HashMap<>();
    private HashMap<Integer, int[]> itemgrid = new HashMap<>();
    private static HashMap<UUID, ArrayList<int[]>> playergrid = new HashMap<>();

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

        itemgrid.put(1, new int[]{8, 8});
        itemgrid.put(2, new int[]{41, 8});
        itemgrid.put(3, new int[]{72, 8});
        itemgrid.put(4, new int[]{104, 8});
        itemgrid.put(5, new int[]{8, 41});
        itemgrid.put(6, new int[]{41, 41});
        itemgrid.put(7, new int[]{72, 41});
        itemgrid.put(8, new int[]{104, 41});
        itemgrid.put(9, new int[]{8, 72});
        itemgrid.put(10, new int[]{41, 72});
        itemgrid.put(11, new int[]{72, 72});
        itemgrid.put(12, new int[]{104, 72});
        itemgrid.put(13, new int[]{8, 104});
        itemgrid.put(14, new int[]{41, 104});
        itemgrid.put(15, new int[]{72, 104});
        itemgrid.put(16, new int[]{104, 104});

    }

    public int[] getGridByIndex (int index) {
        return grid.get(index);
    }

    public int[] getItemGridByIndex (int index) {
        return itemgrid.get(index);
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
