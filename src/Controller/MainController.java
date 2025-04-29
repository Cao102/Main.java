package Controller;

import View.View;

public class MainController {
    private static final View view = new View();
    private final SubjectController subjectController = new SubjectController();
    private final ReportController reportController = new ReportController();
    private final SupportRequestController supportRequestController = new SupportRequestController();
   // private final EventsController eventsController = new EventsController();
    // (Nếu có thêm controller quản lý sinh viên, lớp học,... thì khai báo thêm ở đây)

    public void start() {
        while (true) {
            int input = view.menuView();
            switch (input) {
                case 1:
                    // gọi quản lý sinh viên
                    break;
                case 2:
                    subjectController.startSubject();
                    break;
                case 3:
                    // gọi quản lý đăng ký
                    break;
                case 4:
                    // gọi quản lý lớp học
                    break;
                case 5:
                    // gọi quản lý giáo viên
                    break;
                case 6:
                    // gọi quản lý học phí
                    break;
                case 7:
                    reportController.startReport();
                    break;
                case 8:
                    supportRequestController.startSupportRequest();
                    break;
                case 9:
                    System.out.println("Đã thoát chương trình...");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }
}
