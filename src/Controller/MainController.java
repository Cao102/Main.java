package Controller;

import View.*;

public class MainController {
    private static final View view = new View();
    private final StudentController studentController = new StudentController(this);
    private final ClassroomsController classroomsController = new ClassroomsController(this);
    private final TeacherController teacherController = new TeacherController(this);
    public void start(){
        while (true) {
            int input = view.menuView();
            switch (input) {
                case 1:
                    studentController.start();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    classroomsController.start();
                    break;
                case 5:
                    teacherController.start();
                    break;
                case 6:
                    break;
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
