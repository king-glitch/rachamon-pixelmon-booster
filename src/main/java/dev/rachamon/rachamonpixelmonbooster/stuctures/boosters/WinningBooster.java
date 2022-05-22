package dev.rachamon.rachamonpixelmonbooster.stuctures.boosters;

import dev.rachamon.rachamonpixelmonbooster.stuctures.Booster;
import dev.rachamon.rachamonpixelmonbooster.stuctures.BoosterType;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class WinningBooster extends Booster {
    public WinningBooster() {
        super(BoosterType.BATTLE_WINNING);
    }

    public void initialize() {
        this.setInterval(1000);
    }

    public int calculate(int amount) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        Object result = engine.eval("4*5");
        return Integer.parseInt(result.toString());
    }
}
