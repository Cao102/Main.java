package Controller;

import Model.Classroom;
import Service.ClassroomService;
import View.ViewClassroom;

import java.util.List;

public class ClassroomsController {
    private final ViewClassroom viewClassroom = new ViewClassroom();
    private final ClassroomService classroomService = new ClassroomService();

    private boolean isEmpty(String value, String fieldName) {
        if (value.isEmpty()) {
            viewClassroom.checkEmpty(fieldName);
            return true;
        }
        return false;
    }

    public void start() {
        while (true) {
            viewClassroom.menuObject();
            int choose;
            while (true){
                choose = viewClassroom.getChoose();
                if (choose == 0 ) return;
                if (choose > 0 && choose <= 5){
                    break;
                }
                viewClassroom.errorChoose();
            }
            switch (choose) {
                case 1 -> addObject();
                case 2 -> viewClassroom.getAllObject(classroomService.getAll());
                case 3 -> updateObject();
                case 4 -> deleteObject();
                case 5 -> searchObject();
                default -> viewClassroom.errorChoose();
            }
        }
    }

    public void addObject() {
        viewClassroom.addObject();

        String id;
        while (true) {
            id = viewClassroom.getID();
            if (id.isEmpty()) return;
            if (!classroomService.checkID(id)) {
                viewClassroom.checkID("ID Đã Tồn Tại. Vui Lòng Nhập Lại");
                continue;
            }
            break;
        }

        String name;
        while (true) {
            name = viewClassroom.getName();
            if (!isEmpty(name, "Tên")) break;
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
            if (!isEmpty(location, "Vị Trí")) break;
        }

        classroomService.addObject(new Classroom(id, name, capacity, location));
        viewClassroom.successful("Thêm");
    }

    public void updateObject() {
        viewClassroom.updateObject();

        String id;
        while (true) {
            id = viewClassroom.getID();
            if (id.isEmpty()) return;
            if (classroomService.checkID(id)) {
                viewClassroom.checkID("ID Chưa Tồn Tại. Vui Lòng Nhập Lại");
                continue;
            }
            break;
        }

        Classroom old = classroomService.searchObject("classroom_id", id).getFirst();

        String name = viewClassroom.getName();
        if (name.isEmpty()) name = old.getName();

        int capacity = viewClassroom.getCapacity();
        if (capacity < 0) capacity = old.getCapacity();

        String location = viewClassroom.getLocation();
        if (location.isEmpty()) location = old.getLocation();

        classroomService.updateObject(new Classroom(id, name, capacity, location));
        viewClassroom.successful("Chỉnh Sửa");
    }

    public void deleteObject() {
        viewClassroom.deleteObject();

        String id;
        while (true) {
            id = viewClassroom.getID();
            if (id.isEmpty()) return;
            if (classroomService.checkID(id)) {
                viewClassroom.checkID("ID Chưa Tồn Tại. Vui Lòng Nhập Lại");
                continue;
            }
            break;
        }

        classroomService.deleteObject(id);
        viewClassroom.successful("Xoá");
    }

    public void searchObject() {
        while (true) {
            viewClassroom.viewSearch();
            int choose;
            while (true){
                choose = viewClassroom.getChoose();
                if (choose == 0 ) return;
                if (choose > 0 && choose <= 4){
                    break;
                }
                viewClassroom.errorChoose();
            }
            String column = "";
            String value = "";

            switch (choose) {
                case 1:
                    column = "classroom_id";
                    while (true) {
                        value = viewClassroom.getID();
                        if (!isEmpty(value, "ID")) break;
                    }
                    break;
                case 2:
                    column = "name";
                    while (true) {
                        value = viewClassroom.getName();
                        if (!isEmpty(value, "Tên")) break;
                    }
                    break;
                case 3:
                    column = "capacity";
                    int capacity;
                    while (true) {
                        capacity = viewClassroom.getCapacity();
                        if (capacity < 0) {
                            viewClassroom.checkEmpty("Sức Chứa");
                            continue;
                        }
                        value = String.valueOf(capacity);
                        break;
                    }
                    break;
                case 4:
                    column = "location";
                    while (true) {
                        value = viewClassroom.getLocation();
                        if (!isEmpty(value, "Vị Trí")) break;
                    }
                    break;
                default:
                    viewClassroom.errorChoose();
                    continue;
            }

            List<Classroom> result = classroomService.searchObject(column, value);
            viewClassroom.getAllObject(result);
        }
    }
}
