package Model;

public class Dormitory {
    private String dorm_id;
    private String room_number;
    private int capacity;
    private int occupiedCount; // Số lượng sinh viên đang ở

    // Constructor đầy đủ
    public Dormitory(String dorm_id, String room_number, int capacity, int occupiedCount) {
        this.dorm_id = dorm_id;
        this.room_number = room_number;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    // Constructor không có occupiedCount (được sử dụng khi thêm mới)
    public Dormitory(String dorm_id, String room_number, int capacity) {
        this.dorm_id = dorm_id;
        this.room_number = room_number;
        this.capacity = capacity;
        this.occupiedCount = 0;
    }

    // Getters
    public String getDormId() {
        return dorm_id;
    }

    public String getRoomNumber() {
        return room_number;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getOccupiedCount() {
        return occupiedCount;
    }

    // Phương thức kiểm tra còn chỗ trống không
    public boolean hasAvailableSpace() {
        return occupiedCount < capacity;
    }

    // Phương thức trả về số lượng chỗ trống
    public int getAvailableSpace() {
        return capacity - occupiedCount;
    }
}
