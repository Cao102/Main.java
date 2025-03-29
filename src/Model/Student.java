package Model;
import java.sql.Date;
public class Student extends Person {
    private final Date dob;
    public Student(int student_id, String name, Date dob, String email, String phone){
        super(student_id, name, email, phone);
        this.dob = dob;
    }
    public Student(String name, Date dob, String email, String phone){
        super(name, email, phone);
        this.dob = dob;
    }
    public Date getDob(){
        return this.dob;
    }
    @Override
    public String toString(){
        return String.format("║ %-4d ║ %-14s ║ %-22s ║ %-12s ║ %-10s ║", id, name, email, dob, phone);
    }
}
