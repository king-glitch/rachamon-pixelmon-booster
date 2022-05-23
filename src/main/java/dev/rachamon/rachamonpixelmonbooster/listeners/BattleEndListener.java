package dev.rachamon.rachamonpixelmonbooster.listeners;

import com.pixelmonmod.pixelmon.api.events.BeatTrainerEvent;
import dev.rachamon.rachamonpixelmonbooster.managers.RachamonPixelmonBoosterManager;
import dev.rachamon.rachamonpixelmonbooster.stuctures.BoosterType;
import dev.rachamon.rachamonpixelmonbooster.stuctures.boosters.TrainerMoneyBooster;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.api.entity.living.player.Player;

public class BattleEndListener {

    @SubscribeEvent
    public void onPlayerBeatTrainer(BeatTrainerEvent event) {

        Player player = (Player) event.player;
        TrainerMoneyBooster booster = (TrainerMoneyBooster) RachamonPixelmonBoosterManager
                .getBoosters()
                .get(BoosterType.BATTLE_WINNING);

        if (!booster.isGloballyActive() && booster
                .getPlayers()
                .stream()
                .noneMatch(p -> p.getUuid().equals(player.getUniqueId()))) {
            return;
        }

        try {
            event.trainer.winMoney = booster.calculate(event.trainer.winMoney);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
