package Controller;

import DAO.TuitionDAO;
import Model.Tuition;
import View.ViewTuition;

import java.util.List;

public class TuitionController {

    private final TuitionDAO tuitionDAO;  // Đã khai báo là final
    private final ViewTuition viewTuition;  // Đã khai báo là final

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
        while (true) {
            String studentId = viewTuition.inputStudentId();

            if (!tuitionDAO.isStudentExist(studentId)) {
                viewTuition.showStudentNotExist();
                continue; // cho nhập lại ID
            }

            Tuition existingTuition = tuitionDAO.searchByStudentId(studentId);
            if (existingTuition == null) {
                double amount = viewTuition.inputAmount();
                Tuition tuition = new Tuition(studentId, amount);
                tuitionDAO.add(tuition);
                viewTuition.checkTuitionAdded();
            } else {
                boolean update = viewTuition.confirmUpdateTuition();
                if (update) {
                    double amount = viewTuition.inputAmount();
                    Tuition tuition = new Tuition(studentId, amount);
                    tuitionDAO.update(tuition);
                    viewTuition.checkTuitionUpdated();
                } else {
                    continue;
                }
            }
            break; // kết thúc thêm học phí và quay về menu chính
        }
    }

    private void displayTuitionDetails() {
        List<Tuition> tuitionList = tuitionDAO.getAll();
        viewTuition.getAllTuition(tuitionList);  // Hiển thị danh sách học phí
    }

    private void updateTuition() {
        while(true){
            String studentId = viewTuition.inputStudentId();  // Lấy ID sinh viên
            Tuition existingTuition = tuitionDAO.searchByStudentId(studentId);
            if (existingTuition == null) {
                viewTuition.showTuitionNotExist();  // Thông báo không tồn tại học phí
                continue;
            } else {
                double amount = viewTuition.inputAmount();  // Lấy số tiền học phí mới
                Tuition tuition = new Tuition(studentId, amount);
                tuitionDAO.update(tuition);
                viewTuition.checkTuitionUpdated();
            }
            break;
        }
    }

    private void deleteTuition() {
        while (true){
            String studentId = viewTuition.inputStudentId();  // Lấy ID sinh viên
            Tuition existingTuition = tuitionDAO.searchByStudentId(studentId);
            if (existingTuition == null) {
                viewTuition.showTuitionNotExist();
                continue;
            } else {
                tuitionDAO.delete(studentId);
                viewTuition.checkTuitionDeleted();
            }
            break;
        }
    }
    private void searchByStudentId() {
        while(true){
            String studentId = viewTuition.inputStudentId();  // Lấy ID sinh viên
            Tuition tuition = tuitionDAO.searchByStudentId(studentId);
            if (tuition != null) {
                viewTuition.showTuitionDetails(tuition);
            } else {
                viewTuition.showTuitionNotExist();  // Thông báo học phí không tồn tại
                continue;
            }
            break;
        }
    }
}
