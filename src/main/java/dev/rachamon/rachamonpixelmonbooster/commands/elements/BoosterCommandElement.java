package dev.rachamon.rachamonpixelmonbooster.commands.elements;

import dev.rachamon.rachamonpixelmonbooster.RachamonPixelmonBooster;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.ArgumentParseException;
import org.spongepowered.api.command.args.CommandArgs;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.text.Text;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The type Booster command element.
 */
public class BoosterCommandElement extends CommandElement {
    /**
     * Instantiates a new Booster command element.
     *
     * @param key the key
     */
    public BoosterCommandElement(@Nullable Text key) {
        super(key);
    }

    @Nullable
    @Override
    protected Object parseValue(@Nonnull CommandSource source, @Nonnull CommandArgs args) throws ArgumentParseException {
        return args.next();
    }

    @Nonnull
    @Override
    public List<String> complete(@Nonnull CommandSource src, @Nonnull CommandArgs args, @Nonnull CommandContext context) {
        Set<String> keys = RachamonPixelmonBooster.getInstance().getBooster().getBoosters().keySet();
        try {
            String next = args.next();
            return keys
                    .stream()
                    .filter(key -> key.toLowerCase().contains(next.toLowerCase()))
                    .collect(Collectors.toList());
        } catch (ArgumentParseException e) {
            e.printStackTrace();
        }

        return new ArrayList<>(keys);
    }
}
