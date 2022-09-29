package ru.job4j.odd.lsp.products;

import java.util.List;

public interface Store {

    void add(Food food);
    List<Food> getFoodList();
}
