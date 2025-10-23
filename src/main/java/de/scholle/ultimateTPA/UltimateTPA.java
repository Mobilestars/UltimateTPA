package de.scholle.ultimateTPA;

import org.bukkit.plugin.java.JavaPlugin;
import java.util.*;

public class UltimateTPA extends JavaPlugin {

    private static UltimateTPA instance;
    private LanguageManager languageManager;
    private final Map<UUID, TPARequest> requests = new HashMap<>();

    public static UltimateTPA getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        saveResource("lang/en_US.json", false);
        languageManager = new LanguageManager(this);

        getCommand("tpa").setExecutor(new TPACommand(this));
        getCommand("tpahere").setExecutor(new TPAHereCommand(this));
        getCommand("tpaccept").setExecutor(new TPAAcceptCommand(this));
        getCommand("tpdeny").setExecutor(new TPADenyCommand(this));

        getServer().getPluginManager().registerEvents(new TPAMoveListener(), this);

        getLogger().info("UltimateTPA enabled!");
    }

    public LanguageManager getLanguageManager() {
        return languageManager;
    }

    public Map<UUID, TPARequest> getRequests() {
        return requests;
    }
}
