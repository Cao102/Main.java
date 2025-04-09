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
    public void showCalendabyClassRoomIDController(String classroom_id){
        List<Calenda> list = ca.showCalendabyClassroomIDDAO(classroom_id);
        for(Calenda x : list){
            System.out.println("Phòng "+ x.getClassroomID()+" tại khung giờ " + x.getScheduleTime() + " giảng dạy môn "+ x.getNameSubject() + " bởi giáo viên " + x.getName());
        }
    }
}
