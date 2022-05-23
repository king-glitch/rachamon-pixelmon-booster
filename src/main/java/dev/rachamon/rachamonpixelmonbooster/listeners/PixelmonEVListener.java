package dev.rachamon.rachamonpixelmonbooster.listeners;

import com.pixelmonmod.pixelmon.api.events.pokemon.EVsGainedEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.EVStore;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.StatsType;
import dev.rachamon.rachamonpixelmonbooster.managers.RachamonPixelmonBoosterManager;
import dev.rachamon.rachamonpixelmonbooster.stuctures.BoosterType;
import dev.rachamon.rachamonpixelmonbooster.stuctures.boosters.PokemonEVBooster;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.api.entity.living.player.Player;

public class PixelmonEVListener {
    @SubscribeEvent
    public void onPokemonEVGained(EVsGainedEvent event) {

        Player player = (Player) event.pokemon.getOwnerPlayer();
        PokemonEVBooster booster = (PokemonEVBooster) RachamonPixelmonBoosterManager
                .getBoosters()
                .get(BoosterType.EV);

        if (!booster.isGloballyActive() && booster
                .getPlayers()
                .stream()
                .noneMatch(p -> p.getUuid().equals(player.getUniqueId()))) {
            return;
        }

        try {
            Pokemon pokemon = event.pokemon;
            EVStore evStore = event.evStore;
            int attack = booster.calculate(evStore.getStat(StatsType.Attack));
            int defense = booster.calculate(evStore.getStat(StatsType.Defence));
            int specialAtk = booster.calculate(evStore.getStat(StatsType.SpecialAttack));
            int specialDef = booster.calculate(evStore.getStat(StatsType.SpecialDefence));
            int speed = booster.calculate(evStore.getStat(StatsType.Speed));
            int hp = booster.calculate(evStore.getStat(StatsType.HP));
            int[] evs = new int[]{hp / 2, attack / 2, defense / 2, specialAtk / 2, specialDef / 2, speed / 2};

            EVStore store = pokemon.getStats().evs;
            store.gainEV(new EVStore(evs));
            event.evStore.fillFromArray(store.getArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
