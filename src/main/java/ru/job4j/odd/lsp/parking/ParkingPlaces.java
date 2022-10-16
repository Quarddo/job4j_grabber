package ru.job4j.odd.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class ParkingPlaces implements Parking {

    private final List<Car> parkingCars = new ArrayList<>();

    private int countPassengerPlace;
    private int countTruckPlace;
    private final List<Car> passengerList;
    private final List<Car> truckList;

    public ParkingPlaces(int countPassengerPlace, int countTruckPlace) {
        this.countPassengerPlace = countPassengerPlace;
        this.countTruckPlace = countTruckPlace;
        passengerList = new ArrayList<>(countPassengerPlace);
        truckList = new ArrayList<>(countTruckPlace);
    }

    @Override
    public boolean add(Car car) {
        boolean rsl = false;
        if (checkPassengerPlace(car)) {
            passengerList.add(car);
            countPassengerPlace--;
            rsl = true;
        } else if (checkTruckPlace(car)) {
            truckList.add(car);
            countTruckPlace--;
            rsl = true;
        } else if (addTruckInPassengerPlace(car)) {
            passengerList.add(car);
            countPassengerPlace = countPassengerPlace - car.getSize();
            rsl = true;
        }
        return rsl;
    }

    @Override
    public List<Car> getCarList() {
        return List.copyOf(parkingCars);
    }


    public boolean checkPassengerPlace(Car car) {
        boolean rsl = false;
        if (car.getSize() == Car.CAR_SIZE && countPassengerPlace >= passengerList.size()) {
            rsl = true;
        }
        return rsl;
    }

    public boolean checkTruckPlace(Car car) {
        boolean rsl = false;
        if (car.getSize() > Car.CAR_SIZE && countTruckPlace >= truckList.size()) {
            rsl = true;
        }
        return rsl;
    }

    public boolean addTruckInPassengerPlace(Car car) {
        boolean rsl = false;
        if (car.getSize() > Car.CAR_SIZE && countTruckPlace < truckList.size()
                && countPassengerPlace > truckList.size()) {
            rsl = true;
        }
        return rsl;
    }
}
