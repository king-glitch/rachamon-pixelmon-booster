package dev.rachamon.rachamonpixelmonbooster.listeners;

import com.google.common.collect.Sets;
import com.pixelmonmod.pixelmon.api.events.spawning.SpawnEvent;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonSpec;
import com.pixelmonmod.pixelmon.api.spawning.AbstractSpawner;
import com.pixelmonmod.pixelmon.api.spawning.SpawnInfo;
import com.pixelmonmod.pixelmon.api.spawning.SpawnLocation;
import com.pixelmonmod.pixelmon.api.spawning.conditions.LocationType;
import com.pixelmonmod.pixelmon.api.spawning.util.SpatialData;
import com.pixelmonmod.pixelmon.api.world.MutableLocation;
import com.pixelmonmod.pixelmon.comm.EnumUpdateType;
import com.pixelmonmod.pixelmon.config.BetterSpawnerConfig;
import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import com.pixelmonmod.pixelmon.enums.EnumSpecies;
import com.pixelmonmod.pixelmon.spawning.PixelmonSpawning;
import com.pixelmonmod.pixelmon.spawning.PlayerTrackingSpawner;
import dev.rachamon.rachamonpixelmonbooster.RachamonPixelmonBooster;
import dev.rachamon.rachamonpixelmonbooster.managers.RachamonPixelmonBoosterManager;
import dev.rachamon.rachamonpixelmonbooster.stuctures.BoosterType;
import dev.rachamon.rachamonpixelmonbooster.stuctures.boosters.PokemonHABooster;
import dev.rachamon.rachamonpixelmonbooster.stuctures.boosters.PokemonShinyBooster;
import dev.rachamon.rachamonpixelmonbooster.stuctures.boosters.PokemonSpawnBooster;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

public class PixelmonSpawnListener {

    @SubscribeEvent
    public void onSpawnBoost(SpawnEvent event) {

        if (!(event.action.getOrCreateEntity() instanceof EntityPixelmon)) {
            return;
        }

        if (!(event.spawner instanceof PlayerTrackingSpawner)) {
            return;
        }

        PokemonSpawnBooster booster = (PokemonSpawnBooster) RachamonPixelmonBoosterManager
                .getBoosters()
                .get(BoosterType.POKEMON_SPAWN);

        EntityPlayerMP player = ((PlayerTrackingSpawner) event.spawner).getTrackedPlayer();

        if (player == null) {
            return;
        }

        if (!booster.isGloballyActive() && booster
                .getPlayers()
                .stream()
                .noneMatch(p -> p.getUuid().equals(player.getUniqueID()))) {
            return;
        }

        EntityPixelmon pokemon = (EntityPixelmon) event.action.getOrCreateEntity();
        this.onSpawnHABoost(player, pokemon);
        this.onSpawnShinyBoost(player, pokemon);
        if (!booster.isChance()) {
            return;
        }

        EntityPixelmon spawn = this.getRandomPokemon(player, booster.getBlacklist(), booster.isAllowLegendary());

        this.onSpawnHABoost(player, spawn);
        this.onSpawnShinyBoost(player, spawn);


    }

    public void onSpawnHABoost(EntityPlayerMP player, EntityPixelmon pokemon) {

        PokemonHABooster booster = (PokemonHABooster) RachamonPixelmonBoosterManager
                .getBoosters()
                .get(BoosterType.HIDDEN_ABILITY);

        if (!booster.isGloballyActive() && booster
                .getPlayers()
                .stream()
                .noneMatch(p -> p.getUuid().equals(player.getUniqueID()))) {
            return;
        }

        boolean isChance = booster.isChance();

        if (!isChance) {
            return;
        }

        if (pokemon.getBaseStats().getAllAbilities().size() != 3) {
            return;
        }

        pokemon.getPokemonData().setAbility(pokemon.getBaseStats().getAllAbilities().get(2));
        pokemon.update(EnumUpdateType.Ability);
    }

    public void onSpawnShinyBoost(EntityPlayerMP player, EntityPixelmon pokemon) {

        PokemonShinyBooster booster = (PokemonShinyBooster) RachamonPixelmonBoosterManager
                .getBoosters()
                .get(BoosterType.SHINY_RATE);

        if (!booster.isGloballyActive() && booster
                .getPlayers()
                .stream()
                .noneMatch(p -> p.getUuid().equals(player.getUniqueID()))) {
            return;
        }

        boolean isChance = booster.isChance();

        if (!isChance) {
            return;
        }

        pokemon.getPokemonData().setShiny(true);
    }

    @Nullable
    public EntityPixelmon getRandomPokemon(EntityPlayerMP player, List<String> blacklist, boolean isAllowLegendary) {

        Biome playerBiome = player.getEntityWorld().getBiome(player.getPosition());
        List<String> pokemonNames = new ArrayList<>();

        if (!PixelmonSpawning.coordinator.getActive()) {
            return null;
        }

        AbstractSpawner spawner = PixelmonSpawning.coordinator.getSpawner(player.getName());

        if (spawner == null) {
            return null;
        }

        SpatialData data = spawner.calculateSpatialData(player.getServerWorld(), player.getPosition(), 6, true, (s) -> true);
        SpawnLocation spawnLocation = new SpawnLocation(player, new MutableLocation(player.getServerWorld(), player.getPosition()), Sets.newHashSet(new LocationType[]{LocationType.GRASS}), data.baseBlock, data.uniqueSurroundingBlocks, player
                .getServerWorld()
                .getBiomeForCoordsBody(player.getPosition()), BetterSpawnerConfig.doesBlockSeeSky(player
                .getServerWorld()
                .getBlockState(player.getPosition())), 6, 1);

        ArrayList<SpawnInfo> spawns = spawner.getSuitableSpawns(spawnLocation);
        Map<SpawnLocation, List<SpawnInfo>> possibleSpawns = new HashMap<>();
        possibleSpawns.put(spawnLocation, spawns);
        Set<String> pokemons = spawner.selectionAlgorithm.getPercentages(spawner, possibleSpawns).keySet();

        List<String> filtered = pokemons.stream().filter(blacklist::contains).collect(Collectors.toList());

        if (!isAllowLegendary) {
            filtered = pokemons
                    .stream()
                    .filter(p -> Arrays
                            .stream(EnumSpecies.LEGENDARY_ENUMS)
                            .noneMatch(s -> s.getPokemonName().equalsIgnoreCase(p)))
                    .collect(Collectors.toList());
        }

        if (filtered.size() == 0) {
            return null;
        }
        return PokemonSpec.from(filtered.get(new Random().nextInt(filtered.size()))).create(player.getEntityWorld());
    }


    public int getRandomCoordinate(int coordinate) {
        int radius = RachamonPixelmonBooster.getInstance().getConfig().getGeneralConfig().getSpawnRadius();
        return coordinate + (new Random().nextInt(100) <= 49 ? new Random().nextInt(radius) : -new Random().nextInt(radius));

    }

}
