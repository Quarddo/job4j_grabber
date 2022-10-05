package ru.job4j.odd.lsp.products;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {

    private final List<Food> warehouseFood = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (accept(food)) {
            warehouseFood.add(food);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public List<Food> getFoodList() {
        return List.copyOf(warehouseFood);
    }

    @Override
    public boolean accept(Food food) {
        return percent(food) < PERCENT_25;
    }
}
