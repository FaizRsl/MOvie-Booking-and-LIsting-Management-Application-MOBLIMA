package Model.Cinema;

import Model.Seat.SeatLayout;

import java.io.Serializable;
import java.util.ArrayList;

public class Cinema implements Serializable {

    private Cineplex cineplex;
    private CinemaClass cinemaClass;
    private int cinemaRoom;
    private SeatLayout seatLayout;

    private ArrayList<Showtime> showtimes = new ArrayList<>();

    public Cinema(Cineplex cineplex, CinemaClass cinemaClass, int cinemaRoom, SeatLayout seatLayout){
        this.cineplex = cineplex;
        this.cinemaClass = cinemaClass;
        this.seatLayout = seatLayout;
        this.cinemaRoom = cinemaRoom;
    }

    public int getCinemaRoom() {return cinemaRoom;}

    public void setCinemaRoom(int cinemaRoom) {this.cinemaRoom = cinemaRoom;}

    public ArrayList<Showtime> getShowtimes() {return showtimes;}

    public void addShowTime(Showtime showtime){
        this.showtimes.add(showtime);
    }

    public Cineplex getCineplex() {return cineplex;}

    public void setCineplex(Cineplex cineplex) {this.cineplex = cineplex;}
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
