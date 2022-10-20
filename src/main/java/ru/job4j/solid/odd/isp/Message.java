package ru.job4j.solid.odd.isp;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * В данном случае все методы пригодились, но одного нам не хватило
 * который предназначен, для отправки файлов. Для данного метода стоит выделить
 * отдельный интерфейс.
 */
public class Message implements Program {

    String msg = "Hi";
    List<Object> letter = new ArrayList<>();

    @Override
    public void writes() {
        InputStream in = System.in;
    }

    @Override
    public void reading() {
        System.out.println(msg);
    }

    @Override
    public void add() {
        letter.add(msg);
    }

    @Override
    public void delete() {
        letter.clear();
    }

    @Override
    public void sends() {
        System.out.println("Сообщение отправлено!");
    }

    public void sendFile() {
        System.out.println("Файл отправлен успешно!");
    }
}
