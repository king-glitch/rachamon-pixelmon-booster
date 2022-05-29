package dev.rachamon.rachamonpixelmonbooster.stuctures;

import dev.rachamon.api.sponge.util.TextUtil;
import dev.rachamon.rachamonpixelmonbooster.RachamonPixelmonBooster;
import dev.rachamon.rachamonpixelmonbooster.configs.BoosterConfig;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.scheduler.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * The type Booster.
 */
public class Booster {
    private BoosterType booster = null;
    private final List<PlayerTime> players = new ArrayList<>();
    private boolean isGloballyActive = false;
    private boolean isRunning = false;
    private int interval = 10;
    private Task boosterTask;

    private BoosterConfig.Booster config;

    /**
     * Instantiates a new Booster.
     *
     * @param boosterType the booster type
     */
    public Booster(BoosterType boosterType) {
        this.booster = boosterType;
        try {
            this.interval = RachamonPixelmonBooster.getInstance().getConfig().getGeneralConfig().getTaskInterval();
            this.config = RachamonPixelmonBooster
                    .getInstance()
                    .getBooster()
                    .getBoosters()
                    .get(boosterType.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialize.
     */
    public void initialize() {

    }

    /**
     * Run task.
     */
    public void runTask() {
        if (this.isRunning()) {
            return;
        }

        RachamonPixelmonBooster.getInstance().getLogger().debug("Starting " + this.booster + " booster task");

        this.setRunning(true);
        this.setBoosterTask(Task
                .builder()
                .execute(this::processTask)
                .interval(interval, TimeUnit.SECONDS)
                .submit(RachamonPixelmonBooster.getInstance()));
    }

    private void processTask() {
        this.getPlayers().removeIf(playerTime -> {
            if (playerTime.getTime() > 0) {
                int timeLeft = playerTime.getTime() - this.interval;
                playerTime.setTime(Math.max(timeLeft, 0));

                try {
                    RachamonPixelmonBooster
                            .getInstance()
                            .getPlayerDataService()
                            .setPlayerBoostTimeData(playerTime.getUuid(), this.getBooster(), playerTime.getTime());
                } catch (Exception e) {
                    RachamonPixelmonBooster.getInstance().getLogger().debug(e.getMessage());
                }
                return false;
            }

            Optional<Player> player = Sponge.getServer().getPlayer(playerTime.getUuid());

            if (!player.isPresent()) {
                return true;
            }

            player
                    .get()
                    .sendMessage(TextUtil.toText(RachamonPixelmonBooster
                            .getInstance()
                            .getLanguage()
                            .getGeneralLanguage()
                            .getBoosterEnded()
                            .replaceAll("\\{booster}", booster.toString())));

            return true;


        });

        if (this.getPlayers().size() == 0) {
            this.stopTask();
            RachamonPixelmonBooster
                    .getInstance()
                    .getLogger()
                    .debug(this.getBooster() + " booster has no more players, stopping the task.");
        }
    }

    private void stopTask() {
        if (!this.isRunning()) {
            return;
        }
        this.setRunning(false);
        this.getBoosterTask().cancel();
        RachamonPixelmonBooster.getInstance().getLogger().debug("Stopping " + this.booster + " booster task");
    }

    /**
     * Gets booster.
     *
     * @return the booster
     */
    public BoosterType getBooster() {
        return this.booster;
    }

    /**
     * Gets players.
     *
     * @return the players
     */
    public List<PlayerTime> getPlayers() {
        return this.players;
    }

    /**
     * Is globally active boolean.
     *
     * @return the boolean
     */
    public boolean isGloballyActive() {
        return this.isGloballyActive;
    }

    /**
     * Sets globally active.
     *
     * @param globallyActive the globally active
     */
    public void setGloballyActive(boolean globallyActive) {
        this.isGloballyActive = globallyActive;
    }

    /**
     * Is running boolean.
     *
     * @return the boolean
     */
    public boolean isRunning() {
        return this.isRunning;
    }

    /**
     * Sets running.
     *
     * @param running the running
     */
    public void setRunning(boolean running) {
        this.isRunning = running;
    }

    /**
     * Gets booster task.
     *
     * @return the booster task
     */
    public Task getBoosterTask() {
        return this.boosterTask;
    }

    /**
     * Sets booster task.
     *
     * @param boosterTask the booster task
     */
    public void setBoosterTask(Task boosterTask) {
        this.boosterTask = boosterTask;
    }

    /**
     * Gets config.
     *
     * @return the config
     */
    public BoosterConfig.Booster getConfig() {
        return this.config;
    }

    public void setConfig(BoosterConfig.Booster config) {
        this.config = config;
    }
}
