package view;

import Model.Cinema.Cinema;
import Model.Cinema.CinemaClass;
import Model.Cinema.Cineplex;
import Model.Cinema.Showtime;
import Model.Movie.MovieType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CinemaView {

    public void displayCineplex(List<Cineplex> cineplexes){
        for(int i=0; i<cineplexes.size(); i++){
            System.out.println((i+1) + ") " + cineplexes.get(i).getCineplexName() + " Cineplex");
        }
    }

//    public void displayCineplexDetails(Cineplex cineplex){
//        for(int i=0;i<cineplex.getCinemas().size(); i++){
//            System.out.print(cineplex.getCinemas().get(i).getCinemaRoom() + ") ");
//            System.out.println(cineplex.getCinemas().get(i).getCinemaClass());
//            //cineplex.getCinemas().get(i).getSeatLayout().printSeatLayout();
//            for(int j=0; j<cineplex.getCinemas().get(i).getShowtimes().size(); j++){
//                System.out.println(displayShowtime(cineplex.getCinemas().get(i).getShowtimes().get(j)));
//            }
//
//        }
//    }
public Showtime getShowtimeByCineplex(Cineplex cineplex, int showtimeChoice, String movieName){
    int count = 0;
    for(int i=0; i<cineplex.getCinemas().size(); i++){
        for(int j=0; j<cineplex.getCinemas().get(i).getShowtimes().size(); j++){
            if(cineplex.getCinemas().get(i).getShowtimes().get(j).getMovie().getTitle().toLowerCase().equals(movieName.toLowerCase())) {
                count++;
                if(count == showtimeChoice)
                    return cineplex.getCinemas().get(i).getShowtimes().get(j);
            }
        }
    }
    if(count == 0){
        System.out.println("Sorry, there is no showtime on this date!");
    }
    return null;
}

public Showtime getShowtimeByDate(List<Cineplex> cineplexes, int showtimeChoice, String date, String movieName){
    int count = 0;
    for(int i=0; i<cineplexes.size(); i++){
        for(int j=0; j<cineplexes.get(i).getCinemas().size(); j++){
            for(int k=0; k<cineplexes.get(i).getCinemas().get(j).getShowtimes().size(); k++){
                String showtimeDate = cineplexes.get(i).getCinemas().get(j).getShowtimes().get(k).getDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                if(showtimeDate.equals(date)){
                    if(cineplexes.get(i).getCinemas().get(j).getShowtimes().get(k).getMovie().getTitle().toLowerCase().equals(movieName.toLowerCase())) {
                        count++;
                        if (count == showtimeChoice)
                            return cineplexes.get(i).getCinemas().get(j).getShowtimes().get(k);
                    }
                }
            }
        }
    }
    if(count == 0){
        System.out.println("Sorry, there is no showtime on this date!");
    }
    return null;
}

    public Showtime getAllCineplexShowtimes(List<Cineplex> cineplexes, String movieName, int showtimeChoice){
        int count = 0;
        for(int i=0; i<cineplexes.size(); i++){
            for(int j=0; j<cineplexes.get(i).getCinemas().size(); j++){
                for(int k=0; k<cineplexes.get(i).getCinemas().get(j).getShowtimes().size(); k++){
                    LocalDateTime currentDate = LocalDateTime.now();
                    if(cineplexes.get(i).getCinemas().get(j).getShowtimes().get(k).getDateTime().isAfter(currentDate)){
                        if(cineplexes.get(i).getCinemas().get(j).getShowtimes().get(k).getMovie().getTitle().toLowerCase().equals(movieName.toLowerCase())){
                            count++;
                            if(count == showtimeChoice){
                                return cineplexes.get(i).getCinemas().get(j).getShowtimes().get(k);
                            }
                        }
                    }
                }
            }
        }
        if(count == 0){
            System.out.println("Sorry, there is no showtime on this date!");
        }
        return null;
    }
    public void displayAllCineplexShowtimes(List<Cineplex> cineplexes, String movieName){
        int count = 0;
        for(int i=0; i<cineplexes.size(); i++){
            for(int j=0; j<cineplexes.get(i).getCinemas().size(); j++){
                for(int k=0; k<cineplexes.get(i).getCinemas().get(j).getShowtimes().size(); k++){
                    LocalDateTime currentDate = LocalDateTime.now();
                    if(cineplexes.get(i).getCinemas().get(j).getShowtimes().get(k).getDateTime().isAfter(currentDate)){
                        if(cineplexes.get(i).getCinemas().get(j).getShowtimes().get(k).getMovie().getTitle().toLowerCase().equals(movieName.toLowerCase())){
                            count++;
                            System.out.print(count + ") ");
                            System.out.println(displayShowtime(cineplexes.get(i).getCinemas().get(j).getShowtimes().get(k)));
                        }
                    }
                }
            }
        }
        if(count == 0){
            System.out.println("Sorry, there is no showtime on this date!");
        }
    }

    public void displayShowtimeByCinema(Cineplex cineplex, String movieName){
        int count = 0;
        for(int j=0; j<cineplex.getCinemas().size(); j++){
            for(int k=0; k<cineplex.getCinemas().get(j).getShowtimes().size(); k++){
                LocalDateTime currentDate = LocalDateTime.now();
                if(cineplex.getCinemas().get(j).getShowtimes().get(k).getDateTime().isAfter(currentDate)){
                    if(cineplex.getCinemas().get(j).getShowtimes().get(k).getMovie().getTitle().toLowerCase().equals(movieName.toLowerCase())){
                        count++;
                        System.out.print(count + ") ");
                        System.out.println(displayShowtime(cineplex.getCinemas().get(j).getShowtimes().get(k)));
                    }

                }
            }
        }
        if(count == 0){
            System.out.println("Sorry, there is no showtime on this date!");
        }
    }

    public void displayShowtimeByDate(List<Cineplex> cineplexes, String date, String movieName){
        int count = 0;
        for(int i=0; i<cineplexes.size(); i++){
            for(int j=0; j<cineplexes.get(i).getCinemas().size(); j++){
                for(int k=0; k<cineplexes.get(i).getCinemas().get(j).getShowtimes().size(); k++){
                    String showtimeDate = cineplexes.get(i).getCinemas().get(j).getShowtimes().get(k).getDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    if(showtimeDate.equals(date)){
                        if(cineplexes.get(i).getCinemas().get(j).getShowtimes().get(k).getMovie().getTitle().toLowerCase().equals(movieName.toLowerCase())){
                            count++;
                            System.out.print(count + ") ");
                            System.out.println(displayShowtime(cineplexes.get(i).getCinemas().get(j).getShowtimes().get(k)));
                        }
                    }
                }
            }
        }
        if(count == 0){
            System.out.println("Sorry, there is no showtime on this date!");
        }
    }

    public String displayShowtime(Showtime showtime){
        String returnString = "";
        returnString += showtime.getCinema().getCineplex() + " Cineplex";

        if (showtime.getCinema().getCinemaClass() != CinemaClass.NORMAL){
            returnString += " [ " + showtime.getCinema().getCinemaClass().name() + " ]";
        }

        returnString += "\n       ";
        returnString += "Cinema Code: " + showtime.getCinema().getCinemaRoom();
        returnString += "\n       ";
        returnString += "Movie Name: " + showtime.getMovie().getTitle();
        returnString += "\n       ";

        if (showtime.getMovieType() != MovieType.NORMAL){
            returnString += "Movie Type: " + showtime.getMovieType()  + " ";
            returnString += "\n       ";
        }


        returnString += showtime.getDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm - EEEE ")) + "\n  ";
        return returnString;
    }

    public void displayCinemaShowtime(ArrayList<Showtime> showtimeList){

        Showtime showtime;
        for(int j = 0; j < showtimeList.size(); j++){
            showtime = showtimeList.get(j);
            System.out.println((j+1) + ") Showtime Details: ");
            System.out.println("---------------------");
            System.out.println("Movie Title: " + showtime.getMovie().getTitle() + " (" + showtime.getMovieType() + ")");
            System.out.println("Date: " + showtime.getDateTime().getDayOfMonth() + " " + showtime.getDateTime().getMonth());
            int hour = showtime.getDateTime().getHour();
            if(hour > 12 && hour < 24){
                System.out.println("Time: " + (hour - 12) + ":" + showtime.getDateTime().getMinute() + " PM");
            } else if (hour == 12){
                System.out.println("Time: " + hour + ":" + showtime.getDateTime().getMinute() + " PM");
            } else {
                System.out.println("Time: " + hour + ":" + showtime.getDateTime().getMinute() + " AM");
            }
            System.out.println("---------------------");
        }
        System.out.println();
        
    }

    public void displayCinemas(Cineplex cineplex){
        ArrayList<Cinema> cinemaList = cineplex.getCinemas();
        for(int i = 0; i < cinemaList.size(); i++){
            System.out.println( (i+1) + ") Cinema Hall " + cinemaList.get(i).getCinemaRoom());
        }
    }

    public void displayTimeHours(){
        for(int i = 1; i <= 12; i++){
            System.out.println(i + ") " + i);
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

