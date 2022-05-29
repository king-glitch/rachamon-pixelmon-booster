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

        @Setting(value = "booster-used", comment = "message when player used the boost.")
        protected String boosterUsed = "&7You've used &a&l{booster}&7 booster. booster &a&l{amount}&7 left. time: &a&l{time-left}&7 left";

        @Setting(value = "booster-empty", comment = "message when player has no boost.")
        protected String boosterEmpty = "&7You've no &a&l{booster}&7 booster left.";

        @Setting(value = "booster-resumed", comment = "message when player resumed the boost.")
        protected String boosterResumed = "&7You've resumed &a&l{booster}&7 booster. &a&l{time-left}&7 left.";

        @Setting(value = "booster-already-activated", comment = "message when player already activated the boost.")
        protected String boosterAlreadyActivated = "&7You've already activated &a&l{booster}&7 booster. &a&l{time-left}&7 left.";

        @Setting(value = "booster-resumed-ended", comment = "message when player has no time left in boost.")
        protected String boosterResumedEnded = "&7You've no time left in &a&l{booster}&7 booster.";

        @Setting(value = "booster-paused", comment = "message when player paused the boost.")
        protected String boosterPaused = "&7You've paused &a&l{booster}&7 booster.";

        @Setting(value = "booster-not-activated", comment = "message when player paused the boost.")
        protected String boosterNotActivated = "&7The boost &c&l{booster}&7 isn't activated.";

        @Setting(value = "booster-modification-add", comment = "message when player paused the boost.")
        protected String boosterModificationAdd = "&7You've added &a&l{player}&7. &a&l{booster}&7 booster";

        @Setting(value = "booster-modification-remove", comment = "message when player paused the boost.")
        protected String boosterModificationRemove = "&7You've removed &a&l{player}&7. &a&l{booster}&7 booster";

        @Setting(value = "booster-modification-set", comment = "message when player paused the boost.")
        protected String boosterModificationSet = "&7You've set &a&l{player}&7. &a&l{booster}&7 booster";

        @Setting(value = "booster-modification-time", comment = "message when player paused the boost.")
        protected String boosterModificationTime = "&7You've set time-left of &a&l{player}&7 at &a&l{booster}&7 booster";

        /**
         * The Booster ended.
         */
        @Setting(value = "booster-ended", comment = "message when player booster has ended.")
        protected String boosterEnded = "&7Your &c&l{booster}&7 booster has ended.";

        @Setting(value = "global-booster-activate", comment = "message when player activate global booster.")
        protected String globalBoosterActivate = "&7You have global activated &a&l{booster}&7.";

        @Setting(value = "global-booster-deactivate", comment = "message when player deactivate global booster.")
        protected String globalBoosterDeactivate = "&7You have global deactivated &c&l{booster}&7.";

        @Setting(value = "global-booster-activate-announcement", comment = "message when global booster has activated.")
        protected String globalBoosterActivateAnnouncement = "&7The boost &a&l{booster}&7 booster has started globally.";

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

        public String getBoosterUsed() {
            return boosterUsed;
        }

        public String getBoosterResumed() {
            return boosterResumed;
        }

        public String getBoosterPaused() {
            return boosterPaused;
        }

        public String getBoosterModificationAdd() {
            return boosterModificationAdd;
        }

        public String getBoosterEmpty() {
            return boosterEmpty;
        }

        public String getBoosterResumedEnded() {
            return boosterResumedEnded;
        }

        public String getBoosterNotActivated() {
            return boosterNotActivated;
        }

        public String getBoosterModificationRemove() {
            return boosterModificationRemove;
        }

        public String getBoosterModificationSet() {
            return boosterModificationSet;
        }

        public String getBoosterModificationTime() {
            return boosterModificationTime;
        }

        public String getBoosterAlreadyActivated() {
            return boosterAlreadyActivated;
        }

        public String getGlobalBoosterActivate() {
            return globalBoosterActivate;
        }

        public String getGlobalBoosterDeactivate() {
            return globalBoosterDeactivate;
        }

        public String getGlobalBoosterActivateAnnouncement() {
            return globalBoosterActivateAnnouncement;
        }

        public String getGlobalBoosterDeactivateAnnouncement() {
            return globalBoosterDeactivateAnnouncement;
        }
    }
}
