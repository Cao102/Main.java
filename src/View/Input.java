package View;

import java.util.Scanner;

public class Input {
    private final Scanner scanner = new Scanner(System.in);

    public String inputString(String message) {
        System.out.print(message + ": ");
        return scanner.nextLine();
    }

    public int inputInt(String message) {
        System.out.print(message + ": ");
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Lỗi! Vui lòng nhập số nguyên hợp lệ: ");
            }
        }
    }

    public float inputFloat(String message) {
        System.out.print(message + ": ");
        while (true) {
            try {
                return Float.parseFloat(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Lỗi! Vui lòng nhập số thực hợp lệ: ");
            }
        }
    }
}
