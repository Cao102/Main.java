package Model;

public class Report {
    private int studentCount;
    private int teacherCount;
    private int classroomCount;
    private double totalTuition;
    private String topStudentId;
    private String topStudentName;
    private double topStudentGPA;

    // Constructor tổng hợp cho thống kê
    public Report(int studentCount, int teacherCount, int classroomCount, double totalTuition) {
        this.studentCount = studentCount;
        this.teacherCount = teacherCount;
        this.classroomCount = classroomCount;
        this.totalTuition = totalTuition;
    }

    // Constructor cho top sinh viên
    public Report(String topStudentId, String topStudentName, double topStudentGPA) {
        this.topStudentId = topStudentId;
        this.topStudentName = topStudentName;
        this.topStudentGPA = topStudentGPA;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public int getTeacherCount() {
        return teacherCount;
    }

    public int getClassroomCount() {
        return classroomCount;
    }

    public double getTotalTuition() {
        return totalTuition;
    }

    public String getTopStudentId() {
        return topStudentId;
    }

    public String getTopStudentName() {
        return topStudentName;
    }

    public double getTopStudentGPA() {
        return topStudentGPA;
    }
}
