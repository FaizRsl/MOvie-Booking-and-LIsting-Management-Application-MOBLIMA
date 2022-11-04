package Controller;

import Model.Cinema.Cineplex;
import Model.Cinema.Showtime;
import Model.Movie.Movie;
import view.CinemaView;
import view.MovieView;

import java.util.List;

public class CinemaController {

    private List<Cineplex> cineplexes;

    private CinemaView cinemaView;

    public CinemaController() {
        cinemaView = new CinemaView();
        cineplexes = DatabaseController.getCineplexFromDB();
    }

    public List<Cineplex> getAllCineplex(){
        return cineplexes;
    }

    public void displayAllCineplex(){
        cinemaView.displayCineplex(cineplexes);
    }

    public void displayAllCineplexShowtimes(String movieName){
        cinemaView.displayAllCineplexShowtimes(cineplexes, movieName);
    }

    public void displayShowtimeByDate(String date, String movieTitle){
        cinemaView.displayShowtimeByDate(cineplexes, date, movieTitle);
    }

    public void displayShowtimeByCineplex(int choice, String movieName){
        cinemaView.displayShowtimeByCinema(cineplexes.get(choice-1), movieName);
    }

    public Showtime getCineplexAllShowtimes(int showtimeChoice, String movieName){
        return cinemaView.getAllCineplexShowtimes(cineplexes, movieName, showtimeChoice);
    }

    public void displayShowtimeInfo(Showtime showtime){
        System.out.println(cinemaView.displayShowtime(showtime));
    }

    public Showtime getShowtimeByCineplex(int cineplexChoice, int showtimeChoice, String movieName){
        return cinemaView.getShowtimeByCineplex(cineplexes.get(cineplexChoice-1), showtimeChoice, movieName);
    }
    public Showtime getShowtimeByDate(int choice, String date, String movieTitle){
        return cinemaView.getShowtimeByDate(cineplexes, choice,  date, movieTitle);
    }

}
