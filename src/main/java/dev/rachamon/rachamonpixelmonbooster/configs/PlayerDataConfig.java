package dev.rachamon.rachamonpixelmonbooster.configs;

import dev.rachamon.rachamonpixelmonbooster.stuctures.BoosterType;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ConfigSerializable
public class PlayerDataConfig {
    @Setting(value = "players", comment = "Player Data")
    private final Map<UUID, PlayerData> players = new HashMap<>();

    public Map<UUID, PlayerData> getPlayers() {
        return this.players;
    }

    @ConfigSerializable
    public static class PlayerData {
        @Setting(value = "boosters", comment = "the booster data.")
        protected Map<BoosterType, PlayerBoostData> booster = new HashMap<>();
        public Map<BoosterType, PlayerBoostData> getBooster() {
            return booster;
        }
    }

    @ConfigSerializable
    public static class PlayerBoostData {
        @Setting(value = "time-left", comment = "The duration of the boost.")
        protected int timeLeft = 0;
        @Setting(value = "amount", comment = "The boosts left in the inventory.")
        protected int amount = 0;
        @Setting(value = "is-activate", comment = "is the player activating the boost.")
        protected boolean isActivate = false;

        public PlayerBoostData(int timeLeft, int amount, boolean isActivate) {
            this.timeLeft = timeLeft;
            this.amount = amount;
            this.isActivate = isActivate;
        }

        public int getTimeLeft() {
            return timeLeft;
        }

        public boolean isActivate() {
            return isActivate;
        }

        public int getAmount() {
            return amount;
        }

        public void setTimeLeft(int timeLeft) {
            this.timeLeft = timeLeft;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public void setActivate(boolean activate) {
            isActivate = activate;
        }
    }

}
