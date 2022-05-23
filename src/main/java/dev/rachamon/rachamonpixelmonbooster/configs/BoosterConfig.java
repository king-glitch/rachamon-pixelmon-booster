package dev.rachamon.rachamonpixelmonbooster.configs;

import dev.rachamon.rachamonpixelmonbooster.stuctures.BoosterType;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ConfigSerializable
public class BoosterConfig {
    @Setting(value = "boosters", comment = "Boosters")
    private final Map<String, Booster> boosters = this.initialize();

    public Map<String, Booster> initialize() {
        return new HashMap<String, Booster>() {{
            put(BoosterType.BATTLE_WINNING.toString(), new EvalModifierBooster("{current}*2", 600));
            put(BoosterType.CAPTURE.toString(), new EvalModifierBooster("{current}+5", 600));
            put(BoosterType.DROP.toString(), new EvalModifierBooster("{current}+3", 600));
            put(BoosterType.EXP.toString(), new EvalModifierBooster("{current}*1.5", 600));
            put(BoosterType.EV.toString(), new EvalModifierBooster("{current}*2", 600));
            put(BoosterType.HATCH.toString(), new EvalModifierBooster("{current}-50", 600));
            put(BoosterType.SHINY_RATE.toString(), new ChanceBooster(0.000244140625, 600));
            put(BoosterType.HIDDEN_ABILITY.toString(), new ChanceBooster(0.000244140625, 600));
        }};
    }

    public Map<String, Booster> getBoosters() {
        return boosters;
    }

    @ConfigSerializable
    public static class Booster {
        @Setting(value = "duration", comment = "The duration of the booster.")
        protected int duration;

        public Booster() {
        }

        public Booster(int duration) {
            this.duration = duration;
        }


        public int getDuration() {
            return duration;
        }
    }

    @ConfigSerializable
    public static class EvalModifierBooster extends Booster {
        @Setting(value = "modifier-eval", comment = "Modifier Evan as a javascript engine.")
        protected String modifierEval;

        public EvalModifierBooster() {
        }

        public EvalModifierBooster(String modifierEval, int duration) {
            super(duration);
            this.modifierEval = modifierEval;
        }

        public String getModifierEval() {
            return modifierEval;
        }
    }

    @ConfigSerializable
    public static class ChanceBooster extends Booster {
        @Setting(value = "chance", comment = "chance [default: 0.20]")
        protected double chance = 0.20;

        public ChanceBooster() {
        }

        public ChanceBooster(double chance, int duration) {
            super(duration);
            this.chance = chance;
        }

        public double getChance() {
            return chance;
        }
    }

    @ConfigSerializable
    public static class PokemonBooster extends ChanceBooster {
        @Setting(value = "blacklist", comment = "blacklist boost pokemon.")
        protected List<String> blacklist = new ArrayList<>();

        @Setting(value = "allow-legendary", comment = "allow boost on legendary pokemon.")
        protected boolean allowLegendary = false;

        public PokemonBooster() {
        }

        public PokemonBooster(double chance, int duration, List<String> blacklist) {
            super(chance, duration);
            this.blacklist = blacklist;
        }

        public List<String> getBlacklist() {
            return blacklist;
        }

        public boolean getAllowLegendary() {
            return allowLegendary;
        }
    }
}
