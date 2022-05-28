package dev.rachamon.rachamonpixelmonbooster;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import dev.rachamon.rachamonpixelmonbooster.managers.RachamonPixelmonBoosterManager;
import dev.rachamon.rachamonpixelmonbooster.services.PlayerDataService;

/**
 * The type Rachamon pixelmon showdown module.
 */
public class RachamonPixelmonBoosterModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(RachamonPixelmonBoosterManager.class).in(Scopes.SINGLETON);
        bind(PlayerDataService.class).in(Scopes.SINGLETON);
    }
}