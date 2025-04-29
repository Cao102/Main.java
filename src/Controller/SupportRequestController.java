package Controller;

import DAO.SupportRequestDAO;
import Model.SupportRequest;
import View.ViewSupportRequest;

import java.util.List;

public class SupportRequestController {
    private final SupportRequestDAO dao = new SupportRequestDAO();
    private final ViewSupportRequest view = new ViewSupportRequest();

    public void startSupportRequest() {
        while (true) {
            int choice = view.menuSupport();
            switch (choice) {
                case 1 -> sendRequest();
                case 2 -> displayRequests();
                case 3 -> updateRequest();
                case 4 -> deleteRequest();
                case 5 -> updateStatus();
                case 0 -> { return; }
                default -> view.errorChoose();
            }
        }
    }

    private void displayRequests() {
        List<SupportRequest> list = dao.getAll();
        view.displayRequests(list);
    }

    private void updateRequest() {
        int id = view.inputRequestId();
        if (!dao.exists(id)) {
            view.showRequestNotExist();
            return;
        }
        String newMessage = view.inputNewMessage();
        dao.updateMessage(id, newMessage);
        view.notifyRequestUpdated();
    }

    private void deleteRequest() {
        int id = view.inputRequestId();
        if (!dao.exists(id)) {
            view.showRequestNotExist();
            return;
        }
        dao.delete(id);
        view.notifyRequestDeleted();
    }

    private void updateStatus() {
        int id = view.inputRequestId();
        if (!dao.exists(id)) {
            view.showRequestNotExist();
            return;
        }
        String newStatus = view.inputNewStatus();
        dao.updateStatus(id, newStatus);
        view.notifyStatusUpdated();
    }
    private void sendRequest() {
        String studentId = view.inputStudentId();
        if (!dao.studentExists(studentId)) {
            view.showStudentNotExist();  // Hiển thị thông báo sinh viên không tồn tại
            return;
        }
        String message = view.inputMessage();
        SupportRequest request = new SupportRequest(studentId, message);
        dao.add(request);
        view.notifyRequestAdded();
    }

}
