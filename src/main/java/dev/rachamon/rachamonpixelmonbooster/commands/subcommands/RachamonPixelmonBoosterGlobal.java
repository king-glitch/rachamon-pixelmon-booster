package dev.rachamon.rachamonpixelmonbooster.commands.subcommands;

import dev.rachamon.api.sponge.implement.command.*;
import dev.rachamon.api.sponge.util.TextUtil;
import dev.rachamon.rachamonpixelmonbooster.RachamonPixelmonBooster;
import dev.rachamon.rachamonpixelmonbooster.commands.elements.BoosterCommandElement;
import dev.rachamon.rachamonpixelmonbooster.commands.elements.BoosterGlobalCommandElement;
import dev.rachamon.rachamonpixelmonbooster.stuctures.BoosterType;
import dev.rachamon.rachamonpixelmonbooster.utils.ChatUtil;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.text.Text;

import javax.annotation.Nonnull;
import java.util.Optional;

/**
 * The type Rachamon pixelmon booster global.
 */
@ICommandDescription("Activate, Deactivate boosts")
@ICommandAliases({"global"})
@ICommandPermission("rachamonpixelmonbooster.command.admin.global")
public class RachamonPixelmonBoosterGlobal implements ICommand, IParameterizedCommand {
    @Nonnull
    @Override
    public CommandResult execute(@Nonnull CommandSource source, @Nonnull CommandContext args) throws CommandException {

        Optional<String> boost = args.getOne("boost");
        Optional<String> action = args.getOne("action");

        if (!boost.isPresent() || !action.isPresent()) {
            return CommandResult.empty();
        }

        try {

            BoosterType boosterType = RachamonPixelmonBooster.getInstance().getBoosterManager().getBooster(boost.get());

            if (action.get().equalsIgnoreCase("activate")) {
                RachamonPixelmonBooster.getInstance().getBoosterManager().activateGlobalBooster(boosterType);

                ChatUtil.sendMessage(source, RachamonPixelmonBooster
                        .getInstance()
                        .getLanguage()
                        .getGeneralLanguage()
                        .getGlobalBoosterActivate()
                        .replaceAll("\\{booster}", boosterType.getName()));
            } else if (action.get().equalsIgnoreCase("deactivate")) {
                RachamonPixelmonBooster.getInstance().getBoosterManager().deactivateGlobalBooster(boosterType);
                ChatUtil.sendMessage(source, RachamonPixelmonBooster
                        .getInstance()
                        .getLanguage()
                        .getGeneralLanguage()
                        .getGlobalBoosterDeactivate()
                        .replaceAll("\\{booster}", boosterType.getName()));
            }
        } catch (Exception e) {
            e.printStackTrace();

            source.sendMessage(TextUtil.toText(e.getMessage()));

        }

        return CommandResult.success();
    }

    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[]{new BoosterCommandElement(Text.of("boost")), new BoosterGlobalCommandElement(Text.of("action"))};
    }
}
