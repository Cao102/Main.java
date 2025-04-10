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
                ║ 2.  Quản lý Giáo Viên            ║
                ║ 3.  Quản lý Môn Học              ║
                ║ 4.  Quản lý Lớp                  ║
                ║ 5.  Quản lý Đăng Ký Môn Học      ║
                ║ 6.  Quản lý Điểm Số              ║
                ║ 7.  Quản lý Lịch Học             ║
                ║ 8.  Quản lý Học Phí              ║
                ║ 9.  Quản lý Thư Viện             ║
                ║ 10.  Quản lý Kỳ Thi              ║
                ║ 11.  Quản lý Sự Kiện             ║
                ║ 12.  Quản lý Ký Túc Xá           ║
                ║ 13.  Thoát                       ║
                ╚══════════════════════════════════╝
                Nhập lựa chọn: \s""");
        return Integer.parseInt(sc.nextLine());
    }
    public void exit(){
        System.out.println("Thoát chương trình...");
    }
}
