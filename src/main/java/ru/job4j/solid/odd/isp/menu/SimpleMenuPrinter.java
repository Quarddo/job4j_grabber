package ru.job4j.solid.odd.isp.menu;

import java.util.ArrayList;
import java.util.List;

public class SimpleMenuPrinter implements MenuPrinter {

    public static final String RETRACT = "----";

    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo menuItem : menu) {
            String scheme = RETRACT.repeat(menuItem.getNumber().split("\\.").length - 1)
                    + menuItem.getNumber() + menuItem.getName();
            System.out.println(scheme);
        }
    }
}