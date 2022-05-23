package dev.rachamon.rachamonpixelmonbooster.listeners;

import com.pixelmonmod.pixelmon.api.events.CaptureEvent;
import dev.rachamon.rachamonpixelmonbooster.managers.RachamonPixelmonBoosterManager;
import dev.rachamon.rachamonpixelmonbooster.stuctures.BoosterType;
import dev.rachamon.rachamonpixelmonbooster.stuctures.boosters.PokemonCaptureBooster;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.api.entity.living.player.Player;

public class PokemonCaptureListener {
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
            event.setCatchRate(booster.calculate(event.getCatchRate()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
