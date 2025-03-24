package Controller;

import DAO.ClassDAO;
import DAO.DAO;
import Model.Class;
import View.ObjectView;
import View.ViewClass;

import java.util.List;

public class ClassController {
    private final ObjectView<Class> viewObject = new ViewClass();
    private final DAO<Class> objectDAO = new ClassDAO();

    private final MainController mainController;
    public ClassController(MainController mainController){
        this.mainController = mainController;
    }
    public void start(){
        while (true) {
            int input = viewObject.menuObject();
            switch (input) {
                case 1:
                    Class add = viewObject.addObject();
                    objectDAO.add(add);
                    break;
                case 2:
                    List<Class> objectList = objectDAO.getAll();
                    viewObject.getAllObject(objectList);
                    break;
                case 3:
                    Class updateObject = viewObject.updateObject();
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
