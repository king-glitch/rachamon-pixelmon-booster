package dev.rachamon.rachamonpixelmonbooster.stuctures.boosters;

import dev.rachamon.rachamonpixelmonbooster.stuctures.Booster;
import dev.rachamon.rachamonpixelmonbooster.stuctures.BoosterType;

import java.util.Random;

/**
 * The type Pokemon boss booster.
 */
public class PokemonBossBooster extends Booster {

    /**
     * Instantiates a new Pokemon boss booster.
     */
    public PokemonBossBooster() {
        super(BoosterType.BOSS);
    }

    /**
     * Is chance boolean.
     *
     * @return the boolean
     */
    public boolean isChance() {
        return (this.getConfig()).getChance() >= new Random().nextDouble();
    }

}
