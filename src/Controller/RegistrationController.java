package Controller;

import java.util.List;
import DAO.RegistrationDAO;
import Model.Registration;

public class RegistrationController {
    private RegistrationDAO dao = new RegistrationDAO();

    public void registerSubject(int studentId, int subjectId) {
        Registration reg = new Registration(studentId, subjectId);
        dao.add(studentId, subjectId);
    }

    public void cancelSubject(int studentId, int subjectId) {
        dao.remove(studentId, subjectId);
    }

    public void showRegisteredSubjects(int studentId) {
        List<Registration> list = dao.getRegisteredSubjects(studentId);
        System.out.println("Danh sách môn học đã đăng ký của sinh viên " + studentId + ":");
        for (Registration r : list) {
            System.out.println("- SubjectID: " + r.getSubjectId() + "----" + r.getName());
        }
    }

    public void showStudentsBySubject(int subjectId) {
        List<Registration> list = dao.getRegisteredStudent(subjectId);
        System.out.println("Danh sách sinh viên đăng ký môn học " + subjectId + ":");
        for (Registration r : list) {
            System.out.println("- StudentID: " + r.getStudentId());
        }
    }
}
