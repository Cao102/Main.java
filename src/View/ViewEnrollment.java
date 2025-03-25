package View;

import Model.Enrollment;
import java.util.List;

public class ViewEnrollment implements ObjectView<Enrollment>{
    private Input input = new Input();

    public int menuObject() {
        System.out.print("""
        
        ╔════════════════════════════════════════╗
        ║        QUẢN LÝ ĐĂNG KÝ KHÓA HỌC        ║
        ╠════════════════════════════════════════╣
        ║ 1.  Đăng ký khoá học                   ║
        ║ 2.  Hiển thị danh sách đăng ký         ║
        ║ 3.  Chỉnh sửa đơn đăng ký              ║
        ║ 4.  Xóa đăng ký                        ║
        ║ 5.  Quay lại giao diện                 ║
        ╚════════════════════════════════════════╝
        """);
        return input.inputInt("Nhập lựa chọn");
    }

    public Enrollment addObject() {
        System.out.println("\nNhập thông tin đăng ký khóa học:");
        int student_id = input.inputInt("Nhập ID sinh viên");
        int course_id = input.inputInt("Nhập ID khóa học");
        return new Enrollment(student_id, course_id);
    }

    public void getAllObject(List<Enrollment> enrollmentList) {
        if (enrollmentList.isEmpty()) {
            System.out.println("Không có đơn đăng ký nào.\n");
            return;
        }
        System.out.print("""
        
        ╔═══════════════════════════════════════════╗
        ║          DANH SÁCH ĐƠN ĐĂNG KÝ            ║
        ╠════════════╦═══════════════╦══════════════╣
        ║   ID ĐK    ║ Tên Sinh Viên ║ Tên Khóa Học ║
        ╠════════════╬═══════════════╬══════════════╣
        """);

        for (Enrollment enrollment : enrollmentList) {
            System.out.println(enrollment);
        }
        System.out.println("╚════════════╩═══════════════╩══════════════╝");
    }

    public Enrollment updateObject() {
        System.out.println("\nCập nhật thông tin đăng ký:");
        int id = input.inputInt("Nhập ID đăng ký cần chỉnh sửa");
        int student_id = input.inputInt("Nhập ID sinh viên mới");
        int course_id = input.inputInt("Nhập ID khóa học mới");
        return new Enrollment(id, student_id, course_id);
    }

    public int deleteObject() {
        return input.inputInt("\nNhập ID đơn đăng ký cần xóa");
    }

}
