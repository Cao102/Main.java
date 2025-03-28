package Model;

public class Student extends Person {
    private final String dob;
    public Student(int student_id, String name, String dob, String email, String phone){
        super(student_id, name, email, phone);
        this.dob = dob;
    }
    public Student(String name, String dob, String email, String phone){
        super(name, email, phone);
        this.dob = dob;
    }
    public String getDob(){
        return this.dob;
    }
    @Override
    public String toString(){
        return String.format("║ %-4d ║ %-14s ║ %-22s ║ %-12s ║ %-10s ║", id, name, email, dob, phone);
    }
}
