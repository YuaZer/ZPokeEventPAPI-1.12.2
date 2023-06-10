package io.github.yuazer.zpokeeventpapi.Listener;

import io.github.yuazer.zpokeeventpapi.Main;
import io.github.yuazer.zpokeeventpapi.Utils.YamlUtils;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

public class PlayerEvent implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent event) throws IOException, SQLException {
        Player player = event.getPlayer();
        File file = new File("plugins/ZPokeEventPAPI/data/" + player.getName() + ".yml");
        if (!file.exists()) {
            file.createNewFile();
        }
        QuitSaveOnYaml(player, file);
//        if (YamlUtils.getConfigMessage("DataMode.mode").equalsIgnoreCase("SQLite")) {
//            QuitSaveOnSQLite(player);
//        } else {
//            QuitSaveOnYaml(player, file);
//        }
    }

    public void QuitSaveOnYaml(Player player, File file) throws IOException {
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(file);
        for (String eventName : Main.getEventSet()) {
            if (Main.getEventMap().get(player.getName(), eventName) != null) {
                conf.set(eventName, Main.getEventMap().get(player.getName(), eventName));
            } else {
                Set<String> list = conf.getKeys(false);
                list.remove(eventName);
                if (!list.isEmpty()) {
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

//    public void QuitSaveOnSQLite(Player player) throws SQLException {
//        for (String eventName : Main.getEventSet()) {
//            if (Main.getEventMap().get(player.getName(), eventName) != null) {
//                Main.getDatabase().saveEntry(player.getName(), eventName, Main.getEventMap().get(player.getName(), eventName));
//            } else {
//                Main.getDatabase().saveEntry(player.getName(), eventName, null);
//            }
//        }
//    }
}
