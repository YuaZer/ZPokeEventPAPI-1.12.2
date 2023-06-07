package io.github.yuazer.zpokeeventpapi.Listener;

import io.github.yuazer.zpokeeventpapi.Main;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.io.IOException;

public class PlayerEvent implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent event) throws IOException {
        Player player = event.getPlayer();
        File file = new File("plugins/ZPokeEventPAPI/data/" + player.getName() + ".yml");
        if (!file.exists()) {
            file.createNewFile();
        }
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(file);
        for (String eventName : Main.getEventSet()) {
            if (Main.getEventMap().get(player.getName(), eventName) != null) {
                conf.set(eventName, Main.getEventMap().get(player.getName(), eventName));
            }
        }
        conf.save(file);
    }
}
