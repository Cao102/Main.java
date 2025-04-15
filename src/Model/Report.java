package Model;

import java.util.List;

public class Report {
    private int students;
    private int teachers;
    private int classes;
    private double tuition;
    private List<TopStudent> topStudents;

    // Getters and setters
    public int getStudents() {
        return students;
    }

    public void setStudents(int students) {
        this.students = students;
    }

    public int getTeachers() {
        return teachers;
    }

    public void setTeachers(int teachers) {
        this.teachers = teachers;
    }

    public int getClasses() {
        return classes;
    }

    public void setClasses(int classes) {
        this.classes = classes;
    }

    public double getTuition() {
        return tuition;
    }

    public void setTuition(double tuition) {
        this.tuition = tuition;
    }

    public List<TopStudent> getTopStudents() {
        return topStudents;
    }

    public void setTopStudents(List<TopStudent> topStudents) {
        this.topStudents = topStudents;
    }
}