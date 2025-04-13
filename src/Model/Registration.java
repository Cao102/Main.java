package Model;

public class Registration {
    private String studentId;
    private String subjectId;
    private String name;
    public Registration(String studentId, String subjectId, String name) {
        this.studentId = studentId;
        this.subjectId = subjectId;
    }
    public Registration(String name, String subjectId) {
        this.name = name;
        this.subjectId = subjectId;
    }

    public String getStudentId() {
        return studentId;
    }
    public String getName(){
        return name;
    }
    public String getSubjectId() {
        return subjectId;
    }

    @Override
    public String toString() {
        return "StudentID: " + studentId + ", SubjectID: " + subjectId;
    }
}
