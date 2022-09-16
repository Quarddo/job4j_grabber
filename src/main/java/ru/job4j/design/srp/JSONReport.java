package ru.job4j.design.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.function.Predicate;

public class JSONReport implements Report {

    private Store store;
    private Gson gson = new GsonBuilder().create();

    public JSONReport(Store store) {
        this.store = store;
    }

    public String generate(Predicate<Employee> filter) {
        return gson.toJson(store.findBy(filter));
    }
/**
    public static void main(String[] args) {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker1 = new Employee("Andrey", now, now, 200);
        store.add(worker);
        store.add(worker1);
        Report salary = new JSONReport(store);
        System.out.println(salary.generate(em -> true));
    }
 */
}
