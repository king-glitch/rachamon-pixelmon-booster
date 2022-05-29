package dev.rachamon.rachamonpixelmonbooster.services;

import dev.rachamon.rachamonpixelmonbooster.RachamonPixelmonBooster;
import dev.rachamon.rachamonpixelmonbooster.configs.PlayerDataConfig;
import dev.rachamon.rachamonpixelmonbooster.stuctures.BoosterType;

import java.util.Map;
import java.util.UUID;

/**
 * The type Player data service.
 */
public class PlayerDataService {

    /**
     * Add player data player data config . player data.
     *
     * @param uuid the uuid
     * @return the player data config . player data
     */
    public PlayerDataConfig.PlayerData addPlayerData(UUID uuid) {
        RachamonPixelmonBooster.getInstance().getPlayerData().getPlayers().put(uuid, new PlayerDataConfig.PlayerData());
        this.save();
        return this.getPlayerData(uuid);
    }

    /**
     * Add player boost data player data config . player boost data.
     *
     * @param uuid        the uuid
     * @param boosterType the booster type
     * @return the player data config . player boost data
     * @throws Exception the exception
     */
    public PlayerDataConfig.PlayerBoostData addPlayerBoostData(UUID uuid, BoosterType boosterType) throws Exception {
        PlayerDataConfig.PlayerData playerData = this.getPlayerData(uuid);
        playerData.getBooster().put(boosterType, new PlayerDataConfig.PlayerBoostData(0, 0, false));
        this.save();
        return this.getPlayerBoostData(uuid, boosterType.toString());
    }

    /**
     * Gets player data.
     *
     * @param uuid the uuid
     * @return the player data
     */
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

    /**
     * Gets player boost data.
     *
     * @param uuid  the uuid
     * @param boost the boost
     * @return the player boost data
     * @throws Exception the exception
     */
    public PlayerDataConfig.PlayerBoostData getPlayerBoostData(UUID uuid, String boost) throws Exception {
        BoosterType boosterType = RachamonPixelmonBooster.getInstance().getBoosterManager().getBooster(boost);
        return this.getPlayerBoostData(uuid, boosterType);
    }

    /**
     * Gets player boost data.
     *
     * @param uuid        the uuid
     * @param boosterType the booster type
     * @return the player boost data
     * @throws Exception the exception
     */
    public PlayerDataConfig.PlayerBoostData getPlayerBoostData(UUID uuid, BoosterType boosterType) throws Exception {
        PlayerDataConfig.PlayerBoostData playerBoostData = this.getPlayerData(uuid).getBooster().get(boosterType);
        if (playerBoostData == null) {
            return this.addPlayerBoostData(uuid, boosterType);
        }
        return playerBoostData;
    }

    /**
     * Update player data.
     *
     * @param uuid the uuid
     * @param data the data
     */
    public void updatePlayerData(UUID uuid, PlayerDataConfig.PlayerData data) {
        RachamonPixelmonBooster.getInstance().getPlayerData().getPlayers().put(uuid, data);
        this.save();
    }

    /**
     * Update player boost data.
     *
     * @param uuid        the uuid
     * @param boosterType the booster type
     * @param data        the data
     */
    public void updatePlayerBoostData(UUID uuid, BoosterType boosterType, PlayerDataConfig.PlayerBoostData data) {
        this.getPlayerData(uuid).getBooster().put(boosterType, data);
        this.save();
    }

    /**
     * Update player boost amount data.
     *
     * @param uuid        the uuid
     * @param boosterType the booster type
     * @param amount      the amount
     * @throws Exception the exception
     */
    public void updatePlayerBoostAmountData(UUID uuid, BoosterType boosterType, int amount) throws Exception {
        PlayerDataConfig.PlayerData playerData = this.getPlayerData(uuid);
        PlayerDataConfig.PlayerBoostData playerBoostData = playerData.getBooster().get(boosterType);

        if (playerBoostData == null) {
            playerBoostData = this.addPlayerBoostData(uuid, boosterType);
        }

        playerBoostData.setAmount(Math.max(playerBoostData.getAmount() + amount, 0));

        this.updatePlayerBoostData(uuid, boosterType, playerBoostData);
        this.save();
    }

