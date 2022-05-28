package dev.rachamon.rachamonpixelmonbooster.commands.subcommands;

import dev.rachamon.api.sponge.implement.command.*;
import dev.rachamon.rachamonpixelmonbooster.RachamonPixelmonBooster;
import dev.rachamon.rachamonpixelmonbooster.commands.elements.BoosterBoostCommandElement;
import dev.rachamon.rachamonpixelmonbooster.commands.elements.BoosterCommandElement;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import javax.annotation.Nonnull;
import java.util.Optional;

@ICommandDescription("Use, Resume, Pause a boost")
@ICommandAliases({"boost"})
@ICommandPermission("rachamonpixelmonbooster.command.user.boost")
public class RachamonPixelmonBoosterBoost implements IPlayerCommand, IParameterizedCommand {
    @Nonnull
    @Override
    public CommandResult execute(@Nonnull Player source, @Nonnull CommandContext args) throws CommandException {

        Optional<String> boost = args.getOne("boost");
        Optional<String> action = args.getOne("action");

        if (!boost.isPresent() || !action.isPresent()) {
            return CommandResult.empty();
        }

        try {
            if (action.get().equalsIgnoreCase("use")) {
                RachamonPixelmonBooster.getInstance().getBoosterManager().playerUseBooster(source, boost.get());
            } else if (action.get().equalsIgnoreCase("resume")) {
                RachamonPixelmonBooster.getInstance().getBoosterManager().playerResumeBooster(source, boost.get());
            } else if (action.get().equalsIgnoreCase("pause")) {
                RachamonPixelmonBooster.getInstance().getBoosterManager().playerPauseBooster(source, boost.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return CommandResult.success();
    }

    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[]{new BoosterCommandElement(Text.of("boost")), new BoosterBoostCommandElement(Text.of("action"))};
    }
}