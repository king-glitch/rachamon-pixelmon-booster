package dev.rachamon.rachamonpixelmonbooster.services;

import dev.rachamon.rachamonpixelmonbooster.RachamonPixelmonBooster;
import dev.rachamon.rachamonpixelmonbooster.configs.BoosterConfig;
import dev.rachamon.rachamonpixelmonbooster.configs.PlayerDataConfig;
import dev.rachamon.rachamonpixelmonbooster.stuctures.BoosterType;

import java.util.Map;
import java.util.UUID;

public class PlayerDataService {

    public PlayerDataConfig.PlayerData addPlayerData(UUID uuid) {
        RachamonPixelmonBooster.getInstance().getPlayerData().getPlayers().put(uuid, new PlayerDataConfig.PlayerData());
        this.save();
        return this.getPlayerData(uuid);
    }

    public PlayerDataConfig.PlayerBoostData addPlayerBoostData(UUID uuid, BoosterType boosterType) throws Exception {
        PlayerDataConfig.PlayerData playerData = this.getPlayerData(uuid);
        playerData.getBooster().put(boosterType, new PlayerDataConfig.PlayerBoostData(0, 0, false));
        this.save();
        return this.getPlayerBoostData(uuid, boosterType.toString());
    }

    public PlayerDataConfig.PlayerData getPlayerData(UUID uuid) {
        PlayerDataConfig.PlayerData playerData = RachamonPixelmonBooster
                .getInstance()
                .getPlayerData()
                .getPlayers()
                .get(uuid);

        if (playerData == null) {
            return this.addPlayerData(uuid);
        }

        return playerData;
    }

    public PlayerDataConfig.PlayerBoostData getPlayerBoostData(UUID uuid, String boost) throws Exception {
        BoosterType boosterType = RachamonPixelmonBooster.getInstance().getBoosterManager().getBooster(boost);
        return this.getPlayerBoostData(uuid, boosterType);
    }

    public PlayerDataConfig.PlayerBoostData getPlayerBoostData(UUID uuid, BoosterType boosterType) throws Exception {
        PlayerDataConfig.PlayerBoostData playerBoostData = this.getPlayerData(uuid).getBooster().get(boosterType);
        if (playerBoostData == null) {
            return this.addPlayerBoostData(uuid, boosterType);
        }
        return playerBoostData;
    }

    public void updatePlayerData(UUID uuid, PlayerDataConfig.PlayerData data) {
        RachamonPixelmonBooster.getInstance().getPlayerData().getPlayers().put(uuid, data);
        this.save();
    }

    public void updatePlayerBoostData(UUID uuid, BoosterType boosterType, PlayerDataConfig.PlayerBoostData data) {
        this.getPlayerData(uuid).getBooster().put(boosterType, data);
        this.save();
    }

    public void updatePlayerBoostAmountData(UUID uuid, BoosterType boosterType, int amount) {
        PlayerDataConfig.PlayerData playerData = this.getPlayerData(uuid);
        playerData
                .getBooster()
                .get(boosterType)
                .setAmount(playerData.getBooster().get(boosterType).getAmount() + amount);

        this.updatePlayerBoostData(uuid, boosterType, playerData.getBooster().get(boosterType));
        this.save();
    }

    public void updatePlayerBoostTimeData(UUID uuid, BoosterType boosterType, int amount) {
        PlayerDataConfig.PlayerData playerData = this.getPlayerData(uuid);
        playerData
                .getBooster()
                .get(boosterType)
                .setAmount(playerData.getBooster().get(boosterType).getTimeLeft() + amount);
        this.updatePlayerBoostData(uuid, boosterType, playerData.getBooster().get(boosterType));
        this.save();
    }

    public void setPlayerBoostAmountData(UUID uuid, BoosterType boosterType, int amount) {
        PlayerDataConfig.PlayerData playerData = this.getPlayerData(uuid);
        playerData
                .getBooster()
                .get(boosterType)
                .setAmount(amount);

        this.updatePlayerBoostData(uuid, boosterType, playerData.getBooster().get(boosterType));
        this.save();
    }

    public void setPlayerBoostTimeData(UUID uuid, BoosterType boosterType, int amount) {
        PlayerDataConfig.PlayerData playerData = this.getPlayerData(uuid);
        playerData
                .getBooster()
                .get(boosterType)
                .setAmount(amount);

        this.updatePlayerBoostData(uuid, boosterType, playerData.getBooster().get(boosterType));
        this.save();
    }

    public void cleanUnusedPlayersData() {
        Map<UUID, PlayerDataConfig.PlayerData> players = RachamonPixelmonBooster
                .getInstance()
                .getPlayerData()
                .getPlayers();

        for (UUID uuid : players.keySet()) {
            PlayerDataConfig.PlayerData playerData = players.get(uuid);

            boolean unused = true;

            for (BoosterType boosterType : playerData.getBooster().keySet()) {
                PlayerDataConfig.PlayerBoostData playerBoostData = playerData.getBooster().get(boosterType);
                if (playerBoostData.getTimeLeft() > 0 || playerBoostData.getAmount() > 0) {
                    unused = false;
                }
            }

            if (!unused) {
                continue;
            }

            players.remove(uuid);
            RachamonPixelmonBooster.getInstance().getLogger().debug("remove unused uuid: " + uuid);
        }

        this.save();

    }

    public void save() {
        try {
            RachamonPixelmonBooster.getInstance().getPlayerDataService().save();
        } catch (Exception e) {
            e.printStackTrace();
            RachamonPixelmonBooster.getInstance().getLogger().error("Something wrong while save player data");
        }
    }
}
