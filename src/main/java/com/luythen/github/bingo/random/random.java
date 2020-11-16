package com.luythen.github.bingo.random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class random {

    private static ArrayList<Item> list = new ArrayList<>(
            Arrays.asList(
                    new Item("furnace_front.png", new ItemStack(Material.FURNACE)),
                    new Item("hay_block_side.png", new ItemStack(Material.HAY_BLOCK)),
                    new Item("iron_block.png", new ItemStack(Material.IRON_BLOCK)),
                    new Item("diamond_block.png", new ItemStack(Material.DIAMOND_BLOCK)),
                    new Item("gold_block.png", new ItemStack(Material.GOLD_BLOCK)),
                    new Item("emerald_block.png", new ItemStack(Material.EMERALD_BLOCK)),
                    new Item("lapis_block.png", new ItemStack(Material.LAPIS_BLOCK)),
                    new Item("redstone_block.png", new ItemStack(Material.REDSTONE_BLOCK)),
                    new Item("redstone_lamp.png", new ItemStack(Material.REDSTONE_LAMP)),
                    new Item("bricks.png", new ItemStack(Material.BRICKS)),
                    new Item("barrel_side.png", new ItemStack(Material.BARREL)),
                    new Item("blue_wool.png", new ItemStack(Material.BLUE_WOOL)),
                    new Item("yellow_wool.png", new ItemStack(Material.YELLOW_WOOL)),
                    new Item("cyan_wool.png", new ItemStack(Material.CYAN_WOOL)),
                    new Item("red_wool.png", new ItemStack(Material.RED_WOOL)),
                    new Item("magenta_wool.png", new ItemStack(Material.MAGENTA_WOOL)),
                    new Item("lime_wool.png", new ItemStack(Material.LIME_WOOL)),
                    new Item("black_wool.png", new ItemStack(Material.BLACK_WOOL))
            )
    );

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

}
