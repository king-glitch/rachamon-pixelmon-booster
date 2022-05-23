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

    public static Booster getBooster(BoosterType boosterType) {
        return RachamonPixelmonBoosterManager.getBoosters().get(boosterType);
    }
}
