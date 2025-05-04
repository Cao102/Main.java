package View;

import Model.Classroom;
import util.TableUtils;

import java.util.List;

public class ViewClassroom {
    private final Input input = new Input();

    public int getChoose () {
        return input.inputInt("Nhập Lựa Chọn của bạn");
    }

    public String getID() {
        while (true){
            String id = input.inputString("Nhập ID");
            if (id.length() > 10){
                System.out.println("Vui lòng nhập ID không quá 10 ký tự");
                continue;
            }
            return id;
        }
    }

    public String getName() {
        while (true){
            String name = input.inputString("Nhập Tên");
            if (name.length() > 50){
                System.out.println("Vui lòng nhập tên không quá 50 ký tự");
                continue;
            }
            return name;
        }
    }

    public int getCapacity() {
        while (true) {
            String line = input.inputString("Nhập Sức Chứa");
            if (line.isEmpty()) return -1;
            if (!line.matches("\\d+")) {
                System.out.println("Đầu Vào Là Số");
                continue;
            }
            if (Integer.parseInt(line) == 0){
                System.out.println("Vui Lòng Nhập Sức Chứa Lớn Hơn 0");
                continue;
            }
            return Integer.parseInt(line);
        }
    }

    public String getLocation() {
        return input.inputString("Nhập Địa Chỉ");
    }

    public void menuObject() {
        System.out.print("""
                
                ╔════════════════════════════════════════╗
                ║           QUẢN LÝ Lớp Học              ║
                ╠════════════════════════════════════════╣
                ║ 1. Thêm Lớp Học                        ║
                ║ 2. Hiển Thị Thông Tin Lớp Học          ║
                ║ 3. Chỉnh Sửa Thông Tin Lớp Học         ║
                ║ 4. Xóa Thông Tin Lớp Học               ║
                ║ 5. Tìm Kiếm Thông Tin Lớp Học          ║
                ║ 0. Quay Lại                            ║
                ╚════════════════════════════════════════╝
                """);
    }

    public void addObject() {
        System.out.println("\nNhập Thông tin Lớp Học (Hoặc Enter Để Quay Lại):");
    }

    public void getAllObject(List<Classroom> objectList) {
        if (objectList.isEmpty()) {
            System.out.println("\nHiện Không Có Lớp Học Nào.\n");
            return;
        }
        String[] headers = {"Mã Lớp", "Tên Lớp", "Sức Chứa", "Vị Trí"};
        System.out.println("\nDANH SÁCH LỚP HỌC:");
        TableUtils.printTable(objectList, headers);
    }

    public void updateObject() {
        System.out.println("\nChỉnh Sửa Thông Tin Lớp Học (Hoặc Enter Để Quay Lại):");
    }

    public void deleteObject() {
        System.out.println("Nhập Thông Tin Xoá (Hoặc Enter Để Quay Lại):");
    }

    public void viewSearch() {
        System.out.print("""
                
                ╔════════════════════════════════════════╗
                ║           Tìm Kiếm Thông tin           ║
                ╠════════════════════════════════════════╣
                ║ 1. Tìm Kiếm Theo Mã Lớp                ║
                ║ 2. Tìm Kiếm Theo Tên Lớp               ║
                ║ 3. Tìm Kiếm Theo Sức Chứa              ║
                ║ 4. Tìm Kiếm Theo Vị Trí                ║
                ║ 0. Quay Lại                            ║
                ╚════════════════════════════════════════╝
                """);
    }

    public void checkEmpty(String message) {
        System.out.println("Không Được Để " + message + " Trắng");
    }

    public void checkID(String message) {
        System.out.println(message);
    }

    public void errorChoose() {
        System.out.println("Lựa Chọn Không Hợp Lệ! Vui Lòng Nhập Lại.");
    }

    public void successful(String message) {
        System.out.println("Thực Hiện Thành Công " + message + " Lớp");
    }
}
