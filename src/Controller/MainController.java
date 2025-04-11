package Controller;

import View.*;

public class MainController {
    private static final View view = new View();
    private final StudentController studentController = new StudentController(this);
    private final ClassroomsController classroomsController = new ClassroomsController(this);
    private final TeacherController teacherController = new TeacherController(this);
    private final DormitoryView dormitoryView = new DormitoryView();
    private final SubjectView subjectView = new SubjectView();
    private final ReportView reportView = new ReportView();
    private final TuitionController tuitionController = new TuitionController();
    private final LibraryManagementController libraryManagementController = new LibraryManagementController();
    private final EventsController eventsController = new EventsController();
    public void start() {
        while (true) {
            int input = view.menuView();
            switch (input) {
                case 1:
                    studentController.start();
                    break;
                case 2:
                    teacherController.start();
                    break;
                case 3:
                    subjectView.main(null);
                    break;
                case 4:
                    classroomsController.start();
                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:
                    tuitionController.startTuition();
                    break;
                case 9:
                    libraryManagementController.startLibrary();
                    break;
                case 10:

                    break;
                case 11:
                    eventsController.startEvents();
                    break;
                case 12:
                    dormitoryView.main(null);
                    break;
                case 13:
                    reportView.main(null);
                case 14:
                    view.exit();
                    return;
                default:
                    System.out.println("Vui lòng nhập lại lựa chọn");
                    break;
            }
        }
    }
}
