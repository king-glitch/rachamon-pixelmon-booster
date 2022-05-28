package dev.rachamon.rachamonpixelmonbooster.stuctures.boosters;

import dev.rachamon.rachamonpixelmonbooster.configs.BoosterConfig;
import dev.rachamon.rachamonpixelmonbooster.stuctures.Booster;
import dev.rachamon.rachamonpixelmonbooster.stuctures.BoosterType;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

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
