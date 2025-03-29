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
                    while (true){
                        int choose = ((ViewTeacher) viewObject).viewSearch();
                        if (choose == 5) {
                            break; // Quay lại menu quản lý sinh viên
                        } else if (choose < 1 || choose > 5) {
                            System.out.println("Lựa chọn không hợp lệ! Vui lòng nhập lại.");
                            continue;
                        }
                        String name_column;
                        switch (choose){
                            case 1:
                                name_column = "teacher_id";
                                break;
                            case 2:
                                name_column = "name";
                                break;
                            case 3:
                                name_column = "email";
                                break;
                            case 4:
                                name_column = "phone";
                                break;
                            default:
                                System.out.println("Lựa chọn không hợp lệ! Quay lại menu.");
                                return;
                        }
                        String attribute = ((ViewTeacher) viewObject).infoSearch().trim();
                        List<Teacher> teacherList = ((TeacherDAO) objectDAO).search(name_column, attribute);
                        viewObject.getAllObject(teacherList);
                    }
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Vui lòng nhập lại");
            }
        }
    }
}
