package dev.rachamon.rachamonpixelmonbooster.managers;

import dev.rachamon.rachamonpixelmonbooster.RachamonPixelmonBooster;
import dev.rachamon.rachamonpixelmonbooster.stuctures.Booster;
import javafx.concurrent.Task;

import java.util.HashMap;
import java.util.Map;

public class RachamonPixelmonBoosterManager {

    public static Map<String, Booster> boosters = new HashMap<String, Booster>();
    private static final RachamonPixelmonBooster plugin = RachamonPixelmonBooster.getInstance();

}
