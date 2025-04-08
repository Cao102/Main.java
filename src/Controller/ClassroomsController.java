package Controller;

import DAO.ClassroomDAO;
import Model.Classroom;
import Service.ClassroomService;
import View.ViewClassroom;

import java.util.List;

public class ClassroomsController {
    private final ViewClassroom viewClassroom = new ViewClassroom();
    private final ClassroomService classroomService = new ClassroomService();
    public boolean checkEmpty(String s, String message){
        if(s.isEmpty()){
            viewClassroom.checkEmpty(message);
            return true;
        }
        return false;
    }
    private final MainController mainController;
    public ClassroomsController(MainController mainController){
        this.mainController = mainController;
    }
    public void start(){
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
                    return;
                default:
                    System.out.println("Vui lòng nhập lại");
            }
        }
    }
    public void addObject(){
        viewClassroom.addObject();
        String classroom_id;
        while (true){
            classroom_id = viewClassroom.getID();
            if (classroom_id.isEmpty()) return;
            if(!classroomService.checkID(classroom_id)){
                viewClassroom.checkID("ID đã tồn tại. Vui lòng nhập lại");
                continue;
            }
            break;
        }
        String name;
        while (true){
            name = viewClassroom.getName();
            if(checkEmpty(name, "Tên")){
                continue;
            }
            break;
        }
        int capacity;
        while (true){
            capacity = viewClassroom.getCapacity();
            if(capacity < 0){
                viewClassroom.checkEmpty("Sức Chứa");
                continue;
            }
            break;
        }
        classroomService.addObject(new Classroom(classroom_id, name, capacity));
    }
    public void updateObject(){
        viewClassroom.updateObject();
        String classroom_id;
        while (true){
            classroom_id  = viewClassroom.getID();
            if (classroom_id.isEmpty()) return;
            if (classroomService.checkID(classroom_id)){
                viewClassroom.checkID("\"ID chưa tồn tại. Vui lòng nhập lại\"");
                continue;
            }
            break;
        }
        List<Classroom> classroomList = classroomService.searchObject("classroom_id", classroom_id);
        Classroom classroom = classroomList.getFirst();
        String name = viewClassroom.getName();
        if(name.isEmpty()){
            name = classroom.getName();
        }
        int capacity = viewClassroom.getCapacity();
        if (capacity < 0){
            capacity = classroom.getCapacity();
        }
        classroomService.updateObject(new Classroom(classroom_id, name, capacity));
    }
    public void getAll(){
        viewClassroom.getAllObject(classroomService.getAll());
    }
    public void deleteObject(){
        viewClassroom.deleteObject();
        String classroom_id;
        while (true){
            classroom_id = viewClassroom.getID();
            if(classroom_id.isEmpty()) return;
            if (classroomService.checkID(classroom_id)){
                viewClassroom.checkID("\"ID chưa tồn tại. Vui lòng nhập lại\"");
                continue;
            }
            break;
        }
        classroomService.deleteObject(classroom_id);
    }
}
