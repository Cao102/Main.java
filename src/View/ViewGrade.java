package View;

import Controller.GradeController;

import java.util.Scanner;

public class ViewGrade {
    public static void main(String[] args) {
        GradeController controller = new GradeController();
        Scanner sc = new Scanner(System.in);

        System.out.println("========= QUẢN LÝ ĐIỂM =========");
        System.out.println("1. Nhập điểm");
        System.out.println("2. Sửa điểm");
        System.out.println("3. Xem điểm các môn của một sinh viên");
        System.out.println("4. Xem điểm các sinh viên của một môn học");
        System.out.println("5. Tính GPA của sinh viên");
        System.out.println("0. Thoát");
        System.out.println("================================");

        System.out.print("Chọn chức năng: ");
        int i = Integer.parseInt(sc.nextLine());

        switch (i) {
            case 1:
                System.out.println("--- Nhập Điểm ---");
                System.out.print("Nhập ID sinh viên: ");
                String studentID1 = sc.nextLine();
                System.out.print("Nhập mã môn học: ");
                String subjectID1 = sc.nextLine();
                System.out.print("Nhập điểm sinh viên: ");
                double grade1 = Double.parseDouble(sc.nextLine());
                controller.addGradeController(studentID1, subjectID1, grade1);
                break;

            case 2:
                System.out.println("--- Sửa Điểm ---");
                System.out.print("Nhập ID sinh viên: ");
                String studentID2 = sc.nextLine();
                System.out.print("Nhập mã môn học: ");
                String subjectID2 = sc.nextLine();
                System.out.print("Nhập điểm mới: ");
                double grade2 = Double.parseDouble(sc.nextLine());
                controller.updateGradeController(studentID2, subjectID2, grade2);
                break;

            case 3:
                System.out.println("--- Xem Điểm Theo Sinh Viên ---");
                System.out.print("Nhập ID sinh viên: ");
                String studentID3 = sc.nextLine();
                controller.showGradeByStudentController(studentID3);
                break;

            case 4:
                System.out.println("--- Xem Điểm Theo Môn Học ---");
                System.out.print("Nhập mã môn học: ");
                String subjectID4 = sc.nextLine();
                controller.showGradeBySubjectController(subjectID4);
                break;

            case 5:
                System.out.println("--- Tính GPA ---");
                System.out.print("Nhập ID sinh viên: ");
                String studentID5 = sc.nextLine();
                double gpa = controller.calculateGrade(studentID5);
                System.out.printf("GPA của sinh viên %s là: %.2f", studentID5, gpa);
                break;

            case 0:
                System.out.println("Đã thoát chương trình.");
                break;

            default:
                System.out.println("Lựa chọn không hợp lệ!");
                break;
        }

        System.out.println("================================");
    }
}
