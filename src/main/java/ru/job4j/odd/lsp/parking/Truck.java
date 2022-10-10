package ru.job4j.odd.lsp.parking;

public class Truck extends Car {

    public Truck(String name, int size) {
        super(name, size);
        if (size <= CAR_SIZE) {
            throw new IllegalArgumentException("Размер не соответствует грузовому автомобилю!");
        }
    }
}
