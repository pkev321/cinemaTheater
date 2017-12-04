package ru.levelup.java.cinema.theater.entities;

public class Ticket {
    private int place;
    private Session session;

    public Ticket(int place, Session session) {
        this.place = place;
        this.session = session;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
