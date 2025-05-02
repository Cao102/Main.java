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
                case 3 -> System.exit(0);
                default -> System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại.");
            }
        }
    }
}
