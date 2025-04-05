package Model;

public class Registration {
    private int studentId;
    private int subjectId;
    private String name;
    public Registration(int studentId, int subjectId) {
        this.studentId = studentId;
        this.subjectId = subjectId;
    }
    public Registration(String name, int subjectId) {
        this.name = name;
        this.subjectId = subjectId;
    }
    public int getStudentId() {
        return studentId;
    }
    public String getName(){
        return name;
    }
    public int getSubjectId() {
        return subjectId;
    }

    @Override
    public String toString() {
        return "StudentID: " + studentId + ", SubjectID: " + subjectId;
    }
}
