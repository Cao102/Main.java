package Model;

public class Tuition {
    private int student_id;  // Mã sinh viên, khóa ngoại từ bảng Students
    private double amount;  // Số tiền học phí

    // Constructor với đầy đủ thông tin
    public Tuition(int student_id, double amount) {
        this.student_id = student_id;
        this.amount = amount;
    }

    // Getter cho student_id
    public int getStudentId() {
        return student_id;
    }

    // Getter cho amount (học phí)
    public double getAmount() {
        return amount;
    }

    // Phương thức toString() để in thông tin học phí sinh viên
    @Override
    public String toString() {
        return String.format("║ %-14d ║ %-19.2f ║", student_id, amount);
    }
}
