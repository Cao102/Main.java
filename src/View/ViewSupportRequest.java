package View;

import Model.SupportRequest;
import util.TableUtils;

import java.util.List;

public class ViewSupportRequest {
    private final Input input = new Input();

    public int menuSupport() {
        System.out.print("""
                
                ╔════════════════════════════════════════════════════════╗
                ║              QUẢN LÝ YÊU CẦU HỖ TRỢ SINH VIÊN          ║
                ╠════════════════════════════════════════════════════════╣
                ║ 1. Gửi yêu cầu hỗ trợ                                  ║
                ║ 2. Xem yêu cầu hỗ trợ                                  ║
                ║ 3. Sửa yêu cầu hỗ trợ                                  ║
                ║ 4. Xóa yêu cầu hỗ trợ                                  ║
                ║ 5. Cập nhật trạng thái yêu cầu hỗ trợ                  ║
                ║ 0. Quay lại                                            ║
                ╚════════════════════════════════════════════════════════╝
                """);
        return input.inputInt("Nhập lựa chọn của bạn");
    }

    public String inputStudentId() {
        return input.inputString("Nhập mã sinh viên (VD: S001)");
    }

    public String inputMessage() {
        return input.inputString("Nhập nội dung yêu cầu hỗ trợ");
    }

    public int inputRequestId() {
        return input.inputInt("Nhập ID yêu cầu hỗ trợ (VD: 1)");
    }

    public String inputNewMessage() {
        return input.inputString("Nhập nội dung yêu cầu hỗ trợ mới (hoặc Enter để giữ nguyên)");
    }

    public String inputNewStatus() {
        while (true) {
            String status = input.inputString("Nhập trạng thái mới (Pending/Resolved) hoặc Enter để giữ nguyên");
            if (status.isEmpty()) {
                return status; // Trả về chuỗi rỗng để giữ nguyên giá trị cũ
            }
            if (status.equalsIgnoreCase("Pending") || status.equalsIgnoreCase("Resolved")) {
                return capitalize(status); // Chuẩn hóa lại đầu vào
            }
            System.out.println("Trạng thái không hợp lệ! Vui lòng nhập 'Pending', 'Resolved' hoặc Enter để giữ nguyên.");
        }
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    public void displayRequests(List<SupportRequest> requests) {
        if (requests.isEmpty()) {
            System.out.println("Không có yêu cầu hỗ trợ nào.");
            return;
        }

        String[] headers = {"ID", "Mã Sinh Viên", "Nội Dung", "Trạng Thái"};
        System.out.println("\nDANH SÁCH YÊU CẦU HỖ TRỢ:");
        TableUtils.printTable(requests, headers);
    }

    public void showUpdateInfo() {
        System.out.println("Nhập thông tin cần chỉnh sửa (Enter để giữ nguyên giá trị cũ):");
    }

    public void showCurrentStatus(String currentStatus) {
        System.out.println("Trạng thái hiện tại: " + currentStatus);
    }

    public void checkEmpty(String fieldName) {
        System.out.println("Vui lòng không để " + fieldName + " trống");
    }

    public void notifyRequestAdded() {
        System.out.println("Yêu cầu hỗ trợ đã được gửi thành công!");
    }

    public void notifyRequestUpdated() {
        System.out.println("Yêu cầu hỗ trợ đã được cập nhật thành công!");
    }

    public void notifyRequestDeleted() {
        System.out.println("Yêu cầu hỗ trợ đã được xóa thành công!");
    }

    public void notifyStatusUpdated() {
        System.out.println("Trạng thái yêu cầu đã được cập nhật thành công!");
    }

    public void showRequestNotExist() {
        System.out.println("Không tìm thấy yêu cầu hỗ trợ với ID đã nhập.");
    }

    public void showStudentNotExist() {
        System.out.println("Mã sinh viên không tồn tại.");
    }

    public void errorChoose() {
        System.out.println("Lựa chọn không hợp lệ! Vui lòng nhập lại.");
    }
}