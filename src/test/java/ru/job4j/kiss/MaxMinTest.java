package ru.job4j.kiss;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class MaxMinTest {
    private List<Integer> list = List.of(1, 2, 3, 4, 5, 6);
    private MaxMin maxMin = new MaxMin();

    @Test
    public void max() {
        int rsl = maxMin.max(list, Integer::compareTo);
        assertEquals(6, rsl);
    }

    @Test
    public void min() {
        int rsl = maxMin.min(list, Integer::compareTo);
        assertEquals(1, rsl);
    }

    @Test
    public void valueNull() {
        List<Integer> list = List.of();
        assertNull(maxMin.min(list, Integer::compareTo));
    }
}