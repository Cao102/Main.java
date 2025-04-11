package Controller;

import DAO.ReportDAO;
import Model.Report;
import Model.TopStudent;

import java.util.List;

public class ReportController {
    private ReportDAO ReportDAO = new ReportDAO();

    public int getStudentCountController() {
        return ReportDAO.getStudentCountDAO();
    }

    public int getTeacherCountController() {
        return ReportDAO.getTeacherCountDAO();
    }

    public int getSubjectCountController() {
        return ReportDAO.getSubjectCountDAO();
    }

    public double getRevenueFromTuitionController() {
        return ReportDAO.getRevenueFromTuitionDAO();
    }

    public List<TopStudent> getTopStudentsController(int topN) {
        return ReportDAO.getTopStudentsDAO(topN);
    }

    public Report getReportOverviewController() {
        return ReportDAO.getReportOverviewDAO();
    }

    public void displayReportOverview(Report Report) {
        System.out.println("=========== TỔNG QUAN THỐNG KÊ ===========");
        System.out.println("Tổng số sinh viên     : " + Report.getStudentCount());
        System.out.println("Tổng số giảng viên    : " + Report.getTeacherCount());
        System.out.println("Tổng số môn học       : " + Report.getSubjectCount());
        System.out.println("Tổng doanh thu học phí: " + String.format("%,.2f", Report.getTotalRevenue()) + " VND");
        System.out.println("==========================================");
    }

    public void displayTopStudents(List<TopStudent> topStudents) {
        if (topStudents.isEmpty()) {
            System.out.println("Không có dữ liệu sinh viên.");
            return;
        }

        System.out.println("===== DANH SÁCH SINH VIÊN CÓ ĐIỂM CAO NHẤT =====");
        System.out.printf("%-10s | %-30s | %-10s\n", "Mã SV", "Họ và tên", "Điểm TB");
        System.out.println("--------------------------------------------------");

        for (TopStudent student : topStudents) {
            System.out.printf("%-10s | %-30s | %-10.2f\n",
                    student.getStudentId(),
                    student.getStudentName(),
                    student.getAverageGrade());
        }
        System.out.println("==================================================");
    }
}