package ru.job4j.odd.lsp;

import java.util.Locale;

import static java.lang.Character.isUpperCase;

/**
 * 1) В классе Account1 нарушен принцип LSP, за счет того что подкласс не должен создавать
 * больше предусловий чем это определено в базовом классе, подкласс Account1 добавляет
 * дополнительное предусловие, то есть усиливает его, что недопустимо.
 * 2) В классе Account2 нарушен принцип LSP, так как подкласс должен выполнить все постусловия
 * которые определены в базовом классе.
 * 3) В классе Account3 нарушен принцип LSP, потому что переопределенный метод setName, не производит
 * проверку, что не гарантирует корректного имени, как в родительском классе.
 */

class Account {

    String name;
    int money;

    public String getName() {
        return name;
    }

    public Account(int money) {
        this.money = money;
    }

    /**
     * 1.
     */
    public void capital(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("Капитал на счете, не может быть меньше 0");
        }
    }

    /**
     *2.
     */
    public int cashBack(int purchase, int money, boolean acc) {
        int balance = 3000;
        /**
         * Предусловие
         */
        if (!acc) {
            throw new IllegalArgumentException("Счет заблокирован");
        }
        balance -= purchase;
        /**
         * Постусловие
         */
        if (purchase >= 1000) {
            balance += 100;
        }
        return balance;
    }

    /**
     * 3.
     */
    protected boolean validate(String name) {
        boolean valid = !name.isEmpty();
        if (!isUpperCase(name.codePointAt(0))) {
            valid = false;
        }
        return valid;
    }

    public void setName(String name) {
        validate(name);
        this.name = name;
    }
}

class Account1 extends Account {

    public Account1(int money) {
        super(money);
    }

    @Override
    public void capital(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("Капитал на счете, не может быть меньше 0");
        }
        if (money > 0) {
            throw new IllegalArgumentException("Капитал на счете, не может быть свыше 10 тыс.");
        }
    }
}

class Account2 extends Account {
    public Account2(int money) {
        super(money);
    }

    public int cashBack(int purchase, boolean acc) {
        int balance = 3000;
        if (!acc) {
            throw new IllegalArgumentException("Счет заблокирован");
        }
        balance -= purchase;
        return balance;
    }
}

class Account3 extends Account {

    public Account3(int money) {
        super(money);
    }

    @Override
    protected boolean validate(String name) {
        boolean valid = false;
        return valid;
    }

    @Override
    public void setName(String name) {
        this.name = name;

    }
}