package ru.job4j.odd.lsp.products;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {

    List<Food> warehouseFood = new ArrayList<>();

    @Override
    public void add(Food food) {
        warehouseFood.add(food);
    }

    @Override
    public List<Food> getFoodList() {
        return warehouseFood;
    }
}
