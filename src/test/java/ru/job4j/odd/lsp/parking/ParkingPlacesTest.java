package ru.job4j.odd.lsp.parking;

import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ParkingPlacesTest {

    @Test
    public void whenAddToParkingTwoPassengerAndOneTruck() {
        Car passenger = new Passenger("Camry");
        Car passenger1 = new Passenger("Octavia");
        Car truck = new Truck("Man", 2);
        Parking parking = new ParkingPlaces(2, 1);
        assertThat(parking.add(passenger)).isTrue();
        assertThat(parking.add(passenger1)).isTrue();
        assertThat(parking.add(truck)).isTrue();
    }

    @Test
    public void whenAddToParkingTruckBigSizeAndTruckInPlaceOfPassenger() {
        Car bigTruck = new Truck("Man", 4);
        Car truck = new Truck("Volvo", 2);
        Parking parking = new ParkingPlaces(3, 1);
        assertThat(parking.add(bigTruck)).isTrue();
        assertThat(parking.add(truck)).isTrue();
    }


    @Test
    public void whenNotEnoughSpaceForPassenger() {
        Car passenger = new Passenger("BMW");
        Car passenger1 = new Passenger("KIA");
        Car truck = new Truck("Man", 2);
        Parking parking = new ParkingPlaces(1, 1);
        assertThat(parking.add(passenger)).isTrue();
        assertThat(parking.add(truck)).isTrue();
        assertThat(parking.add(passenger1)).isFalse();

    }


    @Test
    public void whenNotEnoughSpaceForTruck() {
        Car passenger = new Passenger("BMW");
        Car truck = new Truck("Man", 2);
        Car truck1 = new Truck("Volvo", 2);
        Parking parking = new ParkingPlaces(1, 1);
        assertThat(parking.add(passenger)).isTrue();
        assertThat(parking.add(truck)).isTrue();
        assertThat(parking.add(truck1)).isFalse();
    }


    @Test
    public void whenPassengerAndTruckTogether() {
        Car passenger = new Passenger("BMW");
        Car passenger1 = new Passenger("KIA");
        Car truck = new Truck("Man", 2);
        Car truck1 = new Truck("Volvo", 2);
        Parking parking = new ParkingPlaces(5, 1);
        assertThat(parking.add(passenger)).isTrue();
        assertThat(parking.add(passenger1)).isTrue();
        assertThat(parking.add(truck)).isTrue();
        assertThat(parking.add(truck1)).isTrue();
        assertThat(parking.add(new Passenger("test"))).isTrue();
        assertThat(parking.add(new Truck("testT", 3))).isFalse();
        assertThat(parking.add(truck1)).isFalse();
    }
}