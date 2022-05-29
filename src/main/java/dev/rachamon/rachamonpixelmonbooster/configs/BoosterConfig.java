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
            put(BoosterType.BATTLE_WINNING.toString(), new Booster(600, "{current}*2", 0, new ArrayList<>(), false));
            put(BoosterType.CAPTURE.toString(), new Booster(600, "{current}+5", 0, new ArrayList<>(), false));
            put(BoosterType.DROP.toString(), new Booster(600, "{current}+3", 0, new ArrayList<>(), false));
            put(BoosterType.EXP.toString(), new Booster(600, "{current}*1.5", 0, new ArrayList<>(), false));
            put(BoosterType.EV.toString(), new Booster(600, "{current}*2", 0, new ArrayList<>(), false));
            put(BoosterType.HATCH.toString(), new Booster(600, "{current}-50", 0, new ArrayList<>(), false));
            put(BoosterType.SHINY_RATE.toString(), new Booster(600, "", 0.000244F, new ArrayList<>(), false));
            put(BoosterType.HIDDEN_ABILITY.toString(), new Booster(600, "", 0.000244F, new ArrayList<>(), false));
            put(BoosterType.POKEMON_SPAWN.toString(), new Booster(600, "", 0.10F, new ArrayList<>(), false));
            put(BoosterType.BOSS.toString(), new Booster(600, "", 0.10F, new ArrayList<>(), false));
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
        protected int duration = 600;
        /**
         * The Modifier eval.
         */
        @Setting(value = "modifier-eval", comment = "Modifier Evan as a javascript engine.")
        protected String modifierEval = "";
        /**
         * The Chance.
         */
        @Setting(value = "chance", comment = "chance [default: 0.20]")
        protected double chance;
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
         * Instantiates a new Booster.
         */
        public Booster() {
        }

        /**
         * Instantiates a new Booster.
         *
         * @param duration       the duration
         * @param modifierEval   the modifier eval
         * @param chance         the chance
         * @param blacklist      the blacklist
         * @param allowLegendary the allow legendary
         */
        public Booster(int duration, String modifierEval, double
                chance, List<String> blacklist, boolean allowLegendary) {
            this.duration = duration;
            this.modifierEval = modifierEval;
            this.chance = chance;
            this.blacklist = blacklist;
            this.allowLegendary = allowLegendary;
        }


        /**
         * Gets duration.
         *
         * @return the duration
         */
        public int getDuration() {
            return duration;
        }

        /**
         * Gets modifier eval.
         *
         * @return the modifier eval
         */
        public String getModifierEval() {
            return modifierEval;
        }

        /**
         * Gets chance.
         *
         * @return the chance
         */
        public double getChance() {
            return chance;
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
         * Is allow legendary boolean.
         *
         * @return the boolean
         */
        public boolean isAllowLegendary() {
            return allowLegendary;
        }
    }
}
