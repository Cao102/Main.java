package View;

import Model.Classroom;

import java.util.List;

public class ViewClassroom {
    private final Input input = new Input();

    public void checkEmpty(String message) {
        System.out.println("Không được để " + message + " trắng");
    }

    public void checkID(String message) {
        System.out.println(message);
    }

    public String getID() {
        return input.inputString("Nhập ID");
    }

    public String getName() {
        return input.inputString("Nhập Tên");
    }

    public int getCapacity() {
        while (true) {
            String line = input.inputString("Nhập Sức Chứa");
            if (line.isEmpty()) return -1;
            if (!line.matches("\\d+")) {
                System.out.println("Đầu vào là Sô");
                continue;
            }
            return Integer.parseInt(line);
        }
    }

    public int menuObject() {
        System.out.print("""
                ╔════════════════════════════════════════╗
                ║           QUẢN LÝ LỚP HỌC              ║
                ╠════════════════════════════════════════╣
                ║ 1. Thêm Lớp Học                        ║
                ║ 2. Hiển Thị Thông tin Lớp Học          ║
                ║ 3. Chỉnh Sửa Thông tin Lớp Học         ║
                ║ 4. Xóa Thông Tin Lớp Học               ║
                ║ 5. Tìm Kiếm Thông Tin Lớp Học          ║
                ║ 6. Quay Lại                            ║
                ╚════════════════════════════════════════╝
                """);
        return input.inputInt("Nhập lựa chọn của bạn");
    }

    public void addObject() {
        System.out.println("\nNhập Thông tin Lớp Học (hoặc Enter để quay lại):");
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

    public void updateObject() {
        System.out.println("\nChỉnh sửa Thông tin Lớp Học (hoặc Enter để quay lại):");
    }

    public void deleteObject() {
        System.out.println("Nhập thông tin xoá (hoặc Enter để quay lại):");
    }

    public int viewSearch() {
        System.out.print("""
                
                ╔════════════════════════════════════════╗
                ║           Tìm Kiếm Thông Tin           ║
                ╠════════════════════════════════════════╣
                ║ 1. Tìm kiếm theo Mã Lớp                ║
                ║ 2. Tìm kiếm theo Tên Lớp               ║
                ║ 3. Tìm kiếm theo Sức Chứa              ║
                ║ 4. Quay lại                            ║
                ╚════════════════════════════════════════╝
                """);
        return input.inputInt("Nhập lựa chọn");
    }

    public void errorChoose() {
        System.out.println("Lựa chọn không hợp lệ! Vui lòng nhập lại.");
    }

    public void successful(String message) {
        System.out.println("Thực hiện thành công " + message + " Lớp");
    }
}
