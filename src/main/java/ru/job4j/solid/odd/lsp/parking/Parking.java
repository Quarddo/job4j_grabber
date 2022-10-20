package ru.job4j.solid.odd.lsp.parking;

import java.util.List;

public interface Parking {

    boolean add(Car car);
    List<Car> getCarList();


}
