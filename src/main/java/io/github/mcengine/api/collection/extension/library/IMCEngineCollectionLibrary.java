package io.github.mcengine.api.collection.extension.library;

import org.bukkit.plugin.Plugin;

/**
 * Represents a Collection-based Library module that can be dynamically loaded into the MCEngine.
 * <p>
 * Implement this interface to provide shared services and utilities for collection features.
 */
public interface IMCEngineCollectionLibrary {

    /**
     * Called when the Collection Library is loaded by the engine.
     *
     * @param plugin The plugin instance providing context.
     */
    void onLoad(Plugin plugin);

    /**
     * Called when the Collection Library is unloaded or disabled by the engine.
     *
     * @param plugin The plugin instance providing context.
     */
    void onDisload(Plugin plugin);

    /**
     * Sets a unique ID for this Collection Library instance.
     *
     * @param id The unique ID assigned by the engine.
     */
    void setId(String id);
}
