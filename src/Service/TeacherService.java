package Service;

import java.util.List;

import DAO.TeacherDAO;
import Model.Teacher;

public class TeacherService {
    private final TeacherDAO teacherDAO = new TeacherDAO();

    public void addObject(Teacher teacher) {
        teacherDAO.add(teacher);
    }

    public List<Teacher> getAll() {
        return teacherDAO.getAll();
    }

    public void updateObject(Teacher teacher) {
        teacherDAO.update(teacher);
    }

    public void deleteObject(String id) {
        teacherDAO.delete(id);
    }

    public List<Teacher> searchObject(String name_column, String attribute) {
        return teacherDAO.search(name_column, attribute);
    }

    public boolean checkID(String teacher_id) {
        return !teacherDAO.check("teacher_id", teacher_id);
    }

    public boolean checkEmail(String email) {
        return teacherDAO.check("email", email);
    }
    public boolean checkPhone(String phone) {
        return teacherDAO.check("phone", phone);
    }
}
