package io.github.yuazer.zpokeeventpapi;

import io.github.yuazer.zpokeeventpapi.Commands.MainCommand;
import io.github.yuazer.zpokeeventpapi.Hook.EventHook;
import io.github.yuazer.zpokeeventpapi.Listener.PlayerEvent;
import io.github.yuazer.zpokeeventpapi.Listener.PokeEvents;
import io.github.yuazer.zpokeeventpapi.MapHelper.MyHashMap;
import io.github.yuazer.zpokeeventpapi.Utils.YamlUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * TODO
 * 兼容PAPI变量，玩家在线期间获取MyHashMap的值，不在线调用YAML文件中的值
 * 如果玩家退出游戏，则将该玩家的数据存储到YAML文件中
 */
public class Main extends JavaPlugin {
    private static Main instance;
    private static MyHashMap eventMap = new MyHashMap();

    //玩家名 事件名 次数
    public static MyHashMap getEventMap() {
        return eventMap;
    }

    private static final Set<String> eventSet = new HashSet<>();

    public static Set<String> getEventSet() {
        return eventSet;
    }

    public static Main getInstance() {
        return instance;
    }

    private static File dataFile = new File("plugins/ZPokeEventPAPI/data");

    @Override
    public void onEnable() {
        instance = this;
        if (!dataFile.exists()) {
            dataFile.mkdir();
        }
        loadEventName();
        Bukkit.getPluginCommand("zpokeeventpapi").setExecutor(new MainCommand());
        Bukkit.getPluginManager().registerEvents(new PokeEvents(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerEvent(), this);
        logLoaded(this);
        EventHook hook = new EventHook();
        if (hook.canRegister()) hook.register();
    }

    @Override
    public void onDisable() {
        logDisable(this);
        eventSet.clear();
    }

    public static void loadEventName() {
        eventSet.clear();
        for (String eventName : Main.getInstance().getConfig().getConfigurationSection("EventSet").getKeys(false)) {
            eventSet.add(YamlUtils.getConfigMessage("EventSet." + eventName));
        }
    }

    public static void logLoaded(JavaPlugin plugin) {
        Bukkit.getLogger().info(String.format("§e[§b%s§e] §f已加载", plugin.getName()));
        Bukkit.getLogger().info("§b作者:§eZ菌");
        Bukkit.getLogger().info("§b版本:§e" + plugin.getDescription().getVersion());
    }

    public static void logDisable(JavaPlugin plugin) {
        Bukkit.getLogger().info(String.format("§e[§b%s§e] §c已卸载", plugin.getName()));
    }
}
