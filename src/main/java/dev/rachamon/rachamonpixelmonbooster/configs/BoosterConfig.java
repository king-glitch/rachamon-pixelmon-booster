package dev.rachamon.rachamonpixelmonbooster.configs;

import dev.rachamon.rachamonpixelmonbooster.stuctures.BoosterType;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Booster config.
 */
@ConfigSerializable
public class BoosterConfig {
    @Setting(value = "boosters", comment = "Boosters")
    private final Map<String, Booster> boosters = this.initialize();

    /**
     * Initialize map.
     *
     * @return the map
     */
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

    /**
     * Gets boosters.
     *
     * @return the boosters
     */
    public Map<String, Booster> getBoosters() {
        return boosters;
    }

    /**
     * The type Booster.
     */
    @ConfigSerializable
    public static class Booster {
        /**
         * The Duration.
         */
        @Setting(value = "duration", comment = "The duration of the booster.")
        protected int duration;

        /**
         * Instantiates a new Booster.
         */
        public Booster() {
        }

        /**
         * Instantiates a new Booster.
         *
         * @param duration the duration
         */
        public Booster(int duration) {
            this.duration = duration;
        }


        /**
         * Gets duration.
         *
         * @return the duration
         */
        public int getDuration() {
            return duration;
        }
    }

    /**
     * The type Eval modifier booster.
     */
    @ConfigSerializable
    public static class EvalModifierBooster extends Booster {
        /**
         * The Modifier eval.
         */
        @Setting(value = "modifier-eval", comment = "Modifier Evan as a javascript engine.")
        protected String modifierEval;

        /**
         * Instantiates a new Eval modifier booster.
         */
        public EvalModifierBooster() {
        }

        /**
         * Instantiates a new Eval modifier booster.
         *
         * @param modifierEval the modifier eval
         * @param duration     the duration
         */
        public EvalModifierBooster(String modifierEval, int duration) {
            super(duration);
            this.modifierEval = modifierEval;
        }

        /**
         * Gets modifier eval.
         *
         * @return the modifier eval
         */
        public String getModifierEval() {
            return modifierEval;
        }
    }

    /**
     * The type Chance booster.
     */
    @ConfigSerializable
    public static class ChanceBooster extends Booster {
        /**
         * The Chance.
         */
        @Setting(value = "chance", comment = "chance [default: 0.20]")
        protected double chance = 0.20;

        /**
         * Instantiates a new Chance booster.
         */
        public ChanceBooster() {
        }

        /**
         * Instantiates a new Chance booster.
         *
         * @param chance   the chance
         * @param duration the duration
         */
        public ChanceBooster(double chance, int duration) {
            super(duration);
            this.chance = chance;
        }

        /**
         * Gets chance.
         *
         * @return the chance
         */
        public double getChance() {
            return chance;
        }
    }

    /**
     * The type Pokemon booster.
     */
    @ConfigSerializable
    public static class PokemonBooster extends ChanceBooster {
        /**
         * The Blacklist.
         */
        @Setting(value = "blacklist", comment = "blacklist boost pokemon.")
        protected List<String> blacklist = new ArrayList<>();

        /**
         * The Allow legendary.
         */
        @Setting(value = "allow-legendary", comment = "allow boost on legendary pokemon.")
        protected boolean allowLegendary = false;

        /**
         * Instantiates a new Pokemon booster.
         */
        public PokemonBooster() {
        }

        /**
         * Instantiates a new Pokemon booster.
         *
         * @param chance    the chance
         * @param duration  the duration
         * @param blacklist the blacklist
         */
        public PokemonBooster(double chance, int duration, List<String> blacklist) {
            super(chance, duration);
            this.blacklist = blacklist;
        }

        /**
         * Gets blacklist.
         *
         * @return the blacklist
         */
        public List<String> getBlacklist() {
            return blacklist;
        }

        /**
         * Gets allow legendary.
         *
         * @return the allow legendary
         */
        public boolean getAllowLegendary() {
            return allowLegendary;
        }
    }
}
