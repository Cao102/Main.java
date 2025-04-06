package Controller;
import DAO.GradeDAO;
import Model.Grade;

import java.util.ArrayList;
import java.util.List;

public class GradeController {
    private GradeDAO dao = new GradeDAO();
    public void addGradeController(int studentID, int subjectID, double grade) {
        Grade gr = new Grade(studentID, subjectID, grade);
        dao.addGrade(studentID, subjectID, grade);
    }
    public void updateGradeController(int studentID, int subjectID, double grade){
        dao.updateGrade(studentID,subjectID,grade);
    }
    public void showGradeByStudentController(int studentID){
        List<Grade> listGrade = dao.showGradeByStudentDAO(studentID);
        for(Grade x : listGrade){
            System.out.println("Môn " + x.getName() + ": " + x.getGrade() + " điểm");
        }
    }
    public void showGradeBySubjectController(int subjectID){
        List<Grade> listGrade = dao.showGradeBySubjectDAO(subjectID);
        for(Grade x : listGrade){
            System.out.println("Bạn " + x.getName() + " được " + x.getGrade() + " điểm");
        }
    }
}
