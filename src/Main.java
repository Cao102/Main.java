import Controller.*;
import View.SignupView;
import View.UserView;

public class Main {
    private static final SignupView signupView = new SignupView();
    public static void main(String[] args) {
        signupView.displayMenu();
    }
}