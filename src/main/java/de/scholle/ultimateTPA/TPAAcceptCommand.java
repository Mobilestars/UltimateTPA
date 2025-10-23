package de.scholle.ultimateTPA;

import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class TPAAcceptCommand implements CommandExecutor {

    private final UltimateTPA plugin;

    public TPAAcceptCommand(UltimateTPA plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player receiver)) return true;

        TPARequest request = plugin.getRequests().remove(receiver.getUniqueId());
        if (request == null) {
            receiver.sendMessage(plugin.getLanguageManager().get(receiver, "tpa_no_request"));
            return true;
        }

        if (request.isExpired(plugin.getConfig().getLong("request-expire-time") * 1000)) {
            receiver.sendMessage(plugin.getLanguageManager().get(receiver, "tpa_expired"));
            return true;
        }

        Player senderP = request.sender;
        if (!senderP.isOnline()) {
            receiver.sendMessage(plugin.getLanguageManager().get(receiver, "tpa_sender_offline"));
            return true;
        }

        int delay = plugin.getConfig().getInt("teleport-delay");
        boolean cancelOnMove = plugin.getConfig().getBoolean("cancel-on-move");

        receiver.sendMessage(plugin.getLanguageManager().get(receiver, "tpa_accepted"));
        senderP.sendMessage(plugin.getLanguageManager().get(senderP, "tpa_accepted_by")
                .replace("%player%", receiver.getName())
                .replace("%seconds%", String.valueOf(delay)));

        request.teleporting = true;

        if (plugin.getConfig().getBoolean("sound.enabled"))
            senderP.playSound(senderP.getLocation(), Sound.valueOf(plugin.getConfig().getString("sound.accept")), 1, 1);

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if (cancelOnMove && senderP.getLocation().distanceSquared(request.senderLocation) > 0.1) {
                senderP.sendMessage(plugin.getLanguageManager().get(senderP, "tpa_move_cancel"));
                request.teleporting = false;
                return;
            }
            if (request.here) {
                senderP.teleport(receiver.getLocation());
            } else {
                receiver.teleport(senderP.getLocation());
            }
            senderP.sendMessage(plugin.getLanguageManager().get(senderP, "tpa_teleported"));
        }, delay * 20L);
        return true;
    }
}
