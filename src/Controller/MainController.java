package Controller;

import View.*;

public class MainController {
    private static final View view = new View();
    private final StudentController studentController = new StudentController(this);
    private final CourseController courseController = new CourseController(this);
    private final EnrollmentController enrollmentController = new EnrollmentController(this);
    private final ClassController classController = new ClassController(this);
    private final TeacherController teacherController = new TeacherController(this);
    private final ScoreController scoreController = new ScoreController(this);
    public void start(){
        while (true) {
            int input = view.menuView();
            switch (input) {
                case 1:
                    studentController.start();
                    break;
                case 2:
                    courseController.start();
                    break;
                case 3:
                    enrollmentController.start();
                    break;
                case 4:
                    classController.start();
                    break;
                case 5:
                    teacherController.start();
                    break;
                case 6:
                    scoreController.start();
                case 7:
                    System.out.println("Thoát chương trình...");
                    return;
                default:
                    System.out.println("Vui lòng nhập lại lựa chọn");
                    break;
            }
        }
    }
}
