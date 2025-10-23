package de.scholle.ultimateTPA;

import org.bukkit.Sound;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class TPADenyCommand implements CommandExecutor {

    private final UltimateTPA plugin;

    public TPADenyCommand(UltimateTPA plugin) {
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

        receiver.sendMessage(plugin.getLanguageManager().get(receiver, "tpa_denied"));
        request.sender.sendMessage(plugin.getLanguageManager().get(request.sender, "tpa_denied_by")
                .replace("%player%", receiver.getName()));

        if (plugin.getConfig().getBoolean("sound.enabled"))
            receiver.playSound(receiver.getLocation(), Sound.valueOf(plugin.getConfig().getString("sound.deny")), 1, 1);

        return true;
    }
}
