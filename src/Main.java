import Controller.*;

public class Main {
    private final static MainController mainController = new MainController();
    private static ClassroomsController classroomsController = new ClassroomsController(mainController);
    private static StudentController studentController = new StudentController(mainController);
    private static TeacherController teacherController = new TeacherController(mainController);
    public static void main(String[] args) {
        teacherController.start();
    }
}