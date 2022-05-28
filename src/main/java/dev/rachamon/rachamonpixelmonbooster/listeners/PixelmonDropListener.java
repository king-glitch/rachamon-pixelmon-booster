package dev.rachamon.rachamonpixelmonbooster.listeners;

import com.google.common.collect.ImmutableList;
import com.pixelmonmod.pixelmon.api.events.DropEvent;
import com.pixelmonmod.pixelmon.entities.npcs.registry.DropItemRegistry;
import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import com.pixelmonmod.pixelmon.entities.pixelmon.drops.DroppedItem;
import dev.rachamon.rachamonpixelmonbooster.managers.RachamonPixelmonBoosterManager;
import dev.rachamon.rachamonpixelmonbooster.stuctures.BoosterType;
import dev.rachamon.rachamonpixelmonbooster.stuctures.boosters.PokemonDropBooster;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.api.entity.living.player.Player;

import java.util.ArrayList;
import java.util.Random;

/**
 * The type Pixelmon drop listener.
 */
public class PixelmonDropListener {
    /**
     * On pixelmon drop.
     *
     * @param event the event
     */
    @SubscribeEvent
    public void onPixelmonDrop(DropEvent event) {
        Player player = (Player) event.player;
        PokemonDropBooster booster = (PokemonDropBooster) RachamonPixelmonBoosterManager
                .getBoosters()
                .get(BoosterType.DROP);

        if (!booster.isGloballyActive() && booster
                .getPlayers()
                .stream()
                .noneMatch(p -> p.getUuid().equals(player.getUniqueId()))) {
            return;
        }

        EntityPixelmon dropper = (EntityPixelmon) event.entity;
        ArrayList<ItemStack> items = DropItemRegistry.getDropsForPokemon(dropper);

        try {
            ImmutableList<DroppedItem> drops = event.getDrops();
            int difference = (booster.calculate(drops.size())) - drops.size();
            for (int i = 0; i < difference; i++) {
                ItemStack stack = items.get(new Random().nextInt(items.size()));
                Item dropItem = Item.getByNameOrId(String.valueOf(stack.getItem().getRegistryName()));

                if (dropItem == null) {
                    continue;
                }

                event.addDrop(new ItemStack(dropItem));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
