package dev.rachamon.rachamonpixelmonbooster.stuctures.boosters;

import dev.rachamon.rachamonpixelmonbooster.configs.BoosterConfig;
import dev.rachamon.rachamonpixelmonbooster.stuctures.Booster;
import dev.rachamon.rachamonpixelmonbooster.stuctures.BoosterType;

import java.util.Random;

public class PokemonHABooster extends Booster {
    public PokemonHABooster() {
        super(BoosterType.HIDDEN_ABILITY);
    }

    public boolean isChance() {
        return Double.parseDouble(((BoosterConfig.EvalModifierBooster) this.getConfig()).getModifierEval()) >= new Random().nextDouble();
    }
}
