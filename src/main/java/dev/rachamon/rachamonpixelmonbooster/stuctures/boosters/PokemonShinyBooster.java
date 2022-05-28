package dev.rachamon.rachamonpixelmonbooster.stuctures.boosters;

import dev.rachamon.rachamonpixelmonbooster.configs.BoosterConfig;
import dev.rachamon.rachamonpixelmonbooster.stuctures.Booster;
import dev.rachamon.rachamonpixelmonbooster.stuctures.BoosterType;

import java.util.Random;

/**
 * The type Pokemon shiny booster.
 */
public class PokemonShinyBooster extends Booster {
    /**
     * Instantiates a new Pokemon shiny booster.
     */
    public PokemonShinyBooster() {
        super(BoosterType.SHINY_RATE);
    }

    /**
     * Is chance boolean.
     *
     * @return the boolean
     */
    public boolean isChance() {
        return ((BoosterConfig.ChanceBooster) this.getConfig()).getChance() >= new Random().nextDouble();
    }
}
