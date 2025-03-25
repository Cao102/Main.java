package View;

import Model.Course;
import java.util.List;

public class ViewCourse implements ObjectView<Course>{
    private final Input input = new Input();

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
        """);
        return input.inputInt("Nhập lựa chọn");
    }

    public Course addObject() {
        System.out.println("\nNhập thông tin khóa học mới:");
        String name = input.inputString("Nhập tên khóa học");
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
        int id = input.inputInt("Nhập ID khóa học cần chỉnh sửa");
        String name = input.inputString("Nhập tên khóa học mới");
        int credit = getValidCredit();
        return new Course(id, name, credit);
    }

    public int deleteObject() {
        return input.inputInt("\nNhập ID khóa học cần xóa");
    }

    private int getValidCredit() {
        int credit;
        do {
            credit = input.inputInt("Nhập số tín chỉ");
            if (credit <= 0) {
                System.out.println("Số tín chỉ phải lớn hơn 0!");
            }
        } while (credit <= 0);
        return credit;
    }
}
