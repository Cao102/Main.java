package Model;

public class Report {
    private int studentCount;
    private int teacherCount;
    private int subjectCount;
    private double totalRevenue;

    // Constructor
    public Report(int studentCount, int teacherCount, int subjectCount, double totalRevenue) {
        this.studentCount = studentCount;
        this.teacherCount = teacherCount;
        this.subjectCount = subjectCount;
        this.totalRevenue = totalRevenue;
    }

    // Getters
    public int getStudentCount() {
        return studentCount;
    }

    public int getTeacherCount() {
        return teacherCount;
    }

    public int getSubjectCount() {
        return subjectCount;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }
}