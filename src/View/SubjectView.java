package View;

import Controller.SubjectController;
import Model.Subject;

import java.util.List;
import java.util.Scanner;

public class SubjectView {
    public static void main(String[] args) {
        SubjectController controller = new SubjectController();
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n========= QUẢN LÝ MÔN HỌC =========");
            System.out.println("1. Thêm môn học");
            System.out.println("2. Sửa môn học");
            System.out.println("3. Xóa môn học");
            System.out.println("4. Xem thông tin môn học");
            System.out.println("5. Xem danh sách môn học");
            System.out.println("6. Xem danh sách môn học theo giảng viên");
            System.out.println("0. Thoát");
            System.out.println("==================================");

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
                    addSubject(controller, sc);
                    break;
                case 2:
                    updateSubject(controller, sc);
                    break;
                case 3:
                    deleteSubject(controller, sc);
                    break;
                case 4:
                    getSubjectById(controller, sc);
                    break;
                case 5:
                    getAllSubjects(controller);
                    break;
                case 6:
                    getSubjectsByTeacher(controller, sc);
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

    private static void addSubject(SubjectController controller, Scanner sc) {
        System.out.println("--- Thêm Môn Học ---");
        System.out.print("Nhập mã môn học: ");
        String subjectId = sc.nextLine();
        System.out.print("Nhập tên môn học: ");
        String name = sc.nextLine();
        System.out.print("Nhập mô tả: ");
        String description = sc.nextLine();

        Subject subject = new Subject(subjectId, name, description);
        controller.addSubjectController(subject);
    }

    private static void updateSubject(SubjectController controller, Scanner sc) {
        System.out.println("--- Cập Nhật Môn Học ---");
        System.out.print("Nhập mã môn học cần cập nhật: ");
        String subjectId = sc.nextLine();

        // Kiểm tra môn học tồn tại
        Subject existingSubject = controller.getSubjectByIdController(subjectId);
        if (existingSubject == null) {
            System.out.println("Không tìm thấy môn học với mã " + subjectId);
            return;
        }

        System.out.print("Nhập tên môn học mới: ");
        String name = sc.nextLine();
        System.out.print("Nhập mô tả mới: ");
        String description = sc.nextLine();

        Subject subject = new Subject(subjectId, name, description);
        controller.updateSubjectController(subject);
    }

    private static void deleteSubject(SubjectController controller, Scanner sc) {
        System.out.println("--- Xóa Môn Học ---");
        System.out.print("Nhập mã môn học cần xóa: ");
        String subjectId = sc.nextLine();
        controller.deleteSubjectController(subjectId);
    }

    private static void getSubjectById(SubjectController controller, Scanner sc) {
        System.out.println("--- Xem Thông Tin Môn Học ---");
        System.out.print("Nhập mã môn học: ");
        String subjectId = sc.nextLine();
        Subject subject = controller.getSubjectByIdController(subjectId);
        controller.displaySubject(subject);
    }

    private static void getAllSubjects(SubjectController controller) {
        System.out.println("--- Danh Sách Môn Học ---");
        List<Subject> subjectList = controller.getAllSubjectsController();
        controller.displaySubjectList(subjectList);
    }

    private static void getSubjectsByTeacher(SubjectController controller, Scanner sc) {
        System.out.println("--- Danh Sách Môn Học Theo Giảng Viên ---");
        System.out.print("Nhập mã giảng viên: ");
        String teacherId = sc.nextLine();
        List<Subject> subjectList = controller.getSubjectsByTeacherController(teacherId);

        if (subjectList.isEmpty()) {
            System.out.println("Không có môn học nào cho giảng viên " + teacherId);
            return;
        }

        System.out.println("Danh sách môn học của giảng viên " + teacherId + ":");
        controller.displaySubjectList(subjectList);
    }
}
