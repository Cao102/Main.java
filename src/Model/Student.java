package Model;

import java.sql.Date;

public class Student extends Person implements TableConvertible {
    private final Date dob;
    private final String gender;

    public Student(String student_id, String name, Date dob, String gender, String email, String phone, String address) {
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
    public String[] toRow() {
        return new String[] {
                id, name, dob.toString(), gender, email, phone, address
        };
    }
}
