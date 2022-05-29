package dev.rachamon.rachamonpixelmonbooster.stuctures.boosters;

import dev.rachamon.rachamonpixelmonbooster.stuctures.Booster;
import dev.rachamon.rachamonpixelmonbooster.stuctures.BoosterType;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * The type Trainer money booster.
 */
public class TrainerMoneyBooster extends Booster {

    /**
     * Instantiates a new Trainer money booster.
     */
    public TrainerMoneyBooster() {
        super(BoosterType.BATTLE_WINNING);
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
        Object result = engine.eval((this.getConfig())
                .getModifierEval()
                .replaceAll("\\{current}", String.valueOf(current)));
        return Integer.parseInt(result.toString());
    }


}
