package de.scholle.ultimateTPA;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class TPAHereCommand implements CommandExecutor {

    private final UltimateTPA plugin;

    public TPAHereCommand(UltimateTPA plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) return true;
        if (args.length != 1) {
            player.sendMessage("§cUsage: /tpahere <player>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null || !target.isOnline()) {
            player.sendMessage("§cPlayer not found.");
            return true;
        }

        TPARequest request = new TPARequest(player, target, true);
        plugin.getRequests().put(target.getUniqueId(), request);

        target.sendMessage(plugin.getLanguageManager().get(target, "tpahere_request")
                .replace("%player%", player.getName()));

        TextComponent accept = new TextComponent(plugin.getLanguageManager().get(target, "tpa_accept"));
        accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpaccept"));
        TextComponent deny = new TextComponent(plugin.getLanguageManager().get(target, "tpa_deny"));
        deny.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpdeny"));
        target.spigot().sendMessage(accept, new TextComponent(" "), deny);

        player.sendMessage(plugin.getLanguageManager().get(player, "tpahere_sent")
                .replace("%player%", target.getName()));

        if (plugin.getConfig().getBoolean("sound.enabled"))
            target.playSound(target.getLocation(), Sound.valueOf(plugin.getConfig().getString("sound.request")), 1, 1);

        return true;
    }
}
