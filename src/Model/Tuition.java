package Model;

public class Tuition implements TableConvertible {
    private String student_id;  // Mã sinh viên, khóa ngoại từ bảng Students
    private double amount;  // Số tiền học phí
    private String status;
    private String tuitionName;
    public Tuition(String studentId, double amount,  String tuitionName, String status) {
        this.student_id = studentId;
        this.amount = amount;
        this.status = status;
        this.tuitionName =tuitionName;
    }

    public Tuition(String studentId, double amount, String tuitionName) {
        this(studentId, amount, tuitionName,"Chưa nộp" );
    }

    public String getStudentId() {
        return student_id;
    }

    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public String getTuitionName(){
        return tuitionName;
    }

//    @Override
//    public String toString() {
//        return String.format("║ %-14s ║ %-19.2f ║ %-10s ║", student_id, amount, status);
//    }

    public String[] toRow() {
        return new String[]{
                student_id, String.valueOf(amount), status, tuitionName
        };
    }
}
