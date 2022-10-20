package ru.job4j.solid.odd.isp;

/**
 * Для данного класса неверно выделена абстракция, что привело
 * к нарушению принципа ISP. Книгу нужно лишь прочесть, остальные
 * методы тут без надобности.
 * Какое здесь решение? Разделение интерфейсов, причем нам нужно
 * подписать под нужные интерфейсы только нужные реализации.
 */

public class Book implements Program {

    String book = "Book";

    @Override
    public void writes() {

    }

    @Override
    public void reading() {
        System.out.println(book);
    }

    @Override
    public void add() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void sends() {

    }
}
