package Model;

public class SupportRequest implements TableConvertible {
    private int id;
    private String studentId;
    private String message;
    private String status; // Pending / Resolved

    public SupportRequest(int id, String studentId, String message, String status) {
        this.id = id;
        this.studentId = studentId;
        this.message = message;
        this.status = status;
    }

    public SupportRequest(String studentId, String message) {
        this.studentId = studentId;
        this.message = message;
        this.status = "Pending";
    }

    public int getId() {
        return id;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String[] toRow() {
        return new String[]{
                String.valueOf(id),
                studentId,
                message != null ? message : "",
                status
        };
    }
}
