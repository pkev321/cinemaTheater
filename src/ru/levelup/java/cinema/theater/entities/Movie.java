package ru.levelup.java.cinema.theater.entities;

import java.util.List;

public class Movie {

    private List<Session> sessions;
    private String title;
    private int duration;
    private String description;

    public Movie(List<Session> sessions, String title, int duration, String description) {
        this.sessions = sessions;
        this.title = title;
        this.duration = duration;
        this.description = description;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "title='" + title + '\'' +
                ", duration=" + duration +
                ", description='" + description + '\'' +
                ", sessions=" + sessions;
    }
}
