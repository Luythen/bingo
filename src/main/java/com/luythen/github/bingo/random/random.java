package com.luythen.github.bingo.random;

import org.bukkit.entity.Player;

import com.luythen.github.bingo.GridItem;

import java.awt.Color;
import java.util.*;

public class random {

    private static ArrayList<Item> list = new ArrayList<>();

    private static HashMap<UUID, ArrayList<GridItem>> playerlist = new HashMap<>();

    private static int itemGridX = 8;
    private static int itemGirdY = 8;

    private static int[] gridPointA = new int[]{0, 32, 64, 96};
    private static int gridPointB = 32;
    private static int girdPointC = 0;
    private static int gridPointD = 32;

    public static ArrayList<GridItem> generateBingoGridItem (Player p) {
        return playerlist.get(p.getUniqueId());
    }

    public static void generate (Player p) {
        if (playerlist.containsKey(p.getUniqueId()))
            playerlist.clear();

        playerlist.put(p.getUniqueId(), new ArrayList<>());
        Random random = new Random();
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int num = random.nextInt(list.size());
                while (containsItem(p, list.get(num))) {
                    num = random.nextInt(list.size());
                }

                GridItem item = new GridItem(Color.WHITE, list.get(num), new int[]{gridPointA[j], gridPointB, girdPointC, gridPointD}, new int[]{itemGridX, itemGirdY});

                playerlist.get(p.getUniqueId()).add(item);
                
                itemGridX+= 32;
                gridPointB += 32;
            }
            itemGridX = 8;
            itemGirdY+= 32;
            
            gridPointB = 32;
            girdPointC += 32;
            gridPointD += 32;
        }
    }

    private static boolean containsItem (Player p,Item item) {
        for (GridItem gItem : playerlist.get(p.getUniqueId())) {
            if (gItem.getItem().equals(item)) {
                return true;
            }
        }
        return false;
    }

    public static void setList(ArrayList<Item> list) {
        random.list = list;
    }
}
