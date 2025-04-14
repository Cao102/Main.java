package Controller;

import DAO.SupportRequestDAO;
import Model.SupportRequest;

import java.util.List;

public class SupportRequestController {
    private SupportRequestDAO dao = new SupportRequestDAO();

    public void sendRequest(String studentId, String message) {
        dao.addSupportRequest(new SupportRequest(studentId, message));
    }

    public List<SupportRequest> getAllRequests() {
        return dao.getAllRequests();
    }

    public void editRequestMessage(int id, String newMessage) {
        dao.updateRequestMessage(id, newMessage);
    }

    public void deleteRequest(int id) {
        dao.deleteRequest(id);
    }

    public void updateRequestStatus(int id, String status) {
        dao.updateRequestStatus(id, status);
    }
}
