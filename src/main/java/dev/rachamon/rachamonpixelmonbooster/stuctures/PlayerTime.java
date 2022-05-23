package dev.rachamon.rachamonpixelmonbooster.stuctures;

import java.util.UUID;

public class PlayerTime {
    private final UUID uuid;
    private int time;

    public PlayerTime(UUID uuid, int time) {
        this.uuid = uuid;
        this.time = time;
    }

    public UUID getUuid() {
        return uuid;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
