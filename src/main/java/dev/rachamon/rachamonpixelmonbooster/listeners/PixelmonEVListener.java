package dev.rachamon.rachamonpixelmonbooster.listeners;

import com.pixelmonmod.pixelmon.api.events.pokemon.EVsGainedEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.EVStore;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.StatsType;
import dev.rachamon.rachamonpixelmonbooster.RachamonPixelmonBooster;
import dev.rachamon.rachamonpixelmonbooster.managers.RachamonPixelmonBoosterManager;
import dev.rachamon.rachamonpixelmonbooster.stuctures.BoosterType;
import dev.rachamon.rachamonpixelmonbooster.stuctures.boosters.PokemonEVBooster;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.api.entity.living.player.Player;

import java.util.Arrays;

/**
 * The type Pixelmon ev listener.
 */
public class PixelmonEVListener {
    /**
     * On pokemon ev gained.
     *
     * @param event the event
     */
    @SubscribeEvent
    public void onPokemonEVGained(EVsGainedEvent event) {

        Player player = (Player) event.pokemon.getOwnerPlayer();
        PokemonEVBooster booster = (PokemonEVBooster) RachamonPixelmonBoosterManager.getBoosters().get(BoosterType.EV);

        if (!booster.isGloballyActive() && booster
                .getPlayers()
                .stream()
                .noneMatch(p -> p.getUuid().equals(player.getUniqueId()))) {
            return;
        }

        try {
            Pokemon pokemon = event.pokemon;

            if (Arrays.stream(pokemon.getStats().evs.getArray()).sum() >= 510) {
                return;
            }

            EVStore evStore = event.evStore;

            int hp = booster.calculate(evStore.getStat(StatsType.HP));
            int attack = booster.calculate(evStore.getStat(StatsType.Attack));
            int defense = booster.calculate(evStore.getStat(StatsType.Defence));
            int specialAtk = booster.calculate(evStore.getStat(StatsType.SpecialAttack));
            int specialDef = booster.calculate(evStore.getStat(StatsType.SpecialDefence));
            int speed = booster.calculate(evStore.getStat(StatsType.Speed));

            pokemon.getStats().evs.setStat(StatsType.HP, pokemon.getStat(StatsType.HP) + hp);
            pokemon.getStats().evs.setStat(StatsType.Attack, pokemon.getStat(StatsType.Attack) + attack);
            pokemon.getStats().evs.setStat(StatsType.Defence, pokemon.getStat(StatsType.Defence) + defense);
            pokemon.getStats().evs.setStat(StatsType.SpecialAttack, pokemon.getStat(StatsType.SpecialAttack) + specialAtk);
            pokemon.getStats().evs.setStat(StatsType.SpecialDefence, pokemon.getStat(StatsType.SpecialDefence) + specialDef);
            pokemon.getStats().evs.setStat(StatsType.Speed, pokemon.getStat(StatsType.Speed) + speed);

            event.evStore.fillFromArray(pokemon.getStats().evs.getArray());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
