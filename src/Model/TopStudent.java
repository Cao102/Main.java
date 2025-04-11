package Model;

public class TopStudent {
    private String studentId;
    private String studentName;
    private double averageGrade;

    // Constructor
    public TopStudent(String studentId, String studentName, double averageGrade) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.averageGrade = averageGrade;
    }

    // Getters
    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public double getAverageGrade() {
        return averageGrade;
    }
}