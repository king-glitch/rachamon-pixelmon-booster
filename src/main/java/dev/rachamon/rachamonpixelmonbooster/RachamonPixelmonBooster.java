package dev.rachamon.rachamonpixelmonbooster;

import com.google.inject.Inject;
import com.google.inject.Injector;
import dev.rachamon.api.sponge.command.SpongeCommandService;
import dev.rachamon.api.sponge.config.SpongeAPIConfigFactory;
import dev.rachamon.api.sponge.implement.plugin.IRachamonPlugin;
import dev.rachamon.api.sponge.implement.plugin.IRachamonPluginManager;
import dev.rachamon.api.sponge.provider.RachamonSpongePluginProvider;
import dev.rachamon.rachamonpixelmonbooster.configs.BoosterConfig;
import dev.rachamon.rachamonpixelmonbooster.configs.LanguageConfig;
import dev.rachamon.rachamonpixelmonbooster.configs.MainConfig;
import dev.rachamon.rachamonpixelmonbooster.configs.PlayerDataConfig;
import dev.rachamon.rachamonpixelmonbooster.managers.RachamonPixelmonBoosterManager;
import dev.rachamon.rachamonpixelmonbooster.managers.RachamonPixelmonBoosterPluginManager;
import dev.rachamon.rachamonpixelmonbooster.services.PlayerDataService;
import ninja.leaping.configurate.objectmapping.GuiceObjectMapperFactory;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePostInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

import java.nio.file.Path;

/**
 * The type Rachamon pixelmon booster.
 */
@Plugin(id = "rachamonpixelmonbooster", name = RachamonPixelmonBooster.PLUGIN_NAME, dependencies = {@Dependency(id = "pixelmon")})
public class RachamonPixelmonBooster extends RachamonSpongePluginProvider implements IRachamonPlugin {

    /**
     * The constant PLUGIN_NAME.
     */
    public static final String PLUGIN_NAME = "RachamonPixelmonBooster";
    private static RachamonPixelmonBooster instance;
    @Inject
    private Game game;
    @Inject
    private GuiceObjectMapperFactory factory;
    @Inject
    private PluginContainer container;
    @Inject
    @ConfigDir(sharedRoot = false)
    private Path directory;
    @Inject
    private Injector injector;

    private boolean isInitialized = false;
    private Components components;
    private RachamonPixelmonBoosterPluginManager pluginManager;
    private SpongeAPIConfigFactory<RachamonPixelmonBooster, MainConfig> config;
    private SpongeAPIConfigFactory<RachamonPixelmonBooster, LanguageConfig> language;
    private SpongeAPIConfigFactory<RachamonPixelmonBooster, BoosterConfig> booster;
    private SpongeAPIConfigFactory<RachamonPixelmonBooster, PlayerDataConfig> playerData;

    /**
     * Instantiates a new Rachamon pixelmon booster.
     */
    public RachamonPixelmonBooster() {
        super(RachamonPixelmonBooster.PLUGIN_NAME, Sponge.getServer());
    }

    /**
     * On pre initialize.
     *
     * @param event the event
     */
    @Listener
    public void onPreInitialize(GamePreInitializationEvent event) {
        RachamonPixelmonBooster.instance = this;
        this.pluginManager = new RachamonPixelmonBoosterPluginManager();
        this.getLogger().info("On Pre Initialize RachamonPixelmonBooster...");
    }

    /**
     * On initialize.
     *
     * @param event the event
     */
    @Listener(order = Order.EARLY)
    public void onInitialize(GameInitializationEvent event) {
        RachamonPixelmonBooster.getInstance().getLogger().info("On Initialize RachamonPixelmonBooster...");
        RachamonPixelmonBooster.getInstance().getPluginManager().initialize();
    }

    /**
     * On start.
     *
     * @param event the event
     */
    @Listener
    public void onStart(GameStartedServerEvent event) {
        if (!this.isInitialized()) return;
        RachamonPixelmonBooster.getInstance().getLogger().info("On Start RachamonPixelmonBooster...");
        RachamonPixelmonBooster.getInstance().getPluginManager().start();
    }

    /**
     * On post initialize.
     *
     * @param event the event
     */
    @Listener
    public void onPostInitialize(GamePostInitializationEvent event) {
        RachamonPixelmonBooster.getInstance().getLogger().info("On Post Initialize RachamonPixelmonBooster");
        RachamonPixelmonBooster.getInstance().getPluginManager().postInitialize();
    }


