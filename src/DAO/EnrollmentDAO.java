package DAO;

import Model.Enrollment;
import util.DatabaseConnect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO implements DAO<Enrollment>{
    // Thêm đăng ký mới
    public void add(Enrollment enrollment) {
        String sql = "INSERT INTO enrollments (student_id, course_id) VALUES (?, ?)";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, enrollment.getStudent_id());
            statement.setInt(2, enrollment.getCourse_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm đơn đăng ký: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public List<Enrollment> getAll() {
        List<Enrollment> enrollmentList = new ArrayList<>();
        String sql = """
                SELECT e.enrollment_id, e.student_id, e.course_id
                FROM enrollments e
                JOIN students s ON e.student_id = s.student_id
                JOIN courses c ON e.course_id = c.course_id;
                """;
        try (Connection connection = DatabaseConnect.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("enrollment_id");
                int studentId = resultSet.getInt("student_id");
                int courseId = resultSet.getInt("course_id");
                enrollmentList.add(new Enrollment(id, studentId, courseId));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách đơn đăng ký: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return enrollmentList;
    }
    // Cập nhật thông tin đăng ký

    public void update(Enrollment enrollment) {
        String sql = "UPDATE enrollments SET student_id = ?, course_id = ? WHERE enrollment_id = ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, enrollment.getStudent_id());
            statement.setInt(2, enrollment.getCourse_id());
            statement.setInt(3, enrollment.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Lỗi khi chỉnh sửa đơn: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    // Xóa đơn đăng ký

    public void delete(int id) {
        String sql = "DELETE FROM enrollments WHERE enrollment_id = ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Lỗi khi xoá đơn đăng ký: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
