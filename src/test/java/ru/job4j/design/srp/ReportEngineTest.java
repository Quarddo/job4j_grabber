package ru.job4j.design.srp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.StringJoiner;

import static org.assertj.core.api.Assertions.*;
import static ru.job4j.design.srp.ReportEngine.DATE_FORMAT;
import static ru.job4j.design.srp.SalaryChange.EURO;

import net.bytebuddy.matcher.NegatingMatcher;
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
                .append(worker.getSalary()).append(EURO).append(";")
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

    @Test
    public void whenGeneratedXML() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker1 = new Employee("Andrey", now, now, 200);
        store.add(worker);
        store.add(worker1);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        String date = dateFormat.format(now.getTime());
        Report salary = new XMLReport(store);
        StringJoiner expect = new StringJoiner("\n")
                .add("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
                .add("<employees>")
                .add("    <employees>")
                .add(String.format("        <fired>%s</fired>", date))
                .add(String.format("        <hired>%s</hired>", date))
                .add(String.format("        <name>%s</name>", worker.getName()))
                .add(String.format("        <salary>%s</salary>", worker.getSalary()))
                .add("    </employees>")
                .add("    <employees>")
                .add(String.format("        <fired>%s</fired>", date))
                .add(String.format("        <hired>%s</hired>", date))
                .add(String.format("        <name>%s</name>", worker1.getName()))
                .add(String.format("        <salary>%s</salary>", worker1.getSalary()))
                .add("    </employees>")
                .add("</employees>\n");
        assertThat(salary.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenGeneratedJSON() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker1 = new Employee("Andrey", now, now, 200);
        store.add(worker);
        store.add(worker1);
        Report json = new JSONReport(store);
        StringBuilder expect = new StringBuilder()
                .append("[{\"name\":\"").append(worker.getName()).append("\"")
                .append(",\"hired\":{\"year\":").append(now.get(Calendar.YEAR))
                .append(",\"month\":").append(now.get(Calendar.MONTH))
                .append(",\"dayOfMonth\":").append(now.get(Calendar.DAY_OF_MONTH))
                .append(",\"hourOfDay\":").append(now.get(Calendar.HOUR_OF_DAY))
                .append(",\"minute\":").append(now.get(Calendar.MINUTE))
                .append(",\"second\":").append(now.get(Calendar.SECOND))
                .append("},\"fired\":{\"year\":").append(now.get(Calendar.YEAR))
                .append(",\"month\":").append(now.get(Calendar.MONTH))
                .append(",\"dayOfMonth\":").append(now.get(Calendar.DAY_OF_MONTH))
                .append(",\"hourOfDay\":").append(now.get(Calendar.HOUR_OF_DAY))
                .append(",\"minute\":").append(now.get(Calendar.MINUTE))
                .append(",\"second\":").append(now.get(Calendar.SECOND))
                .append("},\"salary\":").append(worker.getSalary())
                .append("},{\"name\":\"").append(worker1.getName()).append("\"")
                .append(",\"hired\":{\"year\":").append(now.get(Calendar.YEAR))
                .append(",\"month\":").append(now.get(Calendar.MONTH))
                .append(",\"dayOfMonth\":").append(now.get(Calendar.DAY_OF_MONTH))
                .append(",\"hourOfDay\":").append(now.get(Calendar.HOUR_OF_DAY))
                .append(",\"minute\":").append(now.get(Calendar.MINUTE))
                .append(",\"second\":").append(now.get(Calendar.SECOND))
                .append("},\"fired\":{\"year\":").append(now.get(Calendar.YEAR))
                .append(",\"month\":").append(now.get(Calendar.MONTH))
                .append(",\"dayOfMonth\":").append(now.get(Calendar.DAY_OF_MONTH))
                .append(",\"hourOfDay\":").append(now.get(Calendar.HOUR_OF_DAY))
                .append(",\"minute\":").append(now.get(Calendar.MINUTE))
                .append(",\"second\":").append(now.get(Calendar.SECOND))
                .append("},\"salary\":").append(worker1.getSalary()).append("}]");
        assertThat(json.generate(em -> true)).isEqualTo(expect.toString());
    }
}