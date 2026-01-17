package com.luythen.github.bingo.event;

import com.luythen.github.bingo.GridItem;
import com.luythen.github.bingo.random.random;
import com.luythen.github.bingo.render.BingoRender;

import java.awt.Color;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;

public class onCraftEvent implements Listener {

    @EventHandler
    public void event (CraftItemEvent e) {
        Player p = (Player) e.getWhoClicked();

        for (int i = 0; i < random.generateBingoGridItem(p).size(); i++) {
            if (e.getRecipe().getResult().equals(random.generateBingoGridItem(p).get(i).getItem().getItemStack())) {
                GridItem gridItem = random.generateBingoGridItem(p).get(i);
                gridItem.setGridColor(Color.GREEN);

                MapView mapView = Bukkit.createMap(p.getWorld());
                mapView.getRenderers().clear();
                mapView.setTrackingPosition(false);
                mapView.addRenderer(new BingoRender());

                ItemStack map = new ItemStack(Material.FILLED_MAP);
                MapMeta mapMeta = (MapMeta) map.getItemMeta();

                mapMeta.setMapView(mapView);
                mapMeta.setDisplayName(ChatColor.BOLD + "Bingo");

                map.setItemMeta(mapMeta);

                if (p.getInventory().getItemInOffHand().getType() == Material.FILLED_MAP) {
                    p.getInventory().getItemInOffHand().setItemMeta(mapMeta);
                    break;
                } else {
                    for (int x = 0; i < p.getInventory().getSize(); x++) {
                        if (p.getInventory().getItem(x).getType() == Material.FILLED_MAP) {
                            p.getInventory().setItem(x, map);
                            break;
                        }
                    }
                }
            }
        }
    }

}
