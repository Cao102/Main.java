package View;

import Controller.ReportController;
import Model.Report;
import Model.TopStudent;

import java.util.List;
import java.util.Scanner;

public class ReportView {
    public static void main(String[] args) {
        ReportController controller = new ReportController();
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n========= BÁO CÁO THỐNG KÊ =========");
            System.out.println("1. Xem thống kê tổng quan");
            System.out.println("2. Xem số lượng sinh viên");
            System.out.println("3. Xem số lượng giảng viên");
            System.out.println("4. Xem số lượng môn học");
            System.out.println("5. Xem tổng doanh thu học phí");
            System.out.println("6. Xem danh sách sinh viên có điểm cao nhất");
            System.out.println("0. Thoát");
            System.out.println("====================================");

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
                    showReportOverview(controller);
                    break;
                case 2:
                    showStudentCount(controller);
                    break;
                case 3:
                    showTeacherCount(controller);
                    break;
                case 4:
                    showSubjectCount(controller);
                    break;
                case 5:
                    showRevenueFromTuition(controller);
                    break;
                case 6:
                    showTopStudents(controller, sc);
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

    private static void showReportOverview(ReportController controller) {
        System.out.println("--- Thống Kê Tổng Quan ---");
        Report Report = controller.getReportOverviewController();
        controller.displayReportOverview(Report);
    }

    private static void showStudentCount(ReportController controller) {
        System.out.println("--- Số Lượng Sinh Viên ---");
        int count = controller.getStudentCountController();
        System.out.println("Tổng số sinh viên trong hệ thống: " + count);
    }

    private static void showTeacherCount(ReportController controller) {
        System.out.println("--- Số Lượng Giảng Viên ---");
        int count = controller.getTeacherCountController();
        System.out.println("Tổng số giảng viên trong hệ thống: " + count);
    }

    private static void showSubjectCount(ReportController controller) {
        System.out.println("--- Số Lượng Môn Học ---");
        int count = controller.getSubjectCountController();
        System.out.println("Tổng số môn học trong hệ thống: " + count);
    }

    private static void showRevenueFromTuition(ReportController controller) {
        System.out.println("--- Tổng Doanh Thu Học Phí ---");
        double revenue = controller.getRevenueFromTuitionController();
        System.out.println("Tổng doanh thu học phí: " + String.format("%,.2f", revenue) + " VND");
    }

    private static void showTopStudents(ReportController controller, Scanner sc) {
        System.out.println("--- Danh Sách Sinh Viên Có Điểm Cao Nhất ---");
        System.out.print("Nhập số lượng sinh viên muốn hiển thị: ");
        int topN;
        try {
            topN = Integer.parseInt(sc.nextLine());
            if (topN <= 0) {
                System.out.println("Số lượng phải lớn hơn 0.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Vui lòng nhập số hợp lệ!");
            return;
        }

        List<TopStudent> topStudents = controller.getTopStudentsController(topN);
        controller.displayTopStudents(topStudents);
    }
}
