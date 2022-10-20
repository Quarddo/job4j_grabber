package ru.job4j.solid.odd.srp;

/**
 * Данный интерфейс должен выполнить два действия узнать какое количество товара нужно для закупа,
 * и после распродать данный товар. Что нарушает принцип SRP, так как у класса должна быть выполнена одна цель,
 * нужно создать отдельный интерфейс.
 */

public interface Product {
    public void purchase(int remainder, int rightAmount);
    public void sale(int price);
}
