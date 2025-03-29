import Controller.*;

public class Main {
    private static MainController mainController = new MainController();
    private static ClassroomsController classroomsController = new ClassroomsController(mainController);
    private static CourseController courseController = new CourseController(mainController);
    private static StudentController studentController = new StudentController(mainController);
    private static TeacherController teacherController = new TeacherController(mainController);
    private static EnrollmentController enrollmentController = new EnrollmentController(mainController);
    private static ScoreController scoreController = new ScoreController(mainController);
    public static void main(String[] args) {
        teacherController.start();
    }
}