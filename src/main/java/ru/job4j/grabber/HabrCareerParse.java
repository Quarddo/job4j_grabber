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
import java.util.Collection;
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
            List<Post> postList = habrCareerParse.list(PAGE_LINK);
            postList.forEach(System.out::println);

    }

    private static String retrieveDescription(String link) throws IOException {
        Connection connection = Jsoup.connect(link);
        Document document = connection.get();
        Elements rows = document.select(".job_show_description__body");
        Element description = rows.select(".style-ugc").first();
        return description.text();
    }

    @Override
    public List<Post> list(String link) {
        List<Post> rslList = new ArrayList<>();
        for (int pageNum = 1; pageNum <= 5; pageNum++) {
            try {
            Connection connection = Jsoup.connect(link + "?page" + pageNum);
            Document document = connection.get();
            Elements rows = document.select(".vacancy-card__inner");
            List<Post> post = new ArrayList<>();
            rows.forEach(row -> post.add(parsePost(row)));
            rslList.addAll(post);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return rslList;
    }

    private Post parsePost(Element row) {
        List<Post> rsl = new ArrayList<>();
        Element titleElement = row.select(".vacancy-card__title").first();
        Element linkElement = titleElement.child(0);
        String vacancyName = titleElement.text();
        Element dateElement = row.select(".vacancy-card__date").first();
        Element linkElementDate = dateElement.child(0);
        String vacancyDate = linkElementDate.attr("datetime");
        LocalDateTime dateTime = dateTimeParser.parse(vacancyDate);
        String vacancyLink = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
        String description = "";
        try {
            description = retrieveDescription(vacancyLink);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Post(vacancyName, vacancyLink, description, dateTime);
    }
}
