package dev.rachamon.rachamonpixelmonbooster.stuctures;

import java.util.UUID;

/**
 * The type Player time.
 */
public class PlayerTime {
    private final UUID uuid;
    private int time;

    /**
     * Instantiates a new Player time.
     *
     * @param uuid the uuid
     * @param time the time
     */
    public PlayerTime(UUID uuid, int time) {
        this.uuid = uuid;
        this.time = time;
    }

    /**
     * Gets uuid.
     *
     * @return the uuid
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * Gets time.
     *
     * @return the time
     */
    public int getTime() {
        return time;
    }

    /**
     * Sets time.
     *
     * @param time the time
     */
    public void setTime(int time) {
        this.time = time;
    }
}
