package Controller;

import DAO.CalendaDAO;
import Model.Calenda;

public class CalendaController {
    private CalendaDAO ca = new CalendaDAO();

    public void addCalendaController(Calenda a) {
        ca.addCalendaDAO(a);
    }
    public void updateCalendaController(Calenda a){
        ca.updateCalenda(a);
    }

}
