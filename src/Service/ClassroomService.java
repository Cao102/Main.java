package Service;import DAO.ClassroomDAO;
import Model.Classroom;

import java.util.List;

public class ClassroomService {
    private final ClassroomDAO classroomDAO = new ClassroomDAO();

    public void addObject(Classroom classroom) {
        classroomDAO.add(classroom);
    }

    public void updateObject(Classroom classroom) {
        classroomDAO.update(classroom);
    }

    public List<Classroom> getAll() {
        return classroomDAO.getAll();
    }

    public void deleteObject(String classroom_id) {
        classroomDAO.delete(classroom_id);
    }

    public List<Classroom> searchObject(String name_column, String attribute) {
        return classroomDAO.search(name_column, attribute);
    }

    public boolean checkID(String classroom_id) {
        return searchObject("classroom_id", classroom_id).isEmpty();
    }
}


