package com.luythen.github.bingo.event;

import com.luythen.github.bingo.game.BingoMatch;
import com.luythen.github.bingo.game.BingoPlayer;
import com.luythen.github.bingo.game.Game;
import com.luythen.github.bingo.game.GridItem;
import com.luythen.github.bingo.render.BingoRender;

import java.util.ArrayList;

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

        ArrayList<BingoMatch> bingoMatchs = Game.getAllActiveMatches();

        try {
            for (BingoMatch bingoMatch : bingoMatchs) {
                if (bingoMatch.isMatchIsStarted()) {

                    BingoPlayer bingoPlayer = bingoMatch.getBingoPlayerByID(p.getUniqueId());
                    ArrayList<GridItem> gridItemsList = bingoPlayer.getGridItems();

                    for (int i = 0; i < gridItemsList.size(); i++) {
                        if (e.getRecipe().getResult().equals(gridItemsList.get(i).getItem().getItemStack())) {
                            System.out.println(e.getRecipe().getResult().equals(gridItemsList.get(i).getItem().getItemStack()));
                            GridItem gridItem = gridItemsList.get(i);
                            gridItem.setIsCompleted(true);

                            MapView mapView = Bukkit.createMap(p.getWorld());
                            mapView.getRenderers().clear();
                            mapView.setTrackingPosition(false);
                            mapView.addRenderer(new BingoRender(bingoPlayer));

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
        } catch (Exception exe) {
            System.out.print(Game.getAllActiveMatches().size());
            System.out.println(exe.getMessage());
        }
    }

}
