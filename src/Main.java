import Controller.*;
import View.UserView;

public class Main {
    private static final UserView userView = new UserView();
    public static void main(String[] args) {
        userView.displayMenu();
    }
}