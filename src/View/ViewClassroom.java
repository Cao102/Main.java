package View;

import Model.Classroom;
import java.util.List;

public class ViewClassroom implements ObjectView<Classroom> {
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

    public Classroom addObject() {
        System.out.println("\nNhập Thông tin Lớp Học");
        String name = input.inputString("Nhập Tên Lớp");
        int teacher_id = input.inputInt("Nhập sức chứa");
        return new Classroom(name, teacher_id);
    }

    public void getAllObject(List<Classroom> objectList) {
        if (objectList.isEmpty()) {
            System.out.println("\nHiện không có lớp học nào.\n");
            return;
        }
        System.out.print("""
            
            ╔════════════════════════════╗
            ║     DANH SÁCH LỚP HỌC      ║
            ╠════╦════════════════╦══════╣
            ║ ID ║     Tên Lớp    ║  SL  ║
            ╠════╬════════════════╬══════╣
            """);

        for (Classroom object : objectList) {
            System.out.println(object);
        }
        System.out.println("╚════╩════════════════╩══════╝");
    }

    public Classroom updateObject() {
        System.out.println("\nChỉnh sửa Thông tin Lớp Học");
        int class_id = input.inputInt("Nhập id lớp cần cần chỉnh");
        String class_name = input.inputString("Nhập tên mới");
        int capacity = input.inputInt("Nhập sức chứa");
        return new Classroom(class_id, class_name, capacity);
    }

    public int deleteObject() {
        return input.inputInt("\nNhập ID Lớp Học Cần Xoá");
    }
}
