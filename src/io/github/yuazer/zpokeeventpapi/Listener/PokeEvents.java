package io.github.yuazer.zpokeeventpapi.Listener;

import catserver.api.bukkit.event.ForgeEvent;
import com.pixelmonmod.pixelmon.api.events.CaptureEvent;
import io.github.yuazer.zpokeeventpapi.Main;
import io.github.yuazer.zpokeeventpapi.Utils.YamlUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PokeEvents implements Listener {
    @EventHandler
    public void onForge(ForgeEvent event) {
        if (event.getForgeEvent() instanceof CaptureEvent.SuccessfulCapture) {
            CaptureEvent.SuccessfulCapture e = (CaptureEvent.SuccessfulCapture) event.getForgeEvent();
            Player player = Bukkit.getPlayer(e.player.func_110124_au());
            String pokename = e.getPokemon().getPokemonData().getSpecies().getPokemonName();
            if (!YamlUtils.getConfigMessage("EventSet.successCapture").equalsIgnoreCase("") && Main.getEventMap().get(player.getName(), YamlUtils.getConfigMessage("EventSet.successCapture")) != null) {
                int before = Main.getEventMap().get(player.getName(), YamlUtils.getConfigMessage("EventSet.successCapture"));
                Main.getEventMap().put(player.getName(), YamlUtils.getConfigMessage("EventSet.successCapture"), ++before);
            }
            if (!YamlUtils.getConfigMessage("EventSet.successCapture_" + pokename).equalsIgnoreCase("") && Main.getEventMap().get(player.getName(), YamlUtils.getConfigMessage("EventSet.successCapture_" + pokename)) != null) {
                int before = Main.getEventMap().get(player.getName(), YamlUtils.getConfigMessage("EventSet.successCapture_" + pokename));
                Main.getEventMap().put(player.getName(), YamlUtils.getConfigMessage("EventSet.successCapture_" + pokename), ++before);
            }
        }
    }
}
