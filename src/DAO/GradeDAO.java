package DAO;

import Model.Grade;
import util.DatabaseConnect;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class GradeDAO {
    public void addGrade(String studentID, String subjectID,double grade ) {
        String checkStudentSQL = "SELECT COUNT(*) FROM studentmanagementsystem.students WHERE student_id = ?";
        String checkSubjectSQL = "SELECT COUNT(*) FROM studentmanagementsystem.subjects WHERE subject_id = ?";
        String checkRegistrationSQL = "SELECT COUNT(*) FROM studentmanagementsystem.registrations WHERE subject_id = ? AND student_id = ?";

        try (Connection cn = DatabaseConnect.getConnection()) {
            PreparedStatement prStudent = cn.prepareStatement(checkStudentSQL);
            prStudent.setString(1, studentID);
            ResultSet rsStudent = prStudent.executeQuery();
            if (rsStudent.next() && rsStudent.getInt(1) == 0) {
                System.out.println("Không tồn tại sinh viên");
                return;
            }

            PreparedStatement prSubject = cn.prepareStatement(checkSubjectSQL);
            prSubject.setString(1, subjectID);
            ResultSet rsSubject = prSubject.executeQuery();
            if (rsSubject.next() && rsSubject.getInt(1) == 0) {
                System.out.println("Không tồn tại môn học");
                return;
            }

            PreparedStatement prRegistration = cn.prepareStatement(checkRegistrationSQL);
            prRegistration.setString(1, subjectID);
            prRegistration.setString(2, studentID);
            ResultSet rsRegistration = prRegistration.executeQuery();
            if (rsRegistration.next() && rsRegistration.getInt(1) == 0) {
                System.out.println("Sinh viên chưa đăng ký môn học này");
                return;
            }

            String sql = "INSERT INTO studentmanagementsystem.grades (student_id, subject_id, grade) VALUES (?, ?, ?)";
            PreparedStatement pr = cn.prepareStatement(sql);
            pr.setString(1, studentID);
            pr.setString(2, subjectID);
            pr.setDouble(3, grade);
            pr.executeUpdate();
            System.out.println("Nhập điểm thành công");

        } catch (Exception e) {
            System.out.println("Môn học hiện tại đã có điểm , nếu muốn sửa điểm hãy chọn Sửa điểm");
        }
    }

    public void updateGrade(String studentID, String subjectID, double grade) {
        String checkStudentSQL = "SELECT COUNT(*) FROM studentmanagementsystem.students WHERE student_id = ?";
        String checkSubjectSQL = "SELECT COUNT(*) FROM studentmanagementsystem.subjects WHERE subject_id = ?";


        try (Connection cn = DatabaseConnect.getConnection()) {
            PreparedStatement prStudent = cn.prepareStatement(checkStudentSQL);
            prStudent.setString(1, studentID);
            ResultSet rsStudent = prStudent.executeQuery();
            if (rsStudent.next() && rsStudent.getInt(1) == 0) {
                System.out.println("Không tồn tại sinh viên");
                return;
            }

            PreparedStatement prSubject = cn.prepareStatement(checkSubjectSQL);
            prSubject.setString(1, subjectID);
            ResultSet rsSubject = prSubject.executeQuery();
            if (rsSubject.next() && rsSubject.getInt(1) == 0) {
                System.out.println("Không tồn tại môn học");
                return;
            }

            String sql = "UPDATE studentmanagementsystem.grades SET grade = ? WHERE student_id = ? AND subject_id = ?";
            PreparedStatement pr = cn.prepareStatement(sql);
            pr.setDouble(1, grade);
            pr.setString(2, studentID);
            pr.setString(3, subjectID);
            int check = pr.executeUpdate();
            if (check > 0) {
                System.out.println("Cập nhật điểm thành công");
            } else {
                System.out.println("Không tìm thấy thông tin điểm của sinh viên và môn học này.");
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật điểm");
        }
    }

    public List<Grade> showGradeByStudentDAO(String studentID) {
        List<Grade> listGrade = new ArrayList<>();
        String sql = "SELECT s.name, g.grade, g.subject_id " +
                "FROM studentmanagementsystem.grades g " +
                "INNER JOIN studentmanagementsystem.subjects s ON g.subject_id = s.subject_id " +
                "WHERE g.student_id = ?";
        try (Connection cn = DatabaseConnect.getConnection()) {
            PreparedStatement pr = cn.prepareStatement(sql);
            pr.setString(1, studentID);
            ResultSet rs = pr.executeQuery();
            if (!rs.next()) {
                System.out.println("Không có điểm cho sinh viên này.");
                return listGrade;
            }
            do {
                String subjectName = rs.getString("name");
                String subjectID = rs.getString("subject_id");
                double grade = rs.getDouble("grade");
                listGrade.add(new Grade(subjectID, grade, subjectName));
            } while (rs.next());
        } catch (Exception e) {
            System.out.println("Lỗi khi hiển thị điểm sinh viên");
        }
        return listGrade;
    }

    public List<Grade> showGradeBySubjectDAO(String subjectID) {
        List<Grade> listGrade = new ArrayList<>();
        String sql = "SELECT s.name, g.grade, g.student_id " +
                "FROM studentmanagementsystem.grades g " +
                "INNER JOIN studentmanagementsystem.students s ON g.student_id = s.student_id " +
                "WHERE g.subject_id = ?";
        try (Connection cn = DatabaseConnect.getConnection()) {
            PreparedStatement pr = cn.prepareStatement(sql);
            pr.setString(1, subjectID);
            ResultSet rs = pr.executeQuery();
            if (!rs.next()) {
                System.out.println("Không có điểm cho môn học này.");
                return listGrade;
            }
            do {
                String studentName = rs.getString("name");
                String studentID = rs.getString("student_id");
                double grade = rs.getDouble("grade");
                listGrade.add(new Grade(grade,studentID,studentName));
            } while (rs.next());
        } catch (Exception e) {
            System.out.println("Lỗi khi hiển thị điểm môn học");
        }
        return listGrade;
    }
}

