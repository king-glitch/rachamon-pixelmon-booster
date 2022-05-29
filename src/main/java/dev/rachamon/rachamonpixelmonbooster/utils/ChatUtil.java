package dev.rachamon.rachamonpixelmonbooster.utils;

import dev.rachamon.api.sponge.util.TextUtil;
import dev.rachamon.rachamonpixelmonbooster.RachamonPixelmonBooster;
import org.spongepowered.api.entity.living.player.Player;

/**
 * The type Rachamon pixelmon booster util.
 */
public class ChatUtil {
    public static void sendMessage(Player player, String message) {
        player.sendMessage(TextUtil.toText(RachamonPixelmonBooster
                .getInstance()
                .getLanguage()
                .getGeneralLanguage()
                .getPrefix() + message));
    }
}
