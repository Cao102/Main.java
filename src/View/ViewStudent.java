package View;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import Model.Student;
import util.TableUtils;

public class ViewStudent {
    private final Input input = new Input();
    // Input

    public int getChoose() {
        return input.inputInt("Nhập Lựa Chọn của bạn");
    }

    private Date validateDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);

        try {
            java.util.Date utilDate = sdf.parse(dateStr);
            return new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) {
            return null;
        }
    }

    public String getID() {
        String id;
        while (true) {
            id = input.inputString("Nhập ID SV");
            if (id.length() > 10) {
                System.out.println("Vui Lòng Không Nhập ID Quá 10 Ký Tự");
                continue;
            }
            break;
        }
        return id;
    }

    public String getName() {
        String name;
        while (true) {
            name = input.inputString("Nhập Tên SV");
            if (name.length() > 50) {
                System.out.println("Vui lòng nhập tên không quá 50 ký tự");
                continue;
            }
            break;
        }
        return name;
    }

    public Date getDob() {
        Date dob;
        while (true) {
            String dobStr = input.inputString("Nhập Ngày Sinh (yyyy-MM-dd)");
            dob = validateDate(dobStr);
            if (dobStr.isEmpty()) {
                break;
            }
            if (dob == null) {
                System.out.println("Ngày Sinh Không Hợp Lệ! Vui Lòng Nhập Lại Theo Định Dạng yyyy-MM-dd.");
                continue;
            }
            break;
        }
        return dob;
    }

    public String getGender() {
        String gender;
        while (true) {
            gender = input.inputString("Nhập Giới Tính SV (Male, Female, Other)");
            if (gender.isEmpty()) break;
            String checkGender = gender.toLowerCase();
            if (!(checkGender.equals("male") || checkGender.equals("female") || checkGender.equals("other"))) {
                System.out.println("Nhập lại Giới Tính Với Đầu Vào Là Male, Female, Other");
                continue;
            }
            break;
        }
        return gender;
    }

    public String getEmail() {
        String Email;
        while (true) {
            Email = input.inputString("Nhập Email Sinh Viên (****@gmail.com)");
            if (Email.isEmpty()) break;
            if (Email.length() > 50) {
                System.out.println("Vui Lòng Nhập Không Quá 50 Ký Tự");
                continue;
            }
            if (!Email.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")) {
                System.out.println("Email Không Hợp Lệ! Vui Lòng Nhập Lại Với Đuôi @gmail.com.");
                continue;
            }
            break;
        }
        return Email;
    }

    public String getPhone() {
        String phone;
        while (true) {
            phone = input.inputString("Nhập SĐT Sinh Viên (10 số)");
            if (phone.isEmpty()) return "";
            if (!phone.matches("^\\d{10}$")) {
                System.out.println("Số Điện Thoại Không Hợp Lệ! Vui Lòng Nhập Lại (10 Chữ Số).");
                continue;
            }
            break;
        }
        return phone;
    }

    public String getAddress() {
        String address;
        while (true) {
            address = input.inputString("Nhập Địa Chỉ SV");
            if (address.length() > 50) {
                System.out.println("Vui Lòng Không Nhập Quá 50 Ký Tự");
                continue;
            }
            break;
        }
        return address;
    }

    // Hàm chạy
    public void menuObject() {
        System.out.print("""
                
                ╔════════════════════════════════════════╗
                ║           QUẢN LÝ Sinh Viên            ║
                ╠════════════════════════════════════════╣
                ║ 1. Thêm Sinh Viên                      ║
                ║ 2. Hiển Thị Thông Tin Sinh Viên        ║
                ║ 3. Chỉnh Sửa Thông Tin Sinh Viên       ║
                ║ 4. Xóa Thông Tin Sinh Viên             ║
                ║ 5. Tìm kiếm Thông Tin Sinh Viên        ║
                ║ 0. Quay Lại                            ║
                ╚════════════════════════════════════════╝
                """);
    }

    public void addObject() {
        System.out.println("Nhập Thông Tin Mới (Hoặc Enter Để Quay Lại):");
    }

    public void updateObject() {
        System.out.println("""
                Nhập Thông Tin Cần Chỉnh Sửa (Hoặc Enter Để Quay Lại)
                Sau Khi Nhập ID Thì Enter Để Giữ Lại Thông Tin Cũ)
                """);
    }

    public void deleteObject() {
        System.out.println("Nhập ID Cần Xoá (Hoặc Enter Để Quay Lại)");
    }

    public void getAllObject(List<Student> objectList) {
        if (objectList.isEmpty()) {
            System.out.println("\nKhông Có Sinh Viên Nào.\n");
            return;
        }

        String[] headers = {"Mã SV", "Họ Tên", "Ngày Sinh", "Giới Tính", "Email", "SĐT", "Địa Chỉ"};
        System.out.println("\nDANH SÁCH SINH VIÊN:");
        TableUtils.printTable(objectList, headers);
    }

    public void viewSearch() {
        System.out.print("""
                
                ╔════════════════════════════════════════╗
                ║           Tìm Kiếm Thông Tin           ║
                ╠════════════════════════════════════════╣
                ║ 1. Tìm Kiếm Theo MSV                   ║
                ║ 2. Tìm Kiếm Theo Tên SV                ║
                ║ 3. Tìm Kiếm Theo Ngày Sinh             ║
                ║ 4. Tìm Kiếm Theo Giới Tính             ║
                ║ 5. Tìm Kiếm Theo Email                 ║
                ║ 6. Tìm Kiếm Theo SĐT                   ║
                ║ 7. Tìm Kiếm Theo Địa Chỉ               ║
                ║ 0. Quay Lại                            ║
                ╚════════════════════════════════════════╝
                """);
    }

    // Check
    public void checkEmpty(String message) {
        System.out.println("Vui Lòng Không Để " + message + " Trống");
    }

    public void checkID(String message) {
        System.out.println("ID " + message + " Tồn Tại. Vui Lòng Nhập Lại");
    }

    public void checkEmail() {
        System.out.println("Email Đã Tồn Tại. Vui Lòng Nhập Lại");
    }

    public void checkPhone() {
        System.out.println("SĐT Đã Tồn Tại. Vui Lòng Nhập Lại");
    }

    public void errorChoose() {
        System.out.println("Lựa Chọn Không Hợp Lệ! Vui Lòng Nhập Lại.");
    }

    public void successful(String message) {
        System.out.println("Thực Hiện Thành Công " + message + " SV");
    }
}
