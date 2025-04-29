package DAO;

import Model.Report;
import util.DatabaseConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO {

    public Report getGeneralStatistics() {
        int studentCount = 0;
        int teacherCount = 0;
        int classroomCount = 0;
        double totalTuition = 0.0;

        try (Connection cn = DatabaseConnect.getConnection()) {
            Statement st = cn.createStatement();

            ResultSet rs1 = st.executeQuery("SELECT COUNT(*) FROM Students");
            if (rs1.next()) studentCount = rs1.getInt(1);

            ResultSet rs2 = st.executeQuery("SELECT COUNT(*) FROM Teachers");
            if (rs2.next()) teacherCount = rs2.getInt(1);

            ResultSet rs3 = st.executeQuery("SELECT COUNT(*) FROM Classrooms");
            if (rs3.next()) classroomCount = rs3.getInt(1);

            ResultSet rs4 = st.executeQuery("SELECT SUM(amount) FROM Tuition");
            if (rs4.next()) totalTuition = rs4.getDouble(1);

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi lấy thống kê chung: " + e.getMessage());
        }

        return new Report(studentCount, teacherCount, classroomCount, totalTuition);
    }

    public List<Report> getTopStudentsByGPA(int limit) {
        List<Report> topStudents = new ArrayList<>();
        String sql = """
            SELECT s.student_id, s.name, AVG(g.grade) as gpa
            FROM Students s
            JOIN Grades g ON s.student_id = g.student_id
            GROUP BY s.student_id, s.name
            ORDER BY gpa DESC
            LIMIT ?
        """;

        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, limit);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                topStudents.add(new Report(
                        rs.getString("student_id"),
                        rs.getString("name"),
                        rs.getDouble("gpa")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi lấy top sinh viên GPA: " + e.getMessage());
        }

        return topStudents;
    }
}