    /**
     * Sets player boost activate.
     *
     * @param uuid        the uuid
     * @param boosterType the booster type
     * @param activate    the activate
     * @throws Exception the exception
     */
    public void setPlayerBoostActivate(UUID uuid, BoosterType boosterType, boolean activate) throws Exception {
        PlayerDataConfig.PlayerData playerData = this.getPlayerData(uuid);
        PlayerDataConfig.PlayerBoostData playerBoostData = playerData.getBooster().get(boosterType);

        if (playerBoostData == null) {
            playerBoostData = this.addPlayerBoostData(uuid, boosterType);
        }

        playerBoostData.setActivate(activate);

        this.updatePlayerBoostData(uuid, boosterType, playerBoostData);
        this.save();
    }

    /**
     * Update player boost time data.
     *
     * @param uuid        the uuid
     * @param boosterType the booster type
     * @param amount      the amount
     * @throws Exception the exception
     */
    public void updatePlayerBoostTimeData(UUID uuid, BoosterType boosterType, int amount) throws Exception {
        PlayerDataConfig.PlayerData playerData = this.getPlayerData(uuid);
        PlayerDataConfig.PlayerBoostData playerBoostData = playerData.getBooster().get(boosterType);

        if (playerBoostData == null) {
            playerBoostData = this.addPlayerBoostData(uuid, boosterType);
        }

        playerBoostData.setTimeLeft(Math.max(playerBoostData.getTimeLeft() + amount, 0));
        this.updatePlayerBoostData(uuid, boosterType, playerBoostData);
        this.save();
    }

    /**
     * Sets player boost amount data.
     *
     * @param uuid        the uuid
     * @param boosterType the booster type
     * @param amount      the amount
     * @throws Exception the exception
     */
    public void setPlayerBoostAmountData(UUID uuid, BoosterType boosterType, int amount) throws Exception {
        PlayerDataConfig.PlayerData playerData = this.getPlayerData(uuid);
        PlayerDataConfig.PlayerBoostData playerBoostData = playerData.getBooster().get(boosterType);

        if (playerBoostData == null) {
            playerBoostData = this.addPlayerBoostData(uuid, boosterType);
        }

        playerBoostData.setAmount(Math.max(amount, 0));

        this.updatePlayerBoostData(uuid, boosterType, playerBoostData);
        this.save();
    }

    /**
     * Sets player boost time data.
     *
     * @param uuid        the uuid
     * @param boosterType the booster type
     * @param amount      the amount
     * @throws Exception the exception
     */
    public void setPlayerBoostTimeData(UUID uuid, BoosterType boosterType, int amount) throws Exception {
        PlayerDataConfig.PlayerData playerData = this.getPlayerData(uuid);
        PlayerDataConfig.PlayerBoostData playerBoostData = playerData.getBooster().get(boosterType);

        if (playerBoostData == null) {
            playerBoostData = this.addPlayerBoostData(uuid, boosterType);
        }

        playerBoostData.setTimeLeft(Math.max(amount, 0));

        this.updatePlayerBoostData(uuid, boosterType, playerBoostData);
        this.save();
    }

    /**
     * Clean unused players data.
     *
     * @throws Exception the exception
     */
    public void cleanUnusedPlayersData() throws Exception {
        Map<UUID, PlayerDataConfig.PlayerData> players = RachamonPixelmonBooster
                .getInstance()
                .getPlayerData()
                .getPlayers();

        for (UUID uuid : players.keySet()) {
            PlayerDataConfig.PlayerData playerData = players.get(uuid);

            boolean unused = true;

            for (BoosterType boosterType : playerData.getBooster().keySet()) {
                PlayerDataConfig.PlayerBoostData playerBoostData = playerData.getBooster().get(boosterType);

                if (playerBoostData == null) {
                    playerBoostData = this.addPlayerBoostData(uuid, boosterType);
                }

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

    /**
     * Save.
     */
    public void save() {
        try {
            RachamonPixelmonBooster.getInstance().getPlayerDataConfig().save();
        } catch (Exception e) {
            e.printStackTrace();
            RachamonPixelmonBooster.getInstance().getLogger().error("Something wrong while save player data");
        }
    }
}
