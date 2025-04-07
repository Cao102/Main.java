package Controller;

import Model.Teacher;
import Service.TeacherService;
import View.ViewTeacher;

import java.math.BigDecimal;
import java.util.List;

public class TeacherController {
    private final ViewTeacher viewTeacher = new ViewTeacher();
    private final TeacherService teacherService = new TeacherService(this);

    private final MainController mainController;
    public TeacherController(MainController mainController){
        this.mainController = mainController;
    }
    public void start(){
        while (true) {
            int input = viewTeacher.menuObject();
            switch (input) {
                case 1:
                    addObject();
                    break;
                case 2:
                    List<Teacher> objectList = teacherService.getAll();
                    viewTeacher.getAllObject(objectList);
                    break;
                case 3:
                    updateObject();
                    break;
                case 4:
                    deleteObject();
                    break;
                case 5:
                    searchObject();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Vui lòng nhập lại");
            }
        }
    }
    public void addObject(){
        viewTeacher.addObject();
        String teacher_id = viewTeacher.getID();
        String name = viewTeacher.getName();
        String email;
        while (true){
            email = viewTeacher.getEmail();
            if(!teacherService.checkEmail(email)){
                viewTeacher.checkEmail();
                continue;
            }
            break;
        }
        String phone = viewTeacher.getPhone();
        String address = viewTeacher.getAddress();
        int years_of_experience = viewTeacher.getYOE();
        BigDecimal baseSalary = viewTeacher.getBaseSalary();
        teacherService.addObject(new Teacher(teacher_id, name, email, phone, address, years_of_experience, baseSalary));
    }
    public void updateObject(){
        viewTeacher.updateObject();
        String teacher_id;
        while (true){
            teacher_id = viewTeacher.getID();
            if(!teacherService.checkID(teacher_id)){
               viewTeacher.checkID();
               continue;
            }
            break;
        }
        String name = viewTeacher.getName();
        String email;
        while (true){
            email = viewTeacher.getEmail();
            if(teacherService.checkEmail(email)){
                viewTeacher.checkEmail();
                continue;
            }
            break;
        }
        String phone = viewTeacher.getPhone();
        String address = viewTeacher.getAddress();
        int years_of_experience = viewTeacher.getYOE();
        BigDecimal baseSalary = viewTeacher.getBaseSalary();
        teacherService.updateObject(new Teacher(teacher_id, name, email, phone, address, years_of_experience, baseSalary));
    }
    public void deleteObject(){
        String teacher_id;
        while (true){
            teacher_id = viewTeacher.getID();
            if(teacherService.checkID(teacher_id)){
                viewTeacher.checkID();
                continue;
            }
            break;
        }
        teacherService.deleteObject(teacher_id);
    }
    public void searchObject(){
        while (true){
            int choose = viewTeacher.viewSearch();
            if (choose == 8) {
                break; // Quay lại menu quản lý sinh viên
            } else if (choose < 1 || choose > 8) {
                System.out.println("Lựa chọn không hợp lệ! Vui lòng nhập lại.");
                continue;
            }
            String name_column, attribute;
            switch (choose){
                case 1:
                    name_column = "teacher_id";
                    attribute = viewTeacher.getID();
                    break;
                case 2:
                    name_column = "name";
                    attribute = viewTeacher.getName();
                    break;
                case 3:
                    name_column = "email";
                    attribute = viewTeacher.getEmail();
                    break;
                case 4:
                    name_column = "phone";
                    attribute = viewTeacher.getPhone();
                    break;
                case 5:
                    name_column = "address";
                    attribute = viewTeacher.getAddress();
                    break;
                case 6:
                    name_column = "years_of_experience";
                    attribute = String.valueOf(viewTeacher.getYOE());
                    break;
                case 7:
                    name_column = "base_salary";
                    attribute = String.valueOf(viewTeacher.getBaseSalary());
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ! Quay lại menu.");
                    return;
            }
            List<Teacher> teacherList =teacherService.searchObject(name_column, attribute);
            viewTeacher.getAllObject(teacherList);
        }
    }
}
