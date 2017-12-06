package ru.levelup.java.cinema.theater;

import javafx.application.Application;
import ru.levelup.java.cinema.theater.entities.Hall;
import ru.levelup.java.cinema.theater.entities.Movie;
import ru.levelup.java.cinema.theater.entities.Session;
import ru.levelup.java.cinema.theater.entities.Ticket;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.DatabaseMetaData;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CinemaTheater {

    private ConsoleHelper consoleHelper;
    private List<Movie> movies;

    public static void main(String[] args) {

        CinemaTheater cinemaTheater = new CinemaTheater();
        cinemaTheater.consoleHelper = new ConsoleHelper();
        cinemaTheater.readData();
        cinemaTheater.initMenu();

    }

    /**
     * Отображение меню
     */
    private void initMenu(){
        consoleHelper.printlnToConsole("Выберите пунк меню");
        consoleHelper.printlnToConsole("1 - Показать информацию о сеансах");
        consoleHelper.printlnToConsole("2 - Покупка билета");
        consoleHelper.printlnToConsole("3 - Заказ еды");
        consoleHelper.printlnToConsole("0 - Выключить");
        consoleHelper.printlnToConsole("Выберите: ");
        int menu = consoleHelper.getIntValueFromConsole();
        switch (menu) {
            case (1):
                sessionInformation();
                break;
            case (2):
                ticketSelling();
                break;
            case(3):
                break;
            case(0):
                return;
            default:
                return;
        }
        // рекурсия
        initMenu();


    }

    /**
     * Отображение информации о имеющихся билетах и сеансах
     */
    private void sessionInformation() {

        for (Movie movie : movies ) {
            consoleHelper.printlnToConsole(movie.toString());
        }
    }

    /**
     * Чтение данных во нутреннюю структуру
     */
    private void readData() {
        movies = new ArrayList<Movie>();

        //Data struct in file settings: title;duration;description;session{startTime;price;Hall{name,amountOfPlace};Ticket{place}}

        final String FILE_NAME = "res/settings.txt";
        try {

            Scanner sc = new Scanner(new File(FILE_NAME)).useDelimiter(";");

            while (sc.hasNext()) {
                String movTitle = sc.next().trim();
                int movDurat = Integer.parseInt(sc.next());
                String movDesc = sc.next();
                Date sesSTime = new Date();
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
                    sesSTime = formatter.parse(sc.next());
                }
                catch (ParseException e)  {

                }
                double sesPrice = sc.nextDouble();
                String hallName = sc.next();
                int hallAPlace = Integer.parseInt(sc.next());
                int ticketNom = Integer.parseInt(sc.next());
                Hall hall = new Hall(hallName,hallAPlace );
                Ticket ticket = new Ticket(ticketNom);

                Session session = new Session(hall, sesSTime, sesPrice );
                session.getTickets().add(ticket);

                Movie movie = new Movie(movTitle, movDurat, movDesc);
                movie.getSessions().add(session);
                addMovie(movie);
                //System.out.println(movie);
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Функция осущствляет формирование структуры, специально для чтения данных из файла
     * @param movie струтура содержащаяся в одной строке файла настроек
     */
    private void addMovie(Movie movie) {
        boolean newMovie = true;
        for (Movie mov : movies) {
            if (mov.getTitle().equals(movie.getTitle())) {
                mov.addSession(movie.getSessions().get(0));
                newMovie = false;
            }
        }
        if (newMovie) {
            movies.add(movie);
        }
    }

    /**
     * Процедура продажи билетов
     */
    private void ticketSelling() {
        consoleHelper.printlnToConsole("");
        // выбор фильма
        consoleHelper.printlnToConsole("Select number of move:");
        int i = 0;
        for (Movie mov : movies) {
            consoleHelper.printlnToConsole( i + " :" + mov.getTitle() + ";" );
            i++;
        }
        int movNumber = consoleHelper.getIntValueFromConsole();
        if (movNumber > movies.size()-1 || movNumber < 0 ) {
            consoleHelper.printlnToConsole("Wrong choice.");
            return;
        }

        // selection session
        consoleHelper.printlnToConsole("Select number of session:");
        int i2 = 0;
        for (Session ses : movies.get(movNumber).getSessions()) {
            consoleHelper.printlnToConsole( i2 + " : Hall[" + ses.getHall() + "] Time[" + ses.getStartTime() + "] Available number of places = " + ses.getAmountOfAvailablePlaces() + ";" );
            i2++;
        }
        int sesNumber = consoleHelper.getIntValueFromConsole();
        if (sesNumber > movies.get(movNumber).getSessions().size()-1 || movNumber < 0 ) {
            consoleHelper.printlnToConsole("Wrong choice.");
            return;
        }

        // selection amount of tickets
        int amountOfAvailablePlaces = movies.get(movNumber).getSessions().get(sesNumber).getAmountOfAvailablePlaces();
        consoleHelper.printlnToConsole("How many tickets do you need? (Available only " + amountOfAvailablePlaces + "  places)");
        int needTickets = consoleHelper.getIntValueFromConsole();

        if (needTickets > amountOfAvailablePlaces) {
            consoleHelper.printlnToConsole("The auditorium is not rubber.");
            return;
        }

        double price = needTickets * movies.get(movNumber).getSessions().get(sesNumber).getPrice();
        consoleHelper.printlnToConsole("Give me your money!!!  not less than " + price + "  bunnies!");
        int bunnies = consoleHelper.getIntValueFromConsole();

        if (bunnies < price)
            consoleHelper.printlnToConsole("Not enough!");
        if (bunnies == price)
            consoleHelper.printlnToConsole("Ok!");
        if (bunnies > price) {
            consoleHelper.printlnToConsole("I will give you surrender  late......................");
            movies.get(movNumber).getSessions().get(sesNumber).saleTicket(needTickets);
        }
        consoleHelper.printlnToConsole("");
        consoleHelper.printlnToConsole("");
        consoleHelper.printlnToConsole("FREE TERMINAL !!!");
        consoleHelper.printlnToConsole("");
        consoleHelper.printlnToConsole("");
    }
}
