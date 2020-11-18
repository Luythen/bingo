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

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    @Override
    public void onEnable() {
        ArrayList<Item> temp;
        // Plugin startup logic
        instance = this;
        new BingoCommand(this);
        Bukkit.getPluginManager().registerEvents(new OnCraftEvent(), this);

        grid.loadgrid();

        try {
            temp = new ArrayList<>(Arrays.asList(
                    new Item(ImageIO.read(new URL("https://static-cyan.vercel.app/block/furnace_front.png")), new ItemStack(Material.FURNACE)),
                    new Item(ImageIO.read(new URL("https://static-cyan.vercel.app/block/hay_block_side.png")), new ItemStack(Material.HAY_BLOCK)),
                    new Item(ImageIO.read(new URL("https://static-cyan.vercel.app/block/iron_block.png")), new ItemStack(Material.IRON_BLOCK)),
                    new Item(ImageIO.read(new URL("https://static-cyan.vercel.app/block/diamond_block.png")), new ItemStack(Material.DIAMOND_BLOCK)),
                    new Item(ImageIO.read(new URL("https://static-cyan.vercel.app/block/gold_block.png")), new ItemStack(Material.GOLD_BLOCK)),
                    new Item(ImageIO.read(new URL("https://static-cyan.vercel.app/block/emerald_block.png")), new ItemStack(Material.EMERALD_BLOCK)),
                    new Item(ImageIO.read(new URL("https://static-cyan.vercel.app/block/lapis_block.png")), new ItemStack(Material.LAPIS_BLOCK)),
                    new Item(ImageIO.read(new URL("https://static-cyan.vercel.app/block/redstone_block.png")), new ItemStack(Material.REDSTONE_BLOCK)),
                    new Item(ImageIO.read(new URL("https://static-cyan.vercel.app/block/redstone_lamp.png")), new ItemStack(Material.REDSTONE_LAMP)),
                    new Item(ImageIO.read(new URL("https://static-cyan.vercel.app/block/bricks.png")), new ItemStack(Material.BRICKS)),
                    new Item(ImageIO.read(new URL("https://static-cyan.vercel.app/block/barrel_side.png")), new ItemStack(Material.BARREL)),
                    new Item(ImageIO.read(new URL("https://static-cyan.vercel.app/block/blue_wool.png")), new ItemStack(Material.BLUE_WOOL)),
                    new Item(ImageIO.read(new URL("https://static-cyan.vercel.app/block/yellow_wool.png")), new ItemStack(Material.YELLOW_WOOL)),
                    new Item(ImageIO.read(new URL("https://static-cyan.vercel.app/block/cyan_wool.png")), new ItemStack(Material.CYAN_WOOL)),
                    new Item(ImageIO.read(new URL("https://static-cyan.vercel.app/block/red_wool.png")), new ItemStack(Material.RED_WOOL)),
                    new Item(ImageIO.read(new URL("https://static-cyan.vercel.app/block/magenta_wool.png")), new ItemStack(Material.MAGENTA_WOOL)),
                    new Item(ImageIO.read(new URL("https://static-cyan.vercel.app/block/lime_wool.png")), new ItemStack(Material.LIME_WOOL)),
                    new Item(ImageIO.read(new URL("https://static-cyan.vercel.app/block/black_wool.png")), new ItemStack(Material.BLACK_WOOL))
            ));
            random.setList(temp);
            System.out.println( ANSI_GREEN +"[bingo] Successfully loaded in image");
        } catch (IOException e) {
            System.out.println( ANSI_RED+"[bingo] Failed to load images");
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
