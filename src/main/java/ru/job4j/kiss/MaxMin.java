package ru.job4j.kiss;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return comparingValues(value, comparator);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return comparingValues(value, comparator.reversed());
    }

    private static <T> T comparingValues(List<T> value, Comparator<T> comparator) {
        T rsl;
        if (value.isEmpty()) {
            rsl = null;
        } else {
            rsl = value.get(0);
        }
        for (T values : value) {
            if (comparator.compare(rsl, values) < 0) {
                rsl = values;
            }
        }
        return rsl;
    }
}
