package DAO;

import Model.Tuition;
import util.DatabaseConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TuitionDAO implements DAO<Tuition> {

    // Thêm học phí cho sinh viên
    public void add(Tuition tuition) {
        String sql = "INSERT INTO Tuition (student_id, amount) VALUES (?, ?)";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(true);
            statement.setString(1, tuition.getStudentId());
            statement.setDouble(2, tuition.getAmount());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi thêm học phí: " + e.getMessage());
        }
    }

    // Cập nhật học phí của sinh viên
    public void update(Tuition tuition) {
        String sql = "UPDATE Tuition SET amount = ? WHERE student_id = ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, tuition.getAmount());
            statement.setString(2, tuition.getStudentId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi cập nhật học phí: " + e.getMessage());
        }
    }

    // Xóa học phí của sinh viên
    public void delete(String studentId) {
        String sql = "DELETE FROM Tuition WHERE student_id = ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, studentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi xóa học phí: " + e.getMessage());
        }
    }

    // Tìm kiếm học phí của sinh viên theo student_id
    public Tuition searchByStudentId(String studentId) {
        String sql = "SELECT * FROM Tuition WHERE student_id = ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                double amount = resultSet.getDouble("amount");
                return new Tuition(studentId, amount);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi truy xuất học phí: " + e.getMessage());
        }
        return null;
    }

    // Kiểm tra sự tồn tại của student_id trong bảng student
    public boolean isStudentExist(String studentId) {
        String sql = "SELECT COUNT(*) FROM students WHERE student_id = ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next() && resultSet.getInt(1) > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi kiểm tra sinh viên: " + e.getMessage());
        }
    }

    // Lấy tất cả học phí
    public List<Tuition> getAll() {
        List<Tuition> tuitionList = new ArrayList<>();
        String sql = "SELECT * FROM Tuition";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String studentId = resultSet.getString("student_id");
                double amount = resultSet.getDouble("amount");
                tuitionList.add(new Tuition(studentId, amount));
            }
            return tuitionList;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi hiển thị thông tin học phí: " + e.getMessage());
        }
    }
}
