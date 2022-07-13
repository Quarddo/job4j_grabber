package ru.job4j.gc.prof;

import java.util.Random;
import java.util.Scanner;

public class Start {

    private static final int MERGE_SORT = 1;
    private static final int INSERT_SORT = 2;
    private static final int BUBBLE_SORT = 3;
    private static final int FINISH = 4;

    private static void choiceMenu() {
        System.out.println(
                "1. Сортировка слиянием.\n"
                        + "2. Сортировка методом вставки.\n"
                        + "3. Сортировка пузырьком.\n"
                        + "4. Завершить работу."
        );
    }

    public static void main(String[] args) {
        Random random = new Random();
        RandomArray randomArray = new RandomArray(random);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество элементов: ");
        int value = scanner.nextInt();
        int count = value;
        value = 0;
        randomArray.insert(count);
        System.out.println("Выберите тип сортировки: ");
        boolean run = true;
        while (run) {
            choiceMenu();
            int choice = scanner.nextInt();
            if (choice == MERGE_SORT) {
                MergeSort mergeSort = new MergeSort();
                System.out.println(mergeSort.sort(randomArray) + System.lineSeparator());
            }
            if (choice == INSERT_SORT) {
                InsertSort insertSort = new InsertSort();
                System.out.println(insertSort.sort(randomArray) + System.lineSeparator());
            }
            if (choice == BUBBLE_SORT) {
                BubbleSort bubbleSort = new BubbleSort();
                System.out.println(bubbleSort.sort(randomArray) + System.lineSeparator());
            }
            if (choice == FINISH) {
                run = false;
                System.out.println("Работа завершена!");
            }
        }
    }
}
