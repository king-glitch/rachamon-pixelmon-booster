package dev.rachamon.rachamonpixelmonbooster.commands;

import dev.rachamon.api.sponge.implement.command.*;
import dev.rachamon.rachamonpixelmonbooster.RachamonPixelmonBooster;
import dev.rachamon.rachamonpixelmonbooster.commands.subcommands.*;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;

import javax.annotation.Nonnull;

/**
 * The type Rachamon pixelmon booster main command.
 */
@ICommandChildren({RachamonPixelmonBoosterGlobal.class, RachamonPixelmonBoosterInfo.class, RachamonPixelmonBoosterReload.class, RachamonPixelmonBoosterModification.class, RachamonPixelmonBoosterBoost.class})
@ICommandAliases({"rachamonpixelmonbooster", "boosters", "boost", "boosts", "booster"})
@ICommandHelpText(title = "Main Pixelmon Booster Help", command = "help")
@ICommandPermission("rachamonpixelmonbooster.command.base")
public class RachamonPixelmonBoosterMainCommand implements ICommand {
    @Nonnull
    @Override
    public CommandResult execute(@Nonnull CommandSource source, @Nonnull CommandContext args) throws CommandException {
        return CommandResult.success();
    }
}
