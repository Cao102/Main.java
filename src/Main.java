import Controller.RegistrationController;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RegistrationController controller = new RegistrationController();
        Scanner scanner = new Scanner(System.in);

        System.out.println("===== QUẢN LÝ ĐĂNG KÝ MÔN HỌC =====");
        System.out.println("1. Đăng ký môn học");
        System.out.println("2. Hủy đăng ký môn học");
        System.out.println("3. Xem danh sách môn học của sinh viên");
        System.out.println("4. Xem danh sách sinh viên của môn học");
        System.out.print("Chọn chức năng: ");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.print("Nhập mã sinh viên: ");
                int studentId1 = scanner.nextInt();
                System.out.print("Nhập mã môn học: ");
                int subjectId1 = scanner.nextInt();
                controller.registerSubject(studentId1, subjectId1);
                break;

            case 2:
                System.out.print("Nhập mã sinh viên: ");
                int studentId2 = scanner.nextInt();
                System.out.print("Nhập mã môn học: ");
                int subjectId2 = scanner.nextInt();
                controller.cancelSubject(studentId2, subjectId2);
                break;

            case 3:
                System.out.print("Nhập mã sinh viên: ");
                int studentId3 = scanner.nextInt();
                controller.showRegisteredSubjects(studentId3);
                break;

            case 4:
                System.out.print("Nhập mã môn học: ");
                int subjectId3 = scanner.nextInt();
                controller.showStudentsBySubject(subjectId3);
                break;

            default:
                System.out.println("Lựa chọn không hợp lệ.");
        }

        scanner.close();
    }
}
