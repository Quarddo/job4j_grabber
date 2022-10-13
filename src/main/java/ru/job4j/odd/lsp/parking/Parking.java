package ru.job4j.odd.lsp.parking;

import java.util.List;

public interface Parking {

    boolean add(Car car);
    List<Car> getCarList();
    boolean checkPlace(Car car);


}
