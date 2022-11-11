package Model.Cinema;

import Model.Movie.Movie;


import Model.Movie.MovieType;
import Model.Seat.SeatLayout;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The class Showtime.
 */

public class Showtime implements Serializable {
	
	/** The date time. */

    private LocalDateTime dateTime;
    
    /** The movie. */

    private Movie movie;
    
    /** The cinema. */

    private Cinema cinema;
    
    /** The movie type. */

    private MovieType movieType;
    
    /** The seat layout. */

    private SeatLayout seatLayout;
    
    /**
     * Instantiates a new showtime.
     *
     * @param dateTime the date time
     * @param movie the movie
     * @param cinema the cinema
     * @param movieType the movie type
     */

    public Showtime(LocalDateTime dateTime, Movie movie, Cinema cinema, MovieType movieType){
        this.dateTime = dateTime;
        this.movie = movie;
        this.cinema = cinema;
        this.movieType = movieType;
        this.seatLayout = this.cinema.getSeatLayout().getSeatsInformation();
    }
    
    /**
     * Gets the date time.
     *
     * @return the date time
     */

    public LocalDateTime getDateTime() {
        return dateTime;
    }
    
    /**
     * Sets the date time.
     *
     * @param dateTime the new date time
     */

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    
    /**
     * Gets the movie.
     *
     * @return the movie
     */

    public Movie getMovie() {
        return movie;
    }
    
    /**
     * Sets the movie.
     *
     * @param movie the new movie
     */

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    
    /**
     * Gets the cinema.
     *
     * @return the cinema
     */

    public Cinema getCinema() {
        return cinema;
    }
    
    /**
     * Sets the cinema.
     *
     * @param cinema the new cinema
     */

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }
    
    /**
     * Gets the movie type.
     *
     * @return the movie type
     */

    public MovieType getMovieType() {
        return movieType;
    }
    
    /**
     * Sets the movie type.
     *
     * @param movieType the new movie type
     */

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }
    
    /**
     * Gets the seat layout.
     *
     * @return the seat layout
     */

    public SeatLayout getSeatLayout() {
        return seatLayout;
    }
    
    /**
     * Sets the seat layout.
     *
     * @param seatLayout the new seat layout
     */

    public void setSeatLayout(SeatLayout seatLayout) {
        this.seatLayout = seatLayout;
    }
    
    /**
     * To string. Overrides toString() for its own purposes.
     *
     * @return the string
     */
    @Override
    public String toString() {
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
