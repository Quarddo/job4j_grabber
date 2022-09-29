package ru.job4j.odd.lsp.products;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ControlQuality {
    public static final int PERCENT_25 = 25;
    public static final int PERCENT_75 = 75;
    public static final int PERCENT_100 = 100;

    Store shop = new Shop();
    Store trash = new Trash();
    Store warehouse = new Warehouse();

    public float percent(Food food) {
        long bestBeforeDate = ChronoUnit.DAYS.between(food.getCreateDate(),
                food.getExpireDate());
        long daysOverdue = ChronoUnit.DAYS.between(food.getCreateDate(),
                LocalDate.now());
        return (float) (daysOverdue / bestBeforeDate * 100);
    }

    public void setDiscount(Food food) {
        food.setPrice(food.getPrice() - food.getDiscount());
    }

    public void productSorting(Food food) {
        if (percent(food) < PERCENT_25) {
            warehouse.add(food);
        } else if (percent(food) >= PERCENT_25 && percent(food) < PERCENT_75) {
            shop.add(food);
        } else if (percent(food) >= PERCENT_75 && percent(food) < PERCENT_100) {
            setDiscount(food);
            shop.add(food);
        } else {
            trash.add(food);

        }
    }
}
