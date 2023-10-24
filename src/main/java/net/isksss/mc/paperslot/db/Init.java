package net.isksss.mc.paperslot.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Init extends Base {
    private static final String CREATE_TABLE_CHEST_SQL = "CREATE TABLE IF NOT EXISTS chest ("
        + "chest_id INTEGER PRIMARY KEY AUTOINCREMENT,"
        + "x INTEGER NOT NULL,"
        + "y INTEGER NOT NULL,"
        + "z INTEGER NOT NULL,"
        + "world_name TEXT NOT NULL,"
        + "bet INTEGER NOT NULL DEFAULT 1"
        + ");";

    public void init() {
        try (Connection conn = DriverManager.getConnection(getPath());
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(CREATE_TABLE_CHEST_SQL);
            getPlugin().getLogger().info("Table 'chest' created successfully.");
        } catch (SQLException e) {
            getPlugin().getLogger().info(e.getMessage());
        }
    }
}