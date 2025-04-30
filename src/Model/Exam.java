package Model;

import java.time.LocalDateTime;

public class Exam {
    private int id;
    private String classId;
    private String subjectId;
    private LocalDateTime examDate;

    public Exam(int id, String classId, String subjectId, LocalDateTime examDate) {
        this.id = id;
        this.classId = classId;
        this.subjectId = subjectId;
        this.examDate = examDate;
    }

    public Exam(String classId, String subjectId, LocalDateTime examDate) {
        this.classId = classId;
        this.subjectId = subjectId;
        this.examDate = examDate;
    }

    // Getters & Setters

    public int getId() { return id; }
    public String getClassId() { return classId; }
    public String getSubjectId() { return subjectId; }
    public LocalDateTime getExamDate() { return examDate; }

    public void setId(int id) { this.id = id; }
    public void setClassId(String classId) { this.classId = classId; }
    public void setSubjectId(String subjectId) { this.subjectId = subjectId; }
    public void setExamDate(LocalDateTime examDate) { this.examDate = examDate; }
}