package Model;

import java.math.BigDecimal;

public class Teacher extends Person {
    private int yearsOfExperience;
    private BigDecimal baseSalary;
    private BigDecimal salary;
    public Teacher(int teacher_id, String name, String email, String phone, String address, int yearsOfExperience, BigDecimal baseSalary , BigDecimal salary){
        super(teacher_id, name, email, phone, address);
        this.yearsOfExperience = yearsOfExperience;
        this.baseSalary = baseSalary;
        this.salary = salary;
    }
    public Teacher(int teacher_id, String name, String email, String phone, String address, int yearsOfExperience, BigDecimal baseSalary ){
        super(teacher_id, name, email, phone, address);
        this.yearsOfExperience = yearsOfExperience;
        this.baseSalary = baseSalary;
        this.salary = calculateSalary();
    }
    public Teacher(String name, String email, String phone, String address, int yearsOfExperience, BigDecimal baseSalary ){
        super(name, email, phone, address);
        this.yearsOfExperience = yearsOfExperience;
        this.baseSalary = baseSalary;
        this.salary = calculateSalary();
    }
    private BigDecimal calculateSalary() {
        int experienceBonus = (yearsOfExperience / 5) * 5000;
        return baseSalary.add(BigDecimal.valueOf(experienceBonus));
    }

    public BigDecimal getBaseSalary() {
        return baseSalary;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }
    @Override
    public String toString(){
        return String.format("║ %d ║ %s ║ %s ║ %s ║ %s ║ %d ║ %.2f ║ %.2f ║", id, name, email, phone, address, yearsOfExperience, baseSalary, salary);
    }
}
