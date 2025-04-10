package Controller;

import Model.Teacher;
import Service.TeacherService;
import View.ViewTeacher;

import java.math.BigDecimal;
import java.util.List;

public class TeacherController {
    private final ViewTeacher viewTeacher = new ViewTeacher();
    private final TeacherService teacherService = new TeacherService(this);

    private boolean checkEmpty(String s, String message) {
        if (s.isEmpty()) {
            viewTeacher.checkEmpty(message);
            return true;
        }
        return false;
    }

    private final MainController mainController;

    public TeacherController(MainController mainController) {
        this.mainController = mainController;
    }

    public void start() {
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
                    viewTeacher.errorChoose();
            }
        }
    }

    public void addObject() {
        viewTeacher.addObject();
        String teacher_id;
        while (true) {
            teacher_id = viewTeacher.getID();
            if (teacher_id.isEmpty()) return;
            if (!teacherService.checkID(teacher_id)) {
                viewTeacher.checkID("đã");
                continue;
            }
            break;
        }
        String name, email, address, phone;

        while (true) {
            name = viewTeacher.getName();
            if (checkEmpty(name, "Tên")) {
                continue;
            }
            break;
        }
        while (true) {
            email = viewTeacher.getEmail();
            if (checkEmpty(email, "email")) {
                continue;
            }
            if (!teacherService.checkEmail(email)) {
                viewTeacher.checkEmail();
                continue;
            }
            break;
        }
        while (true) {
            phone = viewTeacher.getPhone();
            if (checkEmpty(phone, "Đt")) {
                continue;
            }
            break;
        }
        while (true) {
            address = viewTeacher.getAddress();
            if (checkEmpty(address, "Địa chỉ")) {
                continue;
            }
            break;
        }
        int years_of_experience;
        while (true) {
            years_of_experience = viewTeacher.getYOE();
            if (years_of_experience < 0) {
                viewTeacher.checkEmpty("SNCT");
                continue;
            }
            break;
        }
        BigDecimal baseSalary;
        while (true) {
            baseSalary = viewTeacher.getBaseSalary();
            if (baseSalary.compareTo(BigDecimal.ZERO) < 0) {
                viewTeacher.checkEmpty("Lương Cơ bản");
                continue;
            }
            break;
        }
        viewTeacher.successful("thêm");
        teacherService.addObject(new Teacher(teacher_id, name, email, phone, address, years_of_experience, baseSalary));
    }

    public void updateObject() {
        viewTeacher.updateObject();
        String teacher_id;
        while (true) {
            teacher_id = viewTeacher.getID();
            if (teacher_id.isEmpty()) return;
            if (teacherService.checkID(teacher_id)) {
                viewTeacher.checkID("chưa");
                continue;
            }
            break;
        }
        List<Teacher> teacherList = teacherService.searchObject("teacher_id", teacher_id);
        Teacher teacher = teacherList.getFirst();
        String name = viewTeacher.getName();
        if (name.isEmpty()) {
            name = teacher.getName();
        }
        String email;
        while (true) {
            email = viewTeacher.getEmail();
            if (!teacherService.checkEmail(email)) {
                viewTeacher.checkEmail();
                continue;
            }
            break;
        }
        if (email.isEmpty()) {
            email = teacher.getEmail();
        }
        String phone = viewTeacher.getPhone();
        if (phone.isEmpty()) {
            phone = teacher.getPhone();
        }
        String address = viewTeacher.getAddress();
        if (address.isEmpty()) {
            address = teacher.getAddress();
        }
        if (phone.isEmpty()) {
            phone = teacher.getPhone();
        }
        int years_of_experience = viewTeacher.getYOE();
        if (years_of_experience < 0) {
            years_of_experience = teacher.getYearsOfExperience();
        }
        BigDecimal baseSalary = viewTeacher.getBaseSalary();
        if (baseSalary.compareTo(BigDecimal.ZERO) < 0) {
            baseSalary = teacher.getBaseSalary();
        }
        viewTeacher.successful("chỉnh sửa");
        teacherService.updateObject(new Teacher(teacher_id, name, email, phone, address, years_of_experience, baseSalary));
    }

    public void deleteObject() {
        viewTeacher.deleteObject();
        String teacher_id;
        while (true) {
            teacher_id = viewTeacher.getID();
            if (teacher_id.isEmpty()) {
                return;
            }
            if (teacherService.checkID(teacher_id)) {
                viewTeacher.checkID("chưa");
                continue;
            }
            break;
        }
        viewTeacher.successful("xoá");
        teacherService.deleteObject(teacher_id);
    }

    public void searchObject() {
        while (true) {
            int choose = viewTeacher.viewSearch();
            if (choose == 8) {
                break;
            } else if (choose < 1 || choose > 8) {
                viewTeacher.errorChoose();
                continue;
            }
            String name_column, attribute;
            switch (choose) {
                case 1:
                    name_column = "teacher_id";
                    while (true) {
                        attribute = viewTeacher.getID();
                        if (attribute.isEmpty()) {
                            viewTeacher.checkEmpty("ID");
                            continue;
                        }
                        break;
                    }
                    break;
                case 2:
                    name_column = "name";
                    while (true) {
                        attribute = viewTeacher.getName();
                        if (attribute.isEmpty()) {
                            viewTeacher.checkEmpty("Tên");
                            continue;
                        }
                        break;
                    }
                    break;
                case 3:
                    name_column = "email";
                    while (true) {
                        attribute = viewTeacher.getEmail();
                        if (attribute.isEmpty()) {
                            viewTeacher.checkEmpty("Email");
                            continue;
                        }
                        break;
                    }
                    break;
                case 4:
                    name_column = "phone";
                    while (true) {
                        attribute = viewTeacher.getPhone();
                        if (attribute.isEmpty()) {
                            viewTeacher.checkEmpty("SĐT");
                            continue;
                        }
                        break;
                    }
                    break;
                case 5:
                    name_column = "address";
                    while (true) {
                        attribute = viewTeacher.getAddress();
                        if (attribute.isEmpty()) {
                            viewTeacher.checkEmpty("Địa chỉ");
                            continue;
                        }
                        break;
                    }
                    break;
                case 6:
                    name_column = "years_of_experience";
                    while (true) {
                        int YOE = viewTeacher.getYOE();
                        if (YOE < 0) {
                            viewTeacher.checkEmpty("SNCT");
                            continue;
                        }
                        attribute = String.valueOf(YOE);
                        break;
                    }
                    break;
                case 7:
                    name_column = "base_salary";
                    while (true) {
                        BigDecimal bigDecimal = viewTeacher.getBaseSalary();
                        if (bigDecimal.compareTo(BigDecimal.ZERO) < 0) {
                            viewTeacher.checkEmpty("Lương Cơ Bản");
                            continue;
                        }
                        attribute = String.valueOf(bigDecimal);
                        break;
                    }
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ! Quay lại menu.");
                    return;
            }

            List<Teacher> teacherList = teacherService.searchObject(name_column, attribute);
            viewTeacher.getAllObject(teacherList);
        }
    }
}
