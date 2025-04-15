package Controller;

import java.util.List;
import Model.Report;
import Model.TopStudent;
import DAO.ReportDAO;

public class ReportController {
    private ReportDAO dao = new ReportDAO();

    public int getStudentCount() {
        return dao.countStudents();
    }

    public int getTeacherCount() {
        return dao.countTeachers();
    }

    public int getClassCount() {
        return dao.countClasses();
    }

    public double getTotalTuition() {
        return dao.sumTuition();
    }

    public List<TopStudent> getTopStudents(int limit) {
        return dao.getTopStudents(limit);
    }


    public void showTopStudents(List<TopStudent> students) {
        if (students.isEmpty()) {
            System.out.println("Không có dữ liệu sinh viên");
            return;
        }

        System.out.println("======== TOP SINH VIÊN CÓ ĐIỂM TRUNG BÌNH CAO NHẤT ========");
        System.out.printf("%-10s | %-30s | %-15s\n", "Mã SV", "Họ tên", "Điểm TB");
        System.out.println("----------------------------------------------------------");

        for (TopStudent s : students) {
            System.out.printf("%-10s | %-30s | %-15.2f\n", s.getId(), s.getName(), s.getAvgGrade());
        }
        System.out.println("==========================================================");
    }


}