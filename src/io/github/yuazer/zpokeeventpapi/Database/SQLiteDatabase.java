package io.github.yuazer.zpokeeventpapi.Database;

import io.github.yuazer.zpokeeventpapi.Utils.YamlUtils;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.*;

public class SQLiteDatabase {
    private Connection connection;

    public SQLiteDatabase(JavaPlugin plugin) {
        try {
            Class.forName("org.sqlite.JDBC");
            String path = "jdbc:sqlite:" + YamlUtils.getConfigMessage("DataMode.SQLitePath");
//            connection = DriverManager.getConnection("jdbc:sqlite:" + plugin.getDataFolder().getAbsolutePath() + "/" + dbname + ".db");
            connection = DriverManager.getConnection(path.replace("%pluginPath%", plugin.getDataFolder().getAbsolutePath()).replace("%pluginName%", plugin.getName()));
            createTableIfNotExists();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTableIfNotExists() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS myHashMap (" +
                "firstString TEXT, " +
                "secondString TEXT, " +
                "integerValue INTEGER)";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public void saveEntry(String firstString, String secondString, Integer integerValue) throws SQLException {
        if (entryExists(firstString, secondString)) {
            updateEntry(firstString, secondString, integerValue);
        } else {
            insertEntry(firstString, secondString, integerValue);
        }
    }

    private boolean entryExists(String firstString, String secondString) throws SQLException {
        String sql = "SELECT COUNT(*) AS count FROM myHashMap WHERE firstString = ? AND secondString = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, firstString);
            statement.setString(2, secondString);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.getInt("count") > 0;
            }
        }
    }

    private void updateEntry(String firstString, String secondString, Integer integerValue) throws SQLException {
        String sql = "UPDATE myHashMap SET integerValue = ? WHERE firstString = ? AND secondString = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, integerValue);
            statement.setString(2, firstString);
            statement.setString(3, secondString);
            statement.executeUpdate();
        }
    }

    private void insertEntry(String firstString, String secondString, Integer integerValue) throws SQLException {
        String sql = "INSERT INTO myHashMap (firstString, secondString, integerValue) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, firstString);
            statement.setString(2, secondString);
            statement.setInt(3, integerValue);
            statement.executeUpdate();
        }
    }


    public Integer getEntry(String firstString, String secondString) throws SQLException {
        String sql = "SELECT integerValue FROM myHashMap WHERE firstString = ? AND secondString = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, firstString);
            statement.setString(2, secondString);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("integerValue");
                }
            }
        }
        return null;
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

