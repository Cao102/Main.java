package Model;

public class Person {
    protected int id;
    protected String name;
    protected String email;
    protected String phone;
    public Person(int id, String name, String email, String phone){
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    public Person(String name, String email, String phone){
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPhone(){
        return this.phone;
    }
    @Override
    public String toString(){
        return String.format("║ %-5d ║ %-14s ║ %-22s ║ %-10s ║", id, name, email, phone);
    }
}
