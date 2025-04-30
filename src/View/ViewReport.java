
package View;

import Model.Report;
import util.TableUtils;

import java.util.List;

public class ViewReport {
    private final Input input = new Input();

    public int menuReport() {
        System.out.print("""
                
        ╔══════════════════════════════════════════════╗
        ║              BÁO CÁO THỐNG KÊ                ║
        ╠══════════════════════════════════════════════╣
        ║ 1. Xem số lượng sinh viên                    ║
        ║ 2. Xem số lượng giảng viên                   ║
        ║ 3. Xem số lớp học                            ║
        ║ 4. Xem tổng học phí đã thu                   ║
        ║ 5. Xem top sinh viên có GPA cao nhất         ║
        ║ 0. Quay lại                                  ║
        ╚══════════════════════════════════════════════╝
        """);
        return input.inputInt("Nhập lựa chọn của bạn");
    }

    public void displayStudentCount(int count) {
        System.out.println(" Số lượng sinh viên: " + count);
    }

    public void displayTeacherCount(int count) {
        System.out.println(" Số lượng giảng viên: " + count);
    }

    public void displayClassroomCount(int count) {
        System.out.println(" Số lượng lớp học: " + count);
    }

    public void displayTotalTuition(double amount) {
        System.out.printf(" Tổng học phí đã thu: %.2f VND\n", amount);
    }

    public int inputTopLimit() {
        return input.inputInt("Nhập số lượng sinh viên top GPA muốn xem");
    }

    public void displayTopStudents(List<Report> list) {
        if (list.isEmpty()) {
            System.out.println(" Không có sinh viên nào có điểm GPA.");
            return;
        }

        String[] headers = {"Mã Sinh Viên", "Họ Tên", "GPA"};
        System.out.println("\nDANH SÁCH TOP SINH VIÊN CÓ GPA CAO NHẤT:");
        TableUtils.printTable(list, headers);
    }

    public void errorChoose() {
        System.out.println(" Lựa chọn không hợp lệ!");
    }
}
