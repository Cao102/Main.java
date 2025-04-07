package Model;
import java.sql.Date;
public class Student extends Person {
    private final Date dob;
    private final String gender;
    public Student (String student_id, String name, Date dob, String gender, String email, String phone, String address){
        super(student_id, name, email, phone, address);
        this.dob = dob;
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }
    @Override
    public String toString(){
        return String.format("║ %-4s ║ %-14s ║  %tF  ║ %-9s ║ %-25s ║ %-12s ║ %-17s ║", id, name, dob, gender, email, phone, address);
    }
}
