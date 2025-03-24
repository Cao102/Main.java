package Model;

import DAO.Convert;

public class Enrollment {
    private int enrollment_id;
    private final int student_id;
    private final int course_id;
    private static final Convert convert = new Convert();

    public Enrollment(int enrollment_id, int student_id, int course_id){
        this.enrollment_id = enrollment_id;
        this.student_id = student_id;
        this.course_id = course_id;
    }
    public Enrollment(int student_id, int course_id){
        this.student_id = student_id;
        this.course_id = course_id;
    }
    public int getId(){
        return this.enrollment_id;
    }
    public int getStudent_id(){
        return this.student_id;
    }
    public int getCourse_id(){
        return this.course_id;
    }
    @Override
    public String toString(){
        return String.format("║ %-14d ║ %-13s ║ %-12s ║",enrollment_id, convert.getNameById("students", "student_id", "name", this.student_id), convert.getNameById("courses", "course_id", "course_name", this.course_id));
    }
}
