package ru.job4j.odd.lsp.products;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import ru.job4j.solid.odd.lsp.products.*;

import java.time.LocalDate;
import java.util.List;

public class ControlQualityTest {

    @Test
    public void whenAddToShop() {
        Store shop = new Shop();
        List<Store> storeList = List.of(shop);
        LocalDate createDate = LocalDate.now().minusDays(3);
        LocalDate expireDate = LocalDate.now().plusDays(3);
        Food food = new Meat("Мясо", expireDate, createDate, 500, 0);
        ControlQuality controlQuality = new ControlQuality(storeList);
        controlQuality.addFoodsInStore(food);
        assertThat(shop.getFoodList()).containsAll(List.of(food));
    }

    @Test
    public void whenAddToWarehouse() {
        Store warehouse = new Warehouse();
        List<Store> storeList = List.of(warehouse);
        LocalDate createDate = LocalDate.now();
        LocalDate expireDate = LocalDate.now().plusMonths(1);
        Food food = new Meat("Мясо", expireDate, createDate, 500, 0);
        ControlQuality controlQuality = new ControlQuality(storeList);
        controlQuality.addFoodsInStore(food);
        assertThat(warehouse.getFoodList()).containsAll(List.of(food));
    }

    @Test
    public void whenAddToShopWithDiscount() {
        Store shop = new Shop();
        List<Store> storeList = List.of(shop);
        LocalDate expireDate = LocalDate.now().plusDays(1);
        LocalDate createDate = LocalDate.now().minusDays(3);
        Food food = new Meat("Мясо", expireDate, createDate, 500, 200);
        Food expected = new Meat("Мясо", expireDate, createDate, 300, 0);
        ControlQuality controlQuality = new ControlQuality(storeList);
        controlQuality.addFoodsInStore(food);
        controlQuality.addFoodsInStore(expected);
        assertThat(shop.getFoodList()).contains(expected);
    }

    @Test
    public void whenAddToTrash() {
        Store trash = new Trash();
        List<Store> storeList = List.of(trash);
        LocalDate createDate = LocalDate.now().minusDays(1);
        LocalDate expireDate = LocalDate.now();
        Food food = new Meat("Мясо", expireDate, createDate, 500, 0);
        ControlQuality controlQuality = new ControlQuality(storeList);
        controlQuality.addFoodsInStore(food);
        assertThat(trash.getFoodList()).containsAll(List.of(food));
    }

    @Test
    public void addEverywhere() {
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        List<Store> storeList = List.of(warehouse, shop, trash);
        LocalDate createDateFood = LocalDate.now();
        LocalDate expireDateFood = LocalDate.now().plusMonths(1);
        Food food = new Meat("Мясо", expireDateFood, createDateFood, 500, 0);
        LocalDate createDateFood1 = LocalDate.now().minusDays(3);
        LocalDate expireDateFood1 = LocalDate.now().plusDays(3);
        Food food1 = new Meat("Мясо1", expireDateFood1, createDateFood1, 500, 200);
        LocalDate createDateFood2 = LocalDate.now().plusDays(1);
        LocalDate expireDateFood2 = LocalDate.now().minusDays(3);
        Food food2 = new Meat("Мясо2", expireDateFood2, createDateFood2, 500, 0);
        LocalDate createDateFood3 = LocalDate.now().minusDays(1);
        LocalDate expireDateFood3 = LocalDate.now();
        Food food3 = new Meat("Мясо3", expireDateFood3, createDateFood3, 500, 0);
        ControlQuality controlQuality = new ControlQuality(storeList);
        controlQuality.addFoodsInStore(food);
        controlQuality.addFoodsInStore(food1);
        controlQuality.addFoodsInStore(food2);
        controlQuality.addFoodsInStore(food3);
        assertThat(warehouse.getFoodList()).containsAll(List.of(food));
        assertThat(shop.getFoodList()).containsAll(List.of(food1));
        assertThat(shop.getFoodList()).containsAll(List.of(food2));
        assertThat(trash.getFoodList()).containsAll(List.of(food3));
    }
}