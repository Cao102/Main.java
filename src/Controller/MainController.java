package Controller;

import View.*;

public class MainController {
    private static final View view = new View();
    private final StudentController studentController = new StudentController();
    private final ClassroomsController classroomsController = new ClassroomsController();
    private final TeacherController teacherController = new TeacherController();
    private final DormitoryView dormitoryView = new DormitoryView();
    private final SubjectView subjectView = new SubjectView();
    private final ReportView reportView = new ReportView();
    private final TuitionController tuitionController = new TuitionController();
    private final LibraryManagementController libraryManagementController = new LibraryManagementController();
    private final EventsController eventsController = new EventsController();
    private final ViewRegister viewRegister = new ViewRegister();
    private final ViewGrade viewGrade = new ViewGrade();
    private final ViewCalenda viewCalenda = new ViewCalenda();
    private final ExamView examView = new ExamView();
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
                    subjectView.main(null);
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
                    dormitoryView.main(null);
                    break;
                case 13:
                    reportView.main(null);
                case 0:
                    view.exit();
                    return;
                default:
                    System.out.println("Vui lòng nhập lại lựa chọn");
                    break;
            }
        }
    }
}
