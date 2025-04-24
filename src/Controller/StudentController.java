package Controller;

import Model.Student;
import Service.StudentService;
import View.ViewStudent;

import java.sql.Date;
import java.util.List;

public class StudentController {
    private final ViewStudent viewStudent = new ViewStudent();
    private final StudentService studentService = new StudentService();

    private boolean checkEmpty(String s, String message) {
        if (s.isEmpty()) {
            viewStudent.checkEmpty(message);
            return true;
        }
        return false;
    }

    private String inputNotEmpty(String message) {
        String input;
        while (true) {
            switch (message) {
                case "ID" -> input = viewStudent.getID().trim();
                case "Tên" -> input = viewStudent.getName().trim();
                case "Giới tính" -> input = viewStudent.getGender().trim();
                case "Email" -> input = viewStudent.getEmail().trim();
                case "Số điện thoại" -> input = viewStudent.getPhone().trim();
                case "Địa chỉ" -> input = viewStudent.getAddress().trim();
                default -> input = "";
            }

            if (checkEmpty(input, message)) continue;
            break;
        }
        return input;
    }

    private Date inputDateNotNull(String message) {
        Date input;
        while (true) {
            input = viewStudent.getDob();
            if (input == null) {
                viewStudent.checkEmpty(message);
                continue;
            }
            break;
        }
        return input;
    }

    public void start() {
        while (true) {
            int input = viewStudent.menuObject();
            switch (input) {
                case 1 -> addObject();
                case 2 -> viewStudent.getAllObject(studentService.getAll());
                case 3 -> updateObject();
                case 4 -> deleteObject();
                case 5 -> searchObject();
                case 0 -> { return; }
                default -> viewStudent.errorChoose();
            }
        }
    }

    public void addObject() {
        viewStudent.addObject();

        String student_id;
        while (true) {
            student_id = viewStudent.getID().trim();
            if (student_id.isEmpty()) return;
            if (!studentService.checkID(student_id)) {
                viewStudent.checkID("Đã tồn tại ID này");
                continue;
            }
            break;
        }

        String name = inputNotEmpty("Tên");
        Date dob = inputDateNotNull("Ngày Sinh");
        String gender = inputNotEmpty("Giới tính");

        String email;
        while (true) {
            email = viewStudent.getEmail().trim();
            if (checkEmpty(email, "Email")) continue;
            if (studentService.checkEmail(email)) {
                viewStudent.checkEmail();
                continue;
            }
            break;
        }

        String phone;
        while (true) {
            phone = inputNotEmpty("Số điện thoại");
            if (checkEmpty(phone, "Số điện thoại")) continue;
            if (studentService.checkPhone(phone)) {
                viewStudent.checkPhone();
                continue;
            }
            break;
        }
        String address = inputNotEmpty("Địa chỉ");

        studentService.addObject(new Student(student_id, name, dob, gender, email, phone, address));
        viewStudent.successful("thêm");
    }

    public void updateObject() {
        viewStudent.updateObject();
        String student_id;
        while (true) {
            student_id = viewStudent.getID().trim();
            if (student_id.isEmpty()) return;
            if (studentService.checkID(student_id)) {
                viewStudent.checkID("chưa tồn tại");
                continue;
            }
            break;
        }

        List<Student> studentList = studentService.searchObject("student_id", student_id);
        Student student = studentList.getFirst();

        String name = viewStudent.getName().trim();
        name = name.isEmpty() ? student.getName() : name;

        Date dob = viewStudent.getDob();
        dob = dob == null ? student.getDob() : dob;

        String gender = viewStudent.getGender().trim();
        gender = gender.isEmpty() ? student.getGender() : gender;

        String email;
        while (true) {
            email = viewStudent.getEmail().trim();
            if (email.isEmpty()) {
                email = student.getEmail();
                break;
            }
            if (studentService.checkEmail(email)) {
                viewStudent.checkEmail();
                continue;
            }
            break;
        }

        String phone;
        while (true) {
            phone = viewStudent.getPhone().trim();
            if (phone.isEmpty()) {
                phone = student.getPhone();
                break;
            }
            if (studentService.checkPhone(phone)) {
                viewStudent.checkPhone();
                continue;
            }
            break;
        }

        String address = viewStudent.getAddress().trim();
        address = address.isEmpty() ? student.getAddress() : address;

        studentService.updateObject(new Student(student_id, name, dob, gender, email, phone, address));
        viewStudent.successful("chỉnh sửa");
    }

    public void deleteObject() {
        viewStudent.deleteObject();
        String student_id;
        while (true) {
            student_id = viewStudent.getID().trim();
            if (student_id.isEmpty()) return;
            if (studentService.checkID(student_id)) {
                viewStudent.checkID("chưa tồn tại");
                continue;
            }
            break;
        }
        studentService.deleteObject(student_id);
        viewStudent.successful("xoá");
    }

    public void searchObject() {
        while (true) {
            int choose = viewStudent.viewSearch();
            String name_column, attribute;

            switch (choose) {
                case 1 -> {
                    name_column = "student_id";
                    attribute = inputNotEmpty("ID");
                }
                case 2 -> {
                    name_column = "name";
                    attribute = inputNotEmpty("Tên");
                }
                case 3 -> {
                    name_column = "dob";
                    Date dob = inputDateNotNull("DOB");
                    attribute = String.valueOf(dob);
                }
                case 4 -> {
                    name_column = "gender";
                    attribute = inputNotEmpty("Giới tính");
                }
                case 5 -> {
                    name_column = "email";
                    attribute = inputNotEmpty("Email");
                }
                case 6 -> {
                    name_column = "phone";
                    attribute = inputNotEmpty("Số điện thoại");
                }
                case 7 -> {
                    name_column = "address";
                    attribute = inputNotEmpty("Địa chỉ");
                }
                case 0 -> {
                    return;
                }
                default -> {
                    viewStudent.errorChoose();
                    continue;
                }
            }

            List<Student> studentList = studentService.searchObject(name_column, attribute);
            viewStudent.getAllObject(studentList);
        }
    }
}
