package dev.rachamon.rachamonpixelmonbooster.stuctures.boosters;

import dev.rachamon.rachamonpixelmonbooster.stuctures.Booster;
import dev.rachamon.rachamonpixelmonbooster.stuctures.BoosterType;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * The type Pokemon exp booster.
 */
public class PokemonExpBooster extends Booster {

    /**
     * Instantiates a new Pokemon exp booster.
     */
    public PokemonExpBooster() {
        super(BoosterType.EXP);
    }

    /**
     * Calculate int.
     *
     * @param current the current
     * @return the int
     * @throws ScriptException the script exception
     */
    public double calculate(int current) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        Object result = engine.eval((this.getConfig())
                .getModifierEval()
                .replaceAll("\\{current}", String.valueOf(current)));
        return Double.parseDouble(result.toString());
    }
}
