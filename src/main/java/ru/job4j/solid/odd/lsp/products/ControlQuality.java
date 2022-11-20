package ru.job4j.solid.odd.lsp.products;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {

    protected List<Store> stores;
    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void addFoodsInStore(Food food) {
        for (Store store : stores) {
            if (store.add(food)) {
                break;
            }
        }
    }

    public void resort() {
        List<Food> foods = new ArrayList<>();
        for (Store store : stores) {
         foods.addAll(store.getFoodList());
         store.removeAll();
        }
        for (Food food : foods) {
            addFoodsInStore(food);
        }
    }
}
