package DAO;

import Model.Tuition;
import util.DatabaseConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TuitionDAO {

    // Thêm học phí cho sinh viên (mặc định status là "Chưa nộp")
    public void add(Tuition tuition) {
        String sql = "INSERT INTO Tuition (student_id, amount, tuition_name, status) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(true);
            statement.setString(1, tuition.getStudentId());
            statement.setDouble(2, tuition.getAmount());
            statement.setString(3, tuition.getTuitionName());
            statement.setString(4, tuition.getStatus());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi thêm học phí: " + e.getMessage());
        }
    }

    // Cập nhật số tiền học phí của sinh viên
    public void update(Tuition tuition) {
        String sql = "UPDATE Tuition SET amount = ? WHERE student_id = ? AND tuition_name = ? AND status = 'Chưa nộp'";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, tuition.getAmount());
            statement.setString(2, tuition.getStudentId());
            statement.setString(3, tuition.getTuitionName());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("Không có học phí với trạng thái 'Chưa nộp' để cập nhật.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi cập nhật học phí: " + e.getMessage());
        }
    }

    // Cập nhật trạng thái học phí
    public void updateStatus(String studentId, String tuitionName, String status) {
        String sql = "UPDATE Tuition SET status = ? WHERE student_id = ? AND tuition_name = ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status);
            statement.setString(2, studentId);
            statement.setString(3, tuitionName);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi cập nhật trạng thái học phí: " + e.getMessage());
        }
    }

    // Tìm kiếm học phí theo student_id
    public List<Tuition> searchByStudentId(String studentId) {
        List<Tuition> tuitionList = new ArrayList<>();
        String sql = "SELECT * FROM Tuition WHERE student_id = ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                double amount = resultSet.getDouble("amount");
                String tuitionName = resultSet.getString("tuition_name");
                String status = resultSet.getString("status");
                tuitionList.add(new Tuition(studentId, amount, tuitionName, status));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi truy xuất học phí: " + e.getMessage());
        }
        return tuitionList;
    }

    // Lấy danh sách toàn bộ học phí
    public List<Tuition> getAll() {
        List<Tuition> tuitionList = new ArrayList<>();
        String sql = "SELECT * FROM Tuition";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String studentId = resultSet.getString("student_id");
                double amount = resultSet.getDouble("amount");
                String tuitionName = resultSet.getString("tuition_name");
                String status = resultSet.getString("status");
                tuitionList.add(new Tuition(studentId, amount, tuitionName, status));
            }
            return tuitionList;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi hiển thị thông tin học phí: " + e.getMessage());
        }
    }

    public boolean isValueExist(String value, String tableName, String columnName) {
        String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE " + columnName + " = ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, value);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next() && resultSet.getInt(1) > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi kiểm tra giá trị trong bảng " + tableName + ": " + e.getMessage());
        }
    }

    public Tuition getTuitionByStudentIdAndName(String studentId, String tuitionName) {
        String sql = "SELECT * FROM Tuition WHERE student_id = ? AND tuition_name = ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, studentId);
            statement.setString(2, tuitionName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                double amount = resultSet.getDouble("amount");
                String status = resultSet.getString("status");
                return new Tuition(studentId, amount, tuitionName, status);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi truy vấn học phí: " + e.getMessage());
        }
    }
}
