package Controller;

import Model.Student;
import Service.StudentService;
import View.ViewStudent;

import java.sql.Date;
import java.util.List;

public class StudentController {
    private final ViewStudent viewStudent = new ViewStudent();
    private final StudentService studentService = new StudentService(this);

    public StudentController(MainController ignoredMainController) {}

    private boolean checkEmpty(String s, String message) {
        if (s.isEmpty()) {
            viewStudent.checkEmpty(message);
            return true;
        }
        return false;
    }

    public void start() {
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

    public void addObject() {
        viewStudent.addObject();
        String student_id;
        while (true) {
            student_id = viewStudent.getID();
            if (student_id.isEmpty()) return;
            if (!studentService.checkID(student_id)) {
                viewStudent.checkID("Đã");
                continue;
            }
            break;
        }

        String name, gender, email, address, phone;
        while (true) {
            name = viewStudent.getName();
            if (checkEmpty(name, "Tên")) {
                continue;
            }
            break;
        }
        Date dob;
        while (true) {
            dob = viewStudent.getDob();
            if (dob == null) {
                viewStudent.checkEmpty("Ngày Sinh");
                continue;
            }
            break;
        }
        while (true) {
            gender = viewStudent.getGender();
            if (checkEmpty(gender, "giới tính")) {
                continue;
            }
            break;
        }
        while (true) {
            email = viewStudent.getEmail();
            if (checkEmpty(email, "email")) {
                continue;
            }
            if (studentService.checkEmail(email)) {
                viewStudent.checkEmail();
                continue;
            }
            break;
        }
        while (true) {
            phone = viewStudent.getPhone();
            if (checkEmpty(phone, "Đt")) {
                continue;
            }
            break;
        }
        while (true) {
            address = viewStudent.getAddress();
            if (checkEmpty(address, "Địa chỉ")) {
                continue;
            }
            break;
        }
        viewStudent.successful("thêm");
        studentService.addObject(new Student(student_id, name, dob, gender, email, phone, address));
    }

    public void updateObject() {
        viewStudent.updateObject();
        String student_id;
        while (true) {
            student_id = viewStudent.getID();
            if (student_id.isEmpty()) return;
            if (studentService.checkID(student_id)) {
                viewStudent.checkID("chưa");
                continue;
            }
            break;
        }
        List<Student> studentList = studentService.searchObject("student_id", student_id);
        Student student = studentList.getFirst();
        String name = viewStudent.getName();
        if (name.isEmpty()) name = student.getName();
        Date dob = viewStudent.getDob();
        if (dob == null) dob = student.getDob();
        String gender = viewStudent.getGender();
        if (gender.isEmpty()) gender = student.getGender();
        String email;
        while (true) {
            email = viewStudent.getEmail();
            if (studentService.checkEmail(email)) {
                viewStudent.checkEmail();
                continue;
            }
            break;
        }
        if (email.isEmpty()) email = student.getEmail();
        String phone = viewStudent.getPhone();
        if (phone.isEmpty()) phone = student.getPhone();
        String address = viewStudent.getAddress();
        if (phone.isEmpty()) phone = student.getPhone();
        viewStudent.successful("chỉnh sửa");
        studentService.updateObject(new Student(student_id, name, dob, gender, email, phone, address));
    }

    public void deleteObject() {
        viewStudent.deleteObject();
        String student_id;
        while (true) {
            student_id = viewStudent.getID();
            if (student_id.isEmpty()) return;
            if (studentService.checkID(student_id)) {
                viewStudent.checkID("chưa");
                continue;
            }
            break;
        }
        viewStudent.successful("xoá");
        studentService.deleteObject(student_id);
    }

    public void searchObject() {
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
                    while (true) {
                        attribute = viewStudent.getID();
                        if (attribute.isEmpty()) {
                            viewStudent.checkEmpty("ID");
                            continue;
                        }
                        break;
                    }
                    break;
                case 2:
                    name_column = "name";
                    while (true) {
                        attribute = viewStudent.getName();
                        if (attribute.isEmpty()) {
                            viewStudent.checkEmpty("Tên");
                            continue;
                        }
                        break;
                    }
                    break;
                case 3:
                    name_column = "dob";
                    while (true) {
                        Date dob = viewStudent.getDob();
                        if (dob == null) {
                            viewStudent.checkEmpty("DOB");
                            continue;
                        }
                        attribute = String.valueOf(dob);
                        break;
                    }
                    break;
                case 4:
                    name_column = "gender";
                    while (true) {
                        attribute = viewStudent.getGender();
                        if (attribute.isEmpty()) {
                            viewStudent.checkEmpty("Giới tính");
                            continue;
                        }
                        break;
                    }
                    break;
                case 5:
                    name_column = "email";
                    while (true) {
                        attribute = viewStudent.getEmail();
                        if (attribute.isEmpty()) {
                            viewStudent.checkEmpty("Email");
                            continue;
                        }
                        break;
                    }
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
