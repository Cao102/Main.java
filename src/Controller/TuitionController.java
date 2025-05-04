package Controller;

import DAO.TuitionDAO;
import Model.Tuition;
import View.ViewTuition;

import java.util.List;

public class TuitionController {

    private final TuitionDAO tuitionDAO;
    private final ViewTuition viewTuition;

    public TuitionController() {
        this.tuitionDAO = new TuitionDAO();
        this.viewTuition = new ViewTuition();
    }

    public void startTuition() {
        while (true) {
            int input = viewTuition.menuTuition();  // Hiển thị menu và nhận lựa chọn của người dùng
            switch (input) {
                case 1:
                    addTuition();
                    break;
                case 2:
                    displayTuitionDetails();
                    break;
                case 3:
                    updateTuition();
                    break;
                case 4:
                    updateTuitionStatus();
                    break;
                case 5:
                    searchByStudentId();
                    break;
                case 0:
                    return;
                default:
                    viewTuition.errorChoose();
                    break;
            }
        }
    }

    private void addTuition() {
        while (true) {
            String studentId = viewTuition.inputStudentId();
            if (!tuitionDAO.isValueExist(studentId, "Students", "student_id")) {
                viewTuition.showStudentNotExist();
                continue; // cho nhập lại ID
            }
            String tuitionName = viewTuition.inputTutionName();
            Tuition existingTuition = tuitionDAO.getTuitionByStudentIdAndName(studentId, tuitionName);
            if (existingTuition == null) {
                double amount = viewTuition.inputAmount();
                Tuition newTuition = new Tuition(studentId, amount, tuitionName);
                tuitionDAO.add(newTuition);
                viewTuition.checkTuitionAdded();

            } else {
                boolean update = viewTuition.confirmUpdateTuition();
                if (update) {
                    if (existingTuition.getStatus().equals("Đã nộp")) {
                        viewTuition.showTuitionAlreadyPaid();  // Thông báo học phí đã được nộp
                        continue;
                    }
                    double amount = viewTuition.inputAmount();
                    Tuition tuition = new Tuition(studentId, amount, tuitionName);
                    tuitionDAO.update(tuition);
                    viewTuition.checkTuitionUpdated();
                } else {
                    continue;
                }
            }
            break;
        }
    }

    private void displayTuitionDetails() {
        List<Tuition> tuitionList = tuitionDAO.getAll();
        viewTuition.getAllTuition(tuitionList);  // Hiển thị danh sách học phí
    }

    private void updateTuition() {
        while (true) {
            String studentId = viewTuition.inputStudentId();
            String tuitionName = viewTuition.inputTutionName();
            Tuition existingTuition = tuitionDAO.getTuitionByStudentIdAndName(studentId, tuitionName);
            if (existingTuition == null) {
                viewTuition.showTuitionNotExist();
                continue;
            } else {
                if (existingTuition.getStatus().equals("Đã nộp")) {
                    viewTuition.showTuitionAlreadyPaid();  // Thông báo học phí đã được nộp
                    continue;
                }
                double amount = viewTuition.inputAmount();  // Lấy số tiền học phí mới
                Tuition tuition = new Tuition(studentId, amount, tuitionName);
                tuitionDAO.update(tuition);
                viewTuition.checkTuitionUpdated();
            }
            break;
        }
    }

    private void updateTuitionStatus() {
        while (true) {
            String studentId = viewTuition.inputStudentId();
            String tuitionName = viewTuition.inputTutionName();
            Tuition existingTuition = tuitionDAO.getTuitionByStudentIdAndName(studentId, tuitionName);
            if (existingTuition == null) {
                viewTuition.showTuitionNotExist();
                continue;
            } else {
                String status = viewTuition.inputStatus();
                tuitionDAO.updateStatus(studentId, tuitionName, status);
                viewTuition.checkTuitionUpdateStatus();
            }
            break;
        }
    }

    private void searchByStudentId() {
        while (true) {
            String studentId = viewTuition.inputStudentId();
            List<Tuition> tuitionList = tuitionDAO.searchByStudentId(studentId);
            if (tuitionList == null || tuitionList.isEmpty()) {
                viewTuition.showTuitionNotExist();
            } else {
                viewTuition.getAllTuition(tuitionList);
                break;
            }
        }
    }
}
