package dev.rachamon.rachamonpixelmonbooster.configs;

import dev.rachamon.rachamonpixelmonbooster.stuctures.BoosterType;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * The type Player data config.
 */
@ConfigSerializable
public class PlayerDataConfig {
    @Setting(value = "players", comment = "Player Data")
    private final Map<UUID, PlayerData> players = new HashMap<>();

    /**
     * Gets players.
     *
     * @return the players
     */
    public Map<UUID, PlayerData> getPlayers() {
        return this.players;
    }

    /**
     * The type Player data.
     */
    @ConfigSerializable
    public static class PlayerData {
        /**
         * The Booster.
         */
        @Setting(value = "boosters", comment = "the booster data.")
        protected Map<BoosterType, PlayerBoostData> booster = new HashMap<>();

        /**
         * Gets booster.
         *
         * @return the booster
         */
        public Map<BoosterType, PlayerBoostData> getBooster() {
            return booster;
        }
    }

    /**
     * The type Player boost data.
     */
    @ConfigSerializable
    public static class PlayerBoostData {
        /**
         * The Time left.
         */
        @Setting(value = "time-left", comment = "The duration of the boost.")
        protected int timeLeft = 0;
        /**
         * The Amount.
         */
        @Setting(value = "amount", comment = "The boosts left in the inventory.")
        protected int amount = 0;
        /**
         * The Is activate.
         */
        @Setting(value = "is-activate", comment = "is the player activating the boost.")
        protected boolean isActivate = false;

        /**
         * Instantiates a new Player boost data.
         *
         * @param timeLeft   the time left
         * @param amount     the amount
         * @param isActivate the is activate
         */
        public PlayerBoostData(int timeLeft, int amount, boolean isActivate) {
            this.timeLeft = timeLeft;
            this.amount = amount;
            this.isActivate = isActivate;
        }

        /**
         * Gets time left.
         *
         * @return the time left
         */
        public int getTimeLeft() {
            return timeLeft;
        }

        /**
         * Is activate boolean.
         *
         * @return the boolean
         */
        public boolean isActivate() {
            return isActivate;
        }

        /**
         * Gets amount.
         *
         * @return the amount
         */
        public int getAmount() {
            return amount;
        }

        /**
         * Sets time left.
         *
         * @param timeLeft the time left
         */
        public void setTimeLeft(int timeLeft) {
            this.timeLeft = timeLeft;
        }

        /**
         * Sets amount.
         *
         * @param amount the amount
         */
        public void setAmount(int amount) {
            this.amount = amount;
        }

        /**
         * Sets activate.
         *
         * @param activate the activate
         */
        public void setActivate(boolean activate) {
            isActivate = activate;
        }
    }

}
