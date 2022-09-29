package ru.job4j.odd.lsp.products;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {
    List<Food> shopFood = new ArrayList<>();

    @Override
    public void add(Food food) {
        shopFood.add(food);
    }

    @Override
    public List<Food> getFoodList() {
        return shopFood;
    }
}
