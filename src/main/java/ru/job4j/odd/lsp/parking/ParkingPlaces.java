package ru.job4j.odd.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class ParkingPlaces implements Parking {

    private final List<Car> parkingCars = new ArrayList<>();

    private final int passengerSize;
    private final int truckSize;
    private final List<Car> passengerList;
    private final List<Car> truckList;

    public ParkingPlaces(int passengerSize, int truckSize) {
        this.passengerSize = passengerSize;
        this.truckSize = truckSize;
        passengerList = new ArrayList<>(passengerSize);
        truckList = new ArrayList<>(truckSize);
    }

    @Override
    public boolean add(Car car) {
        return true;
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
