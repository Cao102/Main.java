package View;

import Controller.RegistrationController;
import java.util.Scanner;

public class ViewRegister {
    public static void main(String[] args) {
        RegistrationController controller = new RegistrationController();
        Scanner sc = new Scanner(System.in);

        System.out.println("===== QUẢN LÝ ĐĂNG KÝ MÔN HỌC =====");
        System.out.println("1. Đăng ký môn học");
        System.out.println("2. Hủy đăng ký môn học");
        System.out.println("3. Xem danh sách môn học của sinh viên");
        System.out.println("4. Xem danh sách sinh viên của môn học");
        System.out.print("Chọn chức năng: ");

        int choice = Integer.parseInt(sc.nextLine());

        switch (choice) {
            case 1:
                System.out.print("Nhập mã sinh viên: ");
                String studentId1 = sc.nextLine();
                System.out.print("Nhập mã môn học: ");
                String subjectId1 = sc.nextLine();
                controller.registerSubject(studentId1, subjectId1);
                break;

            case 2:
                System.out.print("Nhập mã sinh viên: ");
                String studentId2 = sc.nextLine();
                System.out.print("Nhập mã môn học: ");
                String subjectId2 = sc.nextLine();
                controller.cancelSubject(studentId2, subjectId2);
                break;

            case 3:
                System.out.print("Nhập mã sinh viên: ");
                String studentId3 = sc.nextLine();
                controller.showRegisteredSubjects(studentId3);
                break;

            case 4:
                System.out.print("Nhập mã môn học: ");
                String subjectId3 = sc.nextLine();
                controller.showStudentsBySubject(subjectId3);
                break;

            default:
                System.out.println("Lựa chọn không hợp lệ.");
        }

    }
}

