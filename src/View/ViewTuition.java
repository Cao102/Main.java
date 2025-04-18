package View;

import Model.Tuition;
import util.TableUtils;

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
            ║ 4. Cập nhật trạng thái học phí         ║
            ║ 5. Tìm kiếm học phí theo ID            ║
            ║ 6. Quay lại                            ║
            ╚════════════════════════════════════════╝
            """);
        return input.inputInt("Nhập lựa chọn của bạn");
    }

    public void errorChoose() {
        System.out.println("Lựa chọn không hợp lệ! Vui lòng nhập lại.");
    }

    public String inputStudentId() {
        return input.inputString("Nhập ID sinh viên");
    }

    public String inputStatus(){ return input.inputString("Nhập trạng thái học phí");   }

    public double inputAmount() {
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

    public void getAllTuition(List<Tuition> objectList) {
        if (objectList.isEmpty()) {
            System.out.println("Không có học phí nào.");
            return;
        }
        String[] headers = {"Mã SV", "Học Phí", "Trạng Thái"};
        System.out.println("\nDANH SÁCH HỌC PHÍ:");
        TableUtils.printTable(objectList, headers);
//        System.out.print("""
//            ╔══════════════════════════════════════════════════════════╗
//            ║                     DANH SÁCH HỌC PHÍ                    ║
//            ╠══════╦════════════════╦═════════════════════╦════════════╣
//            ║ STT  ║  Mã sinh viên  ║       Học phí       ║ Trạng thái ║
//            ╠══════╬════════════════╬═════════════════════╬════════════╣
//            """);
//
//        // In danh sách học phí, STT tự động tăng từ 1
//        int stt = 1;
//        for (Tuition tuition : tuitionList) {
//            System.out.println(String.format("║ %-4d ", stt++) + tuition); // In ra STT, thông tin học phí và trạng thái
//        }
//        System.out.println("""
//            ╚══════╩════════════════╩═════════════════════╩════════════╝
//            """);
    }



    public void checkTuitionAdded() {
        System.out.println("Học phí đã được thêm thành công!");
    }

    public void checkTuitionUpdated() {
        System.out.println("Học phí đã được cập nhật thành công!");
    }

    public void checkTuitionUpdateStatus() {
        System.out.println("Trạng thái học phí đã được cập nhật thành công!");
    }
    public boolean confirmUpdateTuition() {
        System.out.print("Sinh viên đã có học phí trước đó. Bạn có muốn cập nhật học phí không? (Y/N) ");
        String choice = input.inputString("");
        return choice.equalsIgnoreCase("Y");  // Nếu người dùng chọn 'Y', trả về true
    }
    public void showStudentNotExist() {
        System.out.println("Sinh viên không tồn tại trong hệ thống. Vui lòng kiểm tra lại ID.");
    }
    public void showTuitionNotExist(){
        System.out.println("Không tìm thấy học phí cho sinh viên với ID đã nhập.");
    }
    public void showTuitionAlreadyPaid(){
        System.out.println("Học phí đã được nộp. Không thể cập nhật");
    }
    public void showTuitionByStudentId(Tuition tuition) {
        System.out.println("Thông tin học phí:");
        System.out.print("""
                ╔════════════════╦═════════════════════╦════════════╗
                ║  Mã sinh viên  ║       Học phí       ║ Trạng thái ║
                ╠════════════════╬═════════════════════╬════════════╣
                """);

        System.out.println(tuition);
        System.out.println(""" 
                ╚════════════════╩═════════════════════╩════════════╝
                """);
    }
}
