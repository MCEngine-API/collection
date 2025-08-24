package io.github.mcengine.api.collection.database;

import java.sql.Connection;

/**
 * Interface for database operations in MCEngineCollection.
 * <p>
 * Provides method contracts for managing database connections.
 */
public interface IMCEngineCollectionDB {

    /**
     * Get a connection to the database.
     *
     * @return the SQL {@link Connection}, or {@code null} if not connected.
     */
    Connection getDBConnection();
}
