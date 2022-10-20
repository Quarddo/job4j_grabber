package ru.job4j.solid.odd.lsp.products;

import java.time.LocalDate;

public class Meat extends Food {


    public Meat(String name, LocalDate expireDate, LocalDate createDate, int price, int discount) {
        super(name, expireDate, createDate, price, discount);
    }
}
