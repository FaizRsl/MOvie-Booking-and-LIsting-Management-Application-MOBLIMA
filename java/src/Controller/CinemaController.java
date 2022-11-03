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

    public void displayShowtimeByDate(String date){
        cinemaView.displayShowtimeByDate(cineplexes, date);
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

//    public Showtime getShowtimeByCineplex(int choice, String movieName){
//        return cinemaView.getShowtimeByCinema(cineplexes.get(choice-1), movieName);
//    }
//    public Showtime getShowtimeByDate(String date){
//        return cinemaView.getShowtimeByDate(cineplexes, date);
//    }

}
