package Controller;

import Model.Classroom;
import Service.ClassroomService;
import View.ViewClassroom;

import java.util.List;

public class ClassroomsController {
    private final ViewClassroom viewClassroom = new ViewClassroom();
    private final ClassroomService classroomService = new ClassroomService();

    public boolean checkEmpty(String s, String message) {
        if (s.isEmpty()) {
            viewClassroom.checkEmpty(message);
            return true;
        }
        return false;
    }

    public void start() {
        while (true) {
            int input = viewClassroom.menuObject();
            switch (input) {
                case 1:
                    addObject();
                    break;
                case 2:
                    getAll();
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
                case 0:
                    return;
                default:
                    viewClassroom.errorChoose();
            }
        }
    }

    public void addObject() {
        viewClassroom.addObject();
        String classroom_id;
        while (true) {
            classroom_id = viewClassroom.getID();
            if (classroom_id.isEmpty()) return;
            if (!classroomService.checkID(classroom_id)) {
                viewClassroom.checkID("ID đã tồn tại. Vui lòng nhập lại");
                continue;
            }
            break;
        }
        String name;
        while (true) {
            name = viewClassroom.getName();
            if (checkEmpty(name, "Tên")) {
                continue;
            }
            break;
        }
        int capacity;
        while (true) {
            capacity = viewClassroom.getCapacity();
            if (capacity < 0) {
                viewClassroom.checkEmpty("Sức Chứa");
                continue;
            }
            break;
        }
        String location;
        while (true) {
            location = viewClassroom.getLocation();
            if (checkEmpty(name, "Vị Chí")) {
                continue;
            }
            break;
        }
        viewClassroom.successful("thêm");
        classroomService.addObject(new Classroom(classroom_id, name, capacity, location));
    }

    public void updateObject() {
        viewClassroom.updateObject();
        String classroom_id;
        while (true) {
            classroom_id = viewClassroom.getID();
            if (classroom_id.isEmpty()) return;
            if (classroomService.checkID(classroom_id)) {
                viewClassroom.checkID("\"ID chưa tồn tại. Vui lòng nhập lại\"");
                continue;
            }
            break;
        }
        List<Classroom> classroomList = classroomService.searchObject("classroom_id", classroom_id);
        Classroom classroom = classroomList.getFirst();
        String name = viewClassroom.getName();
        if (name.isEmpty()) {
            name = classroom.getName();
        }
        int capacity = viewClassroom.getCapacity();
        if (capacity < 0) {
            capacity = classroom.getCapacity();
        }
        String location= viewClassroom.getLocation();
        if (location.isEmpty()) {
            location = classroom.getLocation();
        }
        viewClassroom.successful("Chỉnh sửa");
        classroomService.updateObject(new Classroom(classroom_id, name, capacity, location));
    }

    public void getAll() {
        viewClassroom.getAllObject(classroomService.getAll());
    }

    public void deleteObject() {
        viewClassroom.deleteObject();
        String classroom_id;
        while (true) {
            classroom_id = viewClassroom.getID();
            if (classroom_id.isEmpty()) return;
            if (classroomService.checkID(classroom_id)) {
                viewClassroom.checkID("\"ID chưa tồn tại. Vui lòng nhập lại\"");
                continue;
            }
            break;
        }
        viewClassroom.successful("xoá");
        classroomService.deleteObject(classroom_id);
    }

    public void searchObject() {
        while (true) {
            int choose = viewClassroom.viewSearch();
            if (choose == 0) {
                break;
            } else if (choose < 1 || choose > 5) {
                viewClassroom.errorChoose();
                continue;
            }
            String name_column, attribute;
            switch (choose) {
                case 1:
                    name_column = "classroom_id";
                    while (true) {
                        attribute = viewClassroom.getID();
                        if (attribute.isEmpty()) {
                            viewClassroom.checkEmpty("ID");
                            continue;
                        }
                        break;
                    }
                    break;
                case 2:
                    name_column = "name";
                    while (true) {
                        attribute = viewClassroom.getName();
                        if (attribute.isEmpty()) {
                            viewClassroom.checkEmpty("Tên");
                            continue;
                        }
                        break;
                    }
                    break;
                case 3:
                    name_column = "capacity";
                    while (true) {
                        int capacity = viewClassroom.getCapacity();
                        if (capacity < 0) {
                            viewClassroom.checkEmpty("Sức Chứa");
                            continue;
                        }
                        attribute = String.valueOf(capacity);
                        break;
                    }
                    break;
                case 4:
                    name_column = "location";
                    while (true) {
                        String location = viewClassroom.getLocation();
                        if (location.isEmpty()) {
                            viewClassroom.checkEmpty("Vị trí");
                            continue;
                        }
                        attribute = location;
                        break;
                    }
                    break;
                default:
                    return;
            }
            List<Classroom> classroomList = classroomService.searchObject(name_column, attribute);
            viewClassroom.getAllObject(classroomList);
        }
    }
}
