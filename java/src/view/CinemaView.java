package view;

import Model.Cinema.Cinema;
import Model.Cinema.CinemaClass;
import Model.Cinema.Cineplex;
import Model.Cinema.Showtime;
import Model.Movie.MovieType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public void displayShowtimeByDate(List<Cineplex> cineplexes, String date){
        int count = 0;
        for(int i=0; i<cineplexes.size(); i++){
            for(int j=0; j<cineplexes.get(i).getCinemas().size(); j++){
                for(int k=0; k<cineplexes.get(i).getCinemas().get(j).getShowtimes().size(); k++){
                    String showtimeDate = cineplexes.get(i).getCinemas().get(j).getShowtimes().get(k).getDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    if(showtimeDate.equals(date)){
                        count++;
                        System.out.print(count + ") ");
                        System.out.println(displayShowtime(cineplexes.get(i).getCinemas().get(j).getShowtimes().get(k)));
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
}
