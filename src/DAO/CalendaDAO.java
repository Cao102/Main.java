package DAO;

import Model.Calenda;
import util.DatabaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CalendaDAO {
    public void addCalendaDAO(Calenda a) {
        String sql = "INSERT INTO studentmanagementsystem.schedules " +
                "(id, classroom_id, subject_id, teacher_id, schedule_time) " +
                "VALUES (?, ?, ?, ?, ?)";
        try {
            Connection cn = DatabaseConnect.getConnection();
            PreparedStatement pr = cn.prepareStatement(sql);
            pr.setString(1, a.getID());
            pr.setString(2, a.getClassroomID());
            pr.setString(3, a.getSubjectID());
            pr.setString(4, a.getTeacherID());
            pr.setString(5, a.getScheduleTime());

            pr.executeUpdate();
            System.out.println("Thêm lịch thành công");
        } catch (Exception e) {
            System.out.println("Error when adding Calenda: " + e.getMessage());
        }
    }

    public void updateCalendaDAO(Calenda a) {
        String sql = "UPDATE studentmanagementsystem.schedules " +
                "SET classroom_id = ?, subject_id = ?, teacher_id = ?, schedule_time = ? " +
                "WHERE id = ?";
        try {
            Connection cn = DatabaseConnect.getConnection();
            PreparedStatement pr = cn.prepareStatement(sql);
            pr.setString(1, a.getClassroomID());
            pr.setString(2, a.getSubjectID());
            pr.setString(3, a.getTeacherID());
            pr.setString(4, a.getScheduleTime());
            pr.setString(5, a.getID());

            int check = pr.executeUpdate();
            if (check > 0) {
                System.out.println("Successfully updated");
            } else {
                System.out.println("Không tồn tại lịch để cập nhật");
            }
        } catch (Exception e) {
            System.out.println("Error Update Calenda: " + e.getMessage());
        }
    }

    public void removeCalendaDAO(String id) {
        String sql = "DELETE FROM studentmanagementsystem.schedules WHERE id = ?";
        try {
            Connection cn = DatabaseConnect.getConnection();
            PreparedStatement pr = cn.prepareStatement(sql);
            pr.setString(1, id);
            pr.executeUpdate();
            System.out.println("Xóa lịch thành công");
        } catch (Exception e) {
            System.out.println("Error when deleting Calenda: " + e.getMessage());
        }
    }

    public List<Calenda> showCalendabyClassroomIDDAO(String ClassroomID) {
        String sql = "SELECT s.id, s.classroom_id, s.subject_id, " +
                "sub.name AS subject_name, t.name AS teacher_name, s.schedule_time " +
                "FROM studentmanagementsystem.schedules s " +
                "INNER JOIN studentmanagementsystem.teachers t ON s.teacher_id = t.teacher_id " +
                "INNER JOIN studentmanagementsystem.subjects sub ON s.subject_id = sub.subject_id " +
                "WHERE s.classroom_id = ?";
        List<Calenda> list = new ArrayList<>();
        try {
            Connection cn = DatabaseConnect.getConnection();
            PreparedStatement pr = cn.prepareStatement(sql);
            pr.setString(1, ClassroomID);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                String classroom_id = rs.getString("classroom_id");
                String subject_name = rs.getString("subject_name");
                String teacher_name = rs.getString("teacher_name");
                String schedule_time = rs.getString("schedule_time");

                list.add(new Calenda(classroom_id, subject_name, teacher_name, schedule_time));
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
        return list;
    }
    public List<Calenda> showCalendabyTeacherIDDAO(String TeacherID) {
        String sql = "SELECT s.id, s.classroom_id, s.subject_id, " +
                "sub.name AS subject_name, t.name AS teacher_name, s.schedule_time " +
                "FROM studentmanagementsystem.schedules s " +
                "INNER JOIN studentmanagementsystem.teachers t ON s.teacher_id = t.teacher_id " +
                "INNER JOIN studentmanagementsystem.subjects sub ON s.subject_id = sub.subject_id " +
                "WHERE s.teacher_id = ?";
        List<Calenda> list = new ArrayList<>();
        try {
            Connection cn = DatabaseConnect.getConnection();
            PreparedStatement pr = cn.prepareStatement(sql);
            pr.setString(1, TeacherID);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                String classroom_id = rs.getString("classroom_id");
                String subject_name = rs.getString("subject_name");
                String teacher_name = rs.getString("teacher_name");
                String schedule_time = rs.getString("schedule_time");

                list.add(new Calenda(classroom_id, subject_name, teacher_name, schedule_time));
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
        return list;
    }
}
