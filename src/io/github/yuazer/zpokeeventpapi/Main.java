package io.github.yuazer.zpokeeventpapi;

import io.github.yuazer.zpokeeventpapi.Commands.MainCommand;
import io.github.yuazer.zpokeeventpapi.Database.SQLiteDatabase;
import io.github.yuazer.zpokeeventpapi.Hook.CheckHook;
import io.github.yuazer.zpokeeventpapi.Hook.EventHook;
import io.github.yuazer.zpokeeventpapi.Listener.PlayerEvent;
import io.github.yuazer.zpokeeventpapi.Listener.PokeEvents;
import io.github.yuazer.zpokeeventpapi.MapHelper.MyHashMap;
import io.github.yuazer.zpokeeventpapi.Utils.YamlUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * TODO
 * 将MyHashMap的存储改为存进数据库
 */
public class Main extends JavaPlugin {
    private static Main instance;
    private static MyHashMap eventMap = new MyHashMap();

    //玩家名 事件名 次数
    public static MyHashMap getEventMap() {
        return eventMap;
    }

    private static SQLiteDatabase database;

    public static SQLiteDatabase getDatabase() {
        return database;
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
        saveDefaultConfig();
        if (!dataFile.exists()) {
            dataFile.mkdir();
        }
        loadEventName();
        loadMap();
        Bukkit.getPluginCommand("zpokeeventpapi").setExecutor(new MainCommand());
        Bukkit.getPluginManager().registerEvents(new PokeEvents(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerEvent(), this);
        logLoaded(this);
        if (YamlUtils.getConfigMessage("DataMode.mode").equalsIgnoreCase("SQLite")) {
            database = new SQLiteDatabase(this);
            System.out.println("§aZPokeEventPAPI§7-§bSQLite数据库开启");
        }
        EventHook hook = new EventHook();
        CheckHook checkHook = new CheckHook();
        if (hook.canRegister()) {
            hook.register();
        }
        if (checkHook.canRegister()) {
            checkHook.register();
        }
    }

    @Override
    public void onDisable() {
        logDisable(this);
        eventSet.clear();
        EventHook hook = new EventHook();
        CheckHook checkHook = new CheckHook();
        hook.unregister();
        checkHook.register();
        database.close();
    }

    public void loadMap() {
        if (dataFile.listFiles() != null) {
            for (File file : dataFile.listFiles()) {
                YamlConfiguration conf = YamlConfiguration.loadConfiguration(file);
                for (String event : conf.getKeys(false)) {
                    Main.getEventMap().put(file.getName().replace(".yml", ""), event, conf.getInt(event));
                }
            }
        }
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
