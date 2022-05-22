package dev.rachamon.rachamonpixelmonbooster.stuctures;

import dev.rachamon.rachamonpixelmonbooster.RachamonPixelmonBooster;
import org.spongepowered.api.scheduler.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public abstract class Booster {
    private BoosterType booster = null;
    private final List<UUID> players = new ArrayList<>();
    private boolean isGloballyActive = false;
    private int interval = 1000;
    private boolean isRunning = false;
    private Task boosterTask;

    public Booster(BoosterType boosterType) {
        this.booster = boosterType;
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
                .interval(this.getInterval(), TimeUnit.SECONDS)
                .submit(RachamonPixelmonBooster.getInstance()));
    }

    private void processTask() {
        if (this.getPlayers().size() > 0) {
            return;
        }
        this.stopTask();
    }

    private void stopTask() {
        if (!this.isRunning()) {
            return;
        }
        this.setRunning(false);
        this.getBoosterTask().cancel();
    }

    public BoosterType getBooster() {
        return booster;
    }

    public List<UUID> getPlayers() {
        return players;
    }

    public boolean isGloballyActive() {
        return isGloballyActive;
    }

    public void setGloballyActive(boolean globallyActive) {
        isGloballyActive = globallyActive;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public Task getBoosterTask() {
        return boosterTask;
    }

    public void setBoosterTask(Task boosterTask) {
        this.boosterTask = boosterTask;
    }
}
