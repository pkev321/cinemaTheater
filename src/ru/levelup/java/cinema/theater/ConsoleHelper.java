package ru.levelup.java.cinema.theater;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ConsoleHelper {
    public  void printToConsole(String message){
        System.out.print(message);
    }

    public  void printlnToConsole(String message){
        System.out.println(message);
    }

    public int getIntValueFromConsole(){
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in))

        int value = -1;
        try( Scanner scanner = new Scanner(System.in)) {
            value = scanner.nextInt();
        }
        //scanner.close();
        return value;

    }
}
