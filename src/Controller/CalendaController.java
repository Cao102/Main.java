package Controller;

import DAO.CalendaDAO;
import Model.Calenda;

import java.util.List;

public class CalendaController {
    private CalendaDAO ca = new CalendaDAO();

    public void addCalendaController(Calenda a) {
        ca.addCalendaDAO(a);
    }
    public void updateCalendaController(Calenda a){
        ca.updateCalendaDAO(a);
    }
    public void removeCalendaController(String id){
        ca.removeCalendaDAO(id);
    }
    public void showCalendabyClassRoomIDController(String classroomId) {
        List<Calenda> list = ca.showCalendabyClassroomIDDAO(classroomId);
        if (list.isEmpty()) {
            System.out.println("Không có lịch học nào cho phòng " + classroomId);
            return;
        }

        for (Calenda x : list) {
            System.out.println("----------------------------------------------");
            System.out.println("Phòng học   : " + x.getClassroomID());
            System.out.println("Môn học     : " + x.getNameSubject());
            System.out.println("Giảng viên  : " + x.getName());
            System.out.println("Thời gian   : " + x.getScheduleTime());
            System.out.println("----------------------------------------------");
        }
    }

    public void showCalendabyTeacherIDController(String teacherID) {
        List<Calenda> list = ca.showCalendabyTeacherIDDAO(teacherID);
        if (list.isEmpty()) {
            System.out.println("Không có lịch học nào cho giảng viên " + teacherID);
            return;
        }

        for (Calenda x : list) {
            System.out.println("----------------------------------------------");
            System.out.println("Phòng học   : " + x.getClassroomID());
            System.out.println("Môn học     : " + x.getNameSubject());
            System.out.println("Giảng viên  : " + x.getName());
            System.out.println("Thời gian   : " + x.getScheduleTime());
            System.out.println("----------------------------------------------");
        }
    }

}
