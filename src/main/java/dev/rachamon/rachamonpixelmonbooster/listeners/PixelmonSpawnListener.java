package dev.rachamon.rachamonpixelmonbooster.listeners;

import com.pixelmonmod.pixelmon.api.events.spawning.SpawnEvent;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonSpec;
import com.pixelmonmod.pixelmon.api.spawning.AbstractSpawner;
import com.pixelmonmod.pixelmon.api.spawning.SpawnInfo;
import com.pixelmonmod.pixelmon.api.spawning.SpawnLocation;
import com.pixelmonmod.pixelmon.comm.EnumUpdateType;
import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import com.pixelmonmod.pixelmon.enums.EnumBossMode;
import com.pixelmonmod.pixelmon.enums.EnumSpecies;
import com.pixelmonmod.pixelmon.spawning.PixelmonSpawning;
import com.pixelmonmod.pixelmon.spawning.PlayerTrackingSpawner;
import dev.rachamon.rachamonpixelmonbooster.RachamonPixelmonBooster;
import dev.rachamon.rachamonpixelmonbooster.managers.RachamonPixelmonBoosterManager;
import dev.rachamon.rachamonpixelmonbooster.stuctures.BoosterType;
import dev.rachamon.rachamonpixelmonbooster.stuctures.boosters.PokemonBossBooster;
import dev.rachamon.rachamonpixelmonbooster.stuctures.boosters.PokemonHABooster;
import dev.rachamon.rachamonpixelmonbooster.stuctures.boosters.PokemonShinyBooster;
import dev.rachamon.rachamonpixelmonbooster.stuctures.boosters.PokemonSpawnBooster;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.api.entity.living.player.Player;

import javax.annotation.Nullable;
import java.util.*;

/**
 * The type Pixelmon spawn listener.
 */
public class PixelmonSpawnListener {

    /**
     * On spawn boost.
     *
     * @param event the event
     */
    @SubscribeEvent
    public void onSpawnBoost(SpawnEvent event) {

        if (!(event.action.getOrCreateEntity() instanceof EntityPixelmon)) {
            return;
        }

        if (!(event.spawner instanceof PlayerTrackingSpawner)) {
            return;
        }

        Player player = ((Player) ((PlayerTrackingSpawner) event.spawner).getTrackedPlayer());

        if (player == null) {
            return;
        }

        EntityPixelmon pokemon = (EntityPixelmon) event.action.getOrCreateEntity();

        this.onSpawnHABoost(player, pokemon);
        this.onSpawnShinyBoost(player, pokemon);
        this.onSpawnBossBoost(player, pokemon);
        
        PokemonSpawnBooster booster = (PokemonSpawnBooster) RachamonPixelmonBoosterManager
                .getBoosters()
                .get(BoosterType.POKEMON_SPAWN);

        if (booster == null) {
            return;
        }

        if (!booster.isGloballyActive() && booster
                .getPlayers()
                .stream()
                .noneMatch(p -> p.getUuid().equals(player.getUniqueId()))) {
            return;
        }


        if (!booster.isChance()) {
            return;
        }

        EntityPixelmon spawn = this.getRandomPokemon(((PlayerTrackingSpawner) event.spawner).getTrackedPlayer(), booster.getBlacklist(), booster.isAllowLegendary());

        if (spawn == null) {
            return;
        }

        RachamonPixelmonBooster
                .getInstance()
                .getLogger()
                .debug("attempting spawning random boost: " + spawn.getPokemonData().getDisplayName());

        this.onSpawnHABoost(player, spawn);
        this.onSpawnShinyBoost(player, spawn);
        this.onSpawnBossBoost(player, spawn);
    }

    /**
     * On spawn ha boost.
     *
     * @param player  the player
     * @param pokemon the pokemon
     */
    public void onSpawnHABoost(Player player, EntityPixelmon pokemon) {

        PokemonHABooster booster = (PokemonHABooster) RachamonPixelmonBoosterManager
                .getBoosters()
                .get(BoosterType.HIDDEN_ABILITY);

        if (!booster.isGloballyActive() && booster
                .getPlayers()
                .stream()
                .noneMatch(p -> p.getUuid().equals(player.getUniqueId()))) {
            return;
        }

        boolean isChance = booster.isChance();

        if (!isChance) {
            return;
        }

        RachamonPixelmonBooster
                .getInstance()
                .getLogger()
                .debug("Spawning HA: " + pokemon.getPokemonData().getDisplayName());

        pokemon
                .getPokemonData()
                .setAbility(pokemon
                        .getBaseStats()
                        .getAllAbilities()
                        .get((pokemon.getBaseStats().getAllAbilities().size() - 1)));

        pokemon.update(EnumUpdateType.Ability);


    }

