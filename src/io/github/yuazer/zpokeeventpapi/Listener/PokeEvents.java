package io.github.yuazer.zpokeeventpapi.Listener;

import catserver.api.bukkit.event.ForgeEvent;
import com.pixelmonmod.pixelmon.api.events.CaptureEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PokeEvents implements Listener {
    @EventHandler
    public void onForge(ForgeEvent event){
        if (event.getForgeEvent() instanceof CaptureEvent.SuccessfulCapture){

        }
    }
}
