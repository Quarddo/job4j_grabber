package ru.job4j.solid.odd.lsp.products;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {
    private final List<Food> trashFood = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (accept(food)) {
            trashFood.add(food);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public List<Food> getFoodList() {
        return List.copyOf(trashFood);
    }

    @Override
    public boolean accept(Food food) {
        return percent(food) >= PERCENT_100;
    }
}
