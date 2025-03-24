package View;

import java.util.List;
import java.util.Scanner;
import Model.Student;

public class ViewStudent implements ObjectView<Student>{
    private static final Scanner sc = new Scanner(System.in);
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
            Nhập lựa chọn của bạn: \s""");
            return Integer.parseInt(sc.nextLine());
    }

    public Student addObject() {
        System.out.println("\n Nhập thông tin sinh viên:");
        System.out.print(" Nhập tên sinh viên: ");
        String name = sc.nextLine();

        System.out.print(" Nhập ngày sinh (yyyy-MM-dd): ");
        String dob = sc.nextLine();

        System.out.print(" Nhập email sinh viên: ");
        String email = sc.nextLine();

        System.out.print(" Nhập SĐT sinh viên: ");
        String phone = sc.nextLine();

        System.out.print(" Nhập mã lớp sinh viên: ");
        int class_id = Integer.parseInt(sc.nextLine());

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
        System.out.print("Nhập ID sinh viên cần chỉnh: ");
        int student_id = Integer.parseInt(sc.nextLine());

        System.out.print("Nhập tên mới: ");
        String name = sc.nextLine();

        System.out.print("Nhập ngày sinh mới (yyyy-MM-dd): ");
        String dob = sc.nextLine();

        System.out.print(" Nhập email mới: ");
        String email = sc.nextLine();

        System.out.print(" Nhập SĐT mới: ");
        String phone = sc.nextLine();

        System.out.print(" Nhập mã lớp mới: ");
        int class_id = Integer.parseInt(sc.nextLine());

        return new Student(student_id, name, dob, email, phone, class_id);
    }

    public int deleteObject() {
        System.out.print("\nNhập ID sinh viên cần xoá: ");
        return Integer.parseInt(sc.nextLine());
    }
}
