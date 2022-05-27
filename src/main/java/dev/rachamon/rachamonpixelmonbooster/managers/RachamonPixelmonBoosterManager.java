package dev.rachamon.rachamonpixelmonbooster.managers;

import dev.rachamon.rachamonpixelmonbooster.RachamonPixelmonBooster;
import dev.rachamon.rachamonpixelmonbooster.stuctures.Booster;
import dev.rachamon.rachamonpixelmonbooster.stuctures.BoosterType;

import java.util.HashMap;
import java.util.Map;

public class RachamonPixelmonBoosterManager {

    public static Map<BoosterType, Booster> boosters = new HashMap<BoosterType, Booster>();
    private static final RachamonPixelmonBooster plugin = RachamonPixelmonBooster.getInstance();

    public static Map<BoosterType, Booster> getBoosters() {
        return boosters;
    }

    public Booster getBooster(BoosterType boosterType) throws Exception {
        Booster booster = RachamonPixelmonBoosterManager.getBoosters().get(boosterType);
        if (booster == null) {
            throw new Exception("booster not found");
        }

        return booster;
    }

    public BoosterType getBooster(String boost) throws Exception {
        BoosterType boosterType = BoosterType.fromString(boost);

        if (boosterType == null) {
            throw new Exception("booster not found");
        }

        return boosterType;
    }

    public void givePlayerBooster() {
    }

    public void removePlayerBooster() {
    }

    public void playerUseBooster() {
    }

    public void playerResumeBooster() {
    }

    public void playerPauseBooster() {
    }

    public void activateGlobalBooster(String boost) throws Exception {
        Booster booster = this.getBooster(this.getBooster(boost));
        booster.setGloballyActive(true);
    }

    public void deactivateGlobalBooster(String boost) throws Exception {
        Booster booster = this.getBooster(this.getBooster(boost));
        booster.setGloballyActive(false);
    }

    public void printPlayerBoosterInfo() {
    }

}
