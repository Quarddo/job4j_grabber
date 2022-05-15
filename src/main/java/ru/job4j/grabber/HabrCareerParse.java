package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.DateTimeParser;
import ru.job4j.grabber.utils.HarbCareerDateTimeParser;

import java.io.IOException;
import java.sql.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HabrCareerParse implements Parse {

    private static final String SOURCE_LINK = "https://career.habr.com";

    private static final String PAGE_LINK = String.format("%s/vacancies/java_developer", SOURCE_LINK);

    private final DateTimeParser dateTimeParser;

    public HabrCareerParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    public static void main(String[] args) throws IOException {
        HabrCareerParse habrCareerParse = new HabrCareerParse(new HarbCareerDateTimeParser());
        List<Post> rsl = new ArrayList<>();
        for (int pageNum = 1; pageNum <= 5; pageNum++) {
            List<Post> postList = habrCareerParse.list(PAGE_LINK + "?page=" + pageNum);
            rsl.addAll(postList);
        }
    }

    private static String retrieveDescription(String link) throws IOException {
        Connection connection = Jsoup.connect(link);
        Document document = connection.get();
        Elements rows = document.select(".job_show_description__body");
        Element description = rows.select(".style-ugc").first();
        return description.text();
    }

    @Override
    public List<Post> list(String link) throws IOException {
        List<Post> rsl = new ArrayList<>();
        Connection connection = Jsoup.connect(link);
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
            String vacancyLink = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
            /**System.out.printf("%s %s %s%n", dateTime, vacancyName, vacancyLink);*/
            try {
                String description = retrieveDescription(vacancyLink);
                /**System.out.printf("Описание: %s" + System.lineSeparator(), description);*/
                rsl.add(new Post(vacancyName, vacancyLink, description, dateTime));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return rsl;
    }
}
