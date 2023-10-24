package net.isksss.mc.paperslot.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChestDao extends Base {
    private static final String INSERT_SQL = "INSERT INTO chest (x, y, z, world_name, bet) VALUES (?, ?, ?, ?, ?)";
    private static final String SEARCH_SQL = "SELECT * FROM chest WHERE chest_id = ?";
    private static final String DELETE_SQL = "DELETE FROM chest WHERE chest_id = ?";

    public void insert(Chest c) {
        try (Connection conn = DriverManager.getConnection(super.getPath());
             PreparedStatement stmt = conn.prepareStatement(INSERT_SQL)) {
            stmt.setInt(1, c.getX());
            stmt.setInt(2, c.getY());
            stmt.setInt(3, c.getZ());
            stmt.setString(4, c.getWorldName());
            stmt.setInt(5, c.getBet());
        } catch (SQLException e) {
            getPlugin().getLogger().info("Error inserting chest: " + e.getMessage());
        }
    }

    public List<Chest> search(Chest c) {
        List<Chest> result = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(super.getPath());
             PreparedStatement stmt = conn.prepareStatement(SEARCH_SQL)) {
            stmt.setInt(1, c.getChestId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Chest chest = new Chest();
                chest.setChestId(rs.getInt("chest_id"));
                chest.setX(rs.getInt("x"));
                chest.setY(rs.getInt("y"));
                chest.setZ(rs.getInt("z"));
                chest.setWorldName(rs.getString("world_name"));
                chest.setBet(rs.getInt("bet"));
                result.add(chest);
            }
        } catch (SQLException e) {
            getPlugin().getLogger().info("Error searching chest: " + e.getMessage());
        }
        return result;
    }

    public List<Chest> searchByLoc(Chest c) {
        List<Chest> result = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(super.getPath());
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM chest WHERE x = ? AND y = ? AND z = ? AND world_name = ?")) {
                stmt.setInt(1, c.getX());
                stmt.setInt(2, c.getY());
                stmt.setInt(3, c.getZ());
                stmt.setString(4, c.getWorldName());
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Chest chest = new Chest();
                    chest.setChestId(rs.getInt("chest_id"));
                    chest.setX(rs.getInt("x"));
                    chest.setY(rs.getInt("y"));
                    chest.setZ(rs.getInt("z"));
                    chest.setWorldName(rs.getString("world_name"));
                    chest.setBet(rs.getInt("bet"));
                    result.add(chest);
                }
        } catch (SQLException e) {
            getPlugin().getLogger().info("Error searching chest: " + e.getMessage());
        }
        return result;
    }

    public List<Chest> getAllChest() {
    List<Chest> result = new ArrayList<>();
    try (Connection conn = DriverManager.getConnection(getPath());
         PreparedStatement stmt = conn.prepareStatement("SELECT * FROM chest")) {
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Chest chest = new Chest();
            chest.setChestId(rs.getInt("chest_id"));
            chest.setX(rs.getInt("x"));
            chest.setY(rs.getInt("y"));
            chest.setZ(rs.getInt("z"));
            chest.setWorldName(rs.getString("world_name"));
            chest.setBet(rs.getInt("bet"));
            result.add(chest);
        }
    } catch (SQLException e) {
        super.getPlugin().getLogger().info("Error getting all chests: " + e.getMessage());
    }
    return result;
}

    public void delete(Chest c) {
        try (Connection conn = DriverManager.getConnection(getPath());
             PreparedStatement stmt = conn.prepareStatement(DELETE_SQL)) {
            stmt.setInt(1, c.getChestId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            getPlugin().getLogger().info("Error deleting chest: " + e.getMessage());
        }
    }
}