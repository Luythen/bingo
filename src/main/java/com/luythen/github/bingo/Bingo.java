package com.luythen.github.bingo;

import com.google.gson.Gson;
import com.luythen.github.bingo.Dto.BlocksDto;
import com.luythen.github.bingo.Dto.ItemDto;
import com.luythen.github.bingo.event.OnCraftEvent;
import com.luythen.github.bingo.random.Item;
import com.luythen.github.bingo.random.random;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public final class Bingo extends JavaPlugin {

    public static Bingo instance;
    public grid grid = new grid();

    private String url = "https://raw.githubusercontent.com/Luythen/bingoImg/refs/heads/main/";
    private File configFile;

    private Gson gson = new Gson();

    @Override
    public void onEnable() {
        ArrayList<Item> temp;
        // Plugin startup logic
        instance = this;
        new BingoCommand(this);
        Bukkit.getPluginManager().registerEvents(new OnCraftEvent(), this);

        createCustomConfig();
        grid.loadgrid();

        try {
            long startTime = System.nanoTime();
            temp = new ArrayList<>();
            BlocksDto json = gson.fromJson(new FileReader(configFile), BlocksDto.class);
            for (ItemDto item : json.getBlocks()) {
                temp.add(new Item(ImageIO.read(new URL(url + "" + item.getItemImg())), new ItemStack(Material.getMaterial(item.getItemType()))));
            }
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

    private void createCustomConfig () {
        configFile = new File(getDataFolder(), "BingoBlockconfig.json");
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            saveResource("BingoBlockconfig.json", false);
        }
    }
}
