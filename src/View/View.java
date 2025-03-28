package View;

import java.util.Scanner;

public class View {
    public int menuView() {
        Scanner sc = new Scanner(System.in);
            System.out.print("""
            ╔══════════════════════════════════╗
            ║         HỆ THỐNG QUẢN LÝ         ║
            ╠══════════════════════════════════╣
            ║ 1.  Quản lý Sinh Viên            ║
            ║ 2.  Quản lý Khóa Học             ║
            ║ 3.  Quản lý Đăng Ký Khóa Học     ║
            ║ 4.  Quản lý Lớp Học              ║
            ║ 5.  Quản lý Giáo Viên            ║
            ║ 6.  Quản lý Học Phí              ║
            ║ 7.  Thoát                        ║
            ╚══════════════════════════════════╝
            Nhập lựa chọn: \s""");
            return Integer.parseInt(sc.nextLine());
    }
}
