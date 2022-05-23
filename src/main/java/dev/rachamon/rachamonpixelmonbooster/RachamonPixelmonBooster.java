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
import dev.rachamon.rachamonpixelmonbooster.managers.RachamonPixelmonBoosterManager;
import dev.rachamon.rachamonpixelmonbooster.managers.RachamonPixelmonBoosterPluginManager;
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

@Plugin(id = "rachamonpixelmonbooster", name = RachamonPixelmonBooster.PLUGIN_NAME, dependencies = {@Dependency(id = "after:pixelmon")})
public class RachamonPixelmonBooster extends RachamonSpongePluginProvider implements IRachamonPlugin {

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

    public RachamonPixelmonBooster() {
        super(RachamonPixelmonBooster.PLUGIN_NAME, Sponge.getServer());
    }

    @Listener
    public void onPreInitialize(GamePreInitializationEvent event) {
        RachamonPixelmonBooster.instance = this;
        this.pluginManager = new RachamonPixelmonBoosterPluginManager();
        this.getLogger().info("On Pre Initialize RachamonPixelmonBooster...");
    }

    @Listener(order = Order.EARLY)
    public void onInitialize(GameInitializationEvent event) {
        RachamonPixelmonBooster.getInstance().getLogger().info("On Initialize RachamonPixelmonBooster...");
        RachamonPixelmonBooster.getInstance().getPluginManager().initialize();
    }

    @Listener
    public void onStart(GameStartedServerEvent event) {
        if (!this.isInitialized()) return;
        RachamonPixelmonBooster.getInstance().getLogger().info("On Start RachamonPixelmonBooster...");
        RachamonPixelmonBooster.getInstance().getPluginManager().start();
    }

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

    public static RachamonPixelmonBooster getInstance() {
        return instance;
    }

    public MainConfig getConfig() {
        return this.config.getRoot();
    }

    public void setConfig(MainConfig config) {
        this.config.setClazz(config);
    }

    public void setMainConfig(SpongeAPIConfigFactory<RachamonPixelmonBooster, MainConfig> config) {
        this.config = config;
    }

    public BoosterConfig getBooster() {
        return this.booster.getRoot();
    }

    public void setBooster(BoosterConfig config) {
        this.booster.setClazz(config);
    }

    public void setMainBooster(SpongeAPIConfigFactory<RachamonPixelmonBooster, BoosterConfig> booster) {
        this.booster = booster;
    }

    public LanguageConfig getLanguage() {
        return this.language.getRoot();
    }

    public void setLanguage(LanguageConfig config) {
        this.language.setClazz(config);
    }

    public void setMainLanguage(SpongeAPIConfigFactory<RachamonPixelmonBooster, LanguageConfig> language) {
        this.language = language;
    }

    public Components getComponents() {
        return this.components;
    }

    public void setComponents(Components components) {
        this.components = components;
    }

    public Injector getInjector() {
        return injector;
    }

    public void setInjector(Injector injector) {
        this.injector = injector;
    }

    public RachamonPixelmonBoosterManager getBoosterManager() {
        return this.getComponents().boosterManager;
    }

    public static class Components {
        @Inject
        private RachamonPixelmonBoosterManager boosterManager;

    }
}
