package dev.rachamon.rachamonpixelmonbooster.stuctures;

/**
 * The enum Booster type.
 */
public enum BoosterType {
    /**
     * Ev booster type.
     */
    EV("ev"),
    /**
     * Exp booster type.
     */
    EXP("exp"),
    /**
     * Boss booster type.
     */
    BOSS("boss"),
    /**
     * Drop booster type.
     */
    DROP("drop"),
    /**
     * Hatch booster type.
     */
    HATCH("hatch"),
    /**
     * Capture booster type.
     */
    CAPTURE("capture"),
    /**
     * Shiny rate booster type.
     */
    SHINY_RATE("shiny"),
    /**
     * Pokemon spawn booster type.
     */
    POKEMON_SPAWN("pokemon"),
    /**
     * Hidden ability booster type.
     */
    HIDDEN_ABILITY("ha"),
    /**
     * Battle winning booster type.
     */
    BATTLE_WINNING("winning");
    private final String name;

    BoosterType(String name) {
        this.name = name;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * From string booster type.
     *
     * @param text the text
     * @return the booster type
     */
    public static BoosterType fromString(String text) {
        for (BoosterType b : BoosterType.values()) {
            if (b.getName().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }


}
