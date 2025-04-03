package Model;

public class Person {
    protected int id;
    protected String name;
    protected String email;
    protected String phone;
    protected String address;
    public Person(int id, String name, String email, String phone, String address){
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
    public Person(String name, String email, String phone, String address){
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
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
    public String getAddress() {
        return address;
    }
}
