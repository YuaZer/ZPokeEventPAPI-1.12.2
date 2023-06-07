package io.github.yuazer.zpokeeventpapi.Listener;

import io.github.yuazer.zpokeeventpapi.Main;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.io.IOException;
import java.util.Set;

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
                System.out.println("normal Case");
                conf.set(eventName, Main.getEventMap().get(player.getName(), eventName));
            } else {
                System.out.println("null Case");
                Set<String> list = conf.getKeys(false);
                list.remove(eventName);
                System.out.println(list);
                if (!list.isEmpty()) {
                    System.out.println("run reset");
                    for (String e : list) {
                        conf.set(e, Main.getEventMap().get(player.getName(), e));
                    }
                } else {
                    file.delete();
                    File file1 = new File("plugins/ZPokeEventPAPI/data/" + player.getName() + ".yml");
                    file1.createNewFile();
                    return;
                }
            }
        }
        conf.save(file);
    }
}
