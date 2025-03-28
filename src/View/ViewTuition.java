package View;

import Model.Tuition;
import java.util.List;

public class ViewTuition {
    private final Input input = new Input();

    private boolean checkEmpty(String s) {
        if (s.isEmpty()) {
            System.out.println("Vui lòng không để học phí trống");
            return true;
        }
        return false;
    }

    public int menuTuition() {
        System.out.print("""
            
            ╔════════════════════════════════════════╗
            ║           QUẢN LÝ HỌC PHÍ              ║
            ╠════════════════════════════════════════╣
            ║ 1. Thêm học phí                        ║
            ║ 2. Hiển thị thông tin học phí          ║
            ║ 3. Cập nhật học phí                    ║
            ║ 4. Xóa học phí                         ║
            ║ 5. Tìm kiếm học phí theo ID            ║
            ║ 6. Quay lại                            ║
            ╚════════════════════════════════════════╝
            """);
        return input.inputInt("Nhập lựa chọn của bạn");
    }

    public void errorChoose() {
        System.out.println("Lựa chọn không hợp lệ! Vui lòng nhập lại.");
    }

    public int getStudentId() {
        int student_id;
        while (true) {
            String line = input.inputString("Nhập ID sinh viên");
            if (!line.matches("\\d++")) {
                System.out.println("Nhập lại ID chỉ bao gồm số");
                continue;
            }
            student_id = Integer.parseInt(line);
            break;
        }
        return student_id;
    }

    public double getAmount() {
        double amount;
        while (true) {
            String line = input.inputString("Nhập số tiền học phí");
            if (checkEmpty(line)) {
                continue;
            }
            try {
                amount = Double.parseDouble(line);
                if (amount < 0) {
                    System.out.println("Số tiền học phí không thể nhỏ hơn 0. Vui lòng nhập lại.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Số tiền học phí không hợp lệ! Vui lòng nhập lại.");
            }
        }
        return amount;
    }

    public void getAllTuition(List<Tuition> tuitionList) {
        if (tuitionList.isEmpty()) {
            System.out.println("\nKhông có học phí nào.\n");
            return;
        }
        System.out.print("""
                ╔═════════════════════════════════════════════╗
                ║             DANH SÁCH HỌC PHÍ               ║
                ╠══════╦════════════════╦═════════════════════╣
                ║ STT  ║  Mã sinh viên  ║       Học phí       ║
                ╠══════╬════════════════╬═════════════════════╣
                """);

        // In danh sách học phí, STT tự động tăng từ 1
        int stt = 1;
        for (Tuition tuition : tuitionList) {
            System.out.println(String.format("║ %-4d ", stt++) + tuition); // In ra STT và thông tin học phí
        }
        System.out.println(""" 
                ╚══════╩════════════════╩═════════════════════╝
                """);
    }


    public void checkTuitionAdded() {
        System.out.println("Học phí đã được thêm thành công!");
    }

    public void checkTuitionUpdated() {
        System.out.println("Học phí đã được cập nhật thành công!");
    }

    public void checkTuitionDeleted() {
        System.out.println("Học phí đã được xóa thành công!");
    }

    public void showTuitionDetails(Tuition tuition) {
        if (tuition == null) {
            System.out.println("Không tìm thấy học phí cho sinh viên với ID đã nhập.");
        } else {
            System.out.println("Thông tin học phí:");
            System.out.print("""
                ╔════════════════╦═════════════════════╗
                ║  Mã sinh viên  ║       Học phí       ║
                ╠════════════════╬═════════════════════╣
                """);

            System.out.println(tuition);
            System.out.println(""" 
                ╚════════════════╩═════════════════════╝
                """);
        }
    }
}
