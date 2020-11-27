package com.luythen.github.bingo;

import com.luythen.github.bingo.random.random;
import com.luythen.github.bingo.render.BingoUpdateRender;
import com.luythen.github.bingo.render.PreBingoRender;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import java.util.List;

public class BingoCommand implements CommandExecutor {

    Bingo plugin;

    public BingoCommand (Bingo plugin) {
        this.plugin = plugin;
        plugin.getCommand("bingo").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            System.out.println("Only for players");
        }

        Player p = (Player) sender;

        if (args[0].equalsIgnoreCase("map")) {
            random.generate(p);

            MapView mapView = Bukkit.createMap(p.getWorld());
            mapView.getRenderers().clear();
            mapView.setTrackingPosition(false);
            mapView.addRenderer(new PreBingoRender());

            ItemStack map = new ItemStack(Material.FILLED_MAP);
            MapMeta mapMeta = (MapMeta) map.getItemMeta();

            mapMeta.setMapView(mapView);
            mapMeta.setDisplayName(ChatColor.BOLD + "Bingo");

            map.setItemMeta(mapMeta);

            p.getInventory().setItem(0, map);
        } else if (args[0].equalsIgnoreCase("set") && p.isOp()) {
            int[] points = Bingo.getInstance().grid.getGridByIndex(Integer.valueOf(args[1]));

            MapView mapView = Bukkit.createMap(p.getWorld());
            mapView.getRenderers().clear();
            mapView.setTrackingPosition(false);
            mapView.addRenderer(new BingoUpdateRender(points, p));

            ItemStack map = new ItemStack(Material.FILLED_MAP);
            MapMeta mapMeta = (MapMeta) map.getItemMeta();

            mapMeta.setMapView(mapView);
            mapMeta.setDisplayName(ChatColor.BOLD.AQUA + "Bingo");

            map.setItemMeta(mapMeta);

            if (p.getInventory().getItemInOffHand().getType() == Material.FILLED_MAP) {
                p.getInventory().getItemInOffHand().setItemMeta(mapMeta);
            } else {
                for (int i = 0; i < p.getInventory().getSize(); i++) {
                    if (p.getInventory().getItem(i).getType() == Material.FILLED_MAP) {
                        p.getInventory().setItem(i, map);
                        break;
                    }
                }
            }
        }

        return true;
    }
}
