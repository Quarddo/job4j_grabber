package ru.job4j.srp;

/**
 * В данном случае нарушен принцип SRP, т.к. метод addClient,
 * относится к отдельному бизнес-процессу и для него следует создать отдельный интерфейс.
 */

public interface InformingClients {
    public void sendMsg(String msg);
    public void addClient(String name);

}
