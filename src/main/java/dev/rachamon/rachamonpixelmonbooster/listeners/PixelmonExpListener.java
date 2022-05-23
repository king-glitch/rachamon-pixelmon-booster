package dev.rachamon.rachamonpixelmonbooster.listeners;

import com.pixelmonmod.pixelmon.api.events.ExperienceGainEvent;
import dev.rachamon.rachamonpixelmonbooster.managers.RachamonPixelmonBoosterManager;
import dev.rachamon.rachamonpixelmonbooster.stuctures.BoosterType;
import dev.rachamon.rachamonpixelmonbooster.stuctures.boosters.PokemonExpBooster;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.api.entity.living.player.Player;

public class PixelmonExpListener {

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
            event.setExperience(booster.calculate(event.getExperience()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
