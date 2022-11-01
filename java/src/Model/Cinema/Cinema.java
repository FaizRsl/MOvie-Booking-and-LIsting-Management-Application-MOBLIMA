package Model.Cinema;

import Model.Seat.SeatLayout;

import java.io.Serializable;
import java.util.ArrayList;

public class Cinema implements Serializable {

    private int cinemaRoom;
    private CinemaClass cinemaClass;
    private String cineplexName;
    private SeatLayout seatLayout;

    private ArrayList<Showtime> showtimes = new ArrayList<>();

    public Cinema(int cinemaRoom, CinemaClass cinemaClass, String cineplexName, SeatLayout seatLayout){
        this.cineplexName = cineplexName;
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
