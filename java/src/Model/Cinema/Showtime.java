package Model.Cinema;

import Model.Movie.Movie;
import Model.Movie.MovieType;
import Model.Seat.SeatLayout;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @Override
    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(String.format("Date: %s \n",dateTime.toLocalDate().format(DateTimeFormatter.ofPattern("E, dd MMMM yyyy"))));
//        sb.append(String.format("Time: %s \n",dateTime.toLocalTime().format(DateTimeFormatter.ISO_LOCAL_TIME)));
//        sb.append("Movie: \n");
//        sb.append("=============================================== \n");
//        sb.append(this.movie.toString());
//        sb.append("Cinema: \n");
//        sb.append("=============================================== \n");
//        sb.append(this.cinema.toString());
//        sb.append(String.format("Type of Movie: %s \n",this.movieType.name()));
//        return sb.toString();
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Date: %s \n",dateTime.toLocalDate().format(DateTimeFormatter.ofPattern("E, dd MMMM yyyy"))));
        sb.append(String.format("Time: %s \n",dateTime.toLocalTime().format(DateTimeFormatter.ISO_LOCAL_TIME)));
        sb.append("\n");
        sb.append("Movie Details: \n");
        sb.append("Movie: " + this.movie.getTitle());
        sb.append("\n");
        sb.append("Cinema: " + this.cinema.getCineplex());
        sb.append("\n");
        sb.append("Cinema Room: " + this.cinema.getCinemaRoom());
        sb.append("\n");
        sb.append("Cinema Class: " + this.cinema.getCinemaClass());
        sb.append("\n");
        sb.append(String.format("Type of Movie: %s \n",this.movieType.name()));
        return sb.toString();
    }

}
