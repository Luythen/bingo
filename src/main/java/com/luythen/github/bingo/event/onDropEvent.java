package com.luythen.github.bingo.event;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class onDropEvent implements Listener {
    
    @EventHandler
    public void event (PlayerDropItemEvent e) {
        if (e.getItemDrop().getItemStack().getType().equals(Material.FILLED_MAP)) {
            e.setCancelled(true);
        }
    }
    
}
