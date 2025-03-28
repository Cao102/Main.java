package Controller;

import DAO.TuitionDAO;
import Model.Tuition;
import View.ViewTuition;
import java.util.List;

public class TuitionController {

    private TuitionDAO tuitionDAO;
    private ViewTuition viewTuition;

    public TuitionController() {
        this.tuitionDAO = new TuitionDAO();
        this.viewTuition = new ViewTuition();  // Khởi tạo ViewTuition để tương tác với người dùng
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
                    deleteTuition();
                    break;
                case 5:
                    searchByStudentId();
                    break;
                case 6:
                    return;  // Quay lại MainController hoặc thoát khỏi chương trình
                default:
                    viewTuition.errorChoose();  // Nếu lựa chọn không hợp lệ
                    break;
            }
        }
    }

    private void addTuition() {
        int studentId = viewTuition.getStudentId();  // Lấy ID sinh viên
        double amount = viewTuition.getAmount();  // Lấy số tiền học phí
        Tuition tuition = new Tuition(studentId, amount);
        tuitionDAO.add(tuition);
        viewTuition.checkTuitionAdded();
    }

    private void displayTuitionDetails() {
        List<Tuition> tuitionList = tuitionDAO.getAll();
        viewTuition.getAllTuition(tuitionList);  // Hiển thị danh sách học phí
    }
    private void updateTuition() {
        int studentId = viewTuition.getStudentId();  // Lấy ID sinh viên
        double amount = viewTuition.getAmount();  // Lấy số tiền học phí mới
        Tuition tuition = new Tuition(studentId, amount);
        tuitionDAO.update(tuition);
        viewTuition.checkTuitionUpdated();
    }

    private void deleteTuition() {
        int studentId = viewTuition.getStudentId();  // Lấy ID sinh viên
        tuitionDAO.delete(studentId);
        viewTuition.checkTuitionDeleted();
    }

    private void searchByStudentId() {
        int studentId = viewTuition.getStudentId();  // Lấy ID sinh viên
        Tuition tuition = tuitionDAO.searchByStudentId(studentId);
        viewTuition.showTuitionDetails(tuition);
    }
}
