package dev.rachamon.rachamonpixelmonbooster.managers;

import com.pixelmonmod.pixelmon.Pixelmon;
import dev.rachamon.api.sponge.config.SpongeAPIConfigFactory;
import dev.rachamon.api.sponge.exception.AnnotatedCommandException;
import dev.rachamon.api.sponge.implement.plugin.IRachamonPluginManager;
import dev.rachamon.rachamonpixelmonbooster.RachamonPixelmonBooster;
import dev.rachamon.rachamonpixelmonbooster.RachamonPixelmonBoosterModule;
import dev.rachamon.rachamonpixelmonbooster.commands.RachamonPixelmonBoosterMainCommand;
import dev.rachamon.rachamonpixelmonbooster.configs.BoosterConfig;
import dev.rachamon.rachamonpixelmonbooster.configs.LanguageConfig;
import dev.rachamon.rachamonpixelmonbooster.configs.MainConfig;
import dev.rachamon.rachamonpixelmonbooster.configs.PlayerDataConfig;
import dev.rachamon.rachamonpixelmonbooster.listeners.*;
import dev.rachamon.rachamonpixelmonbooster.stuctures.Booster;
import dev.rachamon.rachamonpixelmonbooster.stuctures.BoosterType;
import dev.rachamon.rachamonpixelmonbooster.stuctures.boosters.*;
import org.spongepowered.api.Sponge;

/**
 * The type Rachamon pixelmon booster plugin manager.
 */
public class RachamonPixelmonBoosterPluginManager implements IRachamonPluginManager {
    private final RachamonPixelmonBooster plugin = RachamonPixelmonBooster.getInstance();

    @Override
    public void initialize() {
        this.plugin.setComponents(new RachamonPixelmonBooster.Components());
        this.plugin.setInjector(this.plugin.getInjector().createChildInjector(new RachamonPixelmonBoosterModule()));
        this.plugin.getInjector().injectMembers(this.plugin.getComponents());
        this.plugin.setInitialized(true);
    }

    @Override
    public void preInitialize() {

    }

    @Override
    public void postInitialize() {
        this.reload();
        this.initializeBoosters();

        Pixelmon.EVENT_BUS.register(new BattleEndListener());
        Pixelmon.EVENT_BUS.register(new PixelmonDropListener());
        Pixelmon.EVENT_BUS.register(new PixelmonEVListener());
        Pixelmon.EVENT_BUS.register(new PixelmonExpListener());
        Pixelmon.EVENT_BUS.register(new PixelmonHatchBoosterListener());
        Pixelmon.EVENT_BUS.register(new PixelmonSpawnListener());
        Pixelmon.EVENT_BUS.register(new PokemonCaptureListener());

        Sponge.getEventManager().registerListeners(this.plugin, new PlayerActionListener());

    }

    @Override
    public void start() {

    }

    @Override
    public void reload() {
        this.registerConfigs();
        this.registerCommands();
    }

    private void initializeBoosters() {
        for (String boost : RachamonPixelmonBooster.getInstance().getBooster().getBoosters().keySet()) {

            try {
                BoosterType boosterType = BoosterType.valueOf(boost);
                switch (boosterType) {
                    case POKEMON_SPAWN:
                        RachamonPixelmonBoosterManager.getBoosters().put(boosterType, new PokemonSpawnBooster());
                        break;
                    case BATTLE_WINNING:
                        RachamonPixelmonBoosterManager.getBoosters().put(boosterType, new TrainerMoneyBooster());
                        break;
                    case BOSS:
                        RachamonPixelmonBoosterManager.getBoosters().put(boosterType, new PokemonBossBooster());
                        break;
                    case HATCH:
                        RachamonPixelmonBoosterManager.getBoosters().put(boosterType, new PokemonHatchBooster());
                        break;
                    case HIDDEN_ABILITY:
                        RachamonPixelmonBoosterManager.getBoosters().put(boosterType, new PokemonHABooster());
                        break;
                    case EXP:
                        RachamonPixelmonBoosterManager.getBoosters().put(boosterType, new PokemonExpBooster());
                        break;
                    case EV:
                        RachamonPixelmonBoosterManager.getBoosters().put(boosterType, new PokemonEVBooster());
                        break;
                    case SHINY_RATE:
                        RachamonPixelmonBoosterManager.getBoosters().put(boosterType, new PokemonShinyBooster());
                        break;
                    case CAPTURE:
                        RachamonPixelmonBoosterManager.getBoosters().put(boosterType, new PokemonCaptureBooster());
                        break;
                    case DROP:
                        RachamonPixelmonBoosterManager.getBoosters().put(boosterType, new PokemonDropBooster());
                        break;
                    default:
                        RachamonPixelmonBoosterManager.getBoosters().put(boosterType, new Booster(boosterType));
                        break;
                }
            } catch (Exception e) {
                RachamonPixelmonBooster.getInstance().getLogger().debug("booster type " + boost + " is null");
            }

        }
    }

    private void registerCommands() {
        try {
            this.plugin.getCommandService().register(new RachamonPixelmonBoosterMainCommand(), this.plugin);
        } catch (AnnotatedCommandException e) {
            e.printStackTrace();
        }
    }

    private void registerConfigs() {
        SpongeAPIConfigFactory<RachamonPixelmonBooster, MainConfig> config = new SpongeAPIConfigFactory<>(this.plugin, "main.conf");
        SpongeAPIConfigFactory<RachamonPixelmonBooster, LanguageConfig> language = new SpongeAPIConfigFactory<>(this.plugin, "language.conf");
        SpongeAPIConfigFactory<RachamonPixelmonBooster, BoosterConfig> boosters = new SpongeAPIConfigFactory<>(this.plugin, "boosters.conf");
        SpongeAPIConfigFactory<RachamonPixelmonBooster, PlayerDataConfig> playerData = new SpongeAPIConfigFactory<>(this.plugin, "player-data.conf");

        this.plugin.setMainConfig(config);
        this.plugin.setMainLanguage(language);
        this.plugin.setMainBooster(boosters);
        this.plugin.setMainPlayerData(playerData);

        this.plugin.setConfig(config
                .setHeader("Main Config")
                .setClazz(new MainConfig())
                .setClazzType(MainConfig.class)
                .build());

        this.plugin.setLanguage(language
                .setHeader("Language Config")
                .setClazz(new LanguageConfig())
                .setClazzType(LanguageConfig.class)
                .build());

        this.plugin.setBooster(boosters
                .setHeader("Boosters Config")
                .setClazz(new BoosterConfig())
                .setClazzType(BoosterConfig.class)
                .build());

        this.plugin.setPlayerData(playerData
                .setHeader("Player Data Config")
                .setClazz(new PlayerDataConfig())
                .setClazzType(PlayerDataConfig.class)
                .build());

        try {
            this.plugin.getLogger().setDebug(this.plugin.getConfig().getGeneralConfig().isDebug());
            RachamonPixelmonBooster.getInstance().getPlayerDataService().cleanUnusedPlayersData();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
