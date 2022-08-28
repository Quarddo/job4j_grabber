package ru.job4j.odd.ocp;

import java.util.List;

/**
 * 1) В данном классе не используется наследование (расширение), если появиться не исправная машина
 * нужно будет использовать изменение,
 * что может привести к некорректной работе программы.
 * 2) Так же, если нужно будет изменить тип возвращаемого значения об исправности автомобиля,
 * то нужно будет переписать код.
 * 3) В классах Car и Capacity, нарушением является, наследование состояния объекта.
 */
public class CarAction {

    private static class Car {
        private final int seats;

        private Car(int seats) {
            this.seats = seats;
        }

        public boolean serviceable() {
            return true;
        }
    }

   public static class Capacity extends Car {
        public Capacity(int seats) {
            super(seats);
        }
    }
}


