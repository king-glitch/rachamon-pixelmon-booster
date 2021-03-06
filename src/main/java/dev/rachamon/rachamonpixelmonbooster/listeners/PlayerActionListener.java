package dev.rachamon.rachamonpixelmonbooster.listeners;

import dev.rachamon.rachamonpixelmonbooster.RachamonPixelmonBooster;
import dev.rachamon.rachamonpixelmonbooster.configs.PlayerDataConfig;
import dev.rachamon.rachamonpixelmonbooster.stuctures.BoosterType;
import dev.rachamon.rachamonpixelmonbooster.utils.ChatUtil;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.network.ClientConnectionEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Player action listener.
 */
public class PlayerActionListener {

    /**
     * On player join.
     *
     * @param event the event
     */
    @Listener
    public void onPlayerJoin(ClientConnectionEvent.Join event) {
        Player player = (Player) event.getSource();
        PlayerDataConfig.PlayerData playerData = RachamonPixelmonBooster
                .getInstance()
                .getPlayerData()
                .getPlayers()
                .get(player.getUniqueId());

        if (playerData == null) {
            playerData = RachamonPixelmonBooster
                    .getInstance()
                    .getPlayerDataService()
                    .addPlayerData(player.getUniqueId());
        }

        List<String> activatingBoosters = new ArrayList<>();

        for (BoosterType boosterType : playerData.getBooster().keySet()) {

            PlayerDataConfig.PlayerBoostData playerBoostData = playerData.getBooster().get(boosterType);


            if (!playerBoostData.isActivate() || playerBoostData.getTimeLeft() < 1) {
                continue;
            }

            try {
                RachamonPixelmonBooster.getInstance().getBoosterManager().playerResumeBooster(player, boosterType);
                activatingBoosters.add(boosterType.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (activatingBoosters.size() == 0) {
            return;
        }

        ChatUtil.sendMessage(player, RachamonPixelmonBooster
                .getInstance()
                .getLanguage()
                .getGeneralLanguage()
                .getPlayerJoined()
                .replaceAll("\\{boosts}", String.join(", ", activatingBoosters)));
    }

    /**
     * On player leave.
     *
     * @param event the event
     */
    @Listener
    public void onPlayerLeave(ClientConnectionEvent.Disconnect event) {
        Player player = (Player) event.getSource();
        PlayerDataConfig.PlayerData playerData = RachamonPixelmonBooster
                .getInstance()
                .getPlayerData()
                .getPlayers()
                .get(player.getUniqueId());

        for (BoosterType boosterType : playerData.getBooster().keySet()) {

            PlayerDataConfig.PlayerBoostData playerBoostData = playerData.getBooster().get(boosterType);

            try {
                RachamonPixelmonBooster.getInstance().getBoosterManager().playerPauseBooster(player, boosterType);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
