package dev.rachamon.rachamonpixelmonbooster.managers;

import dev.rachamon.api.sponge.util.TextUtil;
import dev.rachamon.rachamonpixelmonbooster.RachamonPixelmonBooster;
import dev.rachamon.rachamonpixelmonbooster.configs.PlayerDataConfig;
import dev.rachamon.rachamonpixelmonbooster.stuctures.Booster;
import dev.rachamon.rachamonpixelmonbooster.stuctures.BoosterType;
import dev.rachamon.rachamonpixelmonbooster.stuctures.PlayerTime;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.service.pagination.PaginationList;
import org.spongepowered.api.text.Text;

import java.util.*;

/**
 * The type Rachamon pixelmon booster manager.
 */
public class RachamonPixelmonBoosterManager {

    /**
     * The constant boosters.
     */
    public static Map<BoosterType, Booster> boosters = new HashMap<BoosterType, Booster>();
    private final RachamonPixelmonBooster plugin = RachamonPixelmonBooster.getInstance();

    /**
     * Gets boosters.
     *
     * @return the boosters
     */
    public static Map<BoosterType, Booster> getBoosters() {
        return boosters;
    }

    /**
     * Gets booster.
     *
     * @param boosterType the booster type
     * @return the booster
     * @throws Exception the exception
     */
    public Booster getBooster(BoosterType boosterType) throws Exception {
        Booster booster = RachamonPixelmonBoosterManager.getBoosters().get(boosterType);
        if (booster == null) {
            throw new Exception("booster not found");
        }

        return booster;
    }

    /**
     * Gets booster.
     *
     * @param boost the boost
     * @return the booster
     * @throws Exception the exception
     */
    public BoosterType getBooster(String boost) throws Exception {
        BoosterType boosterType = BoosterType.fromString(boost);

        if (boosterType == null) {
            throw new Exception("booster not found");
        }

        return boosterType;
    }

    /**
     * Add player booster.
     *
     * @param player  the player
     * @param booster the booster
     * @param amount  the amount
     * @throws Exception the exception
     */
    public void addPlayerBooster(Player player, String booster, int amount) throws Exception {

        if (amount <= 0) {
            return;
        }

        BoosterType boosterType = this.getBooster(booster);
        this.plugin.getPlayerDataService().updatePlayerBoostAmountData(player.getUniqueId(), boosterType, amount);
    }

    /**
     * Remove player booster.
     *
     * @param player  the player
     * @param booster the booster
     * @param amount  the amount
     * @throws Exception the exception
     */
    public void removePlayerBooster(Player player, String booster, int amount) throws Exception {
        if (amount <= 0) {
            return;
        }

        BoosterType boosterType = this.getBooster(booster);
        this.plugin.getPlayerDataService().updatePlayerBoostAmountData(player.getUniqueId(), boosterType, -amount);
    }

    /**
     * Sets amount player booster.
     *
     * @param player  the player
     * @param booster the booster
     * @param amount  the amount
     * @throws Exception the exception
     */
    public void setAmountPlayerBooster(Player player, String booster, int amount) throws Exception {
        if (amount < 0) {
            return;
        }

        BoosterType boosterType = this.getBooster(booster);
        this.plugin.getPlayerDataService().setPlayerBoostAmountData(player.getUniqueId(), boosterType, amount);
    }

    /**
     * Sets time player booster.
     *
     * @param player  the player
     * @param booster the booster
     * @param amount  the amount
     * @throws Exception the exception
     */
    public void setTimePlayerBooster(Player player, String booster, int amount) throws Exception {
        if (amount < 0) {
            return;
        }

        BoosterType boosterType = this.getBooster(booster);
        this.plugin.getPlayerDataService().setPlayerBoostTimeData(player.getUniqueId(), boosterType, amount);
    }


    /**
     * Player use booster.
     *
     * @param player  the player
     * @param booster the booster
     * @throws Exception the exception
     */
    public void playerUseBooster(Player player, String booster) throws Exception {
        BoosterType boosterType = this.getBooster(booster);
        PlayerDataConfig.PlayerData playerData = this.plugin.getPlayerDataService().getPlayerData(player.getUniqueId());
        PlayerDataConfig.PlayerBoostData playerBoostData = playerData.getBooster().get(boosterType);

        if (playerBoostData.getAmount() < 1) {
            return;
        }

        this.plugin.getPlayerDataService().updatePlayerBoostAmountData(player.getUniqueId(), boosterType, -1);

        this.plugin
                .getPlayerDataService()
                .updatePlayerBoostTimeData(player.getUniqueId(), boosterType, playerBoostData.getTimeLeft() + RachamonPixelmonBooster
                        .getInstance()
                        .getBooster()
                        .getBoosters()
                        .get(boosterType.toString())
                        .getDuration());
        this.addPlayerToTaskList(player, boosterType);
    }

    /**
     * Player resume booster.
     *
     * @param player  the player
     * @param booster the booster
     * @throws Exception the exception
     */
    public void playerResumeBooster(Player player, String booster) throws Exception {
        BoosterType boosterType = this.getBooster(booster);
        this.playerResumeBooster(player, boosterType);
    }

