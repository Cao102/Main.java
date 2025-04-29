package DAO;

import Model.Calenda;
import util.DatabaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CalendaDAO {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void addCalendaDAO(Calenda a) {
        String insertSQL = "INSERT INTO studentmanagementsystem.schedules " +
                "(id, classroom_id, subject_id, teacher_id, schedule_time) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (Connection cn = DatabaseConnect.getConnection()) {
            // Check classroom
            try (PreparedStatement ps = cn.prepareStatement(
                    "SELECT classroom_id FROM studentmanagementsystem.classrooms WHERE classroom_id = ?")) {
                ps.setString(1, a.getClassroomID());
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new RuntimeException("Không có phòng học");
                }
            }

            // Check subject
            try (PreparedStatement ps = cn.prepareStatement(
                    "SELECT subject_id FROM studentmanagementsystem.subjects WHERE subject_id = ?")) {
                ps.setString(1, a.getSubjectID());
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new RuntimeException("Không có môn học");
                }
            }

            // Check teacher
            try (PreparedStatement ps = cn.prepareStatement(
                    "SELECT teacher_id FROM studentmanagementsystem.teachers WHERE teacher_id = ?")) {
                ps.setString(1, a.getTeacherID());
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new RuntimeException("Không có giáo viên");
                }
            }

            // Check time conflict
            try (PreparedStatement ps = cn.prepareStatement(
                    "SELECT schedule_time FROM studentmanagementsystem.schedules WHERE classroom_id = ?")) {
                ps.setString(1, a.getClassroomID());
                ResultSet rs = ps.executeQuery();
                LocalDateTime newScheduleTime = LocalDateTime.parse(a.getScheduleTime(), formatter);
                while (rs.next()) {
                    LocalDateTime existingScheduleTime = LocalDateTime.parse(rs.getString("schedule_time"), formatter);
                    long minutes = Math.abs(Duration.between(existingScheduleTime, newScheduleTime).toMinutes());
                    if (minutes < 60) {
                        throw new RuntimeException("Lỗi: Thời gian nhập cách lịch hiện có trong phòng dưới 1 tiếng.");
                    }
                }
            }

            //Insert database
            try (PreparedStatement insertStatement = cn.prepareStatement(insertSQL)) {
                insertStatement.setString(1, a.getID());
                insertStatement.setString(2, a.getClassroomID());
                insertStatement.setString(3, a.getSubjectID());
                insertStatement.setString(4, a.getTeacherID());
                insertStatement.setString(5, a.getScheduleTime());
                insertStatement.executeUpdate();
                System.out.println("Thêm lịch thành công");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void updateCalendaDAO(Calenda a) {
        String sql = "UPDATE studentmanagementsystem.schedules " +
                "SET classroom_id = ?, subject_id = ?, teacher_id = ?, schedule_time = ? " +
                "WHERE id = ?";
        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement pr = cn.prepareStatement(sql)) {

            pr.setString(1, a.getClassroomID());
            pr.setString(2, a.getSubjectID());
            pr.setString(3, a.getTeacherID());
            pr.setString(4, a.getScheduleTime());
            pr.setString(5, a.getID());

            int check = pr.executeUpdate();
            if (check > 0) {
                System.out.println("Cập nhật lịch thành công");
            } else {
                System.out.println("Không tìm thấy lịch để cập nhật");
            }

        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật lịch: " + e.getMessage());
        }
    }

    public void removeCalendaDAO(String id) {
        String sql = "DELETE FROM studentmanagementsystem.schedules WHERE id = ?";
        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement pr = cn.prepareStatement(sql)) {

            pr.setString(1, id);
            pr.executeUpdate();
            System.out.println("Xóa lịch thành công");

        } catch (Exception e) {
            System.out.println("Lỗi khi xóa lịch: " + e.getMessage());
        }
    }

    public List<Calenda> showCalendabyClassroomIDDAO(String classroomID) {
        String sql = "SELECT s.id, s.classroom_id, s.subject_id, " +
                "sub.name AS subject_name, t.name AS teacher_name, s.schedule_time " +
                "FROM studentmanagementsystem.schedules s " +
                "INNER JOIN studentmanagementsystem.teachers t ON s.teacher_id = t.teacher_id " +
                "INNER JOIN studentmanagementsystem.subjects sub ON s.subject_id = sub.subject_id " +
                "WHERE s.classroom_id = ?";
        List<Calenda> list = new ArrayList<>();
        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement pr = cn.prepareStatement(sql)) {

            pr.setString(1, classroomID);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                list.add(new Calenda(
                        rs.getString("classroom_id"),
                        rs.getString("subject_name"),
                        rs.getString("teacher_name"),
                        rs.getString("schedule_time")
                ));
            }

        } catch (Exception e) {
            System.out.println("Lỗi khi hiển thị lịch theo phòng: " + e.getMessage());
        }
        return list;
    }

    public List<Calenda> showCalendabyTeacherIDDAO(String teacherID) {
        String sql = "SELECT s.id, s.classroom_id, s.subject_id, " +
                "sub.name AS subject_name, t.name AS teacher_name, s.schedule_time " +
                "FROM studentmanagementsystem.schedules s " +
                "INNER JOIN studentmanagementsystem.teachers t ON s.teacher_id = t.teacher_id " +
                "INNER JOIN studentmanagementsystem.subjects sub ON s.subject_id = sub.subject_id " +
                "WHERE s.teacher_id = ?";
        List<Calenda> list = new ArrayList<>();
        try (Connection cn = DatabaseConnect.getConnection();
             PreparedStatement pr = cn.prepareStatement(sql)) {

            pr.setString(1, teacherID);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                list.add(new Calenda(
                        rs.getString("classroom_id"),
                        rs.getString("subject_name"),
                        rs.getString("teacher_name"),
                        rs.getString("schedule_time")
                ));
            }

        } catch (Exception e) {
            System.out.println("Lỗi khi hiển thị lịch theo giảng viên: " + e.getMessage());
        }
        return list;
    }
}
