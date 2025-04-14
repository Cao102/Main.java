package View;

import Controller.UserController;
import Model.User;
import java.util.Scanner;

public class UserView {
    private final UserController controller = new UserController();
    private final Scanner scanner = new Scanner(System.in);
    public void displayMenu() {
        while (true) {
            System.out.println("1. Đăng ký \n2. Đăng nhập\n3. Đăng xuất\n4. Xem người dùng\n5. Danh sách người dùng\n6. Đặt lại mật khẩu\n7. Thoát");
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
                    System.out.print("Tài khoản: ");
                    String username = scanner.nextLine();
                    System.out.print("Mật khẩu: ");
                    String password = scanner.nextLine();
                    User user = controller.login(username, password);
                    if (user != null) {
                        System.out.println("Đăng nhập thành công!");
                        ExamView examView = new ExamView();
                        examView.displayMenu();
                    } else {
                        System.out.println("Sai thông tin đăng nhập!");
                    }
                }
                case 3 -> {
                    System.out.print("Nhập ID người dùng cần đăng xuất: ");
                    int userId = scanner.nextInt();
                    boolean result = controller.logout(userId);
                    System.out.println(result ? "Đăng xuất thành công" : "Đăng xuất thất bại");
                }
                case 4 -> {
                    System.out.print("Nhập ID Người dùng: ");
                    int userId = scanner.nextInt();
                    User user = controller.getUserById(userId);
                    System.out.println(user != null ? user : "Không tìm thấy người dùng");
                }
                case 5 -> controller.getAllUsers().forEach(System.out::println);
                case 6 -> {
                    System.out.print("Nhập ID người dùng: ");
                    int userId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nhập mật khẩu mới: ");
                    String newPassword = scanner.nextLine();
                    boolean updated = controller.resetPassword(userId, newPassword);
                    System.out.println(updated ? "Cập nhật mật khẩu thành công." : "Cập nhật mật khẩu thất bại.");
                }
                case 7 -> System.exit(0);
                default -> System.out.println("Lựa chọn không hợp lệ vui lòng chọn lại");
            }
        }
    }
}