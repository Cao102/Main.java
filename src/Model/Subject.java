package Model;

public class Subject {
    private String subject_id;
    private String name;
    private String description;

    // Constructor đầy đủ
    public Subject(String subject_id, String name, String description) {
        this.subject_id = subject_id;
        this.name = name;
        this.description = description;
    }

    // Constructor chỉ có ID và tên (cho việc hiển thị tóm tắt)
    public Subject(String subject_id, String name) {
        this.subject_id = subject_id;
        this.name = name;
    }

    // Getters
    public String getSubjectId() {
        return subject_id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
