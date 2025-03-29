package View;

import Model.Teacher;
import java.util.List;

public class ViewTeacher implements ObjectView<Teacher> {
    private final Input input = new Input();
    public int menuObject(){
        System.out.print("""
            ╔════════════════════════════════════════╗
            ║           QUẢN LÝ GIÁO VIÊN            ║
            ╠════════════════════════════════════════╣
            ║ 1. Thêm giáo viên                      ║
            ║ 2. Hiển thị thông tin giáo viên        ║
            ║ 3. Chỉnh sửa thông tin giáo viên       ║
            ║ 4. Xóa thông tin giáo viên             ║
            ║ 5. Tìm kiếm thông tin giáo viên        ║
            ║ 6. Quay lại                            ║
            ╚════════════════════════════════════════╝
            """);
        return input.inputInt("Nhập lựa chọn của bạn");
    }
    public Teacher addObject() {
        System.out.println("\nNhập thông tin giáo viên:");
        String name = input.inputString("Nhập tên giáo viên");
        String email;
        while (true) {
            email = input.inputString("Nhập email mới sinh viên (@gmail.com)");
            if (email.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")) {
                break;
            }
            System.out.println("Email không hợp lệ! Vui lòng nhập lại với đuôi @gmail.com.");
        }
        String phone;
        while (true) {
            phone = input.inputString("Nhập SĐT sinh viên (10 số)");
            if (phone.matches("^\\d{10}$")) {
                break;
            }
            System.out.println("Số điện thoại không hợp lệ! Vui lòng nhập lại (10 chữ số).");
        }
        return new Teacher(name, email, phone);
    }

    public void getAllObject(List<Teacher> objectList) {
        if (objectList.isEmpty()) {
            System.out.println("\nHiện không có khóa học nào.\n");
            return;
        }
        System.out.println("""
                      
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
        int teacher_id = input.inputInt("Nhập ID giáo viên cần chỉnh");
        String name = input.inputString("Nhập tên mới");
        String email;
        while (true) {
            email = input.inputString("Nhập email mới sinh viên (@gmail.com)");
            if (email.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")) {
                break;
            }
            System.out.println("Email không hợp lệ! Vui lòng nhập lại với đuôi @gmail.com.");
        }
        String phone;
        while (true) {
            phone = input.inputString("Nhập SĐT sinh viên (10 số)");
            if (phone.matches("^\\d{10}$")) {
                break;
            }
            System.out.println("Số điện thoại không hợp lệ! Vui lòng nhập lại (10 chữ số).");
        }

        return new Teacher(teacher_id, name,email, phone);
    }

    public int deleteObject() {
        return input.inputInt("\nNhập ID giáo viên cần xoá");
    }

    public int viewSearch () {
        System.out.print("""
            
            ╔════════════════════════════════════════╗
            ║           Tìm Kiếm Thông Tin           ║
            ╠════════════════════════════════════════╣
            ║ 1. Tìm kiếm theo MGV                   ║
            ║ 2. Tìm kiếm theo Tên GV                ║
            ║ 3. Tìm kiếm theo email                 ║
            ║ 4. Tìm kiếm theo SĐT                   ║
            ║ 5. Quay lại                            ║
            ╚════════════════════════════════════════╝
            """);
        return input.inputInt("Nhập lựa chọn");
    }
    public String infoSearch(){
        return input.inputString("Nhập thông tin tìm kiếm");
    }
}

