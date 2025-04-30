package View;

import Controller.MainController;
import Controller.UserController;
import Model.User;

import java.util.Scanner;

public class UserView {
    private final UserController controller = new UserController();
    private final Scanner scanner = new Scanner(System.in);
    private final MainController mainController = new MainController();
    public void displayMenu() {
        while (true) {
            System.out.println(
                            "1. Đăng ký \n" +
                            "2. Đăng nhập\n" +
                            "3. Đăng xuất\n" +
                            "4. Xem người dùng\n" +
                            "5. Danh sách người dùng\n" +
                            "6. Đặt lại mật khẩu\n" +
                            "7. Thoát");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Tài khoản: ");
                    String username = scanner.nextLine();
                    System.out.print("Mật khẩu: ");
                    String password = scanner.nextLine();
                    User user = controller.registerUser(username, password);
                }
                case 2 -> {
                    User user = null;
                    do {
                        System.out.print("Tài khoản: ");
                        String username = scanner.nextLine();
                        System.out.print("Mật khẩu: ");
                        String password = scanner.nextLine();
                        user = controller.login(username, password);
                        if (user == null) {
                            System.out.println("Sai thông tin đăng nhập! Vui lòng thử lại.");
                        }
                    } while (user == null);

                    System.out.println("Đăng nhập thành công!");
                    mainController.start();
                }
                case 3 -> {
                    int userId;
                    while (true) {
                        System.out.print("Nhập ID người dùng cần đăng xuất: ");
                        userId = scanner.nextInt();
                        scanner.nextLine(); // bỏ dòng thừa
                        if (controller.getUserById(userId) != null) {
                            break;
                        }
                        System.out.println("Không tồn tại ID, vui lòng nhập lại.");
                    }
                    boolean result = controller.logout(userId);
                    System.out.println(result ? "Đăng xuất thành công" : "Đăng xuất thất bại");
                }
                case 4 -> {
                    int userId;
                    while (true) {
                        System.out.print("Nhập ID Người dùng: ");
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
                case 5 -> controller.getAllUsers().forEach(System.out::println);
                case 6 -> {
                    int userId;
                    while (true) {
                        System.out.print("Nhập ID người dùng: ");
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
                case 7 -> System.exit(0);
                default -> System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại.");
            }
        }
    }
}
