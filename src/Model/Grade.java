package Model;

public class Grade {
    private String studentID;
    private String subjectID;
    private double grade;
    private String name;

    public Grade(String studentID, String subjectID, double grade) {
        this.studentID = studentID;
        this.subjectID = subjectID;
        this.grade = grade;
    }

    public Grade(String subjectID, double grade ,String name) {
        this.subjectID = subjectID;
        this.name = name;
        this.grade = grade;
    }

    public Grade(double grade, String studentID ,String name) {
        this.studentID = studentID;
        this.name = name;
        this.grade = grade;
    }

    public double getGrade() {
        return grade;
    }
    public String getsubjectID() {
        return subjectID;
    }
    public String getStudentID() {
        return studentID;
    }
    public String getName() {
        return name;
    }
}
