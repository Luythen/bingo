package com.luythen.github.bingo.random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class random {

    private static ArrayList<Item> list = new ArrayList<>();

    private static HashMap<UUID, ArrayList<Item>> playerlist = new HashMap<>();

    public static ArrayList<Item> generateBingoItem (Player p) {
        return playerlist.get(p.getUniqueId());
    }

    public static void generate (Player p) {
        if (playerlist.containsKey(p.getUniqueId()))
            playerlist.clear();

        playerlist.put(p.getUniqueId(), new ArrayList<Item>());

        while (playerlist.get(p.getUniqueId()).size() < 16) {
            Random random = new Random();
            int num = random.nextInt(list.size());
            if (!playerlist.get(p.getUniqueId()).contains(list.get(num))) {
                playerlist.get(p.getUniqueId()).add(list.get(num));
            }
        }
    }

    public static void setList(ArrayList<Item> list) {
        random.list = list;
    }
}
