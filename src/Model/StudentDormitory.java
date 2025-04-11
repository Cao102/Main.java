package Model;

public class StudentDormitory {
    private String student_id;
    private String dorm_id;
    private String student_name; // Để hiển thị thông tin chi tiết
    private String room_number; // Để hiển thị thông tin chi tiết

    // Constructor đầy đủ
    public StudentDormitory(String student_id, String dorm_id, String student_name, String room_number) {
        this.student_id = student_id;
        this.dorm_id = dorm_id;
        this.student_name = student_name;
        this.room_number = room_number;
    }

    // Constructor không có thông tin chi tiết
    public StudentDormitory(String student_id, String dorm_id) {
        this.student_id = student_id;
        this.dorm_id = dorm_id;
    }

    // Getters
    public String getStudentId() {
        return student_id;
    }

    public String getDormId() {
        return dorm_id;
    }

    public String getStudentName() {
        return student_name;
    }

    public String getRoomNumber() {
        return room_number;
    }
}