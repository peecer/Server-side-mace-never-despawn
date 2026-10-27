package io.github.macenodespawn;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/** Only the void or deliberate pickup should remove a naturally dropped mace. */
public final class MaceNoDespawn extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // PluginManager.registerEvents(Listener, Plugin) is the actual Bukkit/Leaf API.
        // Explicit Plugin cast avoids the wrong JavaPlugin overload used by v1.1.0.
        getServer().getPluginManager().registerEvents(this, (Plugin) this);
        getLogger().info("Enabled: dropped maces will not despawn, burn, or take damage except from the void.");
    }

    private static boolean isDroppedMace(Entity entity) {
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
