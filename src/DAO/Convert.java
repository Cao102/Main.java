package DAO;

import util.DatabaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Convert {
    public String getNameById(String tableName, String idColumn, String nameColumn, int id) {
        String sql = "SELECT " + nameColumn + " FROM " + tableName + " WHERE " + idColumn + " = ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString(nameColumn);
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi convert" + e.getMessage());
        }
    }

    public int getIdByName(String tableName, String idColumn, String nameColumn, String name) {
        String sql = "SELECT " + idColumn + " FROM " + tableName + " WHERE " + nameColumn + " = ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(idColumn);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi truy vấn: " + e.getMessage());
        }
        return -1;
    }
}
