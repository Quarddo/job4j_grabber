package ru.job4j.odd.lsp.products;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {
    List<Food> trashFood = new ArrayList<>();

    @Override
    public void add(Food food) {
        trashFood.add(food);
    }

    @Override
    public List<Food> getFoodList() {
        return trashFood;
    }
}
