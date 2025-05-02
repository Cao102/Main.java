//package DAO;
//
//import Model.Subject;
//import util.DatabaseConnect;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class SubjectDAO {
//
//    public void add(Subject subject) {
//        String sql = "INSERT INTO Subjects (subject_id, name, description) VALUES (?, ?, ?)";
//        try (Connection cn = DatabaseConnect.getConnection();
//             PreparedStatement ps = cn.prepareStatement(sql)) {
//            ps.setString(1, subject.getSubjectId());
//            ps.setString(2, subject.getName());
//            ps.setString(3, subject.getDescription());
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException("Lỗi thêm môn học: " + e.getMessage());
//        }
//    }
//
//    public void update(Subject subject) {
//        String sql = "UPDATE Subjects SET name = ?, description = ? WHERE subject_id = ?";
//        try (Connection cn = DatabaseConnect.getConnection();
//             PreparedStatement ps = cn.prepareStatement(sql)) {
//            ps.setString(1, subject.getName());
//            ps.setString(2, subject.getDescription());
//            ps.setString(3, subject.getSubjectId());
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException("Lỗi cập nhật môn học: " + e.getMessage());
//        }
//    }
//
//    public void delete(String subjectId) {
//        String sql = "DELETE FROM Subjects WHERE subject_id = ?";
//        try (Connection cn = DatabaseConnect.getConnection();
//             PreparedStatement ps = cn.prepareStatement(sql)) {
//            ps.setString(1, subjectId);
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException("Lỗi xóa môn học: " + e.getMessage());
//        }
//    }
//
//    public List<Subject> getAll() {
//        List<Subject> subjects = new ArrayList<>();
//        String sql = "SELECT * FROM Subjects ORDER BY subject_id";
//        try (Connection cn = DatabaseConnect.getConnection();
//             PreparedStatement ps = cn.prepareStatement(sql);
//             ResultSet rs = ps.executeQuery()) {
//            while (rs.next()) {
//                subjects.add(new Subject(
//                        rs.getString("subject_id"),
//                        rs.getString("name"),
//                        rs.getString("description")
//                ));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException("Lỗi lấy danh sách môn học: " + e.getMessage());
//        }
//        return subjects;
//    }
//
//    public boolean isSubjectExist(String subjectId) {
//        String sql = "SELECT COUNT(*) FROM Subjects WHERE subject_id = ?";
//        try (Connection cn = DatabaseConnect.getConnection();
//             PreparedStatement ps = cn.prepareStatement(sql)) {
//            ps.setString(1, subjectId);
//            ResultSet rs = ps.executeQuery();
//            return rs.next() && rs.getInt(1) > 0;
//        } catch (SQLException e) {
//            throw new RuntimeException("Lỗi kiểm tra tồn tại môn học: " + e.getMessage());
//        }
//    }
//
//    public Subject getById(String subjectId) {
//        String sql = "SELECT * FROM Subjects WHERE subject_id = ?";
//        try (Connection cn = DatabaseConnect.getConnection();
//             PreparedStatement ps = cn.prepareStatement(sql)) {
//            ps.setString(1, subjectId);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                return new Subject(
//                        rs.getString("subject_id"),
//                        rs.getString("name"),
//                        rs.getString("description")
//                );
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException("Lỗi lấy môn học theo ID: " + e.getMessage());
//        }
//        return null;
//    }
//
//    public List<Subject> getByTeacherId(String teacherId) {
//        List<Subject> subjects = new ArrayList<>();
//        String sql = """
//            SELECT DISTINCT s.subject_id, s.name, s.description
//            FROM Subjects s
//            JOIN Schedules sc ON s.subject_id = sc.subject_id
//            WHERE sc.teacher_id = ?
//        """;
//        try (Connection cn = DatabaseConnect.getConnection();
//             PreparedStatement ps = cn.prepareStatement(sql)) {
//            ps.setString(1, teacherId);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                subjects.add(new Subject(
//                        rs.getString("subject_id"),
//                        rs.getString("name"),
//                        rs.getString("description")
//                ));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException("Lỗi lấy môn học theo giảng viên: " + e.getMessage());
//        }
//        return subjects;
//    }
//}
package DAO;

