package dev.rachamon.rachamonpixelmonbooster.stuctures.boosters;

import dev.rachamon.rachamonpixelmonbooster.configs.BoosterConfig;
import dev.rachamon.rachamonpixelmonbooster.stuctures.Booster;
import dev.rachamon.rachamonpixelmonbooster.stuctures.BoosterType;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * The type Pokemon drop booster.
 */
public class PokemonDropBooster extends Booster {
    /**
     * Instantiates a new Pokemon drop booster.
     */
    public PokemonDropBooster() {
        super(BoosterType.DROP);
    }

    /**
     * Calculate int.
     *
     * @param current the current
     * @return the int
     * @throws ScriptException the script exception
     */
    public int calculate(int current) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        Object result = engine.eval(((BoosterConfig.EvalModifierBooster) this.getConfig())
                .getModifierEval()
                .replaceAll("\\{current}", String.valueOf(current)));
        return Integer.parseInt(result.toString());
    }
}
