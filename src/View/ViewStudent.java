package View;

import java.util.List;
import Model.Student;

public class ViewStudent implements ObjectView<Student>{
    private final Input input = new Input();
    public int menuObject() {
            System.out.print("""
            
            ╔════════════════════════════════════════╗
            ║           QUẢN LÝ SINH VIÊN            ║
            ╠════════════════════════════════════════╣
            ║ 1. Thêm sinh viên                      ║
            ║ 2. Hiển thị thông tin sinh viên        ║
            ║ 3. Chỉnh sửa thông tin sinh viên       ║
            ║ 4. Xóa thông tin sinh viên             ║
            ║ 5. Quay lại                            ║
            ╚════════════════════════════════════════╝
            """);
            return input.inputInt("Nhập lựa chọn của bạn");
    }

    public Student addObject() {
        System.out.println("\nNhập thông tin sinh viên:");
        String name = input.inputString("Nhập tên sinh viên");
        String dob = input.inputString("Nhập ngày sinh (yyyy-MM-dd)");
        String email = input.inputString("Nhập email sinh viên");
        String phone = input.inputString("Nhập SĐT sinh viên");
        int class_id = input.inputInt("Nhập mã lớp sinh viên");
        return new Student(name, dob, email, phone, class_id);
    }

    public void getAllObject(List<Student> objectList) {
        if (objectList.isEmpty()) {
            System.out.println("\nHiện không có khóa học nào.\n");
            return;
        }
        System.out.print("""
                ╔════════════════════════════════════════════════════════════════════════════════════╗
                ║                               DANH SÁCH SINH VIÊN                                  ║
                ╠══════╦════════════════╦═════════════════════╦══════════════╦════════════╦══════════╣
                ║  ID  ║     Tên        ║        Email        ║  Ngày sinh   ║     SĐT    ║ Mã lớp   ║
                ╠══════╬════════════════╬═════════════════════╬══════════════╬════════════╬══════════╣
                """);
        for (Student object : objectList) {
            System.out.println(object);
        }
        System.out.println("╚══════╩════════════════╩═════════════════════╩══════════════╩════════════╩══════════╝");
    }

    public Student updateObject() {
        System.out.println("\n️Chỉnh sửa thông tin sinh viên:");
        System.out.print(": ");
        int student_id = input.inputInt("Nhập ID sinh viên cần chỉnh");
        String name = input.inputString("Nhập tên mới");
        String dob = input.inputString("Nhập ngày sinh mới (yyyy-MM-dd)");
        String email = input.inputString("Nhập email mới");
        String phone = input.inputString("Nhập SĐT mới");
        int class_id = input.inputInt("Nhập mã lớp mới");

        return new Student(student_id, name, dob, email, phone, class_id);
    }

    public int deleteObject() {
        return input.inputInt("Nhập ID sinh viên cần xoá");
    }
}
