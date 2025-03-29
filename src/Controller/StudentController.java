package Controller;

import DAO.DAO;
import Model.Enrollment;
import Model.Student;
import DAO.StudentDAO;
import View.ObjectView;
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
                    while (true) {
                        int choose = ((ViewStudent) viewObject).viewSearch();

                        if (choose == 6) {
                            break; // Quay lại menu quản lý sinh viên
                        } else if (choose < 1 || choose > 6) {
                            System.out.println("Lựa chọn không hợp lệ! Vui lòng nhập lại.");
                            continue;
                        }
                        String name_column;
                        switch (choose) {
                            case 1:
                                name_column = "student_id";
                                break;
                            case 2:
                                name_column = "name";
                                break;
                            case 3:
                                name_column = "dob";
                                break;
                            case 4:
                                name_column = "email";
                                break;
                            case 5:
                                name_column = "phone";
                                break;
                            default:
                                return;
                        }
                        String attribute = ((ViewStudent) viewObject).infoSearch().trim();
                        List<Student> studentList = ((StudentDAO) objectDAO).search(name_column, attribute);
                        viewObject.getAllObject(studentList);
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
