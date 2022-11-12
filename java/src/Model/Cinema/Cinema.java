package Model.Cinema;
import Model.Seat.SeatLayout;

/**
 * The Class Cinema.
 */

import java.io.Serializable;
import java.util.ArrayList;

public class Cinema implements Serializable {

	/** String representing name of cineplex. */

    private String cineplexName;

    /** Object CinemaClass representing type of cinema class. Enum types are Normal, Gold, and Platinum.
     * @see CinemaClass
     *  */

    private CinemaClass cinemaClass;

    /** Integer representing room cinema is in. */

    private int cinemaRoom;

    /** Fixed object of SeatLayout representing seat layout. */

    private final SeatLayout seatLayout;

    /** ArrayList of Showtime objects representing showtimes. Initialised as empty. */

    private ArrayList<Showtime> showtimes = new ArrayList<>();

    /**
     * Instantiates a new cinema.
     *
     * @param cineplexName the cineplex name
     * @param cinemaClass the cinema class
     * @param cinemaRoom the cinema room
     * @param seatLayout the seat layout
     */

    public Cinema(String cineplexName, CinemaClass cinemaClass, int cinemaRoom, SeatLayout seatLayout){
        this.cineplexName = cineplexName;
        this.cinemaClass = cinemaClass;
        this.seatLayout = seatLayout;
        this.cinemaRoom = cinemaRoom;
    }

    /**
     * Gets the room of the cinema.
     *
     * @return integer representing room the cinema is in,
     */

    public int getCinemaRoom() {return cinemaRoom;}

    /**
     * Sets the cinema's room.
     *
     * @param cinemaRoom the new cinema room
     */

    public void setCinemaRoom(int cinemaRoom) {this.cinemaRoom = cinemaRoom;}

    /**
     * Gets the showtimes of the cinema.
     *
     * @return ArrayList of Showtime objects, instance variable showtimes
     */

    public ArrayList<Showtime> getShowtimes() {return showtimes;}

    /**
     * Adds the show time.
     *
     * @param showtime object Showtime
     */

    public void addShowTime(Showtime showtime){
        this.showtimes.add(showtime);
    }

    /**
     * Gets the name of the cineplex.
     *
     * @return String cineplex name
     */

    public String getCineplex() {return cineplexName;}

    /**
     * Sets the name of the cineplex.
     *
     * @param String cineplexName the new cineplex name
     */

    public void setCineplex(String cineplexName) {this.cineplexName = cineplexName;}

    /**
     * Gets the cinema class.
     *
     * @return the class of the cinema, represented by instance variable cinemaClass
     */

    public CinemaClass getCinemaClass() {
        return cinemaClass;
    }

    /**
     * Sets the cinema class.
     *
     * @param cinemaClass the new cinema class
     */

    public void setCinemaClass(CinemaClass cinemaClass) {
        this.cinemaClass = cinemaClass;
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
     * To string function. Overrides inherited toString() method for its own purposes.
     *
     * @return the string
     */

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Cineplex Name: %s \n", this.cineplexName));
        sb.append(String.format("Cinema Class: %s \n", this.cinemaClass.toString()));
        sb.append(String.format("Cineplex Room: %d \n", this.cinemaRoom));
        return sb.toString();
    }
}
