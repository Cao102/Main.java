package Service;

import Controller.StudentController;
import DAO.StudentDAO;
import Model.Student;

import java.util.List;

public class StudentService {
    private final StudentDAO studentDAO = new StudentDAO();

    public StudentService(StudentController studentController) {}

    public void addObject(Student student) {
        studentDAO.add(student);
    }

    public List<Student> getAll() {
        return studentDAO.getAll();
    }

    public void updateObject(Student student) {
        studentDAO.update(student);
    }

    public void deleteObject(String student_id) {
        studentDAO.delete(student_id);
    }

    public List<Student> searchObject(String name_column, String attribute) {
        return studentDAO.search(name_column, attribute);
    }

    public boolean checkID(String student_id) {
        return searchObject("student_id", student_id).isEmpty();
    }

    public boolean checkEmail(String email) {
        return !searchObject("email", email).isEmpty();
    }
}
