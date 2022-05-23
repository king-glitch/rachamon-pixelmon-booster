package dev.rachamon.rachamonpixelmonbooster.configs;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class LanguageConfig {
    @Setting(value = "general", comment = "General Messages")
    private final GeneralLanguage generalLanguage = new GeneralLanguage();

    public GeneralLanguage getGeneralLanguage() {
        return generalLanguage;
    }

    /**
     * The type General language battle.
     */
    @ConfigSerializable
    public static class GeneralLanguage {

        @Setting(value = "prefix", comment = "Prefix for chat message")
        protected String prefix = "&8[&c&lPixelmonBooster&8]&7 ";

        @Setting(value = "booster-ended", comment = "message when player booster has ended.")
        protected String boosterEnded = "&7Your &c&l{booster}&7 booster has ended.";

        public String getPrefix() {
            return prefix;
        }

        public String getBoosterEnded() {
            return boosterEnded;
        }
    }
}
