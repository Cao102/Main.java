package View;

import Controller.UserController;
import Model.User;

import java.util.Scanner;

public class UserView {
    private final UserController controller = new UserController();
    private final Scanner scanner = new Scanner(System.in);
    private final Input input = new Input();

    public void displayMenu() {
        while (true) {
            System.out.print("""
                    
                    ╔════════════════════════════════════════╗
                    ║           Thông Tin Account            ║
                    ╠════════════════════════════════════════╣
                    ║ 1. Xem Account                         ║
                    ║ 2. Danh sách Account                   ║
                    ║ 3. Đặt lại mật khẩu                    ║
                    ║ 4. Thoát                               ║
                    ╚════════════════════════════════════════╝
                    """);
            int choice = input.inputInt("Nhập lựa chọn của bạn");
            if (choice == 4) {
                return;
            }
            switch (choice) {
                case 1 -> {
                    int userId = inputValidUserId();
                    User user = controller.getUserById(userId);
                    if (user != null) {
                        System.out.println(user);
                    } else {
                        System.out.println("Không tồn tại ID.");
                    }
                }
                case 2 -> controller.getAllUsers().forEach(System.out::println);
                case 3 -> {
                    int userId;
                    while (true) {
                        userId = inputValidUserId();
                        if (controller.getUserById(userId) != null) {
                            break;
                        }
                        System.out.println("Không tồn tại ID, vui lòng nhập lại.");
                    }
                    System.out.print("Nhập mật khẩu mới: ");
                    String newPassword = scanner.nextLine();
                    boolean updated = controller.resetPassword(userId, newPassword);
                    System.out.println(updated ? "Cập nhật mật khẩu thành công." : "Cập nhật mật khẩu thất bại.");
                }
                default -> System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại.");
            }
        }
    }
    private int inputValidUserId() {
        while (true) {
            System.out.print("Nhập ID Account: ");
            try {
                String inputStr = scanner.nextLine().trim();
                if (!inputStr.matches("\\d+")) {
                    System.out.println("Vui lòng chỉ nhập số nguyên dương.");
                    continue;
                }
                if (inputStr.length() > 10) {
                    System.out.println("ID không được vượt quá 10 chữ số.");
                    continue;
                }
                long idLong = Long.parseLong(inputStr);
                if (idLong > Integer.MAX_VALUE) {
                    System.out.println("ID vượt quá giới hạn cho phép.");
                    continue;
                }
                return (int) idLong;
            } catch (Exception e) {
                System.out.println("Lỗi không xác định. Vui lòng thử lại.");
            }
        }
    }
}
