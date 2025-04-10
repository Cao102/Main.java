package Model;

import DAO.Convert;

public class Classroom {
    private final Convert convert = new Convert();
    private String classroom_id;
    private final String name;
    private final int capacity;

    public Classroom(String classroom_id, String name, int capacity) {
        this.classroom_id = classroom_id;
        this.name = name;
        this.capacity = capacity;
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

    @Override
    public String toString() {
        return String.format("║%-4s║%-16s║%-6d║", this.classroom_id, this.name, this.capacity);
    }
}
