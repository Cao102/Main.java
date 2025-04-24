package View;

import Model.Teacher;
import util.TableUtils;

import java.math.BigDecimal;
import java.util.List;

public class ViewTeacher {
    private final Input input = new Input();

    public void checkEmpty(String message) {
        System.out.println("Vui Lòng Không Để " + message + " Trống");
    }

    public String getID() {
        String id;
        while (true) {
            id = input.inputString("Nhập ID GV");
            if (id.length() > 10){
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
            name = input.inputString("Nhập Tên GV");
            if (name.length() >= 100) {
                System.out.println("Tên Không Được Quá 100 Ký Tự");
                continue;
            }
            break;
        }
        return name;
    }

    public String getEmail() {
        String email;
        while (true) {
            email = input.inputString("Nhập Email GV (@gmail.com)");
            if (email.isEmpty()) break;
            if (!email.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")) {
                System.out.println("Email Không Hợp Lệ! Vui Lòng Nhập Lại Với Đuôi @gmail.com.");
                continue;
            }
            break;
        }
        return email;
    }

    public String getPhone() {
        String phone;
        while (true) {
            phone = input.inputString("Nhập SĐT GV (10 số)");
            if (phone.isEmpty()) break;
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
            address = input.inputString("Nhập Địa Chỉ GV");
            if (address.isEmpty()) break;
            if (address.length() >= 255) {
                System.out.println("Vui Lòng Không Nhấp Quá 255 Ký Tự");
                continue;
            }
            break;
        }
        return address;
    }

    public int getYOE() {
        int years_of_experience;
        while (true) {
            String line = input.inputString("Nhập Năm Kinh Nghiệm");
            if (line.isEmpty()) {
                years_of_experience = -1;
                break;
            }
            if (!line.matches("\\d+")) {
                System.out.println("Đầu Vào Chỉ Chứa Các Chữ Số");
                continue;
            }
            years_of_experience = Integer.parseInt(line);
            break;
        }
        return years_of_experience;
    }

    public BigDecimal getBaseSalary() {
        BigDecimal bigDecimal;
        while (true) {
            String line = input.inputString("Nhập Mức Lương Cơ Bản Của GV");
            if (line.isEmpty()) {
                bigDecimal = new BigDecimal(-1);
                break;
            }
            if (!line.matches("\\d+")) {
                System.out.println("Nhập Lại Với Input Chỉ Bao Gồm Số");
                continue;
            }
            try {
                bigDecimal = new BigDecimal(line);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Giá Trị Quá Lớn Hoặc Không Hợp Lệ, Vui Lòng Nhập Lại.");
            }
        }
        return bigDecimal;
    }

    public int menuObject() {
        System.out.print("""
                
                ╔════════════════════════════════════════╗
                ║           QUẢN LÝ GIÁO VIÊN            ║
                ╠════════════════════════════════════════╣
                ║ 1. Thêm Giáo Viên                      ║
                ║ 2. Hiển Thị Thông Tin Giáo Viên        ║
                ║ 3. Chỉnh Sửa Thông Tin Giáo Viên       ║
                ║ 4. Xóa Thông Tin Giáo Viên             ║
                ║ 5. Tìm Kiếm Thông Tin Giáo Viên        ║
                ║ 0. Quay lại                            ║
                ╚════════════════════════════════════════╝
                """);
        return input.inputInt("Nhập Lựa Chọn Của Bạn");
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
        System.out.println("Nhập ID Cần Xoá (Hoặc Enter Để Quay Lại):");
    }

    public void getAllObject(List<Teacher> objectList) {
        if (objectList.isEmpty()) {
            System.out.println("\nHiện Không Có Giáo Viên Nào.\n");
            return;
        }
        String[] headers = {"Mã GV", "Họ Tên", "Email", "Số ĐT", "Địa Chỉ", "SNKN", "LươngCB", "Lương"};
        System.out.println("\nDANH SÁCH GIÁO VIÊN:");
        TableUtils.printTable(objectList, headers);
    }

    public int viewSearch() {
        System.out.print("""
                
                ╔════════════════════════════════════════╗
                ║           Tìm Kiếm Thông Tin           ║
                ╠════════════════════════════════════════╣
                ║ 1. Tìm Kiếm Theo MGV                   ║
                ║ 2. Tìm Kiếm Theo Tên GV                ║
                ║ 3. Tìm Kiếm Theo Email                 ║
                ║ 4. Tìm Kiếm Theo SĐT                   ║
                ║ 5. Tìm Kiếm Theo ĐC                    ║
                ║ 6. Tìm Kiếm Theo SNCT                  ║
                ║ 7. Tìm Kiếm Theo Lương CB              ║
                ║ 0. Quay Lại                            ║
                ╚════════════════════════════════════════╝
                """);
        return input.inputInt("Nhập lựa chọn");
    }

    public void checkID(String message) {
        System.out.println("ID " + message + " Tồn Tại. Vui Lòng Nhập Lại");
    }

    public void checkEmail() {
        System.out.println("Email Đã Tồn Tại. Vui Lòng Nhập Lại");
    }

    public void errorChoose() {
        System.out.println("Lựa Chọn Không Hợp Lệ! Vui Lòng Nhập Lại.");
    }

    public void successful(String message) {
        System.out.println("Thực Hiện Thành Công " + message + " GV");
    }
}

