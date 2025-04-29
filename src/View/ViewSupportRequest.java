package View;

import Model.SupportRequest;

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
        return input.inputString("Nhập mã sinh viên ( VD: S001)");
    }

    public String inputMessage() {
        return input.inputString("Nhập nội dung yêu cầu hỗ trợ");
    }

    public int inputRequestId() {
        return input.inputInt("Nhập ID yêu cầu hỗ trợ(VD: 1)");
    }

    public String inputNewMessage() {
        return input.inputString("Nhập nội dung yêu cầu hỗ trợ mới");
    }

    public String inputNewStatus() {
        return input.inputString("Nhập trạng thái mới (Pending/Resolved)");
    }

    public void displayRequests(List<SupportRequest> requests) {
        if (requests.isEmpty()) {
            System.out.println("Không có yêu cầu hỗ trợ nào.");
            return;
        }

        System.out.print("""
        ╔══════╦══════════════╦══════════════════════════════════════════════════════════╦══════════════╗
        ║ ID   ║ Mã sinh viên ║ Nội dung yêu cầu hỗ trợ                                  ║ Trạng thái   ║
        ╠══════╬══════════════╬══════════════════════════════════════════════════════════╬══════════════╣
        """);

        for (SupportRequest req : requests) {
            System.out.printf("║ %-4d ║ %-12s ║ %-50s ║ %-12s ║\n",
                    req.getId(), req.getStudentId(), req.getMessage(), req.getStatus());
        }

        System.out.println("""
        ╚══════╩══════════════╩══════════════════════════════════════════════════════════╩══════════════╝
        """);
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

    public void errorChoose() {
        System.out.println("Lựa chọn không hợp lệ! Vui lòng nhập lại.");
    }
    public void showStudentNotExist() {
        System.out.println("Mã sinh viên không tồn tại.");
    }

}
