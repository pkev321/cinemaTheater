package ru.levelup.java.cinema.theater;

import ru.levelup.java.cinema.theater.entities.Movie;

import java.util.List;

public class CinemaTheater {

    private ConsoleHelper consoleHelper;
    private List<Movie> movies;

    public static void main(String[] args) {
        CinemaTheater cinemaTheater = new CinemaTheater();
        cinemaTheater.consoleHelper = new ConsoleHelper();
        cinemaTheater.initMenu();

    }

    private void initMenu(){
        consoleHelper.printlnToConsole("Выберите пунк меню");
        consoleHelper.printlnToConsole("1 - Показать информацию о сеансах");
        consoleHelper.printlnToConsole("2 - Покупка билета");
        consoleHelper.printlnToConsole("3 - Заказ еды");
        consoleHelper.printlnToConsole("Выберите: ");
        int menu = consoleHelper.getIntValueFromConsole();
    }

    private void sessionInformation() {

        for (Movie movie : movies ) {
            consoleHelper.printlnToConsole(movie.toString());
        }
    }
}
