package io.github.yuazer.zpokeeventpapi.Listener;

import catserver.api.bukkit.ForgeEventV2;
import com.pixelmonmod.pixelmon.api.events.*;
import com.pixelmonmod.pixelmon.api.events.pokemon.EVsGainedEvent;
import com.pixelmonmod.pixelmon.battles.controller.participants.PixelmonWrapper;
import io.github.yuazer.zpokeeventpapi.Main;
import io.github.yuazer.zpokeeventpapi.Utils.YamlUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.lang.reflect.Array;
import java.util.Arrays;

public class PokeEvents implements Listener {
    @EventHandler
    public void onForge(ForgeEventV2 event) {
        if (event.getForgeEvent() instanceof CaptureEvent.SuccessfulCapture) {
            CaptureEvent.SuccessfulCapture e = (CaptureEvent.SuccessfulCapture) event.getForgeEvent();
            Player player = Bukkit.getPlayer(e.getPlayer().func_110124_au());
            String pokename = e.getPokemon().getSpecies().getName();
            if (!YamlUtils.getConfigMessage("EventSet.successCapture").equalsIgnoreCase("") && Main.getEventMap().get(player.getName(), YamlUtils.getConfigMessage("EventSet.successCapture")) != null) {
                int before = Main.getEventMap().get(player.getName(), YamlUtils.getConfigMessage("EventSet.successCapture"));
                Main.getEventMap().put(player.getName(), YamlUtils.getConfigMessage("EventSet.successCapture"), ++before);
            }
            if (!YamlUtils.getConfigMessage("EventSet.successCapture_" + pokename).equalsIgnoreCase("") && Main.getEventMap().get(player.getName(), YamlUtils.getConfigMessage("EventSet.successCapture_" + pokename)) != null) {
                int before = Main.getEventMap().get(player.getName(), YamlUtils.getConfigMessage("EventSet.successCapture_" + pokename));
                Main.getEventMap().put(player.getName(), YamlUtils.getConfigMessage("EventSet.successCapture_" + pokename), ++before);
            }
            return;
        }
        if (event.getForgeEvent() instanceof BeatWildPixelmonEvent) {
            BeatWildPixelmonEvent e = (BeatWildPixelmonEvent) event.getForgeEvent();
            Player player = Bukkit.getPlayer(e.player.func_110124_au());
            for (PixelmonWrapper pokemon : e.wpp.getTeamPokemon()) {
                String pokename = pokemon.getPokemonName();
                if (!YamlUtils.getConfigMessage("EventSet.successBeatWild").equalsIgnoreCase("") && Main.getEventMap().get(player.getName(), YamlUtils.getConfigMessage("EventSet.successBeatWild")) != null) {
                    int before = Main.getEventMap().get(player.getName(), YamlUtils.getConfigMessage("EventSet.successBeatWild"));
                    Main.getEventMap().put(player.getName(), YamlUtils.getConfigMessage("EventSet.successBeatWild"), ++before);
                }
                if (!YamlUtils.getConfigMessage("EventSet.successBeatWild_" + pokename).equalsIgnoreCase("") && Main.getEventMap().get(player.getName(), YamlUtils.getConfigMessage("EventSet.successBeatWild_" + pokename)) != null) {
                    int before = Main.getEventMap().get(player.getName(), YamlUtils.getConfigMessage("EventSet.successBeatWild_" + pokename));
                    Main.getEventMap().put(player.getName(), YamlUtils.getConfigMessage("EventSet.successBeatWild_" + pokename), ++before);
                    System.out.println(Main.getEventMap().get(player.getName(), YamlUtils.getConfigMessage("EventSet.successBeatWild_" + pokename)));
                }
            }
            return;
        }
        if (event.getForgeEvent() instanceof LevelUpEvent.Post) {
            LevelUpEvent.Post e = (LevelUpEvent.Post) event.getForgeEvent();
            Player player = Bukkit.getPlayer(e.getPlayer().func_110124_au());
            String pokename = e.getPokemon().getSpecies().getName();
            int levelUpCount = e.getAfterLevel()-e.getBeforeLevel();
            if (!YamlUtils.getConfigMessage("EventSet.LevelUp").equalsIgnoreCase("") && Main.getEventMap().get(player.getName(), YamlUtils.getConfigMessage("EventSet.LevelUp")) != null) {
                int before = Main.getEventMap().get(player.getName(), YamlUtils.getConfigMessage("EventSet.LevelUp"));
                Main.getEventMap().put(player.getName(), YamlUtils.getConfigMessage("EventSet.LevelUp"), before + levelUpCount);
            }
            if (!YamlUtils.getConfigMessage("EventSet.LevelUp_" + pokename).equalsIgnoreCase("") && Main.getEventMap().get(player.getName(), YamlUtils.getConfigMessage("EventSet.LevelUp_" + pokename)) != null) {
                int before = Main.getEventMap().get(player.getName(), YamlUtils.getConfigMessage("EventSet.LevelUp_" + pokename));
                Main.getEventMap().put(player.getName(), YamlUtils.getConfigMessage("EventSet.LevelUp_" + pokename), before + levelUpCount);
            }
            return;
        }
        if (event.getForgeEvent() instanceof EvolveEvent.Post) {
            EvolveEvent.Post e = (EvolveEvent.Post) event.getForgeEvent();
            Player player = Bukkit.getPlayer(e.getPlayer().func_110124_au());
            if (!YamlUtils.getConfigMessage("EventSet.Evolve").equalsIgnoreCase("") && Main.getEventMap().get(player.getName(), YamlUtils.getConfigMessage("EventSet.Evolve")) != null) {
                int before = Main.getEventMap().get(player.getName(), YamlUtils.getConfigMessage("EventSet.Evolve"));
                Main.getEventMap().put(player.getName(), YamlUtils.getConfigMessage("EventSet.Evolve"), ++before);
            }
            return;
        }
        if (event.getForgeEvent() instanceof EVsGainedEvent) {
            EVsGainedEvent e = (EVsGainedEvent) event.getForgeEvent();
            int sum = Arrays.stream(e.evYields.toArray()).sum();
            Player player = Bukkit.getPlayer(e.pokemon.getOwnerPlayerUUID());
            String pokename = e.pokemon.getSpecies().getName();
            if (!YamlUtils.getConfigMessage("EventSet.EVsGained").equalsIgnoreCase("") && Main.getEventMap().get(player.getName(), YamlUtils.getConfigMessage("EventSet.EVsGained")) != null) {
                int before = Main.getEventMap().get(player.getName(), YamlUtils.getConfigMessage("EventSet.EVsGained"));
                Main.getEventMap().put(player.getName(), YamlUtils.getConfigMessage("EventSet.EVsGained"), sum + before);
            }
            if (!YamlUtils.getConfigMessage("EventSet.EVsGained_" + pokename).equalsIgnoreCase("") && Main.getEventMap().get(player.getName(), YamlUtils.getConfigMessage("EventSet.EVsGained_" + pokename)) != null) {
                int before = Main.getEventMap().get(player.getName(), YamlUtils.getConfigMessage("EventSet.EVsGained_" + pokename));
                Main.getEventMap().put(player.getName(), YamlUtils.getConfigMessage("EventSet.EVsGained_" + pokename), sum + before);
            }
            return;
        }
        if (event.getForgeEvent() instanceof ApricornEvent.Pick) {
            ApricornEvent.Pick e = (ApricornEvent.Pick) event.getForgeEvent();
            Player player = Bukkit.getPlayer(e.getPlayer().func_110124_au());
            String apricorn = e.getApricorn().name();
            if (!YamlUtils.getConfigMessage("EventSet.Apricorn").equalsIgnoreCase("") && Main.getEventMap().get(player.getName(), YamlUtils.getConfigMessage("EventSet.Apricorn")) != null) {
                int before = Main.getEventMap().get(player.getName(), YamlUtils.getConfigMessage("EventSet.Apricorn"));
                Main.getEventMap().put(player.getName(), YamlUtils.getConfigMessage("EventSet.Apricorn"), ++before);
            }
            if (!YamlUtils.getConfigMessage("EventSet.Apricorn_" + apricorn).equalsIgnoreCase("") && Main.getEventMap().get(player.getName(), YamlUtils.getConfigMessage("EventSet.Apricorn_" + apricorn)) != null) {
                int before = Main.getEventMap().get(player.getName(), YamlUtils.getConfigMessage("EventSet.Apricorn_" + apricorn));
                Main.getEventMap().put(player.getName(), YamlUtils.getConfigMessage("EventSet.Apricorn_" + apricorn), ++before);
            }
            return;
        }
        if (event.getForgeEvent() instanceof FishingEvent.Catch) {
            FishingEvent.Catch e = (FishingEvent.Catch) event.getForgeEvent();
            Player player = Bukkit.getPlayer(e.player.func_110124_au());
            if (!YamlUtils.getConfigMessage("EventSet.Fish").equalsIgnoreCase("") && Main.getEventMap().get(player.getName(), YamlUtils.getConfigMessage("EventSet.Fish")) != null) {
                int before = Main.getEventMap().get(player.getName(), YamlUtils.getConfigMessage("EventSet.Fish"));
                Main.getEventMap().put(player.getName(), YamlUtils.getConfigMessage("EventSet.Fish"), ++before);
            }
            return;
        }
        if (event.getForgeEvent() instanceof BeatTrainerEvent) {
            BeatTrainerEvent e = (BeatTrainerEvent) event.getForgeEvent();
            Player player = Bukkit.getPlayer(e.player.func_110124_au());
            String trainer = e.trainer.getName("en_us");
            if (!YamlUtils.getConfigMessage("EventSet.BeatTrainer").equalsIgnoreCase("") && Main.getEventMap().get(player.getName(), YamlUtils.getConfigMessage("EventSet.BeatTrainer")) != null) {
                int before = Main.getEventMap().get(player.getName(), YamlUtils.getConfigMessage("EventSet.BeatTrainer"));
                Main.getEventMap().put(player.getName(), YamlUtils.getConfigMessage("EventSet.BeatTrainer"), ++before);
            }
            if (!YamlUtils.getConfigMessage("EventSet.BeatTrainer_" + trainer).equalsIgnoreCase("") && Main.getEventMap().get(player.getName(), YamlUtils.getConfigMessage("EventSet.BeatTrainer_" + trainer)) != null) {
                int before = Main.getEventMap().get(player.getName(), YamlUtils.getConfigMessage("EventSet.BeatTrainer_" + trainer));
                Main.getEventMap().put(player.getName(), YamlUtils.getConfigMessage("EventSet.BeatTrainer_" + trainer), ++before);
            }
            return;
        }
    }
}
