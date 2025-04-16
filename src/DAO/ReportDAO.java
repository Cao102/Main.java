package DAO;

import Model.Report;
import Model.TopStudent;
import connectDatabase.DatabaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO {

    public int getStudentCountDAO() {
        String sql = "SELECT COUNT(*) AS count FROM studentmanagementsystem.students";
        try {
            Connection cn = DatabaseConnect.getConnection();
            PreparedStatement pr = cn.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                return rs.getInt("count");
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi đếm số lượng sinh viên: " + e.getMessage());
        }
        return 0;
    }

    public int getTeacherCountDAO() {
        String sql = "SELECT COUNT(*) AS count FROM studentmanagementsystem.teachers";
        try {
            Connection cn = DatabaseConnect.getConnection();
            PreparedStatement pr = cn.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                return rs.getInt("count");
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi đếm số lượng giảng viên: " + e.getMessage());
        }
        return 0;
    }

    public int getSubjectCountDAO() {
        String sql = "SELECT COUNT(*) AS count FROM studentmanagementsystem.subjects";
        try {
            Connection cn = DatabaseConnect.getConnection();
            PreparedStatement pr = cn.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                return rs.getInt("count");
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi đếm số lượng môn học: " + e.getMessage());
        }
        return 0;
    }

    public double getRevenueFromTuitionDAO() {
        String sql = "SELECT SUM(amount) AS total FROM studentmanagementsystem.tuition";
        try {
            Connection cn = DatabaseConnect.getConnection();
            PreparedStatement pr = cn.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi tính tổng học phí: " + e.getMessage());
        }
        return 0.0;
    }

    public List<TopStudent> getTopStudentsDAO(int topN) {
        String sql = "SELECT s.student_id, s.name, AVG(g.grade) AS avg_grade " +
                "FROM studentmanagementsystem.students s " +
                "JOIN studentmanagementsystem.grades g ON s.student_id = g.student_id " +
                "GROUP BY s.student_id, s.name " +
                "ORDER BY avg_grade DESC " +
                "LIMIT ?";

        List<TopStudent> topStudents = new ArrayList<>();

        try {
            Connection cn = DatabaseConnect.getConnection();
            PreparedStatement pr = cn.prepareStatement(sql);
            pr.setInt(1, topN);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                String studentId = rs.getString("student_id");
                String studentName = rs.getString("name");
                double avgGrade = rs.getDouble("avg_grade");

                topStudents.add(new TopStudent(studentId, studentName, avgGrade));
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi lấy danh sách sinh viên có điểm cao nhất: " + e.getMessage());
        }

        return topStudents;
    }

    public Report getReportOverviewDAO() {
        int studentCount = getStudentCountDAO();
        int teacherCount = getTeacherCountDAO();
        int subjectCount = getSubjectCountDAO();
        double totalRevenue = getRevenueFromTuitionDAO();

        return new Report(studentCount, teacherCount, subjectCount, totalRevenue);
    }
}