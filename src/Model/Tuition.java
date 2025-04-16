package Model;

public class Tuition {
    private String student_id;  // Mã sinh viên, khóa ngoại từ bảng Students
    private double amount;  // Số tiền học phí
    private String status;
    // Constructor với đầy đủ thông tin
    public Tuition(String studentId, double amount, String status) {
        this.student_id = studentId;
        this.amount = amount;
        this.status = status;
    }
    public Tuition(String studentId, double amount) {
        this(studentId, amount, "Chưa nộp");
    }
    // Getter cho student_id
    public String getStudentId() {
        return student_id;
    }

    // Getter cho amount (học phí)
    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }
    // Phương thức toString() để in thông tin học phí sinh viên
    @Override
    public String toString() {
        return String.format("║ %-14s ║ %-19.2f ║ %-10s ║", student_id, amount, status);
    }
}
