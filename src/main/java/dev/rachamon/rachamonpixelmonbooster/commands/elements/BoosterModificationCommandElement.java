package dev.rachamon.rachamonpixelmonbooster.commands.elements;

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
import java.util.stream.Collectors;

/**
 * The type Booster modification command element.
 */
public class BoosterModificationCommandElement extends CommandElement {
    /**
     * Instantiates a new Booster modification command element.
     *
     * @param key the key
     */
    public BoosterModificationCommandElement(@Nullable Text key) {
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
        List<String> keys = new ArrayList<String>() {{
            add("add");
            add("remove");
            add("set");
            add("time");
        }};

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
