package Controller;

import DAO.DAO;
import DAO.TeacherDAO;
import Model.Teacher;
import View.ObjectView;
import View.ViewTeacher;

import java.util.List;

public class TeacherController {
    private final ObjectView<Teacher> viewObject = new ViewTeacher();
    private final DAO<Teacher> objectDAO = new TeacherDAO();

    private final MainController mainController;
    public TeacherController(MainController mainController){
        this.mainController = mainController;
    }
    public void start(){
        while (true) {
            int input = viewObject.menuObject();
            switch (input) {
                case 1:
                    Teacher add = viewObject.addObject();
                    objectDAO.add(add);
                    break;
                case 2:
                    List<Teacher> objectList = objectDAO.getAll();
                    viewObject.getAllObject(objectList);
                    break;
                case 3:
                    Teacher updateObject = viewObject.updateObject();
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
