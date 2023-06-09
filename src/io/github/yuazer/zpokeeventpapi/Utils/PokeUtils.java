package io.github.yuazer.zpokeeventpapi.Utils;

import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;
import org.bukkit.Location;

public class PokeUtils {
    public static boolean isEntityWithinRange(PixelmonEntity entity, Location minLocation, Location maxLocation) {
        Location entityLocation = entity.getBukkitEntity().getLocation();

        double entityX = entityLocation.getX();
        double entityY = entityLocation.getY();
        double entityZ = entityLocation.getZ();

        double minX = Math.min(minLocation.getX(), maxLocation.getX());
        double minY = Math.min(minLocation.getY(), maxLocation.getY());
        double minZ = Math.min(minLocation.getZ(), maxLocation.getZ());

        double maxX = Math.max(minLocation.getX(), maxLocation.getX());
        double maxY = Math.max(minLocation.getY(), maxLocation.getY());
        double maxZ = Math.max(minLocation.getZ(), maxLocation.getZ());

        return entityX >= minX && entityX <= maxX &&
                entityY >= minY && entityY <= maxY &&
                entityZ >= minZ && entityZ <= maxZ;
    }
}
