package View;

import Model.Teacher;

import java.util.List;
import java.util.Scanner;

public class ViewTeacher implements ObjectView<Teacher> {
    private static final Scanner sc = new Scanner(System.in);
    public int menuObject(){
        System.out.print("""
            ╔════════════════════════════════════════╗
            ║           QUẢN LÝ GIÁO VIÊN            ║
            ╠════════════════════════════════════════╣
            ║ 1. Thêm giáo viên                      ║
            ║ 2. Hiển thị thông tin giáo viên        ║
            ║ 3. Chỉnh sửa thông tin giáo viên       ║
            ║ 4. Xóa thông tin giáo viên             ║
            ║ 5. Quay lại                            ║
            ╚════════════════════════════════════════╝
            Nhập lựa chọn của bạn: \s""");
        return Integer.parseInt(sc.nextLine());
    }
    public Teacher addObject() {
        System.out.println("\nNhập thông tin giáo viên:");
        System.out.print("Nhập tên giáo viên: ");
        String name = sc.nextLine();

        System.out.print("Nhập email giáo viên: ");
        String email = sc.nextLine();

        System.out.print("Nhập SĐT giáo viên: ");
        String phone = sc.nextLine();

        return new Teacher(name, email, phone);
    }

    public void getAllObject(List<Teacher> objectList) {
        if (objectList.isEmpty()) {
            System.out.println("\nHiện không có khóa học nào.\n");
            return;
        }
        System.out.println("""
                      \s
                      ╔══════════════════════════════════════════════════════════════╗
                      ║                      DANH SÁCH GIÁO VIÊN                     ║
                      ╠═══════╦════════════════╦════════════════════════╦════════════╣
                      ║  ID   ║  TÊN GIÁO VIÊN ║           EMAIL        ║     SĐT    ║
                      ╠═══════╬════════════════╬════════════════════════╬════════════╣\s""");

        for (Teacher object : objectList) {
            System.out.println(object);
        }
        System.out.println("╚═══════╩════════════════╩════════════════════════╩════════════╝");
    }

    public Teacher updateObject() {
        System.out.println("\n️Chỉnh sửa thông tin giáo viên:");
        System.out.print("Nhập ID giáo viên cần chỉnh: ");
        int teacher_id = Integer.parseInt(sc.nextLine());

        System.out.print("Nhập tên mới: ");
        String name = sc.nextLine();

        System.out.print("Nhập email mới: ");
        String email = sc.nextLine();

        System.out.print("Nhập SĐT mới: ");
        String phone = sc.nextLine();

        return new Teacher(teacher_id, name,email, phone);
    }

    public int deleteObject() {
        System.out.print("\nNhập ID giáo viên cần xoá: ");
        return Integer.parseInt(sc.nextLine());
    }
}
