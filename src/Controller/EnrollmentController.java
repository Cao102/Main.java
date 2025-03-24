package Controller;

import DAO.DAO;

import Model.Enrollment;
import DAO.EnrollmentDAO;
import View.ObjectView;
import View.ViewEnrollment;

import java.util.List;

public class EnrollmentController {
    private final ObjectView<Enrollment> viewObject = new ViewEnrollment();
    private final DAO<Enrollment> objectDAO = new EnrollmentDAO();

    private final MainController mainController;
    public EnrollmentController(MainController mainController){
        this.mainController = mainController;
    }
    public void start(){
        while (true) {
            int input = viewObject.menuObject();
            switch (input) {
                case 1:
                    Enrollment add = viewObject.addObject();
                    objectDAO.add(add);
                    break;
                case 2:
                    List<Enrollment> objectList = objectDAO.getAll();
                    viewObject.getAllObject(objectList);
                    break;
                case 3:
                    Enrollment updateObject = viewObject.updateObject();
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
