package dev.rachamon.rachamonpixelmonbooster.configs;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

/**
 * The type Main config.
 */
@ConfigSerializable
public class MainConfig {
    @Setting(value = "general", comment = "General Settings")
    private final GeneralConfig generalConfig = new GeneralConfig();

    /**
     * Gets general config.
     *
     * @return the general config
     */
    public GeneralConfig getGeneralConfig() {
        return generalConfig;
    }

    /**
     * The type General config.
     */
    @ConfigSerializable
    public static class GeneralConfig {

        /**
         * The Is debug.
         */
        @Setting(value = "is-debug", comment = "is debug mode [default: false]")
        protected boolean isDebug = false;

        /**
         * The Task interval.
         */
        @Setting(value = "task-interval", comment = "how often should task run on. [default: 10]")
        protected int taskInterval = 10;

        /**
         * The Spawn radius.
         */
        @Setting(value = "spawn-radius", comment = "Pokemon Boost spawn radius. [default: 10]")
        protected int spawnRadius = 10;

        /**
         * Gets task interval.
         *
         * @return the task interval
         */
        public int getTaskInterval() {
            return taskInterval;
        }

        /**
         * Gets spawn radius.
         *
         * @return the spawn radius
         */
        public int getSpawnRadius() {
            return spawnRadius;
        }

        /**
         * Is debug boolean.
         *
         * @return the boolean
         */
        public boolean isDebug() {
            return isDebug;
        }
    }
}
