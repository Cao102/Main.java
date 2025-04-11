package Controller;

import DAO.DormitoryDAO;
import Model.Dormitory;
import Model.StudentDormitory;

import java.util.List;

public class DormitoryController {
    private DormitoryDAO dormitoryDAO = new DormitoryDAO();

    public void assignDormitoryController(String studentId, String dormId) {
        dormitoryDAO.assignDormitoryDAO(studentId, dormId);
    }

    public void removeDormitoryController(String studentId) {
        dormitoryDAO.removeDormitoryDAO(studentId);
    }

    public StudentDormitory getDormitoryByStudentController(String studentId) {
        return dormitoryDAO.getDormitoryByStudentDAO(studentId);
    }

    public List<Dormitory> getAvailableDormsController() {
        return dormitoryDAO.getAvailableDormsDAO();
    }

    public List<Dormitory> getAllDormsController() {
        return dormitoryDAO.getAllDormsDAO();
    }

    public List<StudentDormitory> getStudentsInDormController(String dormId) {
        return dormitoryDAO.getStudentsInDormDAO(dormId);
    }

    // Hiển thị thông tin phòng của sinh viên
    public void displayStudentDormitory(StudentDormitory studentDorm) {
        if (studentDorm == null) {
            System.out.println("Sinh viên không có phòng ký túc xá");
            return;
        }

        System.out.println("----------------------------------------------");
        System.out.println("Mã sinh viên : " + studentDorm.getStudentId());
        System.out.println("Tên sinh viên: " + studentDorm.getStudentName());
        System.out.println("Mã phòng     : " + studentDorm.getDormId());
        System.out.println("Số phòng     : " + studentDorm.getRoomNumber());
        System.out.println("----------------------------------------------");
    }

    // Hiển thị danh sách phòng trống
    public void displayAvailableDorms(List<Dormitory> dormList) {
        if (dormList.isEmpty()) {
            System.out.println("Không có phòng trống");
            return;
        }

        System.out.println("================ DANH SÁCH PHÒNG TRỐNG ================");
        System.out.println("| Mã phòng | Số phòng | Sức chứa | Đã sử dụng | Còn trống |");
        System.out.println("|----------|----------|----------|------------|-----------|");

        for (Dormitory dorm : dormList) {
            System.out.printf("| %-8s | %-8s | %-8d | %-10d | %-9d |\n",
                    dorm.getDormId(),
                    dorm.getRoomNumber(),
                    dorm.getCapacity(),
                    dorm.getOccupiedCount(),
                    dorm.getAvailableSpace());
        }
        System.out.println("======================================================");
    }

    // Hiển thị danh sách sinh viên trong phòng
    public void displayStudentsInDorm(List<StudentDormitory> studentList, String dormId, String roomNumber) {
        if (studentList.isEmpty()) {
            System.out.println("Không có sinh viên nào trong phòng " + roomNumber);
            return;
        }

        System.out.println("================ DANH SÁCH SINH VIÊN PHÒNG " + roomNumber + " ================");
        System.out.println("| Mã sinh viên | Tên sinh viên               |");
        System.out.println("|--------------|----------------------------|");

        for (StudentDormitory student : studentList) {
            System.out.printf("| %-12s | %-28s |\n", student.getStudentId(), student.getStudentName());
        }
        System.out.println("================================================================");
    }
}
