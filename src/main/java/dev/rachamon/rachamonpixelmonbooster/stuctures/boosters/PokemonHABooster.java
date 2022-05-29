package dev.rachamon.rachamonpixelmonbooster.stuctures.boosters;

import dev.rachamon.rachamonpixelmonbooster.configs.BoosterConfig;
import dev.rachamon.rachamonpixelmonbooster.stuctures.Booster;
import dev.rachamon.rachamonpixelmonbooster.stuctures.BoosterType;

import java.util.Random;

/**
 * The type Pokemon ha booster.
 */
public class PokemonHABooster extends Booster {
    /**
     * Instantiates a new Pokemon ha booster.
     */
    public PokemonHABooster() {
        super(BoosterType.HIDDEN_ABILITY);
    }

    /**
     * Is chance boolean.
     *
     * @return the boolean
     */
    public boolean isChance() {
        return Double.parseDouble((this.getConfig()).getModifierEval()) >= new Random().nextDouble();
    }
}
