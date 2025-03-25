package View;

import Model.Class;
import java.util.List;

public class ViewClass implements ObjectView<Class> {
    private final Input input = new Input();
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
            """);
        return input.inputInt("Nhập lựa chọn của bạn");
    }

    public Class addObject() {
        System.out.println("\nNhập Thông tin Lớp Học");
        String name = input.inputString("Nhập Tên Lớp");
        int teacher_id = input.inputInt("Nhập Id Giáo Viên Quản Lý");
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
        int class_id = input.inputInt("Nhập id lớp cần cần chỉnh");
        String class_name = input.inputString("Nhập tên mới");
        int teacher_id = input.inputInt("Nhập Id Giáo Viên Quản Lý");
        return new Class(class_id, class_name, teacher_id);
    }

    public int deleteObject() {
        return input.inputInt("\nNhập ID Lớp Học Cần Xoá: ");
    }
}
