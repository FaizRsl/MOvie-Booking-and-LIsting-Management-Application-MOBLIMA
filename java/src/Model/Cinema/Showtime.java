package Model.Cinema;

import Model.Movie.Movie;
import Model.Movie.MovieType;
import Model.Seat.SeatLayout;
import Model.Ticket.Ticket;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Showtime implements Serializable {

    private LocalDateTime dateTime;

    private Movie movie;

    private Cinema cinema;

    private SeatLayout seatLayout;

    private ArrayList<Ticket> tickets = new ArrayList<>();

    public Showtime(LocalDateTime dateTime, Movie movie, Cinema cinema, MovieType movieType){
        this.dateTime = dateTime;
        this.movie = movie;
        this.cinema = cinema;
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

    public SeatLayout getSeatLayout() {
        return seatLayout;
    }

    public void setSeatLayout(SeatLayout seatLayout) {
        this.seatLayout = seatLayout;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public void addTickets(ArrayList<Ticket> listofTicket) {
        tickets.addAll(listofTicket);
    }

    public void removeTicket(Ticket ticket) {
        tickets.remove(ticket);
    }

    public void removeAllTickets(ArrayList<Ticket> listofTicket) {
        tickets.removeAll(listofTicket);
    }
}
