package View;

import java.util.Scanner;

// Input
public class Input {
    Scanner sc = new Scanner(System.in);

    public String inputString(String message) {
        System.out.print(message + ": ");
        return sc.nextLine().trim();
    }

    public int inputInt(String message) {
        System.out.print(message + ": ");
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Lỗi! Vui lòng nhập số nguyên hợp lệ: ");
            }
        }
    }

}
