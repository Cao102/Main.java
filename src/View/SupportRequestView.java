package View;

import Controller.SupportRequestController;
import Model.SupportRequest;

import java.util.List;
import java.util.Scanner;

public class SupportRequestView {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SupportRequestController controller = new SupportRequestController();
        int choice;

        do {
            System.out.println("===== QUẢN LÝ YÊU CẦU HỖ TRỢ =====");
            System.out.println("1. Gửi yêu cầu hỗ trợ");
            System.out.println("2. Xem tất cả yêu cầu");
            System.out.println("3. Sửa nội dung yêu cầu");
            System.out.println("4. Xóa yêu cầu hỗ trợ");
            System.out.println("5. Cập nhật trạng thái ");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Nhập mã sinh viên: ");
                    String studentId = sc.nextLine();
                    System.out.print("Nhập nội dung yêu cầu: ");
                    String message = sc.nextLine();
                    controller.sendRequest(studentId, message);
                    break;

                case 2:
                    List<SupportRequest> list = controller.getAllRequests();
                    System.out.printf("| %-3s | %-10s | %-30s | %-10s |\n", "ID", "Mã SV", "Nội dung", "Trạng thái");
                    for (SupportRequest r : list) {
                        System.out.printf("| %-3d | %-10s | %-30s | %-10s |\n",
                                r.getId(), r.getStudentId(), r.getMessage(), r.getStatus());
                    }
                    break;

                case 3:
                    System.out.print("Nhập ID yêu cầu: ");
                    int editId = Integer.parseInt(sc.nextLine());
                    System.out.print("Nhập nội dung mới: ");
                    String newMsg = sc.nextLine();
                    controller.editRequestMessage(editId, newMsg);
                    break;

                case 4:
                    System.out.print("Nhập ID yêu cầu cần xóa: ");
                    int delId = Integer.parseInt(sc.nextLine());
                    controller.deleteRequest(delId);
                    break;

                case 5:
                    System.out.print("Nhập ID yêu cầu: ");
                    int statusId = Integer.parseInt(sc.nextLine());
                    System.out.print("Nhập trạng thái mới (Pending/Resolved): ");
                    String status = sc.nextLine();
                    controller.updateRequestStatus(statusId, status);
                    break;

                case 0:
                    System.out.println("Thoát...");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 0);
    }
}
