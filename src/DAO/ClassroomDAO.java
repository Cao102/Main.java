package DAO;

import Model.Classroom;
import connectDatabase.DatabaseConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassroomDAO {
    public void add(Classroom clazz) {
        String sql = "INSERT INTO Classrooms (classroom_id, name, capacity, location) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, clazz.getClassroom_id());
            statement.setString(2, clazz.getName());
            statement.setInt(3, clazz.getCapacity());
            statement.setString(4, clazz.getLocation());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi thêm lớp" + e.getMessage());
        }
    }

    public List<Classroom> getAll() {
        List<Classroom> classroomList = new ArrayList<>();
        String sql = "select * from Classrooms";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String class_id = resultSet.getString("classroom_id");
                String class_name = resultSet.getString("name");
                int capacity = resultSet.getInt("capacity");
                String locatioon = resultSet.getString("location");
                classroomList.add(new Classroom(class_id, class_name, capacity, locatioon));
            }
            return classroomList;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi hiển thị thông tin lớp" + e.getMessage());
        }
    }

    public void update(Classroom clazz) {
        String sql = """
                UPDATE Classrooms
                SET name = ?, capacity = ?, location = ?
                WHERE classroom_id = ?;""";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, clazz.getName());
            statement.setInt(2, clazz.getCapacity());
            statement.setString(3, clazz.getLocation());
            statement.setString(4, clazz.getClassroom_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi sửa lớp học " + e.getMessage());
        }
    }

    public void delete(String classroom_id) {
        String sql = "DELETE FROM Classrooms WHERE classroom_id = ?;";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, classroom_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi xoá lớp" + e.getMessage());
        }
    }

    public List<Classroom> search(String name_column, String attribute) {
        List<Classroom> classroomList = new ArrayList<>();
        String sql = "SELECT * FROM classrooms WHERE " + name_column + " LIKE ?";

        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + attribute + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String classroom_id = resultSet.getString("classroom_id");
                    String name = resultSet.getString("name");
                    int capacity = resultSet.getInt("capacity");
                    String locatioon = resultSet.getString("location");
                    classroomList.add(new Classroom(classroom_id, name, capacity, locatioon));
                }
            }
            return classroomList;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi truy xuất dữ liệu: " + e.getMessage());
        }
    }
}
