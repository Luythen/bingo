package com.luythen.github.bingo;

import com.luythen.github.bingo.event.OnCraftEvent;
import com.luythen.github.bingo.random.Item;
import com.luythen.github.bingo.random.random;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public final class Bingo extends JavaPlugin {

    public static Bingo instance;
    public grid grid = new grid();
    private String url = "https://static-cyan.vercel.app/";

    @Override
    public void onEnable() {
        ArrayList<Item> temp;
        // Plugin startup logic
        instance = this;
        new BingoCommand(this);
        Bukkit.getPluginManager().registerEvents(new OnCraftEvent(), this);

        grid.loadgrid();

        try {
            long startTime = System.nanoTime();
            temp = new ArrayList<>(Arrays.asList(
                    new Item(ImageIO.read(new URL(url + "block/furnace_front.png")), new ItemStack(Material.FURNACE)),
                    new Item(ImageIO.read(new URL(url + "block/hay_block_side.png")), new ItemStack(Material.HAY_BLOCK)),
                    new Item(ImageIO.read(new URL(url +"block/iron_block.png")), new ItemStack(Material.IRON_BLOCK)),
                    new Item(ImageIO.read(new URL(url +"block/diamond_block.png")), new ItemStack(Material.DIAMOND_BLOCK)),
                    new Item(ImageIO.read(new URL(url +"block/gold_block.png")), new ItemStack(Material.GOLD_BLOCK)),
                    new Item(ImageIO.read(new URL(url +"block/emerald_block.png")), new ItemStack(Material.EMERALD_BLOCK)),
                    new Item(ImageIO.read(new URL(url +"block/lapis_block.png")), new ItemStack(Material.LAPIS_BLOCK)),
                    new Item(ImageIO.read(new URL(url +"block/redstone_block.png")), new ItemStack(Material.REDSTONE_BLOCK)),
                    new Item(ImageIO.read(new URL(url +"block/redstone_lamp.png")), new ItemStack(Material.REDSTONE_LAMP)),
                    new Item(ImageIO.read(new URL(url +"block/bricks.png")), new ItemStack(Material.BRICKS)),
                    new Item(ImageIO.read(new URL(url +"block/barrel_side.png")), new ItemStack(Material.BARREL)),
                    new Item(ImageIO.read(new URL(url +"block/blue_wool.png")), new ItemStack(Material.BLUE_WOOL)),
                    new Item(ImageIO.read(new URL(url +"block/yellow_wool.png")), new ItemStack(Material.YELLOW_WOOL)),
                    new Item(ImageIO.read(new URL(url +"block/cyan_wool.png")), new ItemStack(Material.CYAN_WOOL)),
                    new Item(ImageIO.read(new URL(url +"block/red_wool.png")), new ItemStack(Material.RED_WOOL)),
                    new Item(ImageIO.read(new URL(url +"block/magenta_wool.png")), new ItemStack(Material.MAGENTA_WOOL)),
                    new Item(ImageIO.read(new URL(url +"block/lime_wool.png")), new ItemStack(Material.LIME_WOOL)),
                    new Item(ImageIO.read(new URL(url +"block/black_wool.png")), new ItemStack(Material.BLACK_WOOL)),
                    new Item(ImageIO.read(new URL(url +"block/observer_front.png")), new ItemStack(Material.OBSERVER)),
                    new Item(ImageIO.read(new URL(url +"block/obsidian.png")), new ItemStack(Material.OBSIDIAN)),
                    new Item(ImageIO.read(new URL(url +"block/piston_top.png")), new ItemStack(Material.PISTON)),
                    new Item(ImageIO.read(new URL(url +"block/glowstone.png")), new ItemStack(Material.GLOWSTONE)),
                    new Item(ImageIO.read(new URL(url +"block/quartz_pillar.png")), new ItemStack(Material.QUARTZ_PILLAR))
            ));
            random.setList(temp);

            long elapsedTime = System.nanoTime() - startTime;

            System.out.println("[Bingo] Successfully loaded in "+ temp.size() +" images in " + elapsedTime/1000000 + "ms");
        } catch (IOException e) {
            System.out.println("[Bingo] Failed to load images");
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Bingo getInstance() {
        return instance;
    }
}
