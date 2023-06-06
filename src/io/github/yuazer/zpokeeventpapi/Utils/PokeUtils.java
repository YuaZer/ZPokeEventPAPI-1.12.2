package io.github.yuazer.zpokeeventpapi.Utils;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.battles.attacks.Attack;
import com.pixelmonmod.pixelmon.comm.EnumUpdateType;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PokeUtils {
    //将宝可梦存为NBT文件
    public static void setPokemonInFile_NBT(Pokemon pokemon, File file) throws IOException {
        NBTTagCompound nbt = new NBTTagCompound();
        pokemon.setUUID(UUID.randomUUID());
        pokemon.writeToNBT(nbt);
        CompressedStreamTools.func_74795_b(nbt, file);
    }

    //从文件中的NBT获取宝可梦
    public static Pokemon getPokemonInFile_NBT(File file) throws IOException {
        Pokemon pokemon = Pixelmon.pokemonFactory.create(CompressedStreamTools.func_74797_a(file));
        return pokemon;
    }

    public static void tryNotifyPokemon(Pokemon pokemon) {
        if (pokemon != null) {
            pokemon.markDirty(EnumUpdateType.Moveset);
        }
    }

    public static void addPP(Pokemon pokemon, Attack attack, int ppAmount) {
        if (attack != null) {
            attack.pp += ppAmount;
        }
        tryNotifyPokemon(pokemon);
    }
}
