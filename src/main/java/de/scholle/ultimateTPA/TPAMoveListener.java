package de.scholle.ultimateTPA;

import org.bukkit.event.*;
import org.bukkit.event.player.PlayerMoveEvent;

public class TPAMoveListener implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        UltimateTPA plugin = UltimateTPA.getInstance();
        plugin.getRequests().values().forEach(req -> {
            if (req.teleporting && req.sender.equals(e.getPlayer())) {
                if (e.getFrom().distanceSquared(e.getTo()) > 0.1) {
                    e.getPlayer().sendMessage(plugin.getLanguageManager().get(e.getPlayer(), "tpa_move_cancel"));
                    req.teleporting = false;
                }
            }
        });
    }
}