    /**
     * Player resume booster.
     *
     * @param player      the player
     * @param boosterType the booster type
     * @throws Exception the exception
     */
    public void playerResumeBooster(Player player, BoosterType boosterType) throws Exception {
        PlayerDataConfig.PlayerData playerData = this.plugin.getPlayerDataService().getPlayerData(player.getUniqueId());
        PlayerDataConfig.PlayerBoostData playerBoostData = playerData.getBooster().get(boosterType);

        if (playerBoostData.getTimeLeft() < 1) {
            return;
        }

        this.addPlayerToTaskList(player, boosterType);
    }

    private void addPlayerToTaskList(Player player, BoosterType boosterType) throws Exception {
        List<PlayerTime> playerTimeList = RachamonPixelmonBoosterManager.getBoosters().get(boosterType).getPlayers();

        PlayerTime playerTime = playerTimeList
                .stream()
                .filter(pt -> pt.getUuid().equals(player.getUniqueId()))
                .findFirst()
                .orElse(null);

        if (playerTime == null) {
            RachamonPixelmonBoosterManager
                    .getBoosters()
                    .get(boosterType)
                    .getPlayers()
                    .add(new PlayerTime(player.getUniqueId(), RachamonPixelmonBooster
                            .getInstance()
                            .getPlayerDataService()
                            .getPlayerBoostData(player.getUniqueId(), boosterType.toString())
                            .getTimeLeft()));
            return;
        }

        playerTime.setTime(this.plugin
                .getPlayerDataService()
                .getPlayerBoostData(player.getUniqueId(), boosterType.toString())
                .getTimeLeft());

        RachamonPixelmonBoosterManager.getBoosters().get(boosterType).runTask();
    }

    /**
     * Player pause booster.
     *
     * @param player  the player
     * @param booster the booster
     * @throws Exception the exception
     */
    public void playerPauseBooster(Player player, String booster) throws Exception {
        BoosterType boosterType = this.getBooster(booster);
        this.playerPauseBooster(player, boosterType);
    }

    /**
     * Player pause booster.
     *
     * @param player      the player
     * @param boosterType the booster type
     */
    public void playerPauseBooster(Player player, BoosterType boosterType) {
        List<PlayerTime> playerTimeList = RachamonPixelmonBoosterManager.getBoosters().get(boosterType).getPlayers();

        PlayerTime playerTime = playerTimeList
                .stream()
                .filter(pt -> pt.getUuid().equals(player.getUniqueId()))
                .findFirst()
                .orElse(null);

        if (playerTime == null) {
            return;
        }

        playerTimeList.removeIf(pt -> pt.getUuid().equals(player.getUniqueId()));

        RachamonPixelmonBooster
                .getInstance()
                .getPlayerDataService()
                .setPlayerBoostTimeData(player.getUniqueId(), boosterType, playerTime.getTime());
    }

    /**
     * Activate global booster.
     *
     * @param boost the boost
     * @throws Exception the exception
     */
    public void activateGlobalBooster(String boost) throws Exception {
        BoosterType boosterType = this.getBooster(boost);
        Booster booster = this.getBooster(boosterType);
        booster.setGloballyActive(true);
        RachamonPixelmonBoosterManager.getBoosters().get(boosterType).runTask();
    }

    /**
     * Deactivate global booster.
     *
     * @param boost the boost
     * @throws Exception the exception
     */
    public void deactivateGlobalBooster(String boost) throws Exception {
        Booster booster = this.getBooster(this.getBooster(boost));
        booster.setGloballyActive(false);
    }

    /**
     * Print player booster info.
     *
     * @param player the player
     * @throws Exception the exception
     */
    public void printPlayerBoosterInfo(Player player) throws Exception {
        PlayerDataConfig.PlayerData playerData = this.plugin.getPlayerDataService().getPlayerData(player.getUniqueId());

        PaginationList.Builder builder = PaginationList
                .builder()
                .title(TextUtil.toText("&6&lGuilds"))
                .padding(TextUtil.toText("&8="));

        List<Text> contents = new ArrayList<>();
        int i = 1;
        for (BoosterType boosterType : playerData.getBooster().keySet()) {
            PlayerDataConfig.PlayerBoostData playerBoostData = playerData.getBooster().get(boosterType);
            String text = i + ". " + boosterType.toString() + " time-left: " + playerBoostData.getTimeLeft() + ", amount: " + playerBoostData.getAmount() + ", activated: " + this.isPlayerBoostActivated(player.getUniqueId(), boosterType);
            contents.add(TextUtil.toText(text));
            i++;
        }

        builder.contents(contents).sendTo(player);


    }

    /**
     * Is player boost activated boolean.
     *
     * @param uuid        the uuid
     * @param boosterType the booster type
     * @return the boolean
     */
    public boolean isPlayerBoostActivated(UUID uuid, BoosterType boosterType) {
        List<PlayerTime> playerTimeList = RachamonPixelmonBoosterManager.getBoosters().get(boosterType).getPlayers();

        PlayerTime playerTime = playerTimeList
                .stream()
                .filter(pt -> pt.getUuid().equals(uuid))
                .findFirst()
                .orElse(null);

        return playerTime != null;
    }

}
