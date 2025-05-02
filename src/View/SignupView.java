package View;

import Controller.MainController;
import Controller.UserController;
import Model.User;

import java.util.Scanner;

public class SignupView {
    private final UserController controller = new UserController();
    private final Scanner scanner = new Scanner(System.in);
    private final MainController mainController = new MainController();
    private final Input input = new Input();

    public void displayMenu() {
        while (true) {
            System.out.println(
                    "1. Đăng ký \n" +
                            "2. Đăng nhập\n" +
                            "3. Thoát");
            int choice = input.inputInt("Nhập lựa chọn của bạn");
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
                    int count = 0;
                    int maxAttempts = 3;

                    while (count < maxAttempts) {
                        System.out.print("Tài khoản: ");
                        String username = scanner.nextLine();
                        System.out.print("Mật khẩu: ");
                        String password = scanner.nextLine();
                        user = controller.login(username, password);
                        if (user != null) {
                            break;
                        }
                        count++;
                        System.out.println("Sai thông tin đăng nhập! Vui lòng thử lại.");
                        System.out.printf("Bạn còn %d lần thử.\n", maxAttempts - count);
                    }

                    if (user == null) {
                        System.out.println("Bạn đã đăng nhập sai quá số lần cho phép. Quay lại menu chính.\n");
                    } else {
                        System.out.println("Đăng nhập thành công!");
                        mainController.start();
                    }
                }
                case 3 -> System.exit(0);
                default -> System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại.");
            }
        }
    }
}
