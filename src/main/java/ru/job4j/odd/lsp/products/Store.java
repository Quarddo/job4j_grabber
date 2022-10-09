package ru.job4j.odd.lsp.products;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;

public interface Store {
    public static final int PERCENT_25 = 25;
    public static final int PERCENT_75 = 75;
    public static final int PERCENT_100 = 100;

    boolean add(Food food);
    List<Food> getFoodList();
    boolean accept(Food food);

    default float percent(Food food) {
        double bestBeforeDate = ChronoUnit.DAYS.between(food.getCreateDate(),
                food.getExpireDate());
        double daysExisted = ChronoUnit.DAYS.between(food.getCreateDate(),
                LocalDate.now());
        return (float) (daysExisted / bestBeforeDate * 100);
    }
}
