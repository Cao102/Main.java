package DAO;

import Model.Subject;
import util.DatabaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO {

    public void addSubjectDAO(Subject subject) {
        String sql = "INSERT INTO studentmanagementsystem.subjects (subject_id, name, description) VALUES (?, ?, ?)";
        try {
            Connection cn = DatabaseConnect.getConnection();
            PreparedStatement pr = cn.prepareStatement(sql);
            pr.setString(1, subject.getSubjectId());
            pr.setString(2, subject.getName());
            pr.setString(3, subject.getDescription());

            pr.executeUpdate();
            System.out.println("Thêm môn học thành công");
        } catch (Exception e) {
            System.out.println("Lỗi khi thêm môn học: " + e.getMessage());
        }
    }

    public void updateSubjectDAO(Subject subject) {
        String sql = "UPDATE studentmanagementsystem.subjects SET name = ?, description = ? WHERE subject_id = ?";
        try {
            Connection cn = DatabaseConnect.getConnection();
            PreparedStatement pr = cn.prepareStatement(sql);
            pr.setString(1, subject.getName());
            pr.setString(2, subject.getDescription());
            pr.setString(3, subject.getSubjectId());

            int check = pr.executeUpdate();
            if (check > 0) {
                System.out.println("Cập nhật môn học thành công");
            } else {
                System.out.println("Không tìm thấy môn học cần cập nhật");
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật môn học: " + e.getMessage());
        }
    }

    public void deleteSubjectDAO(String subjectId) {
        String sql = "DELETE FROM studentmanagementsystem.subjects WHERE subject_id = ?";
        try {
            Connection cn = DatabaseConnect.getConnection();
            PreparedStatement pr = cn.prepareStatement(sql);
            pr.setString(1, subjectId);

            int check = pr.executeUpdate();
            if (check > 0) {
                System.out.println("Xóa môn học thành công");
            } else {
                System.out.println("Không tìm thấy môn học cần xóa");
            }
        } catch (SQLException e) {
            if (e.getMessage().contains("foreign key constraint")) {
                System.out.println("Không thể xóa môn học này vì đang được sử dụng trong hệ thống");
            } else {
                System.out.println("Lỗi khi xóa môn học: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi xóa môn học: " + e.getMessage());
        }
    }

    public Subject getSubjectByIdDAO(String subjectId) {
        String sql = "SELECT * FROM studentmanagementsystem.subjects WHERE subject_id = ?";
        try {
            Connection cn = DatabaseConnect.getConnection();
            PreparedStatement pr = cn.prepareStatement(sql);
            pr.setString(1, subjectId);

            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                String id = rs.getString("subject_id");
                String name = rs.getString("name");
                String description = rs.getString("description");

                return new Subject(id, name, description);
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi tìm môn học: " + e.getMessage());
        }
        return null;
    }

    public List<Subject> getAllSubjectsDAO() {
        String sql = "SELECT * FROM studentmanagementsystem.subjects";
        List<Subject> subjectList = new ArrayList<>();

        try {
            Connection cn = DatabaseConnect.getConnection();
            PreparedStatement pr = cn.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                String id = rs.getString("subject_id");
                String name = rs.getString("name");
                String description = rs.getString("description");

                subjectList.add(new Subject(id, name, description));
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi lấy danh sách môn học: " + e.getMessage());
        }

        return subjectList;
    }

    public List<Subject> getSubjectsByTeacherDAO(String teacherId) {
        String sql = "SELECT DISTINCT s.subject_id, s.name FROM studentmanagementsystem.subjects s " +
                "INNER JOIN studentmanagementsystem.schedules sc ON s.subject_id = sc.subject_id " +
                "WHERE sc.teacher_id = ?";
        List<Subject> subjectList = new ArrayList<>();

        try {
            Connection cn = DatabaseConnect.getConnection();
            PreparedStatement pr = cn.prepareStatement(sql);
            pr.setString(1, teacherId);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                String id = rs.getString("subject_id");
                String name = rs.getString("name");

                subjectList.add(new Subject(id, name));
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi lấy danh sách môn học theo giảng viên: " + e.getMessage());
        }

        return subjectList;
    }
}