package DAO;

import Model.Tuition;
import util.DatabaseConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TuitionDAO implements DAO<Tuition> {

    // Kiểm tra sự tồn tại của student_id trong bảng students
    // Kiểm tra xem sinh viên đã tồn tại hay chưa
    public boolean isStudentExist(int studentId) {
        String sql = "SELECT COUNT(*) FROM students WHERE student_id = ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next() && resultSet.getInt(1) > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi kiểm tra sinh viên: " + e.getMessage());
        }
    }


    // Thêm học phí cho sinh viên
    public void add(Tuition tuition) {
        // Kiểm tra nếu student_id tồn tại trong bảng students
        if (!isStudentExist(tuition.getStudentId())) {
            throw new RuntimeException("Sinh viên không tồn tại trong hệ thống.");
        }

        // Kiểm tra nếu sinh viên đã có học phí, nếu có thì cập nhật học phí
        Tuition existingTuition = searchByStudentId(tuition.getStudentId());
        if (existingTuition != null) {
            update(tuition);  // Gọi phương thức cập nhật học phí nếu sinh viên đã có
            System.out.println("Học phí của sinh viên đã được cập nhật.");
        } else {
            // Nếu sinh viên chưa có học phí, thực hiện thêm học phí mới
            String sql = "INSERT INTO Tuition (student_id, amount) VALUES (?, ?)";
            try (Connection connection = DatabaseConnect.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, tuition.getStudentId());
                statement.setDouble(2, tuition.getAmount());
                statement.executeUpdate();
                System.out.println("Học phí đã được thêm thành công!");
            } catch (SQLException e) {
                throw new RuntimeException("Lỗi thêm học phí: " + e.getMessage());
            }
        }
    }

    // Lấy danh sách tất cả học phí
    public List<Tuition> getAll() {
        List<Tuition> tuitionList = new ArrayList<>();
        String sql = "SELECT * FROM Tuition";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int studentId = resultSet.getInt("student_id");
                double amount = resultSet.getDouble("amount");
                tuitionList.add(new Tuition(studentId, amount));
            }
            return tuitionList;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi hiển thị thông tin học phí: " + e.getMessage());
        }
    }

    // Cập nhật học phí của sinh viên
    public void update(Tuition tuition) {
        Tuition existingTuition = searchByStudentId(tuition.getStudentId());
        if (existingTuition == null) {
            throw new RuntimeException("Sinh viên chưa có học phí trong hệ thống, không thể cập nhật.");
        }
        String sql = "UPDATE Tuition SET amount = ? WHERE student_id = ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, tuition.getAmount());
            statement.setInt(2, tuition.getStudentId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi cập nhật học phí: " + e.getMessage());
        }
    }

    // Xóa học phí của sinh viên
    public void delete(int studentId) {
        Tuition existingTuition = searchByStudentId(studentId);
        if (existingTuition == null) {
            throw new RuntimeException("Sinh viên chưa có học phí trong hệ thống, không thể xóa.");
        }
        String sql = "DELETE FROM Tuition WHERE student_id = ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi xóa học phí: " + e.getMessage());
        }
    }

    // Tìm kiếm học phí của sinh viên theo student_id
    public Tuition searchByStudentId(int studentId) {
        String sql = "SELECT * FROM Tuition WHERE student_id = ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
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

}
