package io.github.mcengine.api.collection;

import io.github.mcengine.api.collection.database.IMCEngineCollectionDB;
import io.github.mcengine.api.collection.database.mysql.MCEngineCollectionMySQL;
import io.github.mcengine.api.collection.database.postgresql.MCEngineCollectionPostgreSQL;
import io.github.mcengine.api.collection.database.sqlite.MCEngineCollectionSQLite;

import java.sql.Connection;

import org.bukkit.plugin.Plugin;

/**
 * Public entrypoint for the MCEngineCollection API.
 * <p>
 * Manages the database backend (SQLite/MySQL/PostgreSQL/MongoDB) used by the Collection system
 * and exposes a singleton instance for convenient access across the plugin.
 */
public class MCEngineCollectionApi {

    /**
     * Singleton instance of the Collection API.
     */
    private static MCEngineCollectionApi instance;

    /**
     * Database handler instance for storing and retrieving collection data.
     */
    private final IMCEngineCollectionDB db;

    /**
     * The Bukkit plugin instance associated with this Collection API.
     */
    private final Plugin plugin;

    /**
     * Constructs the Collection API instance and initializes the appropriate database connection.
     *
     * @param plugin The Bukkit plugin instance.
     * @throws IllegalArgumentException if the configured database type is unsupported.
     */
    public MCEngineCollectionApi(Plugin plugin) {
        instance = this;
        this.plugin = plugin;

        // Initialize database based on type
        String dbType = plugin.getConfig().getString("database.type", "sqlite").toLowerCase();
        switch (dbType) {
            case "sqlite" -> this.db = new MCEngineCollectionSQLite(plugin);
            case "mysql" -> this.db = new MCEngineCollectionMySQL(plugin);
            case "postgresql" -> this.db = new MCEngineCollectionPostgreSQL(plugin);
            default -> throw new IllegalArgumentException("Unsupported database type: " + dbType);
        }
    }

    /**
     * Gets the global API singleton instance.
     *
     * @return The {@link MCEngineCollectionApi} instance, or {@code null} if not yet constructed.
     */
    public static MCEngineCollectionApi getApi() {
        return instance;
    }

    /**
     * Gets the Bukkit plugin instance linked to this API.
     *
     * @return The plugin instance.
     */
    public Plugin getPlugin() {
        return plugin;
    }

    /**
     * Returns the database handler implementation.
     *
     * @return The database API implementation.
     */
    public IMCEngineCollectionDB getDB() {
        return db;
    }

    /**
     * Retrieves the active database connection used by the Collection plugin.
     * <p>
     * This delegates to the underlying database implementation such as SQLite.
     * Useful for executing custom SQL queries or diagnostics externally.
     *
     * @return The {@link Connection} instance for the configured database, or {@code null} if unavailable.
     */
    public Connection getDBConnection() {
        return db.getDBConnection();
    }
}
