package DAO;

import Model.Registration;
import util.DatabaseConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;
import java.sql.ResultSet;
public class RegistrationDAO {
    public void addSubject(String student_id, String subject_id){
        String sql = "INSERT INTO studentmanagementsystem.registrations (student_id,subject_id) VALUES (?,?)";
        try {
            Connection cn = DatabaseConnect.getConnection();
            PreparedStatement pr = cn.prepareStatement(sql);
            pr.setString(1,student_id);
            pr.setString(2,subject_id);
            pr.executeUpdate();
            System.out.println("Successfully");
        } catch (Exception e) {
            System.out.println("Error Add");
        }
    }
    public void removeSubject(String student_id, String subject_id){
        String sql = "DELETE FROM studentmanagementsystem.registrations WHERE student_id = ? AND subject_id = ?";
        try {
            Connection cn = DatabaseConnect.getConnection();
            PreparedStatement pr = cn.prepareStatement(sql);
            pr.setString(1,student_id);
            pr.setString(2,subject_id);
            pr.executeUpdate();
            System.out.println("Remove Success");
        } catch(Exception e){
            System.out.println("Error Remove");
        }
    }
    public List<Registration> getRegisteredSubjects(String student_id){
        String sql = "SELECT s.subject_id,s.name " +
                "FROM studentmanagementsystem.registrations r " +
                "INNER JOIN studentmanagementsystem.subjects s " +
                "ON r.subject_id = s.subject_id " +
                "WHERE r.student_id = ?";
        List<Registration> subjects = new ArrayList<>();

        try{
            Connection cn = DatabaseConnect.getConnection();
            PreparedStatement pr = cn.prepareStatement(sql);
            pr.setString(1, student_id);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                String sj = rs.getString("subject_id");
                String name = rs.getString("name");
                subjects.add(new Registration(name, sj));
            }

        } catch(Exception e){
            System.out.println("Lỗi khi truy vấn: " + e.getMessage());
        }

        return subjects;
    }

    public List<Registration> getRegisteredStudent(String subject_id){
        String sql = "SELECT student_id FROM studentmanagementsystem.registrations WHERE subject_id = ?";
        List<Registration> registrations = new ArrayList<>();

        try{
            Connection cn = DatabaseConnect.getConnection();
            PreparedStatement pr = cn.prepareStatement(sql);
            pr.setString(1, subject_id);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                String st = rs.getString("student_id");
                registrations.add(new Registration(st, subject_id));
            }

        } catch(Exception e){
            System.out.println("Lỗi khi truy vấn: " + e.getMessage());
        }

        return registrations;
    }

}

