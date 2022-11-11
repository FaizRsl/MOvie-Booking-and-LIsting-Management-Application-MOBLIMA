package Model.Ticket;

import Controller.PriceController;
import Model.Seat.Seats;
import Model.Cinema.Showtime;

import java.io.Serializable;
import java.util.UUID;
/**
 * The abstract class Ticket, from which subclasses such as ChildrenTicket and AdultTicker inherit.
 */
public abstract class Ticket implements Serializable {
	
	/** The ticket id. */
    private UUID id;
    
    /** The price of the ticket. */
    private double price;
    
    /** The discount, instantiated by passing ticket into PriceController.
     * @see PriceController#PriceController() */
    private double discount;
    
    /** The seat number for that ticket. */
    private int seatNumber;
    
    /** object Seats representing the seat for that ticket. */
    private Seats seats;
    
    /** The movie title. */
    private String movieTitle;
    
    /** The showtime. */
    private Showtime showtime;

    /**
     * Instantiates a new ticket.
     *
     * @param seats the seats
     * @param showtime Showtime object
     */
    public Ticket(Seats seats,Showtime showtime){
        this.id = UUID.randomUUID();
        this.seats = seats;
        this.showtime = showtime;

    }
    
    /**
     * Abstract function which returns the type of ticket.
     *
     * @return integer representing ticket type
     */
    public abstract int getTicketType();

    /**
     * Gets the price of the ticket.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }
    /**
     * Sets the price.
     *
     * @param price the new price
     */
    public void setPrice(double price) {
        this.price = price;
    }
    /**
     * Gets the seats.
     *
     * @return the seats
     */
    public Seats getSeats() {
        return seats;
    }
    /**
     * Sets the seats.
     *
     * @param seats the new seats
     */
    public void setSeats(Seats seats) {
        this.seats = seats;
    }
    /**
     * Gets the showtime.
     *
     * @return the showtime
     */
    public Showtime getShowtime() {
        return showtime;
    }
    /**
     * Sets the showtime.
     *
     * @param showtime the new showtime
     */
    public void setShowtime(Showtime showtime) {
        this.showtime = showtime;
    }
    /**
     * Gets the id.
     *
     * @return the id
     */
    public UUID getId() {
        return id;
    }
    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(UUID id) {
        this.id = id;
    }
    /**
     * Sets the price.
     *
     * @param price the new price
     */
    public void setPrice(float price) {
        this.price = price;
    }
    /**
     * Gets the seat number.
     *
     * @return the seat number
     */
    public int getSeatNumber() {
        return seatNumber;
    }
    /**
     * Sets the seat number.
     *
     * @param seatNumber the new seat number
     */
    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }
    /**
     * Gets the movie title.
     *
     * @return the movie title
     */
    public String getMovieTitle() {
        return movieTitle;
    }
    /**
     * Sets the movie title.
     *
     * @param movieTitle the new movie title
     */
    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    /**
     * Returns formatted data of the Ticket.
     *
     * @return the string
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Seat ID: %s\n",this.seats.toString()));
        sb.append(String.format("Price: $%.2f \n",this.price));
        sb.append(this.showtime.toString());
        sb.append("\n");
        return sb.toString();
    }

}
