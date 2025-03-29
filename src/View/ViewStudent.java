package View;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import Model.Student;

public class ViewStudent implements ObjectView<Student>{
    private final Input input = new Input();
    private Date validateDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false); // Không cho phép nhập ngày sai

        try {
            java.util.Date utilDate = sdf.parse(dateStr); // Chuyển chuỗi thành java.util.Date
            return new java.sql.Date(utilDate.getTime()); // Chuyển thành java.sql.Date để lưu vào SQL
        } catch (ParseException e) {
            return null; // Nếu sai định dạng, trả về null
        }
    }
    public int menuObject() {
            System.out.print("""
            
            ╔════════════════════════════════════════╗
            ║           QUẢN LÝ SINH VIÊN            ║
            ╠════════════════════════════════════════╣
            ║ 1. Thêm sinh viên                      ║
            ║ 2. Hiển thị thông tin sinh viên        ║
            ║ 3. Chỉnh sửa thông tin sinh viên       ║
            ║ 4. Xóa thông tin sinh viên             ║
            ║ 5. Tìm kiếm thông tin sinh viên        ║
            ║ 6. Quay lại                            ║
            ╚════════════════════════════════════════╝
            """);
            return input.inputInt("Nhập lựa chọn của bạn");
    }

    public Student addObject() {
        System.out.println("\nNhập thông tin sinh viên:");
        String name = input.inputString("Nhập tên sinh viên");
        Date dob;
        while (true) {
            String dobStr = input.inputString("Nhập ngày sinh (yyyy-MM-dd)");
            dob = validateDate(dobStr);
            if (dob != null) {
                break;
            }
            System.out.println("Ngày sinh không hợp lệ! Vui lòng nhập lại theo định dạng yyyy-MM-dd.");
        }
        String email;
        String phone;
        while (true) {
            email = input.inputString("Nhập email sinh viên (@gmail.com)");
            if (email.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")) {
                break;
            }
            System.out.println("Email không hợp lệ! Vui lòng nhập lại với đuôi @gmail.com.");
        }
        while (true) {
            phone = input.inputString("Nhập SĐT sinh viên (10 số)");
            if (phone.matches("^\\d{10}$")) {
                break;
            }
            System.out.println("Số điện thoại không hợp lệ! Vui lòng nhập lại (10 chữ số).");
        }

        return new Student(name, dob, email, phone);
    }


    public void getAllObject(List<Student> objectList) {
        if (objectList.isEmpty()) {
            System.out.println("\nKhông có sinh viên nào.\n");
            return;
        }
        System.out.print("""
                ╔════════════════════════════════════════════════════════════════════════════╗
                ║                               DANH SÁCH SINH VIÊN                          ║
                ╠══════╦════════════════╦════════════════════════╦══════════════╦════════════╣
                ║  ID  ║     Tên        ║          Email         ║   Ngày sinh  ║    SĐT     ║
                ╠══════╬════════════════╬════════════════════════╬══════════════╬════════════╣
                """);
        for (Student object : objectList) {
            System.out.println(object);
        }
        System.out.println("╚══════╩════════════════╩════════════════════════╩══════════════╩════════════╝");
    }

    public Student updateObject() {
        System.out.println("\nChỉnh sửa thông tin sinh viên:");
        int student_id = input.inputInt("Nhập ID sinh viên cần chỉnh");
        String name = input.inputString("Nhập tên mới");
        Date dob;
        while (true) {
            String dobStr = input.inputString("Nhập ngày sinh (yyyy-MM-dd)");
            dob = validateDate(dobStr);
            if (dob != null) {
                break;
            }
            System.out.println("Ngày sinh không hợp lệ! Vui lòng nhập lại theo định dạng yyyy-MM-dd.");
        }
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

        return new Student(student_id, name, dob, email, phone);
    }

    public int deleteObject() {
        return input.inputInt("Nhập ID sinh viên cần xoá");
    }
    public int viewSearch () {
        System.out.print("""
            
            ╔════════════════════════════════════════╗
            ║           Tìm Kiếm Thông Tin           ║
            ╠════════════════════════════════════════╣
            ║ 1. Tìm kiếm theo MSV                   ║
            ║ 2. Tìm kiếm theo Tên SV                ║
            ║ 3. Tìm kiếm theo ngày sinh             ║
            ║ 4. Tìm kiếm theo email                 ║
            ║ 5. Tìm kiếm theo SĐT                   ║
            ║ 6. Quay lại                            ║
            ╚════════════════════════════════════════╝
            """);
        return input.inputInt("Nhập lựa chọn");
    }
    public String infoSearch(){
        return input.inputString("Nhập thông tin tìm kiếm");
    }
}
