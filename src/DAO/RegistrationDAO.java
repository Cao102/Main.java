package DAO;

import Model.Registration;
import connectDatabase.DatabaseConnect;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;
public class RegistrationDAO {
    public void add(int student_id, int subject_id){
        String sql = "INSERT INTO registrations (student_id,subject_id) VALUES (?,?)";
        try {
            Connection cn = DatabaseConnect.getConnection();
            PreparedStatement pr = cn.prepareStatement(sql)
            pr.setInt(1,student_id);
            pr.setInt(2,subject_id);
            System.out.println("Successfully");
        } catch (Exception e) {
            System.out.println("Error Add");
        }
    }
    public void remove(int student_id, int subject_id){
        String sql = "DELETE FROM registrations WHERE subject_id = ? AND student_id = ?";
        try {
            Connection cn = DatabaseConnect.getConnection();
            PreparedStatement pr = cn.prepareStatement(sql);
            pr.setInt(1,student_id);
            pr.setInt(2,subject_id);
            pr.executeUpdate();
            System.out.println("Remove Success");
        } catch(Exception e){
            System.out.println("Error Remove");
        }
    }
    public void showliststudent(){

    }
}

