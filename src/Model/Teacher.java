package Model;

public class Teacher extends Person {
    public Teacher (int teacher_id, String name, String email, String phone){
        super(teacher_id, name, email, phone);
    }
    public Teacher (String name, String email, String phone){
        super(name, email, phone);
    }
}
