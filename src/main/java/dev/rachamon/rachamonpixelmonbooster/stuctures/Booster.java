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

public abstract class Booster {
    private BoosterType booster = null;
    private final List<PlayerTime> players = new ArrayList<>();
    private boolean isGloballyActive = false;
    private boolean isRunning = false;
    private int interval = 10;
    private Task boosterTask;

    private BoosterConfig.Booster config;

    public Booster(BoosterType boosterType) {
        this.booster = boosterType;
        try {
            this.interval = RachamonPixelmonBooster.getInstance().getConfig().getGeneralConfig().getTaskInterval();
            this.config = RachamonPixelmonBooster
                    .getInstance()
                    .getBooster()
                    .getBoosters()
                    .get(this.getBooster().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize() {

    }

    public void runTask() {
        if (this.isRunning()) {
            return;
        }
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
                playerTime.setTime(playerTime.getTime() - this.interval);
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
    }

    public BoosterType getBooster() {
        return this.booster;
    }

    public List<PlayerTime> getPlayers() {
        return this.players;
    }

    public boolean isGloballyActive() {
        return this.isGloballyActive;
    }

    public void setGloballyActive(boolean globallyActive) {
        this.isGloballyActive = globallyActive;
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public void setRunning(boolean running) {
        this.isRunning = running;
    }

    public Task getBoosterTask() {
        return this.boosterTask;
    }

    public void setBoosterTask(Task boosterTask) {
        this.boosterTask = boosterTask;
    }

    public BoosterConfig.Booster getConfig() {
        return this.config;
    }
}
