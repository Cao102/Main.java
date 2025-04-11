package Controller;

import DAO.SubjectDAO;
import Model.Subject;

import java.util.List;

public class SubjectController {
    private SubjectDAO subjectDAO = new SubjectDAO();

    public void addSubjectController(Subject subject) {
        subjectDAO.addSubjectDAO(subject);
    }

    public void updateSubjectController(Subject subject) {
        subjectDAO.updateSubjectDAO(subject);
    }

    public void deleteSubjectController(String subjectId) {
        subjectDAO.deleteSubjectDAO(subjectId);
    }

    public Subject getSubjectByIdController(String subjectId) {
        return subjectDAO.getSubjectByIdDAO(subjectId);
    }

    public List<Subject> getAllSubjectsController() {
        return subjectDAO.getAllSubjectsDAO();
    }

    public List<Subject> getSubjectsByTeacherController(String teacherId) {
        return subjectDAO.getSubjectsByTeacherDAO(teacherId);
    }

    public void displaySubject(Subject subject) {
        if (subject == null) {
            System.out.println("Không tìm thấy thông tin môn học");
            return;
        }

        System.out.println("----------------------------------------------");
        System.out.println("Mã môn học     : " + subject.getSubjectId());
        System.out.println("Tên môn học    : " + subject.getName());
        System.out.println("Mô tả          : " + subject.getDescription());
        System.out.println("----------------------------------------------");
    }

    public void displaySubjectList(List<Subject> subjectList) {
        if (subjectList.isEmpty()) {
            System.out.println("Không có môn học nào");
            return;
        }

        System.out.println("================ DANH SÁCH MÔN HỌC ================");
        for (Subject subject : subjectList) {
            System.out.println("----------------------------------------------");
            System.out.println("Mã môn học     : " + subject.getSubjectId());
            System.out.println("Tên môn học    : " + subject.getName());
            if (subject.getDescription() != null) {
                System.out.println("Mô tả          : " + subject.getDescription());
            }
            System.out.println("----------------------------------------------");
        }
        System.out.println("===================================================");
    }
}