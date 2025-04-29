package Controller;

import DAO.ReportDAO;
import Model.Report;
import View.ViewReport;

import java.util.List;

public class ReportController {
    private final ReportDAO reportDAO = new ReportDAO();
    private final ViewReport view = new ViewReport();

    public void startReport() {
        while (true) {
            int choice = view.menuReport();
            switch (choice) {
                case 1 -> view.displayStudentCount(reportDAO.getGeneralStatistics().getStudentCount());
                case 2 -> view.displayTeacherCount(reportDAO.getGeneralStatistics().getTeacherCount());
                case 3 -> view.displayClassroomCount(reportDAO.getGeneralStatistics().getClassroomCount());
                case 4 -> view.displayTotalTuition(reportDAO.getGeneralStatistics().getTotalTuition());
                case 5 -> {
                    int limit = view.inputTopLimit();
                    List<Report> topStudents = reportDAO.getTopStudentsByGPA(limit);
                    view.displayTopStudents(topStudents);
                }
                case 0 -> { return; }
                default -> view.errorChoose();
            }
        }
    }
}
