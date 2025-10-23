package de.scholle.ultimateTPA;

import com.google.gson.*;
import org.bukkit.entity.Player;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class LanguageManager {
    private final UltimateTPA plugin;
    private final Map<String, JsonObject> languages = new HashMap<>();

    public LanguageManager(UltimateTPA plugin) {
        this.plugin = plugin;
        loadLanguages();
    }

    private void loadLanguages() {
        File langFolder = new File(plugin.getDataFolder(), "lang");
        if (!langFolder.exists()) langFolder.mkdirs();

        for (File file : Objects.requireNonNull(langFolder.listFiles())) {
            if (file.getName().endsWith(".json")) {
                try (Reader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)) {
                    JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
                    languages.put(file.getName().replace(".json", ""), json);
                } catch (Exception e) {
                    plugin.getLogger().warning("Could not load language file: " + file.getName());
                }
            }
        }
    }

    public String get(Player player, String key) {
        String locale = player.getLocale();
        JsonObject lang = languages.getOrDefault(locale, languages.get("en_US"));
        if (lang != null && lang.has(key)) {
            return format(plugin.getConfig().getString("prefix") + lang.get(key).getAsString());
        }
        return format(plugin.getConfig().getString("prefix") + key);
    }

    private String format(String msg) {
        return msg.replace("&", "§");
    }
}
