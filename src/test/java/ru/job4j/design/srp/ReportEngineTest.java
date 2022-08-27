package ru.job4j.design.srp;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;
import static ru.job4j.design.srp.ReportEngine.DATE_FORMAT;

import org.junit.Test;

public class ReportEngineTest {

    public static final String SEPARATOR = System.lineSeparator();

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenGeneratedHtml() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report html = new HtmlReport(store);
        StringBuilder expect = new StringBuilder()
                .append("<!DOCTYPE html>").append(SEPARATOR)
                .append("<html>").append(SEPARATOR)
                .append("<head>").append(SEPARATOR)
                .append(" <!-- Отчеты в виде html -->").append(SEPARATOR)
                .append("<meta charset=\"UTF-8\">").append(SEPARATOR)
                .append("<title>HtmlReport</title>").append(SEPARATOR)
                .append("</head>").append(SEPARATOR)
                .append("<body>").append(SEPARATOR)
                .append("Name; Hired; Fired; Salary;").append(SEPARATOR)
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary()).append(";").append(SEPARATOR)
                .append("</body>").append(SEPARATOR)
                .append("</html>").append(SEPARATOR);
        assertThat(html.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenSalaryChange() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report salary = new SalaryChange(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary()).append(" EUR. ").append(";")
                .append(System.lineSeparator());
        assertThat(salary.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenSalaryInDescendingOrderWithoutWorkExperience() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker1 = new Employee("Andrey", now, now, 200);
        store.add(worker);
        store.add(worker1);
        Report salary = new ReportHR(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;").append(SEPARATOR)
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";").append(SEPARATOR)
                .append(worker.getName()).append(";")
                .append(worker.getSalary()).append(";").append(SEPARATOR);
        assertThat(salary.generate(em -> true)).isEqualTo(expect.toString());
    }
}