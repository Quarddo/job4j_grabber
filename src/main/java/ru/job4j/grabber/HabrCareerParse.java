package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.HarbCareerDateTimeParser;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class HabrCareerParse {

    private static final String SOURCE_LINK = "https://career.habr.com";

    private static final String PAGE_LINK = String.format("%s/vacancies/java_developer", SOURCE_LINK);

    public static void main(String[] args) throws IOException {
        for (int pageNum = 0; pageNum <= 5; pageNum++) {
            Connection connection = Jsoup.connect(PAGE_LINK + "?page" + pageNum);
            Document document = connection.get();
            HarbCareerDateTimeParser harbCareerDateTimeParser = new HarbCareerDateTimeParser();
            Elements rows = document.select(".vacancy-card__inner");
            rows.forEach(row -> {
                Element titleElement = row.select(".vacancy-card__title").first();
                Element linkElement = titleElement.child(0);
                String vacancyName = titleElement.text();
                Element dateElement = row.select(".vacancy-card__date").first();
                Element linkElementDate = dateElement.child(0);
                String vacancyDate = linkElementDate.attr("datetime");
                LocalDateTime dateTime = harbCareerDateTimeParser.parse(vacancyDate);
                String link = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
                System.out.printf("%s %s %s%n", dateTime, vacancyName, link);
                try {
                    String description = retrieveDescription(link);
                    System.out.printf("Описание: %s" + System.lineSeparator(), description);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static String retrieveDescription(String link) throws IOException {
        Connection connection = Jsoup.connect(link);
        Document document = connection.get();
        Elements rows = document.select(".job_show_description__body");
        Element description = rows.select(".style-ugc").first();
        return description.text();
    }
}
