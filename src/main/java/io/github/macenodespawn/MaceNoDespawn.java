package io.github.macenodespawn;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

/** Protects dropped maces from natural loss, except falling into the void. */
public final class MaceNoDespawn extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    private static boolean isDroppedMace(org.bukkit.entity.Entity entity) {
        return entity instanceof Item item
                && item.getItemStack().getType() == Material.MACE;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onItemDespawn(ItemDespawnEvent event) {
        if (isDroppedMace(event.getEntity())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onItemDamage(EntityDamageEvent event) {
        if (event.getCause() != EntityDamageEvent.DamageCause.VOID
                && isDroppedMace(event.getEntity())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onItemCombust(EntityCombustEvent event) {
        if (isDroppedMace(event.getEntity())) {
            event.setCancelled(true);
        }
    }
}
