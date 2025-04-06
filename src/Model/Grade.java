package Model;

public class Grade {
    private int studentID;
    private int subjectID;
    private double grade;
    private String name;
    public Grade(int studentID,int subjectID, double grade){
        this.studentID = studentID;
        this.subjectID = subjectID;
        this.grade = grade;
    }
    public Grade(String name, double grade){
        this.name = name;
        this.grade = grade;
    }
    public double getGrade(){
        return grade;
    }
    public String getName(){
        return name;
    }
}
