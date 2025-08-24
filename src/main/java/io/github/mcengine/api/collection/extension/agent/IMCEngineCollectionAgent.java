package io.github.mcengine.api.collection.extension.agent;

import org.bukkit.plugin.Plugin;

/**
 * Represents a Collection-based Agent module that can be dynamically loaded into the MCEngine.
 * <p>
 * Implement this interface to integrate collection-oriented agents into the system.
 */
public interface IMCEngineCollectionAgent {

    /**
     * Called when the Collection Agent is loaded by the engine.
     *
     * @param plugin The plugin instance providing context.
     */
    void onLoad(Plugin plugin);

    /**
     * Called when the Collection Agent is unloaded or disabled by the engine.
     *
     * @param plugin The plugin instance providing context.
     */
    void onDisload(Plugin plugin);

    /**
     * Sets a unique ID for this Collection Agent instance.
     *
     * @param id The unique ID assigned by the engine.
     */
    void setId(String id);
}
