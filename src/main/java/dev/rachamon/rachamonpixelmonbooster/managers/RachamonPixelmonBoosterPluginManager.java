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
                        if (!RachamonPixelmonBoosterManager.getBoosters().containsKey(boosterType)) {
                            RachamonPixelmonBoosterManager.getBoosters().put(boosterType, new PokemonSpawnBooster());
                            break;
                        }
                        PokemonSpawnBooster pokemonSpawnBooster = (PokemonSpawnBooster) RachamonPixelmonBoosterManager
                                .getBoosters()
                                .get(boosterType);
                        pokemonSpawnBooster.setConfig(RachamonPixelmonBooster
                                .getInstance()
                                .getBooster()
                                .getBoosters()
                                .get(boosterType.toString()));
                        RachamonPixelmonBoosterManager.getBoosters().put(boosterType, pokemonSpawnBooster);
                        break;
                    case BATTLE_WINNING:
                        if (!RachamonPixelmonBoosterManager.getBoosters().containsKey(boosterType)) {
                            RachamonPixelmonBoosterManager.getBoosters().put(boosterType, new TrainerMoneyBooster());
                            break;
                        }
                        TrainerMoneyBooster trainerMoneyBooster = (TrainerMoneyBooster) RachamonPixelmonBoosterManager
                                .getBoosters()
                                .get(boosterType);
                        trainerMoneyBooster.setConfig(RachamonPixelmonBooster
                                .getInstance()
                                .getBooster()
                                .getBoosters()
                                .get(boosterType.toString()));
                        RachamonPixelmonBoosterManager.getBoosters().put(boosterType, trainerMoneyBooster);
                        break;
                    case BOSS:
                        if (!RachamonPixelmonBoosterManager.getBoosters().containsKey(boosterType)) {
                            RachamonPixelmonBoosterManager.getBoosters().put(boosterType, new PokemonBossBooster());
                            break;
                        }
                        PokemonBossBooster pokemonBossBooster = (PokemonBossBooster) RachamonPixelmonBoosterManager
                                .getBoosters()
                                .get(boosterType);
                        pokemonBossBooster.setConfig(RachamonPixelmonBooster
                                .getInstance()
                                .getBooster()
                                .getBoosters()
                                .get(boosterType.toString()));
                        RachamonPixelmonBoosterManager.getBoosters().put(boosterType, pokemonBossBooster);
                        break;
                    case HATCH:
                        if (!RachamonPixelmonBoosterManager.getBoosters().containsKey(boosterType)) {
                            RachamonPixelmonBoosterManager.getBoosters().put(boosterType, new PokemonHatchBooster());
                            break;
                        }
                        PokemonHatchBooster pokemonHatchBooster = (PokemonHatchBooster) RachamonPixelmonBoosterManager
                                .getBoosters()
                                .get(boosterType);
                        pokemonHatchBooster.setConfig(RachamonPixelmonBooster
                                .getInstance()
                                .getBooster()
                                .getBoosters()
                                .get(boosterType.toString()));
                        RachamonPixelmonBoosterManager.getBoosters().put(boosterType, pokemonHatchBooster);
                        break;
                    case HIDDEN_ABILITY:
                        if (!RachamonPixelmonBoosterManager.getBoosters().containsKey(boosterType)) {
                            RachamonPixelmonBoosterManager.getBoosters().put(boosterType, new PokemonHABooster());
                            break;
                        }
                        PokemonHABooster pokemonHABooster = (PokemonHABooster) RachamonPixelmonBoosterManager
                                .getBoosters()
                                .get(boosterType);
                        pokemonHABooster.setConfig(RachamonPixelmonBooster
                                .getInstance()
                                .getBooster()
                                .getBoosters()
                                .get(boosterType.toString()));
                        RachamonPixelmonBoosterManager.getBoosters().put(boosterType, pokemonHABooster);
                        break;
                    case EXP:
                        if (!RachamonPixelmonBoosterManager.getBoosters().containsKey(boosterType)) {
                            RachamonPixelmonBoosterManager.getBoosters().put(boosterType, new PokemonExpBooster());
                            break;
                        }
                        PokemonExpBooster pokemonExpBooster = (PokemonExpBooster) RachamonPixelmonBoosterManager
                                .getBoosters()
                                .get(boosterType);
                        pokemonExpBooster.setConfig(RachamonPixelmonBooster
                                .getInstance()
                                .getBooster()
                                .getBoosters()
                                .get(boosterType.toString()));
                        RachamonPixelmonBoosterManager.getBoosters().put(boosterType, pokemonExpBooster);
                        break;
                    case EV:
                        if (!RachamonPixelmonBoosterManager.getBoosters().containsKey(boosterType)) {
                            RachamonPixelmonBoosterManager.getBoosters().put(boosterType, new PokemonEVBooster());
                            break;
                        }
                        PokemonEVBooster pokemonEVBooster = (PokemonEVBooster) RachamonPixelmonBoosterManager
                                .getBoosters()
                                .get(boosterType);
                        pokemonEVBooster.setConfig(RachamonPixelmonBooster
                                .getInstance()
                                .getBooster()
                                .getBoosters()
                                .get(boosterType.toString()));
                        RachamonPixelmonBoosterManager.getBoosters().put(boosterType, pokemonEVBooster);
                        break;
                    case SHINY_RATE:
                        if (!RachamonPixelmonBoosterManager.getBoosters().containsKey(boosterType)) {
                            RachamonPixelmonBoosterManager.getBoosters().put(boosterType, new PokemonShinyBooster());
                            break;
                        }
                        PokemonShinyBooster pokemonShinyBooster = (PokemonShinyBooster) RachamonPixelmonBoosterManager
                                .getBoosters()
                                .get(boosterType);
                        pokemonShinyBooster.setConfig(RachamonPixelmonBooster
                                .getInstance()
                                .getBooster()
                                .getBoosters()
                                .get(boosterType.toString()));
                        RachamonPixelmonBoosterManager.getBoosters().put(boosterType, pokemonShinyBooster);
                        break;
                    case CAPTURE:
                        if (!RachamonPixelmonBoosterManager.getBoosters().containsKey(boosterType)) {
                            RachamonPixelmonBoosterManager.getBoosters().put(boosterType, new PokemonCaptureBooster());
                            break;
                        }
                        PokemonCaptureBooster pokemonCaptureBooster = (PokemonCaptureBooster) RachamonPixelmonBoosterManager
                                .getBoosters()
                                .get(boosterType);
                        pokemonCaptureBooster.setConfig(RachamonPixelmonBooster
                                .getInstance()
                                .getBooster()
                                .getBoosters()
                                .get(boosterType.toString()));
                        RachamonPixelmonBoosterManager.getBoosters().put(boosterType, pokemonCaptureBooster);
                        break;
                    case DROP:
                        if (!RachamonPixelmonBoosterManager.getBoosters().containsKey(boosterType)) {
                            RachamonPixelmonBoosterManager.getBoosters().put(boosterType, new PokemonDropBooster());
                            break;
                        }
                        PokemonDropBooster pokemonDropBooster = (PokemonDropBooster) RachamonPixelmonBoosterManager
                                .getBoosters()
                                .get(boosterType);
                        pokemonDropBooster.setConfig(RachamonPixelmonBooster
                                .getInstance()
                                .getBooster()
                                .getBoosters()
                                .get(boosterType.toString()));
                        RachamonPixelmonBoosterManager.getBoosters().put(boosterType, pokemonDropBooster);
                        break;
                    default:
                        if (!RachamonPixelmonBoosterManager.getBoosters().containsKey(boosterType)) {
                            RachamonPixelmonBoosterManager.getBoosters().put(boosterType, new Booster(boosterType));
                            break;
                        }
                        Booster booster = RachamonPixelmonBoosterManager.getBoosters().get(boosterType);
                        RachamonPixelmonBoosterManager.getBoosters().put(boosterType, booster);
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
