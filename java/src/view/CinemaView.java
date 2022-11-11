package view;

import Model.Cinema.Cinema;
import Model.Cinema.CinemaClass;
import Model.Cinema.Cineplex;
import Model.Cinema.Showtime;
import Model.Movie.MovieType;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CinemaView {

    public int displayCineplex(List<Cineplex> cineplexes){
        for(int i=0; i<cineplexes.size(); i++){
            System.out.println((i+1) + ") " + cineplexes.get(i).getCineplexName() + " Cineplex");
        }
        return cineplexes.size();
    }

    public Showtime getShowtime(List<Cineplex> cineplexes, int showtimeChoice, String movieName) {
        int count = 0;
        for (Cineplex cineplex : cineplexes) {
            for (Cinema cinema : cineplex.getCinemas()) {
                for (Showtime cinemaShowtime : cinema.getShowtimes()) {
                    if (cinemaShowtime.getDateTime().isAfter(LocalDateTime.now()) && cinemaShowtime.getMovie().getTitle().equalsIgnoreCase(movieName)) {
                        count++;
                        if (count == showtimeChoice)
                            return cinemaShowtime;
                    }
                }
            }
        }
        if (count == 0)
            System.out.println("Sorry, there is no showtime on this date!");
        return null;
    }
    public Showtime getShowtime(List < Cineplex > cineplexes,int showtimeChoice, String movieName, String date){
        int count = 0;
        for (Cineplex cineplex : cineplexes) {
            for (Cinema cinema : cineplex.getCinemas()) {
                for (Showtime cinemaShowtime : cinema.getShowtimes()) {
                    String cinemaDate = cinemaShowtime.getDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    if (cinemaDate.equalsIgnoreCase(date) && cinemaShowtime.getMovie().getTitle().equalsIgnoreCase(movieName)) {
                        count++;
                        if (count == showtimeChoice)
                            return cinemaShowtime;
                    }
                }
            }
        }
        if (count == 0)
            System.out.println("Sorry, there is no showtime on this date!");
        return null;
    }

    public Showtime getShowtimeByCineplex(Cineplex cineplex, int showtimeChoice, String movieName){
        return getShowtime(Collections.singletonList(cineplex),showtimeChoice,movieName);
    }

    public int displayShowtimes(List<Cineplex> cineplexes, String movieName) {
        int count = 0;
        for (Cineplex cineplex : cineplexes) {
            for (Cinema cinema : cineplex.getCinemas()) {
                for (Showtime cinemaShowtime : cinema.getShowtimes()) {
                    if (cinemaShowtime.getDateTime().isAfter(LocalDateTime.now()) && cinemaShowtime.getMovie().getTitle().equalsIgnoreCase(movieName)) {
                        count++;
                        System.out.printf("%d ) \n", count);
                        displayShowtime(cinemaShowtime);
                    }
                }
            }
        }
        if (count == 0)
            System.out.println("Sorry, there is no showtime on this date");
        return count;
    }
    public int displayShowtimes(List<Cineplex> cineplexes, String movieName, String date) {
        int count = 0;
        for (Cineplex cineplex : cineplexes) {
            for (Cinema cinema : cineplex.getCinemas()) {
                for (Showtime cinemaShowtime : cinema.getShowtimes()) {
                    String cinemaDate = cinemaShowtime.getDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    if (cinemaDate.equalsIgnoreCase(date) && cinemaShowtime.getMovie().getTitle().equalsIgnoreCase(movieName)) {
                        count++;
                        System.out.printf("%d ) \n", count);
                        displayShowtime(cinemaShowtime);
                    }
                }
            }
        }
        if (count == 0)
            System.out.println("Sorry, there is no showtime on this date");
        return count;
    }

    public int displayShowtimeByCinema(Cineplex cineplex, String movieName){
        return displayShowtimes(Collections.singletonList(cineplex),movieName);
    }

    public void displayShowtime(Showtime showtime){
        StringBuilder sb = new StringBuilder();
        if (showtime.getCinema().getCinemaClass() != CinemaClass.NORMAL){
            sb.append(String.format(" [ %s ] \n",showtime.getCinema().getCinemaClass().name()));
        }

        sb.append("\n       ");
        sb.append(String.format("Cinema Code: %d",showtime.getCinema().getCinemaRoom()));
        sb.append("\n       ");
        sb.append(String.format("Movie Name: %s",showtime.getMovie().getTitle()));
        sb.append("\n       ");

        if (showtime.getMovieType() != MovieType.NORMAL){
            sb.append(String.format("Movie Type: %s ",showtime.getMovieType()));
            sb.append("\n       ");
        }


        sb.append(String.format("%s \n",showtime.getDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm - EEEE "))));
        System.out.println(sb);
    }
    public void displayCinemaShowtime(ArrayList<Showtime> showtimeList){

        Showtime showtime;
        for(int j = 0; j < showtimeList.size(); j++){
            showtime = showtimeList.get(j);
            System.out.printf("%d ) Showtime Details: \n",j+1);
            System.out.println("---------------------");
            System.out.printf("Movie Title: %s ( %s ) \n",showtime.getMovie().getTitle(),showtime.getMovieType());
            System.out.printf("Date: %d %s\n",showtime.getDateTime().getDayOfMonth(),showtime.getDateTime().getMonth());
            int hour = showtime.getDateTime().getHour();
            System.out.printf("Time: %s\n",showtime.getDateTime().toLocalTime().format(DateTimeFormatter.ofPattern("hh.mm a")));
            System.out.println("---------------------");
        }
        System.out.println();

    }
    public void displayCinemas(Cineplex cineplex){
        ArrayList<Cinema> cinemaList = cineplex.getCinemas();
        for(int i = 0; i < cinemaList.size(); i++){
            System.out.printf("%d ) Cinema Hall %d \n",i+1,cinemaList.get(i).getCinemaRoom());
        }
    }
    public void displayTimeHours(){
        for(int i = 0; i <= 23; i++){
            if (i > 1 && i < 8)
                continue;
            System.out.printf("%d ) %s \n",i,LocalTime.of(i,0));
        }
    }
    public void displayTimeMinutes(){
        int index = 1;
        for(int i = 0; i < 60; i+=15){
            System.out.println(index + ") " + i);
            index++;
        }
    }
    public void displayMovieTypes(){
        System.out.println("1) Normal");
        System.out.println("2) 3D");
        System.out.println("3) IMAX");
        System.out.println("4) Blockbuster");
    }
    public void displayUpdateShowtimeOptions(){
        System.out.println("What would you like to update?");
        System.out.println("1) Movie");
        System.out.println("2) Movie Type");
        System.out.println("3) Date");
        System.out.println("4) Cinema");
    }
}

