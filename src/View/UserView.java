package View;

import Controller.UserController;
import Model.User;
import java.util.Scanner;

public class UserView {
    private final UserController controller = new UserController();
    private final Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        while (true) {
            System.out.println("1. Register\n2. Login\n3. Logout\n4. View User\n5. List Users\n6. Reset Password\n7. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Username: ");
                    String username = scanner.nextLine();
                    System.out.print("Password: ");
                    String password = scanner.nextLine();
                    User user = controller.registerUser(username, password);
                    System.out.println("Registered: " + user);
                }
                case 2 -> {
                    System.out.print("Username: ");
                    String username = scanner.nextLine();
                    System.out.print("Password: ");
                    String password = scanner.nextLine();
                    User user = controller.login(username, password);
                    System.out.println(user != null ? "Login successful!" : "Login failed!");
                }
                case 3 -> {
                    System.out.print("User ID to logout: ");
                    int userId = scanner.nextInt();
                    boolean result = controller.logout(userId);
                    System.out.println(result ? "Logged out." : "Logout failed.");
                }
                case 4 -> {
                    System.out.print("User ID: ");
                    int userId = scanner.nextInt();
                    User user = controller.getUserById(userId);
                    System.out.println(user != null ? user : "User not found.");
                }
                case 5 -> controller.getAllUsers().forEach(System.out::println);
                case 6 -> {
                    System.out.print("User ID: ");
                    int userId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("New Password: ");
                    String newPassword = scanner.nextLine();
                    boolean updated = controller.resetPassword(userId, newPassword);
                    System.out.println(updated ? "Password updated." : "Update failed.");
                }
                case 7 -> System.exit(0);
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}