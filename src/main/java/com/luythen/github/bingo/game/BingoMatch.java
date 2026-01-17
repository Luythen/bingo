package com.luythen.github.bingo.game;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;

import com.luythen.github.bingo.render.BingoRender;

public class BingoMatch {
 
    private UUID matchID;
    private ArrayList<BingoPlayer> matchPlayers;

    private int matchPlayerSize;

    private boolean matchIsStarted;

    public BingoMatch (int matchSize) {
        this.matchPlayerSize = matchSize;
        this.matchID = UUID.randomUUID();
        this.matchIsStarted = false;
        this.matchPlayers = new ArrayList<>();
    }

    public void addPlayerToMatch (BingoPlayer p) {
        matchPlayers.add(p);
        if (matchPlayers.size() == matchPlayerSize) {
            startMatch();
        }
    }

    public void removePlayerFromMatch (BingoPlayer p) throws Exception {
        if (!matchPlayers.contains(p)) throw new Exception("This match does not containt player");
        matchPlayers.remove(p);
    }

    public boolean matchContainsPlayerByUUID (UUID uuid) {
        for (BingoPlayer bingoPlayer : matchPlayers) {
            if (bingoPlayer.getPlayerUUID().equals(uuid)) return true;
        }

        return false;
    }

    public BingoPlayer getBingoPlayerByID (UUID uuid) {
        for (BingoPlayer bingoPlayer : getMatchPlayers()) {
            if (bingoPlayer.getPlayerUUID().equals(uuid)) return bingoPlayer;
        }
        return null;
    }

    public UUID getMatchID() {
        return matchID;
    }

    public ArrayList<BingoPlayer> getMatchPlayers() {
        return matchPlayers;
    }

    public int getMatchPlayerSize() {
        return matchPlayerSize;
    }

    public boolean isMatchIsStarted() {
        return matchIsStarted;
    }

    public void startMatch() {
        this.matchIsStarted = true;
        createBingoMapsForMatchPlayers();
    }

    private void createBingoMapsForMatchPlayers () {
        MapView mapView = Bukkit.createMap(Bukkit.getWorld("world"));
        mapView.getRenderers().clear();
        mapView.setTrackingPosition(false);

        for (BingoPlayer bingoPlayer : getMatchPlayers()) {
            mapView.addRenderer(new BingoRender(bingoPlayer));
            ItemStack map = new ItemStack(Material.FILLED_MAP);
            MapMeta mapMeta = (MapMeta) map.getItemMeta();

            mapMeta.setMapView(mapView);
            mapMeta.setDisplayName(ChatColor.BOLD + "Bingo");

            map.setItemMeta(mapMeta);
            bingoPlayer.getPlayer().getInventory().setItem(0, map);
        }
    }
    
}
