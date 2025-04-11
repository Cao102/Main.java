package DAO;

import Model.Dormitory;
import Model.StudentDormitory;
import util.DatabaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DormitoryDAO {

    public void assignDormitoryDAO(String studentId, String dormId) {
        // Kiểm tra xem sinh viên đã có phòng chưa
        if (getDormitoryByStudentDAO(studentId) != null) {
            System.out.println("Sinh viên đã được gán phòng trước đó. Vui lòng hủy phòng cũ trước.");
            return;
        }

        // Kiểm tra xem phòng còn chỗ trống không
        String checkRoomSQL = "SELECT d.dorm_id, d.capacity, COUNT(sd.student_id) as occupied " +
                "FROM studentmanagementsystem.dormitories d " +
                "LEFT JOIN studentmanagementsystem.studentdormitory sd ON d.dorm_id = sd.dorm_id " +
                "WHERE d.dorm_id = ? " +
                "GROUP BY d.dorm_id, d.capacity";

        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement pr = cn.prepareStatement(checkRoomSQL)) {
            pr.setString(1, dormId);
            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                int capacity = rs.getInt("capacity");
                int occupied = rs.getInt("occupied");

                if (occupied >= capacity) {
                    System.out.println("Phòng đã đầy. Không thể gán thêm sinh viên vào phòng này.");
                    return;
                }
            } else {
                System.out.println("Không tìm thấy phòng với mã " + dormId);
                return;
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi kiểm tra phòng: " + e.getMessage());
            return;
        }

        // Gán phòng cho sinh viên
        String sql = "INSERT INTO studentmanagementsystem.studentdormitory (student_id, dorm_id) VALUES (?, ?)";
        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement pr = cn.prepareStatement(sql)) {
            pr.setString(1, studentId);
            pr.setString(2, dormId);

            int rowsAffected = pr.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Gán phòng thành công cho sinh viên " + studentId);
            } else {
                System.out.println("Không thể gán phòng. Vui lòng kiểm tra lại thông tin.");
            }
        } catch (SQLException e) {
            if (e.getMessage().contains("foreign key constraint")) {
                System.out.println("Không tìm thấy sinh viên hoặc phòng. Vui lòng kiểm tra lại mã sinh viên và mã phòng.");
            } else {
                System.out.println("Lỗi khi gán phòng: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi gán phòng: " + e.getMessage());
        }
    }

    public void removeDormitoryDAO(String studentId) {
        String sql = "DELETE FROM studentmanagementsystem.studentdormitory WHERE student_id = ?";

        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement pr = cn.prepareStatement(sql)) {
            pr.setString(1, studentId);

            int rowsAffected = pr.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Đã hủy phòng ký túc xá cho sinh viên " + studentId);
            } else {
                System.out.println("Sinh viên " + studentId + " không có phòng ký túc xá nào được gán");
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi hủy phòng: " + e.getMessage());
        }
    }

    public StudentDormitory getDormitoryByStudentDAO(String studentId) {
        String sql = "SELECT sd.student_id, sd.dorm_id, s.name AS student_name, d.room_number " +
                "FROM studentmanagementsystem.studentdormitory sd " +
                "JOIN studentmanagementsystem.students s ON sd.student_id = s.student_id " +
                "JOIN studentmanagementsystem.dormitories d ON sd.dorm_id = d.dorm_id " +
                "WHERE sd.student_id = ?";

        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement pr = cn.prepareStatement(sql)) {
            pr.setString(1, studentId);
            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                String dorm_id = rs.getString("dorm_id");
                String student_name = rs.getString("student_name");
                String room_number = rs.getString("room_number");

                return new StudentDormitory(studentId, dorm_id, student_name, room_number);
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi tìm thông tin phòng cho sinh viên: " + e.getMessage());
        }

        return null;
    }

    public List<Dormitory> getAvailableDormsDAO() {
        String sql = "SELECT d.dorm_id, d.room_number, d.capacity, COUNT(sd.student_id) as occupied " +
                "FROM studentmanagementsystem.dormitories d " +
                "LEFT JOIN studentmanagementsystem.studentdormitory sd ON d.dorm_id = sd.dorm_id " +
                "GROUP BY d.dorm_id, d.room_number, d.capacity " +
                "HAVING COUNT(sd.student_id) < d.capacity";

        List<Dormitory> availableDorms = new ArrayList<>();

        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement pr = cn.prepareStatement(sql);
             ResultSet rs = pr.executeQuery()) {

            while (rs.next()) {
                String dorm_id = rs.getString("dorm_id");
                String room_number = rs.getString("room_number");
                int capacity = rs.getInt("capacity");
                int occupied = rs.getInt("occupied");

                availableDorms.add(new Dormitory(dorm_id, room_number, capacity, occupied));
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi lấy danh sách phòng trống: " + e.getMessage());
        }

        return availableDorms;
    }

    public List<Dormitory> getAllDormsDAO() {
        String sql = "SELECT d.dorm_id, d.room_number, d.capacity, COUNT(sd.student_id) as occupied " +
                "FROM studentmanagementsystem.dormitories d " +
                "LEFT JOIN studentmanagementsystem.studentdormitory sd ON d.dorm_id = sd.dorm_id " +
                "GROUP BY d.dorm_id, d.room_number, d.capacity";

        List<Dormitory> dormList = new ArrayList<>();

        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement pr = cn.prepareStatement(sql);
             ResultSet rs = pr.executeQuery()) {

            while (rs.next()) {
                String dorm_id = rs.getString("dorm_id");
                String room_number = rs.getString("room_number");
                int capacity = rs.getInt("capacity");
                int occupied = rs.getInt("occupied");

                dormList.add(new Dormitory(dorm_id, room_number, capacity, occupied));
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi lấy danh sách tất cả phòng: " + e.getMessage());
        }

        return dormList;
    }

    // Lấy danh sách sinh viên trong một phòng
    public List<StudentDormitory> getStudentsInDormDAO(String dormId) {
        String sql = "SELECT sd.student_id, sd.dorm_id, s.name AS student_name, d.room_number " +
                "FROM studentmanagementsystem.studentdormitory sd " +
                "JOIN studentmanagementsystem.students s ON sd.student_id = s.student_id " +
                "JOIN studentmanagementsystem.dormitories d ON sd.dorm_id = d.dorm_id " +
                "WHERE sd.dorm_id = ?";

        List<StudentDormitory> studentList = new ArrayList<>();

        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement pr = cn.prepareStatement(sql)) {
            pr.setString(1, dormId);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                String student_id = rs.getString("student_id");
                String student_name = rs.getString("student_name");
                String room_number = rs.getString("room_number");

                studentList.add(new StudentDormitory(student_id, dormId, student_name, room_number));
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi lấy danh sách sinh viên trong phòng: " + e.getMessage());
        }

        return studentList;
    }
}
