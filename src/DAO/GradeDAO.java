package DAO;

import Model.Grade;
import util.DatabaseConnect;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class GradeDAO {
    public void addGrade(String studentID, String subjectID, double grade) {
        String sql = "INSERT INTO studentmanagementsystem.grades (student_id, subject_id, grade) VALUES (?, ?, ?)";
        try {
            Connection cn = DatabaseConnect.getConnection();
            PreparedStatement pr = cn.prepareStatement(sql);
            pr.setString(1, studentID);
            pr.setString(2, subjectID);
            pr.setDouble(3, grade);
            pr.executeUpdate();
            System.out.println("Successfully");
        } catch (Exception e) {
            System.out.println("Error Add Grade: " + e.getMessage());
        }
    }

    public void updateGrade(String studentID, String subjectID, double grade) {
        String sql = "UPDATE studentmanagementsystem.grades SET grade = ? WHERE student_id = ? AND subject_id = ?";
        try {
            Connection cn = DatabaseConnect.getConnection();
            PreparedStatement pr = cn.prepareStatement(sql);
            pr.setDouble(1, grade);
            pr.setString(2, studentID);
            pr.setString(3, subjectID);
            int check = pr.executeUpdate();
            if (check > 0) {
                System.out.println("Successfully");
            } else {
                System.out.println("Không tồn tại");
            }
        } catch (Exception e) {
            System.out.println("Error Update Grade: " + e.getMessage());
        }
    }

    public List<Grade> showGradeByStudentDAO(String studentID) {
        List<Grade> listGrade = new ArrayList<>();
        String sql = "SELECT s.name, g.grade " +
                "FROM studentmanagementsystem.grades g " +
                "INNER JOIN studentmanagementsystem.subjects s ON g.subject_id = s.subject_id " +
                "WHERE g.student_id = ?";
        try {
            Connection cn = DatabaseConnect.getConnection();
            PreparedStatement pr = cn.prepareStatement(sql);
            pr.setString(1, studentID);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                String subjectName = rs.getString("name");
                double grade = rs.getDouble("grade");
                listGrade.add(new Grade(subjectName, grade));
            }
        } catch (Exception e) {
            System.out.println("Error showGradeByStudent: " + e.getMessage());
        }
        return listGrade;
    }

    public List<Grade> showGradeBySubjectDAO(String subjectID) {
        List<Grade> listGrade = new ArrayList<>();
        String sql = "SELECT s.name, g.grade " +
                "FROM studentmanagementsystem.grades g " +
                "INNER JOIN studentmanagementsystem.students s ON g.student_id = s.student_id " +
                "WHERE g.subject_id = ?";
        try {
            Connection cn = DatabaseConnect.getConnection();
            PreparedStatement pr = cn.prepareStatement(sql);
            pr.setString(1, subjectID);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                String studentName = rs.getString("name");
                double grade = rs.getDouble("grade");
                listGrade.add(new Grade(studentName, grade));
            }
        } catch (Exception e) {
            System.out.println("Error showGradeBySubject: " + e.getMessage());
        }
        return listGrade;
    }
}
