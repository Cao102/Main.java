package Model;

public class SupportRequest {
    private int id;
    private String studentId;
    private String message;
    private String status;

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

    // Getters & Setters
    public int getId() { return id; }
    public String getStudentId() { return studentId; }
    public String getMessage() { return message; }
    public String getStatus() { return status; }

    public void setMessage(String message) { this.message = message; }
    public void setStatus(String status) { this.status = status; }
}
