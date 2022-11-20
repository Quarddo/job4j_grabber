package ru.job4j.solid.odd.lsp.products;

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
        return percent(food) >= PERCENT_25 && percent(food) < PERCENT_100;
    }

    @Override
    public void removeAll() {
        shopFood.clear();
    }

    private boolean discountCheck(Food food) {
        return percent(food) >= PERCENT_75 && percent(food) < PERCENT_100;
    }

    private void setDiscount(Food food) {
        food.setPrice(food.getPrice() - food.getDiscount());
    }

/**
     public static void main(String[] args) {
         LocalDate expireDate = LocalDate.now().plusDays(1);
         LocalDate createDate = LocalDate.now().minusDays(3);
        double bestBeforeDate = ChronoUnit.DAYS.between(createDate,
                expireDate);
        System.out.println(bestBeforeDate);
        double daysExisted = ChronoUnit.DAYS.between(createDate,
                    LocalDate.now());
        System.out.println(daysExisted);
        float rsl = (float) (daysExisted / bestBeforeDate * 100);
        System.out.println(rsl);
    }
 */
}
