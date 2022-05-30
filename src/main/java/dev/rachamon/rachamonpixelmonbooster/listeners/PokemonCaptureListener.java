package dev.rachamon.rachamonpixelmonbooster.listeners;

import com.pixelmonmod.pixelmon.api.events.CaptureEvent;
import dev.rachamon.rachamonpixelmonbooster.RachamonPixelmonBooster;
import dev.rachamon.rachamonpixelmonbooster.managers.RachamonPixelmonBoosterManager;
import dev.rachamon.rachamonpixelmonbooster.stuctures.BoosterType;
import dev.rachamon.rachamonpixelmonbooster.stuctures.boosters.PokemonCaptureBooster;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.api.entity.living.player.Player;

/**
 * The type Pokemon capture listener.
 */
public class PokemonCaptureListener {
    /**
     * On start capture.
     *
     * @param event the event
     */
    @SubscribeEvent
    public void onStartCapture(CaptureEvent.StartCapture event) {
        Player player = (Player) event.player;
        PokemonCaptureBooster booster = (PokemonCaptureBooster) RachamonPixelmonBoosterManager.getBoosters().get(BoosterType.CAPTURE);

        if (!booster.isGloballyActive() && booster
                .getPlayers()
                .stream()
                .noneMatch(p -> p.getUuid().equals(player.getUniqueId()))) {
            return;
        }

        try {
            RachamonPixelmonBooster.getInstance().getLogger().debug("catch rate: " + event.getCatchRate());
            event.setCatchRate(booster.calculate(event.getCatchRate()));
            RachamonPixelmonBooster.getInstance().getLogger().debug("new catch rate: " + event.getCatchRate());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
