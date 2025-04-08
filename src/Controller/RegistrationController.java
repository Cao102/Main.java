package Controller;

import java.util.List;
import DAO.RegistrationDAO;
import Model.Registration;

public class RegistrationController {
    private RegistrationDAO dao = new RegistrationDAO();

    public void registerSubject(String studentId, String subjectId) {
        Registration reg = new Registration(studentId, subjectId);
        dao.addSubject(studentId, subjectId);
    }

    public void cancelSubject(String studentId, String subjectId) {
        dao.removeSubject(studentId, subjectId);
    }

    public void showRegisteredSubjects(String studentId) {
        List<Registration> list = dao.getRegisteredSubjects(studentId);
        System.out.println("Danh sách môn học đã đăng ký của sinh viên " + studentId + ":");
        for (Registration r : list) {
            System.out.println("- SubjectID: " + r.getSubjectId() + "----" + r.getName());
        }
    }

    public void showStudentsBySubject(String subjectId) {
        List<Registration> list = dao.getRegisteredStudent(subjectId);
        System.out.println("Danh sách sinh viên đăng ký môn học " + subjectId + ":");
        for (Registration r : list) {
            System.out.println("- StudentID: " + r.getStudentId());
        }
    }
}
