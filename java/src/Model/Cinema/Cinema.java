package Model.Cinema;

import Model.Seat.SeatLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class Cinema implements Serializable {

    private UUID id;
    private Cineplex cineplex;
    private CinemaClass cinemaClass;
    private SeatLayout seatLayout;

    private ArrayList<Showtime> showtimes = new ArrayList<>();

    public Cinema(Cineplex cineplex, CinemaClass cinemaClass, SeatLayout seatLayout){
        this.id = UUID.randomUUID();
        this.cineplex = cineplex;
        this.cinemaClass = cinemaClass;
        this.seatLayout = seatLayout;
    }

    public ArrayList<Showtime> getShowtimes() {return showtimes;}

    public void addShowTime(Showtime showtime){
        this.showtimes.add(showtime);
    }

    public Cineplex getCineplex() {return cineplex;}

    public void setCineplex(Cineplex cineplex) {this.cineplex = cineplex;}

    public UUID getId() {return id;}

    public void setId(UUID id) {this.id = id;}
    public CinemaClass getCinemaClass() {
        return cinemaClass;
    }

    public void setCinemaClass(CinemaClass cinemaClass) {
        this.cinemaClass = cinemaClass;
    }
    public SeatLayout getSeatLayout() {
        return seatLayout;
    }

}
