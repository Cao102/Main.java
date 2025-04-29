package DAO;

import Model.Registration;
import util.DatabaseConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class RegistrationDAO {

    public void addSubject(String student_id, String subject_id){
        String sqlInsert = "INSERT INTO studentmanagementsystem.registrations (student_id, subject_id) VALUES (?, ?)";

        try (Connection cn = DatabaseConnect.getConnection()) {
            try (PreparedStatement ps = cn.prepareStatement(
                    "SELECT student_id FROM studentmanagementsystem.students WHERE student_id = ?")) {
                ps.setString(1, student_id);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new RuntimeException("Không tồn tại sinh viên");
                }
            }
            try (PreparedStatement ps = cn.prepareStatement(
                    "SELECT subject_id FROM studentmanagementsystem.subjects WHERE subject_id = ?")) {
                ps.setString(1, subject_id);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new RuntimeException("Không tồn tại môn học");
                }
            }
            try (PreparedStatement psInsert = cn.prepareStatement(sqlInsert)) {
                psInsert.setString(1, student_id);
                psInsert.setString(2, subject_id);
                psInsert.executeUpdate();
                System.out.println("Đăng ký môn học thành công");
            }

        } catch (Exception e) {
            System.out.println("Sinh viên đã đăng ký môn này rồi");
        }
    }

    public void removeSubject(String student_id, String subject_id){
        String sql = "DELETE FROM studentmanagementsystem.registrations WHERE student_id = ? AND subject_id = ?";

        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, student_id);
            ps.setString(2, subject_id);

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Xóa đăng ký môn học thành công");
            } else {
                System.out.println("Không tìm thấy đăng ký để xóa");
            }

        } catch(Exception e){
            System.out.println("Lỗi khi xóa: " + e.getMessage());
        }
    }

    public List<Registration> getRegisteredSubjects(String student_id){
        String sql = "SELECT s.subject_id, s.name " +
                "FROM studentmanagementsystem.registrations r " +
                "INNER JOIN studentmanagementsystem.subjects s ON r.subject_id = s.subject_id " +
                "WHERE r.student_id = ?";
        List<Registration> subjects = new ArrayList<>();

        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, student_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String sj = rs.getString("subject_id");
                String name = rs.getString("name");
                subjects.add(new Registration(name, sj));
            }

        } catch(Exception e){
            System.out.println("Lỗi khi truy vấn đăng ký môn học: " + e.getMessage());
        }

        return subjects;
    }

    public List<Registration> getRegisteredStudent(String subject_id){
        String sql = "SELECT student_id FROM studentmanagementsystem.registrations WHERE subject_id = ?";
        List<Registration> registrations = new ArrayList<>();

        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, subject_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String st = rs.getString("student_id");
                registrations.add(new Registration(st, subject_id));
            }

        } catch(Exception e){
            System.out.println("Lỗi khi truy vấn danh sách sinh viên đăng ký: " + e.getMessage());
        }

        return registrations;
    }
}
