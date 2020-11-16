package com.luythen.github.bingo;

import com.luythen.github.bingo.render.PreBingoRender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

public class BingoEvent implements Listener {

    @EventHandler
    public void onMapInitialize(MapInitializeEvent e) {
        MapView mapView = e.getMap();

        mapView.getRenderers().clear();
        mapView.setTrackingPosition(false);
        mapView.addRenderer(new PreBingoRender());

    }
}
