package dev.rachamon.rachamonpixelmonbooster.utils;

/**
 * The type Booster util.
 */
public class BoosterUtil {

    /**
     * Second to time string.
     *
     * @param second the second
     * @return the string
     */
    public static String secondToTime(int second) {
        int hours = second / 3600;
        int minutes = (second % 3600) / 60;
        int seconds = second % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

}
