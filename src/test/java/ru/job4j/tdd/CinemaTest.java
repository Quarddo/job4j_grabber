package ru.job4j.tdd;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.Matchers.emptyArray;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class CinemaTest {

    @Test(expected = IllegalArgumentException.class)
    @Ignore
    public void whenBuy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2022, 07, 22, 17, 30);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Test(expected = IllegalArgumentException.class)
    @Ignore
    public void whenNotBuy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2022, 07, 22, 17, 30);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertNull(ticket);
    }

    @Test(expected = IllegalArgumentException.class)
    @Ignore
    public void whenFind() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Arrays.asList(new Session3D())));
    }

    @Test(expected = IllegalArgumentException.class)
    @Ignore
    public void whenNotFind() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> false);
        assertThat(sessions, is(Arrays.asList(emptyArray())));
    }

    @Test(expected = IllegalArgumentException.class)
    @Ignore
    public void whenInvalidPlace() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2022, 07, 22, 17, 30);
        Ticket ticket = cinema.buy(account, -1, -1, date);
    }

    @Test(expected = IllegalArgumentException.class)
    @Ignore
    public void whenInvalidDate() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2022, 13, 22, 17, 30);
        Ticket ticket = cinema.buy(account, 1, 1, date);
    }
}