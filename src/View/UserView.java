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
                    ║           Quản lý Admin                ║
                    ╠════════════════════════════════════════╣
                    ║ 1. Xem Admin                           ║
                    ║ 2. Danh sách Admin                     ║
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
                    int userId;
                    while (true) {
                        System.out.print("Nhập ID Admin: ");
                        userId = scanner.nextInt();
                        scanner.nextLine();
                        User user = controller.getUserById(userId);
                        if (user != null) {
                            System.out.println(user);
                            break;
                        } else {
                            System.out.println("Không tồn tại ID, vui lòng nhập lại.");
                        }
                    }
                }
                case 2 -> controller.getAllUsers().forEach(System.out::println);
                case 3 -> {
                    int userId;
                    while (true) {
                        System.out.print("Nhập ID Admin: ");
                        userId = scanner.nextInt();
                        scanner.nextLine();
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
}
