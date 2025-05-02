package View;

import Controller.DormitoryController;
import Model.Dormitory;
import Model.StudentDormitory;

import java.util.List;
import java.util.Scanner;

public class DormitoryView {
    public static void main(String[] args) {
        DormitoryController controller = new DormitoryController();
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n========= QUẢN LÝ PHÒNG KÝ TÚC XÁ =========");
            System.out.println("1. Gán phòng cho sinh viên");
            System.out.println("2. Hủy phòng sinh viên");
            System.out.println("3. Xem thông tin phòng của sinh viên");
            System.out.println("4. Xem danh sách phòng trống");
            System.out.println("5. Xem danh sách tất cả phòng");
            System.out.println("6. Xem danh sách sinh viên trong phòng");
            System.out.println("0. Thoát");
            System.out.println("=========================================");

            System.out.print("Chọn chức năng: ");
            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số!");
                continue;
            }

            switch (choice) {
                case 1:
                    assignDormitory(controller, sc);
                    break;
                case 2:
                    removeDormitory(controller, sc);
                    break;
                case 3:
                    getDormitoryByStudent(controller, sc);
                    break;
                case 4:
                    displayAvailableDorms(controller);
                    break;
                case 5:
                    displayAllDorms(controller);
                    break;
                case 6:
                    displayStudentsInDorm(controller, sc);
                    break;
                case 0:
                    exit = true;
                    System.out.println("Đã thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        }
    }

    private static void assignDormitory(DormitoryController controller, Scanner sc) {
        System.out.println("--- Gán Phòng Cho Sinh Viên ---");

        // Hiển thị danh sách phòng trống
        List<Dormitory> availableDorms = controller.getAvailableDormsController();
        controller.displayAvailableDorms(availableDorms);

        if (availableDorms.isEmpty()) {
            System.out.println("Không có phòng trống để gán");
            return;
        }

        System.out.print("Nhập mã sinh viên: ");
        String studentId = sc.nextLine();

        // Kiểm tra xem sinh viên đã có phòng chưa
        StudentDormitory existingDorm = controller.getDormitoryByStudentController(studentId);
        if (existingDorm != null) {
            System.out.println("Sinh viên đã được gán phòng " + existingDorm.getRoomNumber());
            System.out.println("Hãy hủy phòng trước khi gán phòng mới");
            return;
        }

        System.out.print("Nhập mã phòng: ");
        String dormId = sc.nextLine();

        controller.assignDormitoryController(studentId, dormId);
    }

    private static void removeDormitory(DormitoryController controller, Scanner sc) {
        System.out.println("--- Hủy Phòng Sinh Viên ---");
        System.out.print("Nhập mã sinh viên: ");
        String studentId = sc.nextLine();

        // Kiểm tra xem sinh viên có phòng không
        StudentDormitory studentDorm = controller.getDormitoryByStudentController(studentId);
        if (studentDorm == null) {
            System.out.println("Sinh viên " + studentId + " không có phòng ký túc xá");
            return;
        }

        System.out.println("Thông tin phòng của sinh viên:");
        controller.displayStudentDormitory(studentDorm);

        System.out.print("Bạn có chắc muốn hủy phòng này? (Y/N): ");
        String confirm = sc.nextLine();

        if (confirm.equalsIgnoreCase("Y")) {
            controller.removeDormitoryController(studentId);
        } else {
            System.out.println("Đã hủy thao tác");
        }
    }

    private static void getDormitoryByStudent(DormitoryController controller, Scanner sc) {
        System.out.println("--- Xem Thông Tin Phòng Của Sinh Viên ---");
        System.out.print("Nhập mã sinh viên: ");
        String studentId = sc.nextLine();

        StudentDormitory studentDorm = controller.getDormitoryByStudentController(studentId);
        controller.displayStudentDormitory(studentDorm);
    }

    private static void displayAvailableDorms(DormitoryController controller) {
        System.out.println("--- Danh Sách Phòng Trống ---");
        List<Dormitory> availableDorms = controller.getAvailableDormsController();
        controller.displayAvailableDorms(availableDorms);
    }

    private static void displayAllDorms(DormitoryController controller) {
        System.out.println("--- Danh Sách Tất Cả Phòng ---");
        List<Dormitory> allDorms = controller.getAllDormsController();
        controller.displayAvailableDorms(allDorms);
    }

    private static void displayStudentsInDorm(DormitoryController controller, Scanner sc) {
        System.out.println("--- Xem Danh Sách Sinh Viên Trong Phòng ---");
        System.out.print("Nhập mã phòng: ");
        String dormId = sc.nextLine();

        // Lấy thông tin phòng (chủ yếu để lấy số phòng)
        List<Dormitory> allDorms = controller.getAllDormsController();
        String roomNumber = "";
        for (Dormitory dorm : allDorms) {
            if (dorm.getDormId().equals(dormId)) {
                roomNumber = dorm.getRoomNumber();
                break;
            }
        }

        if (roomNumber.isEmpty()) {
            System.out.println("Không tìm thấy phòng với mã " + dormId);
            return;
        }

        List<StudentDormitory> studentsInDorm = controller.getStudentsInDormController(dormId);
        controller.displayStudentsInDorm(studentsInDorm, dormId, roomNumber);
    }
}