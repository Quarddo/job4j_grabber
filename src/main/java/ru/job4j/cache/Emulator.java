package ru.job4j.cache;

import java.util.Scanner;

public class Emulator {

    private static void choiceMenu() {
        System.out.println(
                "1. Загрузить содержимое файла в кэш \n"
                        + "2. Получить содержимое файла \n"
                        + "3. Завершить работу");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Укажите кэшируемую директорию: ");
        String dirName = scanner.nextLine();
        DirFileCache cache = new DirFileCache(dirName);
        System.out.println("Введите имя файла: ");
        String fileName = scanner.nextLine();
        boolean run = true;
        while (run) {
            choiceMenu();
            int choice = scanner.nextInt();
            if (choice == 1) {
                cache.put(fileName, cache.load(fileName));
                System.out.println("Содержимое фйла загруженно в кэш.");
            }
            if (choice == 2) {
                System.out.println("содержимое файла: " + cache.get(fileName));

            }
            if (choice == 3) {
                run = false;
                System.out.println("Работа завершена.");
            }
        }
    }
}
