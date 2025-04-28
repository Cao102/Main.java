import View.ViewCalenda;
import View.ViewGrade;
import View.ViewRegister;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("=== MENU CHÍNH ===");
            System.out.println("1. Quản lý lịch học");
            System.out.println("2. Quản lý điểm");
            System.out.println("3. Quản lý đăng ký");
            System.out.println("0. Thoát chương trình");
            System.out.print("Chọn chức năng: ");

            int i = Integer.parseInt(sc.nextLine());

            if (i == 1) {
                ViewCalenda.main(null);
            } else if (i == 2) {
                ViewGrade.main(null);
            } else if (i == 3) {
                ViewRegister.main(null);
            } else if (i == 0) {
                System.out.println("Đăng xuất");
                break;
            } else {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }
}