    @Override
    public GuiceObjectMapperFactory getFactory() {
        return this.factory;
    }

    @Override
    public Game getGame() {
        return this.game;
    }

    @Override
    public Path getDirectory() {
        return this.directory;
    }

    @Override
    public PluginContainer getContainer() {
        return this.container;
    }

    @Override
    public SpongeCommandService getCommandService() {
        return SpongeCommandService.getInstance();
    }

    @Override
    public IRachamonPluginManager getPluginManager() {
        return this.pluginManager;
    }

    @Override
    public boolean isInitialized() {
        return this.isInitialized;
    }

    @Override
    public void setInitialized(boolean isInitialized) {
        this.isInitialized = isInitialized;
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static RachamonPixelmonBooster getInstance() {
        return instance;
    }

    /**
     * Gets config.
     *
     * @return the config
     */
    public MainConfig getConfig() {
        return this.config.getRoot();
    }

    /**
     * Sets config.
     *
     * @param config the config
     */
    public void setConfig(MainConfig config) {
        this.config.setClazz(config);
    }

    /**
     * Sets main config.
     *
     * @param config the config
     */
    public void setMainConfig(SpongeAPIConfigFactory<RachamonPixelmonBooster, MainConfig> config) {
        this.config = config;
    }

    /**
     * Gets booster.
     *
     * @return the booster
     */
    public BoosterConfig getBooster() {
        return this.booster.getRoot();
    }

    /**
     * Sets booster.
     *
     * @param config the config
     */
    public void setBooster(BoosterConfig config) {
        this.booster.setClazz(config);
    }

    /**
     * Sets main booster.
     *
     * @param booster the booster
     */
    public void setMainBooster(SpongeAPIConfigFactory<RachamonPixelmonBooster, BoosterConfig> booster) {
        this.booster = booster;
    }

    /**
     * Gets language.
     *
     * @return the language
     */
    public LanguageConfig getLanguage() {
        return this.language.getRoot();
    }

    /**
     * Sets language.
     *
     * @param config the config
     */
    public void setLanguage(LanguageConfig config) {
        this.language.setClazz(config);
    }

    /**
     * Sets main language.
     *
     * @param language the language
     */
    public void setMainLanguage(SpongeAPIConfigFactory<RachamonPixelmonBooster, LanguageConfig> language) {
        this.language = language;
    }

    /**
     * Gets player data.
     *
     * @return the player data
     */
    public PlayerDataConfig getPlayerData() {
        return this.playerData.getRoot();
    }

    /**
     * Gets player data config.
     *
     * @return the player data config
     */
    public SpongeAPIConfigFactory<RachamonPixelmonBooster, PlayerDataConfig> getPlayerDataConfig() {
        return this.playerData;
    }

    /**
     * Sets player data.
     *
     * @param playerData the player data
     */
    public void setPlayerData(PlayerDataConfig playerData) {
        this.playerData.setClazz(playerData);
    }

    /**
     * Sets main player data.
     *
     * @param playerData the player data
     */
    public void setMainPlayerData(SpongeAPIConfigFactory<RachamonPixelmonBooster, PlayerDataConfig> playerData) {
        this.playerData = playerData;
    }

    /**
     * Gets components.
     *
     * @return the components
     */
    public Components getComponents() {
        return this.components;
    }

    /**
     * Sets components.
     *
     * @param components the components
     */
    public void setComponents(Components components) {
        this.components = components;
    }

    /**
     * Gets injector.
     *
     * @return the injector
     */
    public Injector getInjector() {
        return injector;
    }

    /**
     * Sets injector.
     *
     * @param injector the injector
     */
    public void setInjector(Injector injector) {
        this.injector = injector;
    }

    /**
     * Gets booster manager.
     *
     * @return the booster manager
     */
    public RachamonPixelmonBoosterManager getBoosterManager() {
        return this.getComponents().boosterManager;
    }

    /**
     * Gets player data service.
     *
     * @return the player data service
     */
    public PlayerDataService getPlayerDataService() {
        return this.getComponents().playerDataService;
    }

    /**
     * The type Components.
     */
    public static class Components {
        @Inject
        private RachamonPixelmonBoosterManager boosterManager;

        @Inject
        private PlayerDataService playerDataService;

    }
}
