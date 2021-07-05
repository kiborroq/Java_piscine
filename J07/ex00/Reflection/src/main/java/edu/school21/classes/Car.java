package edu.school21.classes;

public class Car {
    private String name;
    private Double mileage;
    private Integer yearProduction;

    public Car() {
        this.name = "name";
        this.mileage = 0.0;
        this.yearProduction = 0;
    }

    public Car(String name, Double mileage, Integer yearProduction) {
        this.name = name;
        this.mileage = mileage;
        this.yearProduction = yearProduction;
    }

    public Double takeRide(Double distance) {
        this.mileage += distance;
        return this.mileage;
    }

    public void decreaseMileage(Double offset) {
        this.mileage -= offset;
        if (this.mileage < 0.0)
            this.mileage = 0.0;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", mileage=" + mileage +
                ", yearProduction=" + yearProduction +
                '}';
    }
}
