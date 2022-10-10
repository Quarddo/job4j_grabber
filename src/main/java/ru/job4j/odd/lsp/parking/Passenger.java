package ru.job4j.odd.lsp.parking;

public class Passenger extends Car {

    public Passenger(String name, int size) {
        super(name, size);
        if (size != CAR_SIZE) {
            throw new IllegalArgumentException("Размер не соответствует легковому автомобилю!");
        }
    }
}
