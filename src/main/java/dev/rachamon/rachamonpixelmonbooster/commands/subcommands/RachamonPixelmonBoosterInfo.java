package dev.rachamon.rachamonpixelmonbooster.commands.subcommands;

import dev.rachamon.api.sponge.implement.command.*;
import dev.rachamon.rachamonpixelmonbooster.RachamonPixelmonBooster;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.entity.living.player.Player;

import javax.annotation.Nonnull;

@ICommandDescription("Get current user info")
@ICommandAliases({"info"})
@ICommandPermission("rachamonpixelmonbooster.command.user.info")
public class RachamonPixelmonBoosterInfo implements IPlayerCommand, IParameterizedCommand {
    @Nonnull
    @Override
    public CommandResult execute(@Nonnull Player source, @Nonnull CommandContext args) throws CommandException {
        try {
            RachamonPixelmonBooster.getInstance().getBoosterManager().printPlayerBoosterInfo(source);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommandResult.success();
    }

    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[0];
    }
}
