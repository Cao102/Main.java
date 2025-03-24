package Model;

import DAO.Convert;

public class Class {
    private final Convert convert = new Convert();
    private  int class_id;
    private final String class_name;
    private final int teacher_id;
    public Class(int class_id, String class_name, int teacher_id){
        this.class_id = class_id;
        this.class_name = class_name;
        this.teacher_id = teacher_id;
    }
    public Class(String class_name, int teacher_id){
        this.class_name = class_name;
        this.teacher_id = teacher_id;
    }
    public int getClass_id(){
        return this.class_id;
    }
    public String getClass_name(){
        return this.class_name;
    }
    public int getTeacher_id(){
        return this.teacher_id;
    }
    @Override
    public String toString(){
        return String.format("║ %-6d ║ %-16s ║ %-14s ║", class_id, class_name, convert.getNameById("teachers", "teacher_id", "name", this.teacher_id));
    }
}
