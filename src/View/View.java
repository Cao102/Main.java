package View;

import java.util.Scanner;

public class View {
    public int menuView() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("""
            ╔═══════════════════════════════════╗
            ║         HỆ THỐNG QUẢN LÝ          ║
            ╠═══════════════════════════════════╣
            ║ 1.  Quản lý Sinh viên             ║
            ║ 2.  Quản lý Môn học               ║
            ║ 3.  Quản lý Đăng ký khóa học      ║
            ║ 4.  Quản lý Lớp học               ║
            ║ 5.  Quản lý Giáo viên             ║
            ║ 6.  Quản lý Học phí               ║
            ║ 7.  Báo cáo Thống kê              ║
            ║ 8.  Quản lý Sự Hỗ trợ             ║
            ║ 9.  Thoát                         ║
            ╚═══════════════════════════════════╝
            Nhập lựa chọn của bạn: """);
        return Integer.parseInt(scanner.nextLine());
    }
}
