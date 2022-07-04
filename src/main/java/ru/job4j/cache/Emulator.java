package ru.job4j.cache;

import java.util.Scanner;

public class Emulator {

    private static final int LOAD_IN_CACHE = 1;
    private static final int GET_FILE_CONTENT = 2;
    private static final int FINISH_WORK = 3;

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
            if (choice == LOAD_IN_CACHE) {
                cache.put(fileName, cache.get(fileName));
                System.out.println("Содержимое файла загружено в кэш:\n" + cache + "\n");
            }
            if (choice == GET_FILE_CONTENT) {
                System.out.println("Содержимое файла: \n" + cache.get(fileName) + "\n");

            }
            if (choice == FINISH_WORK) {
                run = false;
                System.out.println("Работа завершена.");
            }
        }
    }
}