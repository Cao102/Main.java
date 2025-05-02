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

        // Lấy thông tin hiện tại của yêu cầu hỗ trợ
        SupportRequest currentRequest = dao.getById(id);

        // Hiển thị thông báo update
        view.showUpdateInfo();

        // Nhập thông tin mới từ người dùng
        String newMessage = view.inputNewMessage();

        // Nếu người dùng bỏ trống, giữ nguyên thông tin cũ
        if (newMessage.isEmpty()) {
            newMessage = currentRequest.getMessage();
        }

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

        // Lấy thông tin hiện tại của yêu cầu hỗ trợ
        SupportRequest currentRequest = dao.getById(id);

        // Hiển thị trạng thái hiện tại
        view.showCurrentStatus(currentRequest.getStatus());

        // Nhập trạng thái mới từ người dùng
        String newStatus = view.inputNewStatus();

        // Nếu người dùng bỏ trống, giữ nguyên trạng thái cũ
        if (newStatus.isEmpty()) {
            newStatus = currentRequest.getStatus();
        }

        dao.updateStatus(id, newStatus);
        view.notifyStatusUpdated();
    }

    private void sendRequest() {
        String studentId = view.inputStudentId();
        if (studentId.isEmpty()) {
            view.checkEmpty("Mã sinh viên");
            return;
        }

        if (!dao.studentExists(studentId)) {
            view.showStudentNotExist();
            return;
        }

        String message = view.inputMessage();
        if (message.isEmpty()) {
            view.checkEmpty("Nội dung yêu cầu");
            return;
        }

        SupportRequest request = new SupportRequest(studentId, message);
        dao.add(request);
        view.notifyRequestAdded();
    }
}