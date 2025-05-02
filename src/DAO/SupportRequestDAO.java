package DAO;

import Model.SupportRequest;
import util.DatabaseConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupportRequestDAO {

    public void add(SupportRequest request) {
        String sql = "INSERT INTO SupportRequests (student_id, message, status) VALUES (?, ?, 'Pending')";
        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, request.getStudentId());
            ps.setString(2, request.getMessage());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi thêm yêu cầu hỗ trợ: " + e.getMessage());
        }
    }

    public List<SupportRequest> getAll() {
        List<SupportRequest> list = new ArrayList<>();
        String sql = "SELECT * FROM SupportRequests ORDER BY id";
        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new SupportRequest(
                        rs.getInt("id"),
                        rs.getString("student_id"),
                        rs.getString("message"),
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi lấy danh sách yêu cầu hỗ trợ: " + e.getMessage());
        }
        return list;
    }

    // Thêm phương thức getById để lấy thông tin yêu cầu hiện tại
    public SupportRequest getById(int id) {
        String sql = "SELECT * FROM SupportRequests WHERE id = ?";
        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new SupportRequest(
                        rs.getInt("id"),
                        rs.getString("student_id"),
                        rs.getString("message"),
                        rs.getString("status")
                );
            } else {
                throw new RuntimeException("Không tìm thấy yêu cầu hỗ trợ với ID: " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi lấy thông tin yêu cầu hỗ trợ: " + e.getMessage());
        }
    }

    public void updateMessage(int id, String newMessage) {
        String sql = "UPDATE SupportRequests SET message = ? WHERE id = ?";
        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, newMessage);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi cập nhật yêu cầu hỗ trợ: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM SupportRequests WHERE id = ?";
        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi xóa yêu cầu hỗ trợ: " + e.getMessage());
        }
    }

    public void updateStatus(int id, String status) {
        String sql = "UPDATE SupportRequests SET status = ? WHERE id = ?";
        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi cập nhật trạng thái yêu cầu hỗ trợ: " + e.getMessage());
        }
    }

    public boolean studentExists(String studentId) {
        String sql = "SELECT COUNT(*) FROM students WHERE student_id = ?";
        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, studentId);
            ResultSet rs = ps.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi kiểm tra sinh viên: " + e.getMessage());
        }
    }

    public boolean exists(int id) {
        String sql = "SELECT COUNT(*) FROM SupportRequests WHERE id = ?";
        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi kiểm tra yêu cầu hỗ trợ: " + e.getMessage());
        }
    }
}