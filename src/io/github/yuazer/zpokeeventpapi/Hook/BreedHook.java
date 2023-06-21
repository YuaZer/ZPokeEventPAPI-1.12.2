package io.github.yuazer.zpokeeventpapi.Hook;

import io.github.yuazer.zpokeeventpapi.Main;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class BreedHook extends PlaceholderExpansion {
    @Override
    public String getIdentifier() {
        return "zpepbreed";
    }

    @Override
    public String onPlaceholderRequest(Player p, String indentifier) {
        if (p == null) {
            return "";
        }
        return "false";
    }

    @Override
    public String getAuthor() {
        return "Z菌";
    }

    @Override
    public String getVersion() {
        return Main.getInstance().getDescription().getVersion();
    }
}
