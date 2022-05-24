package dev.rachamon.rachamonpixelmonbooster.configs;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class MainConfig {
    @Setting(value = "general", comment = "General Settings")
    private final GeneralConfig generalConfig = new GeneralConfig();

    public GeneralConfig getGeneralConfig() {
        return generalConfig;
    }

    @ConfigSerializable
    public static class GeneralConfig {

        @Setting(value = "is-debug", comment = "is debug mode [default: false]")
        protected boolean isDebug = false;

        @Setting(value = "task-interval", comment = "how often should task run on. [default: 10]")
        protected int taskInterval = 10;

        @Setting(value = "spawn-radius", comment = "Pokemon Boost spawn radius. [default: 10]")
        protected int spawnRadius = 10;

        public int getTaskInterval() {
            return taskInterval;
        }

        public int getSpawnRadius() {
            return spawnRadius;
        }

        public boolean isDebug() {
            return isDebug;
        }
    }
}
