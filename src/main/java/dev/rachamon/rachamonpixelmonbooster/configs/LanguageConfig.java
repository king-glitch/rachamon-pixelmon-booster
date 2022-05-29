package dev.rachamon.rachamonpixelmonbooster.configs;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

/**
 * The type Language config.
 */
@ConfigSerializable
public class LanguageConfig {
    @Setting(value = "general", comment = "General Messages")
    private final GeneralLanguage generalLanguage = new GeneralLanguage();

    /**
     * Gets general language.
     *
     * @return the general language
     */
    public GeneralLanguage getGeneralLanguage() {
        return generalLanguage;
    }

    /**
     * The type General language battle.
     */
    @ConfigSerializable
    public static class GeneralLanguage {

        /**
         * The Prefix.
         */
        @Setting(value = "prefix", comment = "Prefix for chat message")
        protected String prefix = "&8[&c&lPixelmonBooster&8]&7 ";

        /**
         * The Booster used.
         */
        @Setting(value = "booster-used", comment = "message when player used the boost.")
        protected String boosterUsed = "&7You've used &a&l{booster}&7 booster. booster &a&l{amount}&7 left. time: &a&l{time-left}&7 left";

        /**
         * The Booster empty.
         */
        @Setting(value = "booster-empty", comment = "message when player has no boost.")
        protected String boosterEmpty = "&7You've no &a&l{booster}&7 booster left.";

        /**
         * The Booster resumed.
         */
        @Setting(value = "booster-resumed", comment = "message when player resumed the boost.")
        protected String boosterResumed = "&7You've resumed &a&l{booster}&7 booster. &a&l{time-left}&7 left.";

        /**
         * The Booster already activated.
         */
        @Setting(value = "booster-already-activated", comment = "message when player already activated the boost.")
        protected String boosterAlreadyActivated = "&7You've already activated &a&l{booster}&7 booster. &a&l{time-left}&7 left.";

        /**
         * The Booster resumed ended.
         */
        @Setting(value = "booster-resumed-ended", comment = "message when player has no time left in boost.")
        protected String boosterResumedEnded = "&7You've no time left in &a&l{booster}&7 booster.";

        /**
         * The Booster paused.
         */
        @Setting(value = "booster-paused", comment = "message when player paused the boost.")
        protected String boosterPaused = "&7You've paused &a&l{booster}&7 booster.";

        /**
         * The Booster not activated.
         */
        @Setting(value = "booster-not-activated", comment = "message when player paused the boost.")
        protected String boosterNotActivated = "&7The boost &c&l{booster}&7 isn't activated.";

        /**
         * The Booster modification add.
         */
        @Setting(value = "booster-modification-add", comment = "message when player paused the boost.")
        protected String boosterModificationAdd = "&7You've added &a&l{player}&7. &a&l{booster}&7 booster";

        /**
         * The Booster modification remove.
         */
        @Setting(value = "booster-modification-remove", comment = "message when player paused the boost.")
        protected String boosterModificationRemove = "&7You've removed &a&l{player}&7. &a&l{booster}&7 booster";

        /**
         * The Booster modification set.
         */
        @Setting(value = "booster-modification-set", comment = "message when player paused the boost.")
        protected String boosterModificationSet = "&7You've set &a&l{player}&7. &a&l{booster}&7 booster";

        /**
         * The Booster modification time.
         */
        @Setting(value = "booster-modification-time", comment = "message when player paused the boost.")
        protected String boosterModificationTime = "&7You've set time-left of &a&l{player}&7 at &a&l{booster}&7 booster";

        /**
         * The Player booster modification add.
         */
        @Setting(value = "player-booster-modification-add", comment = "message when player paused the boost.")
        protected String playerBoosterModificationAdd = "&7You've got &a&l{amount}&7x &a&l{booster}&7 booster";

        /**
         * The Player booster modification remove.
         */
        @Setting(value = "player-booster-modification-remove", comment = "message when player paused the boost.")
        protected String playerBoosterModificationRemove = "&7Your &a&l{booster}&7 booster been removed &a&l{amount}&7x.";

        /**
         * The Player booster modification set.
         */
        @Setting(value = "player-booster-modification-set", comment = "message when player paused the boost.")
        protected String playerBoosterModificationSet = "&7Your &a&l{booster}&7 booster time has been set to &a&l{amount}&7.";

        /**
         * The Player booster modification time.
         */
        @Setting(value = "player-booster-modification-time", comment = "message when player paused the boost.")
        protected String playerBoosterModificationTime = "&7You've set time-left of &a&l{player}&7 at &a&l{booster}&7 booster";

