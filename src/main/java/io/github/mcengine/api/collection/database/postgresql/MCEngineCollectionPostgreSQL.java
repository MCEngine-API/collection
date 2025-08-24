package io.github.mcengine.api.collection.database.postgresql;

import io.github.mcengine.api.collection.database.IMCEngineCollectionDB;

import org.bukkit.plugin.Plugin;
import java.sql.*;

/**
 * PostgreSQL implementation of the MCEngineCollection database connection.
 */
public class MCEngineCollectionPostgreSQL implements IMCEngineCollectionDB {

    /**
     * The Bukkit plugin instance used for configuration and logging.
     */
    private final Plugin plugin;

    /**
     * Persistent PostgreSQL connection (may be {@code null} if connection fails).
     */
    private final Connection conn;

    /**
     * Initializes the PostgreSQL connection using plugin configuration.
     *
     * @param plugin The Bukkit plugin instance.
     */
    public MCEngineCollectionPostgreSQL(Plugin plugin) {
        this.plugin = plugin;

        String host = plugin.getConfig().getString("database.postgresql.host", "localhost");
        String port = plugin.getConfig().getString("database.postgresql.port", "5432");
        String dbName = plugin.getConfig().getString("database.postgresql.name", "mcengine_ai");
        String user = plugin.getConfig().getString("database.postgresql.user", "postgres");
        String pass = plugin.getConfig().getString("database.postgresql.password", "");

        String jdbcUrl = "jdbc:postgresql://" + host + ":" + port + "/" + dbName;

        Connection tempConn = null;
        try {
            tempConn = DriverManager.getConnection(jdbcUrl, user, pass);
        } catch (SQLException e) {
            plugin.getLogger().warning("Failed to connect to PostgreSQL: " + e.getMessage());
            e.printStackTrace();
        }
        this.conn = tempConn;
    }

    /**
     * Returns the current PostgreSQL database connection.
     *
     * @return Active {@link Connection} to the PostgreSQL database, or {@code null} if unavailable.
     */
    @Override
    public Connection getDBConnection() {
        return conn;
    }

    /**
     * Disconnects the PostgreSQL connection.
     */
    public void disConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                plugin.getLogger().info("Disconnected from PostgreSQL.");
            }
        } catch (SQLException e) {
            plugin.getLogger().warning("Error closing PostgreSQL connection: " + e.getMessage());
        }
    }
}
