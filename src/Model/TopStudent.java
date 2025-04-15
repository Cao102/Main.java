package Model;

public class TopStudent {
    private String id;
    private String name;
    private double avgGrade;

    public TopStudent(String id, String name, double avgGrade) {
        this.id = id;
        this.name = name;
        this.avgGrade = avgGrade;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getAvgGrade() {
        return avgGrade;
    }
}