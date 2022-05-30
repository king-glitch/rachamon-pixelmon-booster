package dev.rachamon.rachamonpixelmonbooster.listeners;

import com.pixelmonmod.pixelmon.api.events.ExperienceGainEvent;
import dev.rachamon.rachamonpixelmonbooster.managers.RachamonPixelmonBoosterManager;
import dev.rachamon.rachamonpixelmonbooster.stuctures.BoosterType;
import dev.rachamon.rachamonpixelmonbooster.stuctures.boosters.PokemonExpBooster;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.api.entity.living.player.Player;

/**
 * The type Pixelmon exp listener.
 */
public class PixelmonExpListener {

    /**
     * On pokemon exp gain.
     *
     * @param event the event
     */
    @SubscribeEvent
    public void onPokemonExpGain(ExperienceGainEvent event) {

        Player player = (Player) event.pokemon.getPlayerOwner();
        PokemonExpBooster booster = (PokemonExpBooster) RachamonPixelmonBoosterManager
                .getBoosters()
                .get(BoosterType.EXP);

        if (!booster.isGloballyActive() && booster
                .getPlayers()
                .stream()
                .noneMatch(p -> p.getUuid().equals(player.getUniqueId()))) {
            return;
        }

        try {
            event.setExperience((int) booster.calculate(event.getExperience()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
