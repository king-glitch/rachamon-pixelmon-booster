package dev.rachamon.rachamonpixelmonbooster.stuctures.boosters;

import dev.rachamon.rachamonpixelmonbooster.configs.BoosterConfig;
import dev.rachamon.rachamonpixelmonbooster.stuctures.Booster;
import dev.rachamon.rachamonpixelmonbooster.stuctures.BoosterType;

import java.util.Random;

public class PokemonShinyBooster extends Booster {
    public PokemonShinyBooster() {
        super(BoosterType.SHINY_RATE);
    }

    public boolean isChance() {
        return ((BoosterConfig.ChanceBooster) this.getConfig()).getChance() >= new Random().nextDouble();
    }
}
