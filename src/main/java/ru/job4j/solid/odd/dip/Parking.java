package ru.job4j.solid.odd.dip;

import java.util.List;

/**
 * У парковки единое поле для хранения данных, это является нарушением DIP
 * т.к. нет зависимости от абстракции, а зависимость от реализации.
 *
 * Так же метод occupiedPlaces является нарушением DIP, потому что есть
 * прямая зависимость самого логирования от реализации.
 */

public class Parking {
    List<Car> list;

    public boolean add(Car car) {
        return false;
    }

    public Car findBuId() {
        return new Car();
    }


    public String occupiedPlaces(Car car) {
        return "occupied places: " + car.id + "" + car.name;

    }
}

class Car {
    String name;
    int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}