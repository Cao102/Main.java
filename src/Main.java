

import View.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("====== STUDENT MANAGEMENT SYSTEM ======");
        System.out.println("1. Quản lý kí túc xá");
        System.out.println("2. Báo cáo thống kê");
        System.out.println("3. Quản lý môn học");
        System.out.println("=================================");
        System.out.print("Chọn chức năng: ");

        int i = Integer.parseInt(sc.nextLine());
        if (i == 1) {
            DormitoryView.main(null);
        }
        else if (i == 2) {
            ReportView.main(null);
        } else if (i == 3) {
            SubjectView.main(null);
        }  else {
            System.out.println("Lựa chọn không hợp lệ!");
        }
    }
}