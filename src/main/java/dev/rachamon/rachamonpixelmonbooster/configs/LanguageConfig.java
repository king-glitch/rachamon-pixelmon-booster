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
         * The Booster ended.
         */
        @Setting(value = "booster-ended", comment = "message when player booster has ended.")
        protected String boosterEnded = "&7Your &c&l{booster}&7 booster has ended.";

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
    }
}
