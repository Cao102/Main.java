package Model;

public class Student extends Person {
    private final String dob;
    private final int class_id;
    public Student(int id_student, String name, String dob, String email, String phone, int class_id){
        super(id_student, name, email, phone);
        this.dob = dob;
        this.class_id = class_id;
    }
    public Student(String name, String dob, String email, String phone, int class_id){
        super(name, email, phone);
        this.dob = dob;
        this.class_id = class_id;
    }
    public String getDob(){
        return this.dob;
    }
    public int getClass_id(){
        return this.class_id;
    }
    @Override
    public String toString(){
        return String.format("║ %-4d ║ %-14s ║ %-19s ║ %-12s ║ %-10s ║ %-8d ║", id, name, email, dob, phone, class_id);
    }
}
