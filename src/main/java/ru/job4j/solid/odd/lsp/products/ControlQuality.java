package ru.job4j.solid.odd.lsp.products;

import java.util.List;

public class ControlQuality {

    private List<Store> stores;
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
}
