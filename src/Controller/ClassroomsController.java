package Controller;

import DAO.ClassroomDAO;
import Model.Classroom;
import View.ObjectView;
import View.ViewClassroom;

import java.util.List;

public class ClassroomsController {
    private final ObjectView<Classroom> viewObject = new ViewClassroom();
    private final ClassroomDAO classroomDAO = new ClassroomDAO();

    private final MainController mainController;
    public ClassroomsController(MainController mainController){
        this.mainController = mainController;
    }
    public void start(){
        while (true) {
            int input = viewObject.menuObject();
            switch (input) {
                case 1:
                    Classroom add = viewObject.addObject();
                    classroomDAO.add(add);
                    break;
                case 2:
                    List<Classroom> objectList = classroomDAO.getAll();
                    viewObject.getAllObject(objectList);
                    break;
                case 3:
                    Classroom updateObject = viewObject.updateObject();
                    classroomDAO.update(updateObject);
                    break;
                case 4:
                    int id = viewObject.deleteObject();
                    classroomDAO.delete(id);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Vui lòng nhập lại");
            }
        }
    }
}
