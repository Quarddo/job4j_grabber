package ru.job4j.solid.odd.isp.menu;

import java.util.Scanner;

public class TODOApp {

    public static final ActionDelegate STUB_ACTION = System.out::println;
    public static  final  String MENU_ACTION = """
            Выберите действие:
            1 - Добавить пункт меню
            2 - Добавить подпункт меню
            3 - Вывести список пунктов
            4 - Выход
            """;

    private static final String ADD = "1";
    private static final String ADD_SUB = "2";
    private static final String PRINT_ITEM = "3";
    private static final String EXIT = "4";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new SimpleMenu();
        MenuPrinter printer = new SimpleMenuPrinter();
        while (true) {
            System.out.println(MENU_ACTION);
            String choose = scanner.nextLine();
            if (EXIT.equals(choose)) {
                break;
            } else if (ADD.equals(choose)) {
                System.out.println("Введите новый пункт меню!");
                menu.add(Menu.ROOT, scanner.nextLine(), STUB_ACTION);
            } else if (ADD_SUB.equals(choose)) {
                System.out.println("Введите название пункта!");
                String item = scanner.nextLine();
                menu.add(item, scanner.nextLine(), STUB_ACTION);
            } else if (PRINT_ITEM.equals(choose)) {
                printer.print(menu);
            } else {
                System.out.println("Пункт меню не найден, введите корректные данные!");
            }
        }
    }
}