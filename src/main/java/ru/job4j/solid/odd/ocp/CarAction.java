package ru.job4j.solid.odd.ocp;

import java.util.ArrayList;
import java.util.List;

/**
 * 1) Поле должно быть абстрактным, ибо при дальнейшем использовании нужно будет изменить список, нарушив OCP.
 * Так же допущена реализация поля.
 * 2) В данном классе не используется наследование (расширение), если появиться не исправная машина
 * нужно будет использовать изменение,
 * что может привести к некорректной работе программы.
 * 3) Так же, если нужно будет изменить параметры и тип возвращаемого значения об исправности автомобиля,
 * то нужно будет переписать код.
 * 4) В классах Car и Capacity, нарушением является, наследование состояния объекта.
 */
public class CarAction {

    private int count = 100;
    public ArrayList<Car> info = new ArrayList<>();

    private static class Car {
        private final int seats;

        private Car(int seats) {
            this.seats = seats;
        }

        public boolean serviceable(String result) {
            return true;
        }
    }

   public static class Capacity extends Car {
        public Capacity(int seats) {
            super(seats);
        }
    }
}


