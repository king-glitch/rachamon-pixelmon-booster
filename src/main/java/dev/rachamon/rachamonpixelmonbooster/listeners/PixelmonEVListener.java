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

            RachamonPixelmonBooster
                    .getInstance()
                    .getLogger()
                    .debug(hp + "," + attack + ", " + defense + ", " + specialAtk + ", " + specialDef + ", " + speed);

            int[] evs = new int[]{hp, attack, defense, specialAtk, specialDef, speed};

//            pokemon.getStats().evs.gainEV(new EVStore(evs));
//
            event.evStore.fillFromArray(pokemon.getStats().evs.getArray());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
