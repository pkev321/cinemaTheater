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

    private void initMenu(){
        consoleHelper.printlnToConsole("Выберите пунк меню");
        consoleHelper.printlnToConsole("1 - Показать информацию о сеансах");
        consoleHelper.printlnToConsole("2 - Покупка билета");
        consoleHelper.printlnToConsole("3 - Заказ еды");
        consoleHelper.printlnToConsole("Выберите: ");
        int menu = consoleHelper.getIntValueFromConsole();
        switch (menu) {
            case (1):
                sessionInformation();
                break;
            case (2):
                break;
            case(3):
                break;
            default:
                return;
        }


    }

    private void sessionInformation() {

        for (Movie movie : movies ) {
            consoleHelper.printlnToConsole(movie.toString());
        }
    }

    private void readData() {
// title;duration;description;session{startTime;price;Hall{name,amountOfPlace};Ticket{place}}
        final String FILE_NAME = "res/settings.txt";
        try {

            Scanner sc = new Scanner(new File(FILE_NAME)).useDelimiter(";");

            while (sc.hasNext()) {
                String movTitle = sc.next();
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
                Movie movie = new Movie(new ArrayList<Session>(), movTitle, movDurat, movDesc);
                System.out.println(movie);
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
