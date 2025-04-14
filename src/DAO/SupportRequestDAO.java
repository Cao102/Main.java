package DAO;

import Model.SupportRequest;
import connectDatabase.DatabaseConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupportRequestDAO {

    public void addSupportRequest(SupportRequest request) {
        String sql = "INSERT INTO SupportRequests (student_id, message, status) VALUES (?, ?, ?)";
        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement pr = cn.prepareStatement(sql)) {
            pr.setString(1, request.getStudentId());
            pr.setString(2, request.getMessage());
            pr.setString(3, request.getStatus());
            pr.executeUpdate();
            System.out.println("Gửi yêu cầu thành công!");
        } catch (Exception e) {
            System.out.println("Lỗi khi gửi yêu cầu: " + e.getMessage());
        }
    }

    public List<SupportRequest> getAllRequests() {
        List<SupportRequest> list = new ArrayList<>();
        String sql = "SELECT * FROM SupportRequests";
        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement pr = cn.prepareStatement(sql);
             ResultSet rs = pr.executeQuery()) {
            while (rs.next()) {
                list.add(new SupportRequest(
                        rs.getInt("id"),
                        rs.getString("student_id"),
                        rs.getString("message"),
                        rs.getString("status")
                ));
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi lấy yêu cầu: " + e.getMessage());
        }
        return list;
    }

    public void updateRequestMessage(int id, String newMessage) {
        String sql = "UPDATE SupportRequests SET message = ? WHERE id = ?";
        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement pr = cn.prepareStatement(sql)) {
            pr.setString(1, newMessage);
            pr.setInt(2, id);
            int rows = pr.executeUpdate();
            System.out.println(rows > 0 ? "Cập nhật nội dung thành công!" : "Không tìm thấy yêu cầu.");
        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật yêu cầu: " + e.getMessage());
        }
    }

    public void deleteRequest(int id) {
        String sql = "DELETE FROM SupportRequests WHERE id = ?";
        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement pr = cn.prepareStatement(sql)) {
            pr.setInt(1, id);
            int rows = pr.executeUpdate();
            System.out.println(rows > 0 ? "Xóa yêu cầu thành công!" : "Không tìm thấy yêu cầu.");
        } catch (Exception e) {
            System.out.println("Lỗi khi xóa yêu cầu: " + e.getMessage());
        }
    }

    public void updateRequestStatus(int id, String status) {
        String sql = "UPDATE SupportRequests SET status = ? WHERE id = ?";
        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement pr = cn.prepareStatement(sql)) {
            pr.setString(1, status);
            pr.setInt(2, id);
            int rows = pr.executeUpdate();
            System.out.println(rows > 0 ? "Cập nhật trạng thái thành công!" : "Không tìm thấy yêu cầu.");
        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật trạng thái: " + e.getMessage());
        }
    }
}
