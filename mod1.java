package project;

// UNIT I: OOP Concepts – Classes, Objects, Inheritance, Polymorphism, etc.
abstract class Vehicle {
    protected String model;
    protected int year;

    Vehicle(String model, int year) {
        this.model = model;
        this.year = year;
    }

    abstract void displayInfo();
}

class Car extends Vehicle {
    private String owner;

    Car(String model, int year, String owner) {
        super(model, year);
        this.owner = owner;
    }

    @Override
    void displayInfo() {
        System.out.println("Car Model: " + model + ", Year: " + year + ", Owner: " + owner);
    }
}

public class mod1 {
    public static void main(String[] args) {
        Car c1 = new Car("Honda City", 2022, "Chanakyan");
        c1.displayInfo();
    }
}

