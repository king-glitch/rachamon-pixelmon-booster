package dev.rachamon.rachamonpixelmonbooster.commands.subcommands;

import dev.rachamon.api.sponge.implement.command.*;
import dev.rachamon.api.sponge.util.TextUtil;
import dev.rachamon.rachamonpixelmonbooster.RachamonPixelmonBooster;
import dev.rachamon.rachamonpixelmonbooster.commands.elements.BoosterCommandElement;
import dev.rachamon.rachamonpixelmonbooster.commands.elements.BoosterModificationCommandElement;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import javax.annotation.Nonnull;
import java.util.Optional;

/**
 * The type Rachamon pixelmon booster modification.
 */
@ICommandDescription("Modification about the boost.")
@ICommandAliases({"modification", "mod", "config"})
@ICommandPermission("rachamonpixelmonbooster.command.admin.modification")
public class RachamonPixelmonBoosterModification implements ICommand, IParameterizedCommand {
    @Nonnull
    @Override
    public CommandResult execute(@Nonnull CommandSource source, @Nonnull CommandContext args) throws CommandException {

        Optional<Player> player = args.getOne("player");
        Optional<String> boost = args.getOne("boost");
        Optional<String> action = args.getOne("action");
        Optional<Integer> amount = args.getOne("amount");

        if (!player.isPresent() || !boost.isPresent() || !action.isPresent() || !amount.isPresent()) {
            return CommandResult.empty();
        }

        try {
            if (action.get().equalsIgnoreCase("add")) {
                RachamonPixelmonBooster
                        .getInstance()
                        .getBoosterManager()
                        .addPlayerBooster(player.get(), boost.get(), amount.get());
            } else if (action.get().equalsIgnoreCase("remove")) {
                RachamonPixelmonBooster
                        .getInstance()
                        .getBoosterManager()
                        .removePlayerBooster(player.get(), boost.get(), amount.get());
            } else if (action.get().equalsIgnoreCase("set")) {
                RachamonPixelmonBooster
                        .getInstance()
                        .getBoosterManager()
                        .setAmountPlayerBooster(player.get(), boost.get(), amount.get());
            } else if (action.get().equalsIgnoreCase("time")) {
                RachamonPixelmonBooster
                        .getInstance()
                        .getBoosterManager()
                        .setTimePlayerBooster(player.get(), boost.get(), amount.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
            source.sendMessage(TextUtil.toText(e.getMessage()));
        }

        return CommandResult.success();
    }

    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[]{GenericArguments.player(Text.of("player")), new BoosterCommandElement(Text.of("boost")), new BoosterModificationCommandElement(Text.of("action")), GenericArguments.integer(Text.of("amount"))};
    }
}
