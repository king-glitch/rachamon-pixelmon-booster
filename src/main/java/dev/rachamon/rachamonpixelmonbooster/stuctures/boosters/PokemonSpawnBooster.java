package dev.rachamon.rachamonpixelmonbooster.stuctures.boosters;

import dev.rachamon.rachamonpixelmonbooster.configs.BoosterConfig;
import dev.rachamon.rachamonpixelmonbooster.stuctures.Booster;
import dev.rachamon.rachamonpixelmonbooster.stuctures.BoosterType;

import java.util.List;
import java.util.Random;

public class PokemonSpawnBooster extends Booster {
    public PokemonSpawnBooster() {
        super(BoosterType.POKEMON_SPAWN);
    }

    public boolean isChance() {
        return ((BoosterConfig.PokemonBooster) this.getConfig()).getChance() >= new Random().nextDouble();
    }

    public List<String> getBlacklist() {
        return ((BoosterConfig.PokemonBooster) this.getConfig()).getBlacklist();
    }

    public boolean isAllowLegendary() {
        return ((BoosterConfig.PokemonBooster) this.getConfig()).getAllowLegendary();
    }
}
