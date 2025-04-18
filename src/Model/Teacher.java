package Model;

import java.math.BigDecimal;

public class Teacher extends Person implements TableConvertible{
    private final int yearsOfExperience;
    private BigDecimal baseSalary = BigDecimal.ZERO;
    private BigDecimal salary;

    public Teacher(String teacher_id, String name, String email, String phone, String address,
                   int yearsOfExperience, BigDecimal baseSalary, BigDecimal salary) {
        super(teacher_id, name, email, phone, address);
        this.yearsOfExperience = yearsOfExperience;
        this.baseSalary = baseSalary;
        this.salary = salary;
    }

    public Teacher(String teacher_id, String name, String email, String phone, String address,
                   int yearsOfExperience, BigDecimal baseSalary) {
        super(teacher_id, name, email, phone, address);
        this.yearsOfExperience = yearsOfExperience;
        this.baseSalary = baseSalary;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public BigDecimal getBaseSalary() {
        return baseSalary;
    }

    @Override
    public String toString() {
        return String.format(
                "║ %-4s ║ %-18s ║ %-23s ║ %-10s ║ %-17s ║ %-4d ║ %-10.2f ║ %-12.2f ║",
                id, name, email, phone, address, yearsOfExperience,
                baseSalary != null ? baseSalary.doubleValue() : 0.0,
                salary != null ? salary.doubleValue() : 0.0
        );
    }
    @Override
    public String[] toRow() {
        return new String[] {
                id, name, email, phone, address, String.valueOf(yearsOfExperience) , String.valueOf(baseSalary), String.valueOf(salary)
        };
    }
}
