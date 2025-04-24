import Controller.*;

public class Main {
    private final static MainController mainController = new MainController();
    private  final static StudentController studentController = new StudentController();
    private final static TeacherController teacherController = new TeacherController();
    public static void main(String[] args) {
        teacherController.start();
    }
}