package io.github.mcengine.api.collection.extension.dlc;

import org.bukkit.plugin.Plugin;

/**
 * Represents a Collection-based DLC module that can be dynamically loaded into the MCEngine.
 * <p>
 * Implement this interface to integrate downloadable content focused on collection mechanics.
 */
public interface IMCEngineCollectionDLC {

    /**
     * Called when the Collection DLC is loaded by the engine.
     *
     * @param plugin The plugin instance providing context.
     */
    void onLoad(Plugin plugin);

    /**
     * Called when the Collection DLC is unloaded or disabled by the engine.
     *
     * @param plugin The plugin instance providing context.
     */
    void onDisload(Plugin plugin);

    /**
     * Sets a unique ID for this Collection DLC instance.
     *
     * @param id The unique ID assigned by the engine.
     */
    void setId(String id);
}
