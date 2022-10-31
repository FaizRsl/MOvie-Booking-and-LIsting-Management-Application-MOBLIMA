package Model.Cinema;

import Model.Movie.Movie;
import Model.Movie.MovieType;
import Model.Seat.SeatLayout;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Showtime implements Serializable {

    private LocalDateTime dateTime;

    private Movie movie;

    private Cinema cinema;

    private MovieType movieType;

    private SeatLayout seatLayout;

    public Showtime(LocalDateTime dateTime, Movie movie, Cinema cinema, MovieType movieType){
        this.dateTime = dateTime;
        this.movie = movie;
        this.cinema = cinema;
        this.movieType = movieType;
        this.seatLayout = this.cinema.getSeatLayout().getSeatsInformation();
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    public SeatLayout getSeatLayout() {
        return seatLayout;
    }

    public void setSeatLayout(SeatLayout seatLayout) {
        this.seatLayout = seatLayout;
    }
}
