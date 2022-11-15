package ru.job4j.solid.odd.dip;

/**
 * Нарушение принципа DIP (Dependency Inversion Principle) - принцип инверсии зависимостей,
 * заключается в том, что класс Vehicle зависит от класса c реализацией (VehicleFly)
 * следует использовать интерфейс, т.к. средство передвижения может не только летать.
 *
 */

public class Vehicle {

    public String ok = "Средство передвижения исправно";
    public VehicleFly vehicleFly;


    public void fly() {
        vehicleFly.fly(ok);
    }
}

class VehicleFly {
    public void fly(String ok) {
        System.out.println("К полету готов");
    }
}
