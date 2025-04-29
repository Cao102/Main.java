package Model;

public class Classroom implements TableConvertible{
    private final String classroom_id;
    private final String name;
    private final int capacity;
    private final String location;
    public Classroom(String classroom_id, String name, int capacity, String location) {
        this.classroom_id = classroom_id;
        this.name = name;
        this.capacity = capacity;
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getClassroom_id() {
        return classroom_id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return String.format("║%-4s║%-16s║%-6d║ %-25s ║", this.classroom_id, this.name, this.capacity, this.location);
    }
    public String[] toRow() {
        return new String[] {
                classroom_id, name, String.valueOf(capacity), location
        };
    }
}
