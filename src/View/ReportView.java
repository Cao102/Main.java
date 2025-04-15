package View;

import java.util.List;
import java.util.Scanner;
import Controller.ReportController;
import Model.Report;
import Model.TopStudent;

public class ReportView {
    public static void main(String[] args) {
        ReportController ctrl = new ReportController();
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n========= BÁO CÁO HỆ THỐNG =========");
            System.out.println("1. Xem số lượng sinh viên");
            System.out.println("2. Xem số lượng giảng viên");
            System.out.println("3. Xem số lượng lớp học");
            System.out.println("4. Xem tổng học phí đã thu");
            System.out.println("5. Xem top sinh viên có điểm cao nhất");
            System.out.println("0. Thoát");
            System.out.println("===================================");

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
                    System.out.println("Tổng số sinh viên: " + ctrl.getStudentCount());
                    break;
                case 2:
                    System.out.println("Tổng số giảng viên: " + ctrl.getTeacherCount());
                    break;
                case 3:
                    System.out.println("Tổng số lớp học: " + ctrl.getClassCount());
                    break;
                case 4:
                    System.out.printf("Tổng học phí đã thu: %.2f\n", ctrl.getTotalTuition());
                    break;
                case 5:
                    System.out.print("Nhập số lượng sinh viên muốn hiển thị: ");
                    int limit;
                    try {
                        limit = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Vui lòng nhập số!");
                        continue;
                    }
                    List<TopStudent> top = ctrl.getTopStudents(limit);
                    ctrl.showTopStudents(top);
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
        sc.close();
    }
}