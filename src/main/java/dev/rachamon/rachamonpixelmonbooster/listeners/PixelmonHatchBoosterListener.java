package dev.rachamon.rachamonpixelmonbooster.listeners;

import com.pixelmonmod.pixelmon.api.events.BreedEvent;
import com.pixelmonmod.pixelmon.config.PixelmonConfig;
import dev.rachamon.rachamonpixelmonbooster.managers.RachamonPixelmonBoosterManager;
import dev.rachamon.rachamonpixelmonbooster.stuctures.BoosterType;
import dev.rachamon.rachamonpixelmonbooster.stuctures.boosters.PokemonHatchBooster;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.UUID;

/**
 * The type Pixelmon hatch booster listener.
 */
public class PixelmonHatchBoosterListener {

    /**
     * On pokemon hatch.
     *
     * @param event the event
     */
    @SubscribeEvent
    public void onPokemonHatch(BreedEvent.MakeEgg event) {

        UUID uuid = event.owner;
        PokemonHatchBooster booster = (PokemonHatchBooster) RachamonPixelmonBoosterManager
                .getBoosters()
                .get(BoosterType.HATCH);

        if (!booster.isGloballyActive() && booster
                .getPlayers()
                .stream()
                .noneMatch(p -> p.getUuid().equals(uuid))) {
            return;
        }

        try {
            NBTTagCompound nbt = new NBTTagCompound();
            event.getEgg().writeToNBT(nbt);
            int current = nbt.getInteger("steps");
            nbt.setInteger("steps", PixelmonConfig.stepsPerEggCycle - booster.calculate(current));
            event.getEgg().readFromNBT(nbt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
