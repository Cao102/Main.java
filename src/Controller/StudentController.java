package Controller;

import Model.Student;
import Service.StudentService;
import View.ViewStudent;

import java.sql.Date;
import java.util.List;

public class StudentController {
    private final ViewStudent viewStudent = new ViewStudent();
    private final StudentService studentService = new StudentService(this);

    private final MainController mainController;
    public StudentController(MainController mainController){
        this.mainController = mainController;
    }
    public void start(){
        while (true) {
            int input = viewStudent.menuObject();
            switch (input) {
                case 1:
                    addObject();
                    break;
                case 2:
                    List<Student> objectList = studentService.getAll();
                    viewStudent.getAllObject(objectList);
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
        String name = viewStudent.getName();
        Date dob = viewStudent.getDob();
        String gender = viewStudent.getGender();
        String email;
        while(true){
            email = viewStudent.getEmail();
            if(!studentService.checkEmail(email)){
                viewStudent.checkEmail();
                continue;
            }
            break;
        }
        String phone = viewStudent.getPhone();
        String address = viewStudent.getAddress();
        studentService.addObject(new Student(name, dob, gender, email, phone, address));
    }
    public void updateObject(){
        int studen_id;
        while (true){
            studen_id = viewStudent.getID();
            if(studentService.checkID(studen_id)){
                viewStudent.checkID();
                continue;
            }
            break;
        }

        String name = viewStudent.getName();
        Date dob = viewStudent.getDob();
        String gender = viewStudent.getGender();
        String email;
        while(true){
            email = viewStudent.getEmail();
            if(!studentService.checkEmail(email)){
                viewStudent.checkEmail();
                continue;
            }
            break;
        }
        String phone = viewStudent.getPhone();
        String address = viewStudent.getAddress();
        studentService.updateObject(new Student(studen_id, name, dob, gender, email, phone, address));
    }
    public void deleteObject(){
        int student_id;
        while (true){
            student_id = viewStudent.getID();
            if(studentService.checkID(student_id)){
                viewStudent.checkID();
                continue;
            }
            break;
        }
        studentService.deleteObject(student_id);
    }
    public void searchObject(){
        while (true) {
            int choose = viewStudent.viewSearch();
            if (choose == 8) {
                break;
            } else if (choose < 1 || choose > 8) {
                viewStudent.errorChoose();
                continue;
            }
            String name_column, attribute;
            switch (choose) {
                case 1:
                    name_column = "student_id";
                    attribute = String.valueOf(viewStudent.getID());
                    break;
                case 2:
                    name_column = "name";
                    attribute = viewStudent.getName();
                    break;
                case 3:
                    name_column = "dob";
                    attribute = String.valueOf(viewStudent.getDob());
                    break;
                case 4:
                    name_column = "gender";
                    attribute = viewStudent.getGender();
                    break;
                case 5:
                    name_column = "email";
                    attribute = viewStudent.getEmail();
                    break;
                case 6:
                    name_column = "phone";
                    attribute = viewStudent.getPhone();
                    break;
                case 7:
                    name_column = "address";
                    attribute = viewStudent.getAddress();
                    break;
                default:
                    return;
            }
            List<Student> studentList = studentService.searchObject(name_column, attribute);
            viewStudent.getAllObject(studentList);
        }
    }
}
