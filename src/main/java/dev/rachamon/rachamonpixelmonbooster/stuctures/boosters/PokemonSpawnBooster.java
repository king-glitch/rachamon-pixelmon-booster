package dev.rachamon.rachamonpixelmonbooster.stuctures.boosters;

import dev.rachamon.rachamonpixelmonbooster.configs.BoosterConfig;
import dev.rachamon.rachamonpixelmonbooster.stuctures.Booster;
import dev.rachamon.rachamonpixelmonbooster.stuctures.BoosterType;

import java.util.List;
import java.util.Random;

/**
 * The type Pokemon spawn booster.
 */
public class PokemonSpawnBooster extends Booster {
    /**
     * Instantiates a new Pokemon spawn booster.
     */
    public PokemonSpawnBooster() {
        super(BoosterType.POKEMON_SPAWN);
    }

    /**
     * Is chance boolean.
     *
     * @return the boolean
     */
    public boolean isChance() {
        return ((BoosterConfig.PokemonBooster) this.getConfig()).getChance() >= new Random().nextDouble();
    }

    /**
     * Gets blacklist.
     *
     * @return the blacklist
     */
    public List<String> getBlacklist() {
        return ((BoosterConfig.PokemonBooster) this.getConfig()).getBlacklist();
    }

    /**
     * Is allow legendary boolean.
     *
     * @return the boolean
     */
    public boolean isAllowLegendary() {
        return ((BoosterConfig.PokemonBooster) this.getConfig()).getAllowLegendary();
    }
}