        /**
         * The Booster ended.
         */
        @Setting(value = "booster-ended", comment = "message when player booster has ended.")
        protected String boosterEnded = "&7Your &c&l{booster}&7 booster has ended.";

        /**
         * The Global booster activate.
         */
        @Setting(value = "global-booster-activate", comment = "message when player activate global booster.")
        protected String globalBoosterActivate = "&7You have global activated &a&l{booster}&7.";

        /**
         * The Global booster deactivate.
         */
        @Setting(value = "global-booster-deactivate", comment = "message when player deactivate global booster.")
        protected String globalBoosterDeactivate = "&7You have global deactivated &c&l{booster}&7.";

        /**
         * The Global booster activate announcement.
         */
        @Setting(value = "global-booster-activate-announcement", comment = "message when global booster has activated.")
        protected String globalBoosterActivateAnnouncement = "&7The boost &a&l{booster}&7 booster has started globally.";

        /**
         * The Global booster deactivate announcement.
         */
        @Setting(value = "global-booster-deactivate-announcement", comment = "message when global booster has deactivated.")
        protected String globalBoosterDeactivateAnnouncement = "&7The boost &a&l{booster}&7 booster has stop globally.";

        /**
         * Gets prefix.
         *
         * @return the prefix
         */
        public String getPrefix() {
            return prefix;
        }

        /**
         * Gets booster ended.
         *
         * @return the booster ended
         */
        public String getBoosterEnded() {
            return boosterEnded;
        }

        /**
         * Gets booster used.
         *
         * @return the booster used
         */
        public String getBoosterUsed() {
            return boosterUsed;
        }

        /**
         * Gets booster resumed.
         *
         * @return the booster resumed
         */
        public String getBoosterResumed() {
            return boosterResumed;
        }

        /**
         * Gets booster paused.
         *
         * @return the booster paused
         */
        public String getBoosterPaused() {
            return boosterPaused;
        }

        /**
         * Gets booster modification add.
         *
         * @return the booster modification add
         */
        public String getBoosterModificationAdd() {
            return boosterModificationAdd;
        }

        /**
         * Gets booster empty.
         *
         * @return the booster empty
         */
        public String getBoosterEmpty() {
            return boosterEmpty;
        }

        /**
         * Gets booster resumed ended.
         *
         * @return the booster resumed ended
         */
        public String getBoosterResumedEnded() {
            return boosterResumedEnded;
        }

        /**
         * Gets booster not activated.
         *
         * @return the booster not activated
         */
        public String getBoosterNotActivated() {
            return boosterNotActivated;
        }

        /**
         * Gets booster modification remove.
         *
         * @return the booster modification remove
         */
        public String getBoosterModificationRemove() {
            return boosterModificationRemove;
        }

        /**
         * Gets booster modification set.
         *
         * @return the booster modification set
         */
        public String getBoosterModificationSet() {
            return boosterModificationSet;
        }

        /**
         * Gets booster modification time.
         *
         * @return the booster modification time
         */
        public String getBoosterModificationTime() {
            return boosterModificationTime;
        }

        /**
         * Gets booster already activated.
         *
         * @return the booster already activated
         */
        public String getBoosterAlreadyActivated() {
            return boosterAlreadyActivated;
        }

        /**
         * Gets global booster activate.
         *
         * @return the global booster activate
         */
        public String getGlobalBoosterActivate() {
            return globalBoosterActivate;
        }

        /**
         * Gets global booster deactivate.
         *
         * @return the global booster deactivate
         */
        public String getGlobalBoosterDeactivate() {
            return globalBoosterDeactivate;
        }

        /**
         * Gets global booster activate announcement.
         *
         * @return the global booster activate announcement
         */
        public String getGlobalBoosterActivateAnnouncement() {
            return globalBoosterActivateAnnouncement;
        }

        /**
         * Gets global booster deactivate announcement.
         *
         * @return the global booster deactivate announcement
         */
        public String getGlobalBoosterDeactivateAnnouncement() {
            return globalBoosterDeactivateAnnouncement;
        }

        /**
         * Gets player booster modification add.
         *
         * @return the player booster modification add
         */
        public String getPlayerBoosterModificationAdd() {
            return playerBoosterModificationAdd;
        }

        /**
         * Gets player booster modification remove.
         *
         * @return the player booster modification remove
         */
        public String getPlayerBoosterModificationRemove() {
            return playerBoosterModificationRemove;
        }

        /**
         * Gets player booster modification set.
         *
         * @return the player booster modification set
         */
        public String getPlayerBoosterModificationSet() {
            return playerBoosterModificationSet;
        }

        /**
         * Gets player booster modification time.
         *
         * @return the player booster modification time
         */
        public String getPlayerBoosterModificationTime() {
            return playerBoosterModificationTime;
        }
    }
}
