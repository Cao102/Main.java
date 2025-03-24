package Controller;

import DAO.DAO;
import View.ObjectView;
import Model.Course;
import DAO.CourseDAO;
import View.ViewCourse;

import java.util.List;

public class CourseController {
    private final ObjectView<Course> viewObject = new ViewCourse();
    private final DAO<Course> objectDAO = new CourseDAO();

    private final MainController mainController;
    public CourseController(MainController mainController){
        this.mainController = mainController;
    }
    public void start(){
        while (true) {
            int input = viewObject.menuObject();
            switch (input) {
                case 1:
                    Course add = viewObject.addObject();
                    objectDAO.add(add);
                    break;
                case 2:
                    List<Course> objectList = objectDAO.getAll();
                    viewObject.getAllObject(objectList);
                    break;
                case 3:
                    Course updateObject = viewObject.updateObject();
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
