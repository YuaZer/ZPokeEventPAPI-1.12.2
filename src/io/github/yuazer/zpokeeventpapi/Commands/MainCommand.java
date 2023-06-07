package io.github.yuazer.zpokeeventpapi.Commands;

import io.github.yuazer.zpokeeventpapi.Main;
import io.github.yuazer.zpokeeventpapi.Utils.YamlUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (command.getName().equalsIgnoreCase("zpokeeventpapi")) {
            if (args[0].equalsIgnoreCase("reload") && sender.isOp()) {
                Main.getInstance().reloadConfig();
                Main.loadEventName();
                sender.sendMessage(YamlUtils.getConfigMessage("Message.reload"));
            }
            if (args[0].equalsIgnoreCase("start") && sender.isOp() && args.length == 3) {
                String eventName = args[1];
                Player player = Bukkit.getPlayer(args[2]);
                if (player != null && Main.getEventSet().contains(eventName)) {
                    if (Main.getEventMap().get(player.getName(), eventName) == null) {
                        Main.getEventMap().put(player.getName(), eventName, 0);
                        sender.sendMessage(YamlUtils.getConfigMessage("Message.successStart").replace("%player%", player.getName()).replace("%event%", eventName));
                    } else {
                        sender.sendMessage(YamlUtils.getConfigMessage("Message.alreadyStart"));
                    }
                } else {
                    sender.sendMessage(YamlUtils.getConfigMessage("Message.playerOffline"));
                }
                return true;
            }
            if (args[0].equalsIgnoreCase("stop") && sender.isOp() && args.length == 3) {
                String eventName = args[1];
                Player player = Bukkit.getPlayer(args[2]);
                if (player != null && Main.getEventSet().contains(eventName)) {
                    if (Main.getEventMap().get(player.getName(), eventName) != null) {
                        Main.getEventMap().put(player.getName(), eventName, null);
                        sender.sendMessage(YamlUtils.getConfigMessage("Message.successStop").replace("%player%", player.getName()).replace("%event%", eventName));
                    } else {
                        sender.sendMessage(YamlUtils.getConfigMessage("Message.alreadyStop"));
                    }
                } else {
                    sender.sendMessage(YamlUtils.getConfigMessage("Message.playerOffline"));
                }
                return true;
            }
            if (args[0].equalsIgnoreCase("debug") && sender.isOp()) {
                Player player = (Player) sender;
                System.out.println(Main.getEventSet());
                System.out.println(Main.getEventMap().get(player.getName(), args[1]));
            }
        }
        return false;
    }
}