import Model.Subject;
import util.DatabaseConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO {

    public void add(Subject subject) {
        String sql = "INSERT INTO Subjects (subject_id, name, description) VALUES (?, ?, ?)";
        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, subject.getSubjectId());
            ps.setString(2, subject.getName());
            ps.setString(3, subject.getDescription());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi thêm môn học: " + e.getMessage());
        }
    }

    public void update(Subject subject) {
        String sql = "UPDATE Subjects SET name = ?, description = ? WHERE subject_id = ?";
        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, subject.getName());
            ps.setString(2, subject.getDescription());
            ps.setString(3, subject.getSubjectId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi cập nhật môn học: " + e.getMessage());
        }
    }

    public boolean hasSubjectDependency(String subjectId) {
        String[] relatedTables = {"exams", "grades", "schedules", "registrations"};
        String[] foreignKeys = {"subject_id", "subject_id", "subject_id", "subject_id"};

        try (Connection cn = DatabaseConnect.getConnection()) {
            for (int i = 0; i < relatedTables.length; i++) {
                String sql = "SELECT COUNT(*) FROM " + relatedTables[i] + " WHERE " + foreignKeys[i] + " = ?";
                try (PreparedStatement ps = cn.prepareStatement(sql)) {
                    ps.setString(1, subjectId);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next() && rs.getInt(1) > 0) {
                        return true; // Bị ràng buộc ở ít nhất một bảng
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi kiểm tra ràng buộc môn học: " + e.getMessage());
        }
        return false;
    }


    public List<Subject> getDeletableSubjects() {
        List<Subject> allSubjects = getAll();
        List<Subject> deletableSubjects = new ArrayList<>();

        for (Subject subject : allSubjects) {
            if (!hasSubjectDependency(subject.getSubjectId())) {
                deletableSubjects.add(subject);
            }
        }
        return deletableSubjects;
    }


    public void delete(String subjectId) {
        if (hasSubjectDependency(subjectId)) {
            throw new RuntimeException("Không thể xoá môn học vì đã được sử dụng trong các bảng liên quan.");
        }

        String sql = "DELETE FROM Subjects WHERE subject_id = ?";
        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, subjectId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi xóa môn học: " + e.getMessage());
        }
    }


    public List<Subject> getAll() {
        List<Subject> subjects = new ArrayList<>();
        String sql = "SELECT * FROM Subjects ORDER BY subject_id";
        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                subjects.add(new Subject(
                        rs.getString("subject_id"),
                        rs.getString("name"),
                        rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi lấy danh sách môn học: " + e.getMessage());
        }
        return subjects;
    }

    public boolean isSubjectExist(String subjectId) {
        String sql = "SELECT COUNT(*) FROM Subjects WHERE subject_id = ?";
        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, subjectId);
            ResultSet rs = ps.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi kiểm tra tồn tại môn học: " + e.getMessage());
        }
    }

    public Subject getById(String subjectId) {
        String sql = "SELECT * FROM Subjects WHERE subject_id = ?";
        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, subjectId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Subject(
                        rs.getString("subject_id"),
                        rs.getString("name"),
                        rs.getString("description")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi lấy môn học theo ID: " + e.getMessage());
        }
        return null;
    }

    public List<Subject> getByTeacherId(String teacherId) {
        List<Subject> subjects = new ArrayList<>();
        String sql = """
                    SELECT DISTINCT s.subject_id, s.name, s.description
                    FROM Subjects s
                    JOIN Schedules sc ON s.subject_id = sc.subject_id
                    WHERE sc.teacher_id = ?
                """;
        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, teacherId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                subjects.add(new Subject(
                        rs.getString("subject_id"),
                        rs.getString("name"),
                        rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi lấy môn học theo giảng viên: " + e.getMessage());
        }
        return subjects;
    }
}