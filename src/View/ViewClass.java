package View;

import Model.Class;

import java.util.List;
import java.util.Scanner;

public class ViewClass implements ObjectView<Class> {
    private final Scanner sc = new Scanner(System.in);
    public int menuObject() {
        System.out.print("""
            ╔════════════════════════════════════════╗
            ║           QUẢN LÝ LỚP HỌC              ║
            ╠════════════════════════════════════════╣
            ║ 1. Thêm Lớp Học                        ║
            ║ 2. Hiển Thị Thông tin Lớp Học          ║
            ║ 3. Chỉnh Sửa Thông tin Lớp Học         ║
            ║ 4. Xóa Thông Tin Lớp Học               ║
            ║ 5. Quay Lại                            ║
            ╚════════════════════════════════════════╝
            Nhập lựa chọn của bạn:\s""");
        return Integer.parseInt(sc.nextLine());
    }

    public Class addObject() {
        System.out.println("\nNhập Thông tin Lớp Học");
        System.out.print("Nhập Tên Lớp: ");
        String name = sc.nextLine();
        System.out.print("Nhập Id Giáo Viên Quản Lý:");
        int teacher_id = Integer.parseInt(sc.nextLine());
        return new Class(name, teacher_id);
    }

    public void getAllObject(List<Class> objectList) {
        if (objectList.isEmpty()) {
            System.out.println("\nHiện không có lớp học nào.\n");
            return;
        }
        System.out.print("""
            
            ╔════════════════════════════════════════════╗
            ║              DANH SÁCH LỚP HỌC             ║
            ╠════════╦══════════════════╦════════════════╣
            ║   ID   ║     Tên Lớp      ║    Giáo viên   ║
            ╠════════╬══════════════════╬════════════════╣
            """);

        for (Class object : objectList) {
            System.out.println(object);
        }
        System.out.println("╚════════╩══════════════════╩════════════════╝");
    }

    public Class updateObject() {
        System.out.println("\nChỉnh sửa Thông tin Lớp Học");
        System.out.print("Nhập id lớp cần cần chỉnh: ");
        int class_id = Integer.parseInt(sc.nextLine());

        System.out.print("Nhập tên mới: ");
        String class_name = sc.nextLine();

        System.out.print("Nhập Id Giáo Viên Quản Lý:");
        int teacher_id = Integer.parseInt(sc.nextLine());

        return new Class(class_id, class_name, teacher_id);
    }

    public int deleteObject() {
        System.out.print("\nNhập ID Lớp Học Cần Xoá: ");
        return Integer.parseInt(sc.nextLine());
    }
}