    /**
     * On spawn ha boost.
     *
     * @param player  the player
     * @param pokemon the pokemon
     */
    public void onSpawnBossBoost(Player player, EntityPixelmon pokemon) {

        PokemonBossBooster booster = (PokemonBossBooster) RachamonPixelmonBoosterManager
                .getBoosters()
                .get(BoosterType.BOSS);

        if (!booster.isGloballyActive() && booster
                .getPlayers()
                .stream()
                .noneMatch(p -> p.getUuid().equals(player.getUniqueId()))) {
            return;
        }

        boolean isChance = booster.isChance();

        if (!isChance) {
            return;
        }

        RachamonPixelmonBooster
                .getInstance()
                .getLogger()
                .debug("Spawning Boss: " + pokemon.getPokemonData().getDisplayName());

        pokemon.setBoss(EnumBossMode.getRandomMode());
    }

    /**
     * On spawn shiny boost.
     *
     * @param player  the player
     * @param pokemon the pokemon
     */
    public void onSpawnShinyBoost(Player player, EntityPixelmon pokemon) {

        PokemonShinyBooster booster = (PokemonShinyBooster) RachamonPixelmonBoosterManager
                .getBoosters()
                .get(BoosterType.SHINY_RATE);

        if (!booster.isGloballyActive() && booster
                .getPlayers()
                .stream()
                .noneMatch(p -> p.getUuid().equals(player.getUniqueId()))) {
            return;
        }

        boolean isChance = booster.isChance();

        if (!isChance) {
            return;
        }

        pokemon.getPokemonData().setShiny(true);

        RachamonPixelmonBooster
                .getInstance()
                .getLogger()
                .debug("Spawning Shiny: " + pokemon.getPokemonData().getDisplayName());
    }

    private String getRandomPokemon(Map<String, Double> chances) {
        double chance = new Random().nextDouble() * 100.0;
        double cumulative = 0.0;
        for (String pokemon : chances.keySet()) {
            cumulative += chances.get(pokemon);
            if (chance < cumulative) {
                return pokemon;
            }
        }
        return null;
    }

    /**
     * Gets random pokemon.
     *
     * @param player           the player
     * @param blacklist        the blacklist
     * @param isAllowLegendary the is allow legendary
     * @return the random pokemon
     */
    @Nullable
    public EntityPixelmon getRandomPokemon(EntityPlayerMP player, List<String> blacklist, boolean isAllowLegendary) {

        if (!PixelmonSpawning.coordinator.getActive()) {
            RachamonPixelmonBooster.getInstance().getLogger().debug("not active");
            return null;
        }

        AbstractSpawner spawner = PixelmonSpawning.coordinator.getSpawner(player.getName());
        PlayerTrackingSpawner pSpawner = (PlayerTrackingSpawner) spawner;

        if (pSpawner == null) {
            RachamonPixelmonBooster.getInstance().getLogger().debug("pSpawner is null;");
            return null;
        }

        ArrayList<SpawnLocation> spawnLocations = pSpawner.spawnLocationCalculator.calculateSpawnableLocations(spawner.getTrackedBlockCollection(player, 0.0F, 0.0F, pSpawner.horizontalSliceRadius, pSpawner.verticalSliceRadius, 0, 0));

        Map<SpawnLocation, List<SpawnInfo>> possibleSpawns = new HashMap<>();

        for (SpawnLocation spawnLocation : spawnLocations) {
            ArrayList<SpawnInfo> spawns = spawner.getSuitableSpawns(spawnLocation);
            if (!spawns.isEmpty()) {
                possibleSpawns.put(spawnLocation, spawns);
            }
        }

        Map<String, Double> pokemons = spawner.selectionAlgorithm.getPercentages(spawner, possibleSpawns);

        pokemons.keySet().forEach(p -> {
            boolean isContain = blacklist.contains(p);
            if (isContain) {
                pokemons.remove(p);
            }
        });

        if (!isAllowLegendary) {
            pokemons.keySet().forEach(p -> {
                Arrays.stream(EnumSpecies.LEGENDARY_ENUMS).forEach(s -> {
                    if (s.getPokemonName().equalsIgnoreCase(p)) {
                        pokemons.remove(p);
                    }
                });
            });
        }

        if (pokemons.size() == 0) {
            return null;
        }

        return PokemonSpec.from(this.getRandomPokemon(pokemons)).create(player.getEntityWorld());
    }

}
