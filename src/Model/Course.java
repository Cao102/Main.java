package Model;

public class Course {
    private int id;
    private final String name;
    private final int credit;
    public Course(int id, String name, int credit){
        this.id = id;
        this.name = name;
        this.credit = credit;
    }
    public Course(String name, int credit){
        this.name = name;
        this.credit = credit;
    }

    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public int getCredit(){
        return this.credit;
    }
    @Override
    public String toString(){
        return String.format("║ %-9d ║ %-24s ║ %-12d ║", id, name, credit);
    }
}
