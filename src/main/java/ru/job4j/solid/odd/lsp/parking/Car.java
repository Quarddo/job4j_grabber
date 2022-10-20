package ru.job4j.solid.odd.lsp.parking;

public abstract class Car {

    public static final int CAR_SIZE = 1;

    private String name;
    private int size;

    public Car(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
