package DAO;

import Model.Tuition;
import connectDatabase.DatabaseConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TuitionDAO {

    // Thêm học phí cho sinh viên (mặc định status là "Chưa nộp")
    public void add(Tuition tuition) {
        String sql = "INSERT INTO Tuition (student_id, amount, status) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(true);
            statement.setString(1, tuition.getStudentId());
            statement.setDouble(2, tuition.getAmount());
            statement.setString(3, tuition.getStatus());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi thêm học phí: " + e.getMessage());
        }
    }

    // Cập nhật số tiền học phí của sinh viên
    public void update(Tuition tuition) {
        String sql = "UPDATE Tuition SET amount = ? WHERE student_id = ? AND status = 'Chưa nộp'";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, tuition.getAmount());
            statement.setString(2, tuition.getStudentId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("Không có học phí với trạng thái 'Chưa nộp' để cập nhật.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi cập nhật học phí: " + e.getMessage());
        }
    }
    // Cập nhật trạng thái học phí
    public void updateStatus(String studentId, String status) {
        String sql = "UPDATE Tuition SET status = ? WHERE student_id = ?";
        try (Connection conn = DatabaseConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setString(2, studentId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi cập nhật trạng thái học phí: " + e.getMessage());
        }
    }

    // Tìm kiếm học phí theo student_id
    public Tuition searchByStudentId(String studentId) {
        String sql = "SELECT * FROM Tuition WHERE student_id = ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                double amount = resultSet.getDouble("amount");
                String status = resultSet.getString("status");
                return new Tuition(studentId, amount, status);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi truy xuất học phí: " + e.getMessage());
        }
        return null;
    }

    // Kiểm tra sinh viên có tồn tại không
    public boolean isStudentExist(String studentId) {
        String sql = "SELECT COUNT(*) FROM Students WHERE student_id = ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next() && resultSet.getInt(1) > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi kiểm tra sinh viên: " + e.getMessage());
        }
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
                String status = resultSet.getString("status");
                tuitionList.add(new Tuition(studentId, amount, status));
            }
            return tuitionList;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi hiển thị thông tin học phí: " + e.getMessage());
        }
    }
}
