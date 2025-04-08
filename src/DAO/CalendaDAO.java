package DAO;

import Model.Calenda;
import connectDatabase.DatabaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Calendar;

public class CalendaDAO {
    public void addCalendaDAO(Calenda a){
        String sql = "INSERT INTO studentmanagementsystem.schedules (id, classroom_id, subject_id, teacher_id, schedule_time) VALUES (?, ?, ?, ?, ?)";
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
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void updateCalenda(Calenda a) {
        String sql = "UPDATE studentmanagementsystem.schedules SET classroom_id = ?, subject_id = ?, teacher_id = ?, schedule_time = ? WHERE id = ?";
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

}
