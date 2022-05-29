package dev.rachamon.rachamonpixelmonbooster.utils;

import dev.rachamon.api.sponge.util.TextUtil;
import dev.rachamon.rachamonpixelmonbooster.RachamonPixelmonBooster;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.entity.living.player.Player;

/**
 * The type Rachamon pixelmon booster util.
 */
public class ChatUtil {
    /**
     * Send message.
     *
     * @param player  the player
     * @param message the message
     */
    public static void sendMessage(Player player, String message) {
        player.sendMessage(TextUtil.toText(RachamonPixelmonBooster
                .getInstance()
                .getLanguage()
                .getGeneralLanguage()
                .getPrefix() + message));
    }

    /**
     * Send message.
     *
     * @param source  the source
     * @param message the message
     */
    public static void sendMessage(CommandSource source, String message) {
        source.sendMessage(TextUtil.toText(RachamonPixelmonBooster
                .getInstance()
                .getLanguage()
                .getGeneralLanguage()
                .getPrefix() + message));
    }
}
