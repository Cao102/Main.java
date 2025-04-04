package Model;

public class Registration {
    private int studentId;
    private int subjectId;

    public Registration(int studentId, int subjectId) {
        this.studentId = studentId;
        this.subjectId = subjectId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    @Override
    public String toString() {
        return "StudentID: " + studentId + ", SubjectID: " + subjectId;
    }
}
