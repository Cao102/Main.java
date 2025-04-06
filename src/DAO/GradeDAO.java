package DAO;
import Model.Grade;
import connectDatabase.DatabaseConnect;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class GradeDAO {
    public void addGrade(int studentID, int subjectID, double grade){
        String sql = "INSERT INTO studentmanagementsystem.grades (student_id,subject_id, grade) VALUES (?,? ,?)";
        try{
            Connection cn = DatabaseConnect.getConnection();
            PreparedStatement pr = cn.prepareStatement(sql);
            pr.setInt(1,studentID);
            pr.setInt(2,subjectID);
            pr.setDouble(3,grade);
            pr.executeUpdate();
            System.out.println("Successfully");
        } catch(Exception e){
            System.out.println("Error Add Grade");
        }
    }
    public void updateGrade(int studentID, int subjectID, double grade){
        String sql = "UPDATE studentmanagementsystem.grades SET grade = ? WHERE student_id = ? AND subject_id = ?";
        try{
            Connection cn = DatabaseConnect.getConnection();
            PreparedStatement pr = cn.prepareStatement(sql);
            pr.setDouble(1,grade);
            pr.setInt(2,studentID);
            pr.setInt(3,subjectID);
            int check = pr.executeUpdate();
            if(check > 0){
                System.out.println("Successfully");
            }
            else{
                System.out.println("Khong ton tai");
            }
        } catch(Exception e){
            System.out.println("Error Add Grade");
        }
    }
    public List<Grade> showGradeByStudentDAO(int studentID){
        List<Grade> listGrade = new ArrayList<>();
        String sql = "SELECT s.name, g.grade " +
                "FROM studentmanagementsystem.grades g " +
                "INNER JOIN studentmanagementsystem.subjects s ON g.subject_id = s.subject_id " +
                "WHERE g.student_id = ?";
        try {
            Connection cn = DatabaseConnect.getConnection();
            PreparedStatement pr = cn.prepareStatement(sql);
            pr.setInt(1,studentID);
            ResultSet rs = pr.executeQuery();
            while(rs.next()){
                String n = rs.getString("name");
                double gr = rs.getDouble("grade");
                listGrade.add(new Grade(n,gr));
            }
        } catch(Exception e){
            System.out.println("Error");
        }
        return listGrade;
    }
}
