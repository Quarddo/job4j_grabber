package ru.job4j.odd.lsp.products;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Calendar;

public class ControlQualityTest {

    @Test
    public void whenAddToShop() {
        LocalDate expireDate = LocalDate.of(2022, Calendar.SEPTEMBER, 25);
        LocalDate createDate = LocalDate.of(2022, Calendar.OCTOBER, 6);
        Food food = new Food("Мясо", expireDate, createDate, 500, 0);
        ControlQuality controlQuality = new ControlQuality();
        controlQuality.productSorting(food);
        assertThat(controlQuality.shop.getFoodList().contains(food));
    }

    @Test
    public void whenAddToWarehouse() {
        LocalDate expireDate = LocalDate.of(2022, Calendar.SEPTEMBER, 29);
        LocalDate createDate = LocalDate.of(2022, Calendar.OCTOBER, 9);
        Food food = new Food("Мясо", expireDate, createDate, 500, 0);
        ControlQuality controlQuality = new ControlQuality();
        controlQuality.productSorting(food);
        assertThat(controlQuality.warehouse.getFoodList().contains(food));
    }

    @Test
    public void whenAddToShopWithDiscount() {
        LocalDate expireDate = LocalDate.of(2022, Calendar.SEPTEMBER, 25);
        LocalDate createDate = LocalDate.of(2022, Calendar.OCTOBER, 3);
        Food food = new Food("Мясо", expireDate, createDate, 500, 200);
        Food expected = new Food("Мясо", expireDate, createDate, 300, 200);
        ControlQuality controlQuality = new ControlQuality();
        controlQuality.productSorting(food);
        assertThat(controlQuality.shop.getFoodList().contains(expected));
    }

    @Test
    public void whenAddToTrash() {
        LocalDate expireDate = LocalDate.of(2022, Calendar.AUGUST, 31);
        LocalDate createDate = LocalDate.of(2022, Calendar.SEPTEMBER, 1);
        Food food = new Food("Мясо", expireDate, createDate, 500, 0);
        ControlQuality controlQuality = new ControlQuality();
        controlQuality.productSorting(food);
        assertThat(controlQuality.warehouse.getFoodList().contains(food));
    }
}