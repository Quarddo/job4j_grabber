package ru.job4j.odd.lsp.products;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {
   private final List<Food> shopFood = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (accept(food)) {
            if (discountCheck(food)) {
                setDiscount(food);
            }
            shopFood.add(food);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public List<Food> getFoodList() {
        return List.copyOf(shopFood);
    }

    @Override
    public boolean accept(Food food) {
        return percent(food) >= PERCENT_25 && percent(food) < PERCENT_75;
    }

    public boolean discountCheck(Food food) {
        return percent(food) >= PERCENT_75 && percent(food) < PERCENT_100;
    }

    public void setDiscount(Food food) {
        food.setPrice(food.getPrice() - food.getDiscount());
    }
}
