package View;

import Model.Course;
import java.util.List;
import java.util.Scanner;

public class ViewCourse implements ObjectView<Course>{
    private final Scanner sc = new Scanner(System.in);

    public int menuObject() {
        System.out.print("""
        
        ╔════════════════════════════════════════╗
        ║           QUẢN LÝ KHÓA HỌC             ║
        ╠════════════════════════════════════════╣
        ║ 1. Thêm khóa học                       ║
        ║ 2. Hiển thị danh sách khóa học         ║
        ║ 3. Chỉnh sửa thông tin khóa học        ║
        ║ 4. Xóa khóa học                        ║
        ║ 5. Quay lại giao diện                  ║
        ╚════════════════════════════════════════╝
        Nhập lựa chọn: \s""");
        return getIntInput();
    }

    public Course addObject() {
        System.out.println("\nNhập thông tin khóa học mới:");
        System.out.print("Nhập tên khóa học: ");
        String name = sc.nextLine();
        int credit = getValidCredit();
        return new Course(name, credit);
    }

    public void getAllObject(List<Course> objectList) {
        if (objectList.isEmpty()) {
            System.out.println("\nHiện không có khóa học nào.\n");
            return;
        }
        System.out.print("""
        
        ╔════════════════════════════════════════════════════════╗
        ║                   DANH SÁCH KHOÁ HỌC                   ║
        ╠══════════════╦══════════════════════════╦══════════════╣
        ║ ID Khóa Học  ║       Tên Khóa Học       ║  Số Tín Chỉ  ║
        ╠══════════════╬══════════════════════════╬══════════════╣
        """);

        for (Course object : objectList) {
            System.out.println(object);
        }

        System.out.println("╚══════════════╩══════════════════════════╩══════════════╝");
    }

    public Course updateObject() {
        System.out.println("\nCập nhật thông tin khóa học:");
        int id = getValidId("Nhập ID khóa học cần chỉnh sửa: ");
        System.out.print("Nhập tên khóa học mới: ");
        String name = sc.nextLine();
        int credit = getValidCredit();
        return new Course(id, name, credit);
    }

    public int deleteObject() {
        return getValidId("\nNhập ID khóa học cần xóa: ");
    }

    // ------------------ Các phương thức hỗ trợ ------------------

    // Lấy số nguyên từ người dùng và xử lý lỗi nhập liệu
    private int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Lỗi! Vui lòng nhập số nguyên hợp lệ: ");
            }
        }
    }

    // Lấy ID hợp lệ
    private int getValidId(String message) {
        System.out.print(message);
        return getIntInput();
    }

    // Lấy số tín chỉ hợp lệ (phải > 0)
    private int getValidCredit() {
        int credit;
        do {
            System.out.print("Nhập số tín chỉ: ");
            credit = getIntInput();
            if (credit <= 0) {
                System.out.println("Số tín chỉ phải lớn hơn 0!");
            }
        } while (credit <= 0);
        return credit;
    }
}
