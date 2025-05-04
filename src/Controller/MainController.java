package Controller;

import View.*;

public class MainController {
    private static final View view = new View();
    private final StudentController studentController = new StudentController();
    private final ClassroomsController classroomsController = new ClassroomsController();
    private final TeacherController teacherController = new TeacherController();
    private final ReportController reportController = new ReportController();
    private final SubjectController subjectController = new SubjectController();
    private final SupportRequestController supportRequestController = new SupportRequestController();
    private final TuitionController tuitionController = new TuitionController();
    private final LibraryManagementController libraryManagementController = new LibraryManagementController();
    private final EventsController eventsController = new EventsController();
    private final ViewRegister viewRegister = new ViewRegister();
    private final ViewGrade viewGrade = new ViewGrade();
    private final ViewCalenda viewCalenda = new ViewCalenda();
    private final ExamView examView = new ExamView();
    private final UserView userView = new UserView();
    private static final SignupView signupView = new SignupView();
    public void start() {
        while (true) {
            view.menuView();
            String choose;
            while (true){
                choose = view.inputChoose();
                if (!choose.matches("\\d+")){
                    view.errorChoose();
                    continue;
                }
                break;
            }
            int input = Integer.parseInt(choose);
            switch (input) {
                case 1:
                    studentController.start();
                    break;
                case 2:
                    teacherController.start();
                    break;
                case 3:
                    subjectController.startSubject();
                    break;
                case 4:
                    classroomsController.start();
                    break;
                case 5:
                    viewRegister.main(null);
                    break;
                case 6:
                    viewGrade.main(null);
                    break;
                case 7:
                    viewCalenda.main(null);
                    break;
                case 8:
                    tuitionController.startTuition();
                    break;
                case 9:
                    libraryManagementController.startLibrary();
                    break;
                case 10:
                    examView.displayMenu();
                    break;
                case 11:
                    eventsController.startEvents();
                    break;
                case 12:
                    supportRequestController.startSupportRequest();
                    break;
                case 13:
                    reportController.startReport();
                    break;
                case 14:
                    userView.displayMenu();
                    break;
                case 0:
                    signupView.displayMenu();
                default:
                    System.out.println("Vui lòng nhập lại lựa chọn");
                    break;
            }
        }
    }
}
