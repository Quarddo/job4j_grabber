package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.InputStream;
import java.util.Properties;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class AlertRabbitDemo {

    private static Properties properties = new Properties();

    private static int interval() {
        int rslInterval = 0;
        try (InputStream in = AlertRabbitDemo.class.getClassLoader().getResourceAsStream("rabbit.properties")) {
            properties.load(in);
            rslInterval = Integer.parseInt(properties.getProperty("rabbit.interval"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rslInterval;
    }

    public static void main(String[] args) {
        try {
            /** Конфигурирование.
             * Начало работы происходит с создания класса управляющего всеми работами.
             * В объект Scheduler мы будем добавлять задачи, которые хотим выполнять периодически. */
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            /** Создание задачи.
             * quartz каждый раз создает объект с типом org.quartz.Job */
            JobDetail job = newJob(Rabbit.class).build();
            /**Создание расписания.
             * Конструкция ниже настраивает периодичность запуска. В нашем случае,
             * мы будем запускать задачу через 10 секунд и делать это бесконечно.*/
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(interval())
                    .repeatForever();
            /** Задача выполняется через триггер.
             * Здесь можно указать, когда начинать запуск. Мы хотим сделать это сразу. */
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            /** Загрузка задачи и триггера в планировщик. */
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }

    /** Для создания задачи(2), Вам нужно создать класс реализующий этот интерфейс.
     Внутри этого класса нужно описать требуемые действия. В нашем случае - это вывод на консоль текста.*/
    public static class Rabbit implements Job {
        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            System.out.println("Rabbit runs here ...");
        }
    }
}
