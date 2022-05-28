package dev.rachamon.rachamonpixelmonbooster.commands.subcommands;

import dev.rachamon.api.sponge.implement.command.*;
import dev.rachamon.rachamonpixelmonbooster.RachamonPixelmonBooster;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;

import javax.annotation.Nonnull;

/**
 * The type Rachamon pixelmon booster reload.
 */
@ICommandDescription("Reload all configs")
@ICommandAliases({"reload"})
@ICommandPermission("rachamonpixelmonbooster.command.admin.reload")
public class RachamonPixelmonBoosterReload implements ICommand, IParameterizedCommand {
    @Nonnull
    @Override
    public CommandResult execute(@Nonnull CommandSource source, @Nonnull CommandContext args) throws CommandException {

        RachamonPixelmonBooster.getInstance().getPluginManager().reload();

        return CommandResult.success();
    }

    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[0];
    }
}
