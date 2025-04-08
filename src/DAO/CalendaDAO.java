package DAO;

import java.util.Calendar;

public class CalendaDAO {
    public void addCalenda(Calendar a){
        String sql = "INSERT INTO studentmanagementsystem.schedules (id, classroom_id, subject_id, teacher_id, schedule_time) VALUES (?, ?, ?, ?, ?)";

    }
}
