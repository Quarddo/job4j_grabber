package ru.job4j.solid.odd.isp;

/**
 * Нарушение принципа ISP (Interface Segregation Principle), заключается в том что данный
 * интерфейс имеет слишком много методов(более трех), не все методы могут пригодиться.
 */

public interface Program {

    void writes();
    void reading();
    void add();
    void delete();
    void sends();
}
