package ru.job4j.grabber;

import java.util.List;

/**
 * Извлечение данных с сайта.
 * Операция извлечения данных с  сайта описывается отдельным интерфейсом.
 *
 * Этот компонент позволяет собрать короткое описание всех объявлений, а так же загрузить детали по каждому объявлению.
 *
 * list(link) - этот метод загружает список объявлений по ссылке типа - https:-www.sql.ru-forum-job-offers-1
 *
 * Описание компонента через интерфейс позволяет расширить наш проект.
 *
 * Например, осуществить сбор данных с других площадок: SqlRuParse, SuperJobParse.
 */
public interface Parse {
    List<Post> list(String link);
}
