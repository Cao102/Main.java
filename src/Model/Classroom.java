package Model;

import DAO.Convert;

public class Classroom {
    private final Convert convert = new Convert();
    private  int classroom_id;
    private final String name;
    private final int capacity;
    public Classroom(int classroom_id, String name, int capacity){
        this.classroom_id = classroom_id;
        this.name = name;
        this.capacity = capacity;
    }
    public Classroom(String class_name, int capacity){
        this.name = class_name;
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getClassroom_id() {
        return classroom_id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        return String.format("║%-4d║%-16s║%-6d║", this.classroom_id, this.name, this.capacity);
    }
}
