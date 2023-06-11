package io.github.yuazer.zpokeeventpapi.Hook;

import io.github.yuazer.zpokeeventpapi.Main;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class EventHook extends PlaceholderExpansion {
    @Override
    public String getIdentifier() {
        return "zpokeeventpapi";
    }

    @Override
    public String getAuthor() {
        return "Z菌";
    }

    @Override
    public String onPlaceholderRequest(Player p, String indentifier) {
        if (p == null) {
            return "";
        }
        String[] key = indentifier.split("_");
        String e = key[0];
        String player = key[1];
        if (Bukkit.getPlayer(player) != null) {
            if (Main.getEventSet().contains(e) && Main.getEventMap().get(player, e) != -1) {
                return String.valueOf(Main.getEventMap().get(player, e));
            } else {
                return "0";
            }
        } else {
            File file = new File("plugins/ZPokeEventPAPI/data/" + player + ".yml");
            if (file.exists()) {
                YamlConfiguration conf = YamlConfiguration.loadConfiguration(file);
                return conf.getString(e).equalsIgnoreCase("") ? "0" : conf.getString(e);
            }
        }
        return "error";
    }

    @Override
    public String getVersion() {
        return Main.getInstance().getDescription().getVersion();
    }
}
