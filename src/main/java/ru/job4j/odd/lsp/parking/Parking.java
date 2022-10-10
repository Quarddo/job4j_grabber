package ru.job4j.odd.lsp.parking;

import java.util.List;

public interface Parking {
    public static final int PARKING_SIZE = 10;

    void add(Car car);
    List<Car> getCarList();
    boolean checkPlace(Car car);
}
