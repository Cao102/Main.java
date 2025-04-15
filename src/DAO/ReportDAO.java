package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Model.Report;
import Model.TopStudent;
import connectDatabase.DatabaseConnect;

public class ReportDAO {

    public int countStudents() {
        String sql = "SELECT COUNT(*) as count FROM students";
        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement pr = cn.prepareStatement(sql);
             ResultSet rs = pr.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("count");
            }
        } catch (Exception e) {
            System.out.println("Lỗi đếm sinh viên: " + e.getMessage());
        }
        return 0;
    }

    public int countTeachers() {
        String sql = "SELECT COUNT(*) as count FROM teachers";
        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement pr = cn.prepareStatement(sql);
             ResultSet rs = pr.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("count");
            }
        } catch (Exception e) {
            System.out.println("Lỗi đếm giảng viên: " + e.getMessage());
        }
        return 0;
    }

    public int countClasses() {
        String sql = "SELECT COUNT(*) as count FROM classrooms";
        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement pr = cn.prepareStatement(sql);
             ResultSet rs = pr.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("count");
            }
        } catch (Exception e) {
            System.out.println("Lỗi đếm lớp học: " + e.getMessage());
        }
        return 0;
    }

    public double sumTuition() {
        String sql = "SELECT SUM(amount) as total FROM tuition";
        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement pr = cn.prepareStatement(sql);
             ResultSet rs = pr.executeQuery()) {

            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (Exception e) {
            System.out.println("Lỗi tính học phí: " + e.getMessage());
        }
        return 0;
    }

    public List<TopStudent> getTopStudents(int limit) {
        String sql = "SELECT s.student_id, s.name, AVG(g.grade) as avg_grade " +
                "FROM students s " +
                "JOIN grades g ON s.student_id = g.student_id " +
                "GROUP BY s.student_id, s.name " +
                "ORDER BY avg_grade DESC " +
                "LIMIT ?";

        List<TopStudent> topStudents = new ArrayList<>();

        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement pr = cn.prepareStatement(sql)) {

            pr.setInt(1, limit);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                String id = rs.getString("student_id");
                String name = rs.getString("name");
                double avgGrade = rs.getDouble("avg_grade");

                topStudents.add(new TopStudent(id, name, avgGrade));
            }
        } catch (Exception e) {
            System.out.println("Lỗi lấy top sinh viên: " + e.getMessage());
        }

        return topStudents;
    }

}