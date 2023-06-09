package io.github.yuazer.zpokeeventpapi.Hook;

import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;
import io.github.yuazer.zaxlib.Utils.NMSUtils;
import io.github.yuazer.zpokeeventpapi.Main;
import io.github.yuazer.zpokeeventpapi.Utils.PokeUtils;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.minecraft.entity.Entity;

import net.minecraft.world.World;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.stream.Collectors;

public class CheckHook extends PlaceholderExpansion {
    @Override
    public String getIdentifier() {
        return "zpepcheck";
    }

    @Override
    public String getAuthor() {
        return "Z菌";
    }

    @Override
    public String onPlaceholderRequest(Player p, String indentifier) {
        if (p == null) {
            return "";
        }
        String[] key = indentifier.split("-");
        String w = key[0];
        String loc1 = key[1];
        String loc2 = key[2];
        String pokename = key[3];
        int count = 0;
        World world = NMSUtils.bkToNmsWorld(Bukkit.getWorld(w));
        String[] min = loc1.split("/");
        String[] max = loc2.split("/");
        Location minLoc = new Location(Bukkit.getWorld(w), Double.parseDouble(min[0]), Double.parseDouble(min[1]), Double.parseDouble(min[2]));
        Location maxLoc = new Location(Bukkit.getWorld(w), Double.parseDouble(max[0]), Double.parseDouble(max[1]), Double.parseDouble(max[2]));
        for (Entity entity : world.getMinecraftWorld().getEntities().collect(Collectors.toSet())) {
            if (entity instanceof PixelmonEntity) {
                PixelmonEntity entityPixelmon = ((PixelmonEntity) entity);
                if (entityPixelmon.hasOwner() || entityPixelmon.battleController != null) {
                    continue;
                }
                if (PokeUtils.isEntityWithinRange(entityPixelmon, minLoc, maxLoc) && entityPixelmon.getPokemon().getSpecies().getName().equalsIgnoreCase(pokename)) {
                    count++;
                }
            }
        }
        return String.valueOf(count);
    }

    @Override
    public String getVersion() {
        return Main.getInstance().getDescription().getVersion();
    }

}
