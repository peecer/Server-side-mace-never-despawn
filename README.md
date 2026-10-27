# MaceNoDespawn v1.1.1

Lightweight server-side plugin for Minecraft **Spigot, Paper, and Leaf 1.21+**.

## Behavior

Only while an item is a **dropped mace**, the plugin:

- Cancels natural item despawning.
- Prevents fire, lava, explosions, cactus, and other event-driven damage.
- Still permits **void damage**, so dropping a mace into the void removes it.
- Makes **no changes** to the dropped mace's item stack; names, enchantments, durability, lore, and custom metadata are retained.
- Does not modify other materials, inventories, or normal player pickups.

Note: This plugin does not attempt to block administrative commands, direct removal by other plugins, or world resets. Those can remove items without a cancellable Bukkit damage/despawn event.

## Install

1. Stop your server.
2. **Delete `MaceNoDespawn-1.1.0.jar`** (and any older copies) from the `plugins` folder.
3. Copy `MaceNoDespawn-1.1.1.jar` from `dist/` into the `plugins` folder.
4. Restart the server, and look for a message starting with `Enabled: dropped maces` after `[MaceNoDespawn] Enabling MaceNoDespawn v1.1.1`.

There are no commands or config files.

## Fixed in v1.1.1

The previous binary was accidentally compiled against an incorrect API stub and linked to a non-existent `PluginManager.registerEvents(Listener, JavaPlugin)` method. Leaf 26.3 therefore disabled it during startup with `NoSuchMethodError`.

The corrected JAR now links to the official Bukkit `PluginManager.registerEvents(Listener, Plugin)` signature, fixing that startup failure. Event behavior and the void exception remain as intended.

## Build from source

With Java 21 and Maven: `mvn -B package`. Maven resolves the official Spigot API with `provided` scope; **no Bukkit classes are bundled** in the plugin JAR. The bundled `dist/` artifact has been locally validated against a minimal API-compatible test harness, including listener registration, event cancellation, and void exception. It has not been run on an actual Leaf server in this environment.
