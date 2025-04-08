package View;

import Controller.GradeController;

import java.util.Scanner;

public class ViewGrade {
    public static void main(String[] args) {
        GradeController controller = new GradeController();
        Scanner sc = new Scanner(System.in);

        System.out.println("Thao tác điểm:");
        System.out.println("1. Nhập điểm");
        System.out.println("2. Sửa điểm");
        System.out.println("3. Show điểm các môn của 1 sinh viên");
        System.out.println("4. Show điểm các sinh viên của 1 môn");
        System.out.println("5. Tính GPA của sinh viên");
        System.out.print("Chọn thao tác (1-5): ");

        int n = Integer.parseInt(sc.nextLine());

        if (n == 1) {
            System.out.print("Nhập ID sinh viên: ");
            String studentID = sc.nextLine();
            System.out.print("Nhập mã môn học: ");
            String subjectID = sc.nextLine();
            System.out.print("Nhập điểm sinh viên: ");
            double grade = sc.nextDouble();
            controller.addGradeController(studentID, subjectID, grade);
        } else if (n == 2) {
            System.out.print("Nhập ID sinh viên: ");
            String studentID = sc.nextLine();
            System.out.print("Nhập mã môn học: ");
            String subjectID = sc.nextLine();
            System.out.print("Nhập điểm mới: ");
            double grade = sc.nextDouble();
            controller.updateGradeController(studentID, subjectID, grade);
        } else if (n == 3) {
            System.out.print("Nhập ID sinh viên: ");
            String studentID = sc.nextLine();
            controller.showGradeByStudentController(studentID);
        } else if (n == 4) {
            System.out.print("Nhập ID môn học: ");
            String subjectID = sc.nextLine();
            controller.showGradeBySubjectController(subjectID);
        } else if (n == 5) {
            System.out.print("Nhập ID sinh viên: ");
            String studentID = sc.nextLine();
            double gpa = controller.calculateGrade(studentID);
            System.out.printf("GPA của sinh viên %s là: %.2f\n", studentID, gpa);
        } else {
            System.out.println("Lựa chọn không hợp lệ!");
        }
    }
}

