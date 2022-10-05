package ru.job4j.odd.lsp.products;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
