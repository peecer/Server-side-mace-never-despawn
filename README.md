# MaceNoDespawn 1.1.0

Small server-side Minecraft plugin for **Spigot/Paper 1.21+**.

## Behavior

While a mace is a dropped item entity:

- Its normal five-minute despawn is cancelled.
- Incoming item damage (including lava, fire, explosions, and cactus) is cancelled, **except VOID damage**.
- Combustion/ignition is cancelled.
- It can still fall into the void and be removed there.

The plugin **never edits or replaces the ItemStack**. Enchantments, exact durability, name, lore, and other metadata remain unchanged. No other materials are affected. Regular player pickup, storage, and game mechanics still work.

**Limitations:** Server commands, other plugins, or direct entity removal mechanisms can still remove an item. Protections operate through standard Bukkit events; no Bukkit plugin can guarantee protection from every external plugin or administrator command. Maces held in inventories are not protected by this plugin.

## Install

1. Use Spigot or Paper Minecraft server 1.21 or newer (Java 21).
2. Replace the previous `MaceNoDespawn-1.0.0.jar` (if installed) with `MaceNoDespawn-1.1.0.jar` in the server's `plugins` folder.
3. Restart the server.

No configuration, commands, or permissions.

## Build from source

With JDK 21 and Maven installed, run `mvn package` to build `target/MaceNoDespawn-1.1.0.jar` against the Spigot API dependency declared in `pom.xml`.
