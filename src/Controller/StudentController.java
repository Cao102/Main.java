package Controller;

import DAO.DAO;
import Model.Enrollment;
import Model.Student;
import DAO.StudentDAO;
import View.ObjectView;
import View.ViewEnrollment;
import View.ViewStudent;
import java.util.List;

public class StudentController {
    private final ObjectView<Student> viewObject = new ViewStudent();
    private final DAO<Student> objectDAO = new StudentDAO();

    private final MainController mainController;
    public StudentController(MainController mainController){
        this.mainController = mainController;
    }
    public void start(){
        while (true) {
            int input = viewObject.menuObject();
            switch (input) {
                case 1:
                    Student add = viewObject.addObject();
                    objectDAO.add(add);
                    break;
                case 2:
                    List<Student> objectList = objectDAO.getAll();
                    viewObject.getAllObject(objectList);
                    break;
                case 3:
                    Student updateObject = viewObject.updateObject();
                    objectDAO.update(updateObject);
                    break;
                case 4:
                    int id = viewObject.deleteObject();
                    objectDAO.delete(id);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Vui lòng nhập lại");
            }
        }
    }
}
