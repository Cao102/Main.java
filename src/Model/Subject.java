
package Model;

public class Subject implements TableConvertible {
    private String subjectId;
    private String name;
    private String description;

    public Subject(String subjectId, String name, String description) {
        this.subjectId = subjectId;
        this.name = name;
        this.description = description;
    }

    public Subject(String subjectId, String name) {
        this.subjectId = subjectId;
        this.name = name;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String[] toRow() {
        return new String[] {
                subjectId,
                name,
                description != null ? description : "Không có mô tả"
        };
    }
}
