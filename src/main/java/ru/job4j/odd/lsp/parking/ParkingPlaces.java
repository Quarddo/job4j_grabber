package ru.job4j.odd.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class ParkingPlaces implements Parking {

    private final List<Car> parkingCars = new ArrayList<>();

    @Override
    public void add(Car car) {
    }

    @Override
    public List<Car> getCarList() {
        return List.copyOf(parkingCars);
    }

    @Override
    public boolean checkPlace(Car car) {
        return false;
    }
}
