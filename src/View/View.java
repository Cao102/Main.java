package View;

import java.util.Scanner;

public class View {
    private static final Scanner sc = new Scanner(System.in);
    public void menuView() {

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
                ║ 12.  Quản lý Hỗ Trợ Sinh Viên    ║
                ║ 13.  Báo Cáo Và Thống Kê         ║
                ║ 14.  Quản lý người dùng          ║
                ║ 0.  Đăng Xuất                    ║
                ╚══════════════════════════════════╝
                """);
    }
    public String inputChoose(){
        System.out.print("Nhập lựa chọn: ");
        return sc.nextLine().trim();
    }
    public void errorChoose(){
        System.out.println("Vui lòng nhập lại lựa chọn chỉ có số");
    }
    public void exit(){
        System.out.println("Thoát chương trình...");
    }
}
