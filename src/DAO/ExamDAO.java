package DAO;

import DatabaseConnection.DatabaseConnect;
import Model.Exam;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ExamDAO {

    public boolean scheduleExam(Exam exam) {
        String sql = "INSERT INTO Exams(class_id, subject_id, exam_date) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, exam.getClassId());
            stmt.setString(2, exam.getSubjectId());
            stmt.setTimestamp(3, Timestamp.valueOf(exam.getExamDate()));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    return false;
    }

    public boolean updateExam(Exam exam) {
        String sql = "UPDATE Exams SET class_id = ?, subject_id = ?, exam_date = ? WHERE id = ?";
        try (Connection conn = DatabaseConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, exam.getClassId());
            stmt.setString(2, exam.getSubjectId());
            stmt.setTimestamp(3, Timestamp.valueOf(exam.getExamDate()));
            stmt.setInt(4, exam.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteExam(int examId) {
        String sql = "DELETE FROM Exams WHERE id = ?";
        try (Connection conn = DatabaseConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, examId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Exam> getExamsByClass(String classId) {
        List<Exam> list = new ArrayList<>();
        String sql = "SELECT * FROM Exams WHERE class_id = ?";

        try (Connection conn = DatabaseConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, classId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Exam exam = new Exam(
                        rs.getInt("id"),
                        rs.getString("class_id"),
                        rs.getString("subject_id"),
                        rs.getTimestamp("exam_date").toLocalDateTime()
                );
                list.add(exam);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public String getExamResults(int examId) {
        // Mô phỏng trả kết quả, thực tế cần JOIN bảng điểm (ExamResults)
        return "Kết quả kỳ thi ID " + examId + ": chưa có kết quả";
    }

    // ✅ Thêm hàm kiểm tra lịch thi có trùng không
    public boolean isExamScheduled(String classId, String subjectId, LocalDateTime examDate) {
        String sql = "SELECT COUNT(*) FROM Exams WHERE class_id = ? AND subject_id = ? AND exam_date = ?";
        try (Connection conn = DatabaseConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, classId);
            stmt.setString(2, subjectId);
            stmt.setTimestamp(3, Timestamp.valueOf(examDate));

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}