package com.luythen.github.bingo;

import com.luythen.github.bingo.random.random;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Bingo extends JavaPlugin {

    public static Bingo instance;
    public grid grid = new grid();

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        new BingoCommand(this);

        grid.loadgrid();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Bingo getInstance() {
        return instance;
    }
}
