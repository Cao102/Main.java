import Controller.*;

public class Main {
    private final static MainController mainController = new MainController();
    private  final static StudentController studentController = new StudentController();
    public static void main(String[] args) {
        studentController.start();
    }
}