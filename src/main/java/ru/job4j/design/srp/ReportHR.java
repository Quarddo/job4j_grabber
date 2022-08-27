package ru.job4j.design.srp;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class ReportHR implements Report {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");
    public static final String SEPARATOR = System.lineSeparator();

    private Store store;

    public ReportHR(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
        employees.sort((o1, o2) -> Double.compare(o2.getSalary(), o1.getSalary()));
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(SEPARATOR);
        for (Employee employee : employees) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(SEPARATOR);
        }
        return text.toString();
    }
}