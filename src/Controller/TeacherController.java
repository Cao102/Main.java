package Controller;

import Model.Teacher;
import Service.TeacherService;
import View.ViewTeacher;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class TeacherController {
    private final ViewTeacher viewTeacher = new ViewTeacher();
    private final TeacherService teacherService = new TeacherService();

    private boolean checkEmpty(String s, String message) {
        if (s.isEmpty()) {
            viewTeacher.checkEmpty(message);
            return true;
        }
        return false;
    }

    private int inputPositiveInt(String label, int value) {
        while (value < 0) {
            viewTeacher.checkEmpty(label);
            if ("SNCT".equals(label)) value = viewTeacher.getYOE();
        }
        return value;
    }

    private BigDecimal inputPositiveDecimal(String label, BigDecimal value) {
        while (value.compareTo(BigDecimal.ZERO) < 0) {
            viewTeacher.checkEmpty(label);
            if ("Lương Cơ Bản".equals(label)) value = viewTeacher.getBaseSalary();
        }
        return value;
    }

    public void start() {
        while (true) {
            viewTeacher.menuObject();
            int choose;
            while (true){
                choose = viewTeacher.getChoose();
                if (choose == 0 ) return;
                if (choose > 0 && choose <= 5){
                    break;
                }
                viewTeacher.errorChoose();
            }
            switch (choose) {
                case 1 -> addObject();
                case 2 -> viewTeacher.getAllObject(teacherService.getAll());
                case 3 -> updateObject();
                case 4 -> deleteObject();
                case 5 -> searchObject();
                default -> viewTeacher.errorChoose();
            }
        }
    }

    private String inputString(String label, String value) {
        while (checkEmpty(value, label)) {
            switch (label) {
                case "Tên" -> value = viewTeacher.getName();
                case "Email" -> value = viewTeacher.getEmail();
                case "SĐT" -> value = viewTeacher.getPhone();
                case "Địa Chỉ" -> value = viewTeacher.getAddress();
                case "ID" -> value = viewTeacher.getID();
            }
        }
        return value;
    }

    public void addObject() {
        viewTeacher.addObject();

        String teacher_id;
        while (true) {
            teacher_id = viewTeacher.getID();
            if (teacher_id.isEmpty()) return;
            if (!teacherService.checkID(teacher_id)) {
                viewTeacher.checkID("Đã");
                continue;
            }
            break;
        }

        String name = inputString("Tên", viewTeacher.getName());

        String email;
        while (true) {
            email = inputString("Email", viewTeacher.getEmail());
            if (teacherService.checkEmail(email)) {
                viewTeacher.checkEmail();
                continue;
            }
            break;
        }

        String phone;
        while (true) {
            phone = inputString("SĐT", viewTeacher.getPhone());;
            if (teacherService.checkPhone(phone)) {
                viewTeacher.checkPhone();
                continue;
            }
            break;
        }
        String address = inputString("Địa Chỉ", viewTeacher.getAddress());

        int years_of_experience = inputPositiveInt("SNCT", viewTeacher.getYOE());
        BigDecimal baseSalary = inputPositiveDecimal("Lương Cơ Bản", viewTeacher.getBaseSalary());

        Teacher newTeacher = new Teacher(teacher_id, name, email, phone, address, years_of_experience, baseSalary);
        teacherService.addObject(newTeacher);
        viewTeacher.successful("Thêm");
    }

    public void updateObject() {
        viewTeacher.updateObject();
        String teacher_id;
        while (true) {
            teacher_id = viewTeacher.getID();
            if (teacher_id.isEmpty()) return;
            if (teacherService.checkID(teacher_id)) {
                viewTeacher.checkID("Chưa");
                continue;
            }
            break;
        }

        List<Teacher> list =  teacherService.searchObject("teacher_id", teacher_id);
        Teacher teacher = list.getFirst();

        String name = viewTeacher.getName();
        name = name.isEmpty() ? teacher.getName() : name;

        String email;
        while (true) {
            email = viewTeacher.getEmail();
            if (email.equals(teacher.getEmail())) break;
            if (!email.isEmpty() && teacherService.checkEmail(email)) {
                viewTeacher.checkEmail();
                continue;
            }
            break;
        }
        email = email.isEmpty() ? teacher.getEmail() : email;

        String phone;
        while (true) {
            phone = viewTeacher.getPhone();
            if (phone.equals(teacher.getPhone())) break;
            if (!phone.isEmpty() && teacherService.checkPhone(phone)) {
                viewTeacher.checkPhone();
                continue;
            }
            break;
        }
        phone = phone.isEmpty() ? teacher.getPhone() : phone;

        String address = viewTeacher.getAddress();
        address = address.isEmpty() ? teacher.getAddress() : address;

        int yoe = viewTeacher.getYOE();
        yoe = (yoe < 0) ? teacher.getYearsOfExperience() : yoe;

        BigDecimal baseSalary = viewTeacher.getBaseSalary();
        baseSalary = baseSalary.compareTo(BigDecimal.ZERO) < 0 ? teacher.getBaseSalary() : baseSalary;

        Teacher updatedTeacher = new Teacher(teacher_id, name, email, phone, address, yoe, baseSalary);
        teacherService.updateObject(updatedTeacher);
        viewTeacher.successful("Chỉnh Sửa");
    }

    public void deleteObject() {
        viewTeacher.deleteObject();
        String teacher_id;
        while (true) {
            teacher_id = viewTeacher.getID();
            if (teacher_id.isEmpty()) return;
            if (teacherService.checkID(teacher_id)) {
                viewTeacher.checkID("Chưa");
                continue;
            }
            break;
        }
        teacherService.deleteObject(teacher_id);
        viewTeacher.successful("Xoá");
    }

    public void searchObject() {
        while (true) {
            viewTeacher.viewSearch();
            int choose;
            while (true){
                choose = viewTeacher.getChoose();
                if (choose == 0 ) return;
                if (choose > 0 && choose <= 7){
                    break;
                }
                viewTeacher.errorChoose();
            }

            String name_column = "";
            String attribute = "";

            switch (choose) {
                case 1 -> {
                    name_column = "teacher_id";
                    attribute = inputString("ID", viewTeacher.getID());
                }
                case 2 -> {
                    name_column = "name";
                    attribute = inputString("Tên", viewTeacher.getName());
                }
                case 3 -> {
                    name_column = "email";
                    attribute = inputString("Email", viewTeacher.getEmail());
                }
                case 4 -> {
                    name_column = "phone";
                    attribute = inputString("SĐT", viewTeacher.getPhone());
                }
                case 5 -> {
                    name_column = "address";
                    attribute = inputString("Địa Chỉ", viewTeacher.getAddress());
                }
                case 6 -> {
                    name_column = "years_of_experience";
                    int yoe = inputPositiveInt("SNCT", viewTeacher.getYOE());
                    attribute = String.valueOf(yoe);
                }
                case 7 -> {
                    name_column = "base_salary";
                    BigDecimal salary = inputPositiveDecimal("Lương Cơ Bản", viewTeacher.getBaseSalary());
                    attribute = String.valueOf(salary);
                }
            }

            List<Teacher> teacherList = teacherService.searchObject(name_column, attribute);
            viewTeacher.getAllObject(teacherList);
        }
    }
}
