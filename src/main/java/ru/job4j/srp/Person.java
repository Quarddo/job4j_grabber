package ru.job4j.srp;

/**
 * В данном случае нарушение SRP происходит из-за того что, помимо представления сущности Person
 * класс выполняет манипуляцию по остатку средств на счете, данный метод нужно вынести отдельно.
 */
public class Person {
    private String name;
    private int accountAmount;

    public Person(String name, int accountAmount) {
        this.name = name;
        this.accountAmount = accountAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccountAmount() {
        return accountAmount;
    }

    public void setAccountAmount(int accountAmount) {
        this.accountAmount = accountAmount;
    }

    public int accountBalance(int money) {
        return money - 1000;
    }
}
