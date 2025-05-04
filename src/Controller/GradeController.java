package Controller;

import DAO.GradeDAO;
import Model.Grade;

import java.util.List;

public class GradeController {
    private GradeDAO dao = new GradeDAO();

    public void addGradeController(String studentID, String subjectID, double grade) {
        Grade gr = new Grade(studentID, subjectID, grade);
        dao.addGrade(studentID, subjectID, grade);
    }

    public void updateGradeController(String studentID, String subjectID, double grade) {
        dao.updateGrade(studentID, subjectID, grade);
    }

    public void showGradeByStudentController(String studentID) {
        List<Grade> listGrade = dao.showGradeByStudentDAO(studentID);
        for (Grade x : listGrade) {
            System.out.println(x.getsubjectID() + " Môn " + x.getName() + ": " + x.getGrade() + " điểm");
        }
    }

    public void showGradeBySubjectController(String subjectID) {
        List<Grade> listGrade = dao.showGradeBySubjectDAO(subjectID);
        for (Grade x : listGrade) {
            System.out.println(x.getStudentID() + " Bạn " + x.getName() + " được " + x.getGrade() + " điểm");
        }
    }

    public double calculateGrade(String studentID) {
        List<Grade> listGrade = dao.showGradeByStudentDAO(studentID);
        if(listGrade.isEmpty()){
            return -1;
        }
        double gpa = 0;
        double n = 0;
        for (Grade x : listGrade) {
            gpa += x.getGrade();
            n += 1.0;
        }
        if (n == 0) return 0;
        return gpa / n;
    }
}
