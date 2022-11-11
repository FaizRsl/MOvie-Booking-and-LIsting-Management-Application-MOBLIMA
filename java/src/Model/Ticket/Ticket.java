package Model.Ticket;

import Controller.PriceController;
import Model.Seat.Seats;
import Model.Cinema.Showtime;

import java.io.Serializable;
import java.util.UUID;

public abstract class Ticket implements Serializable {

    private UUID id;
    private double price;

    private double discount;
    private int seatNumber;
    private Seats seats;
    private String movieTitle;
    private Showtime showtime;

    public Ticket(Seats seats,Showtime showtime){
        this.id = UUID.randomUUID();
        this.seats = seats;
        this.showtime = showtime;

    }
    public abstract int getTicketType();

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Seats getSeats() {
        return seats;
    }

    public void setSeats(Seats seats) {
        this.seats = seats;
    }

    public Showtime getShowtime() {
        return showtime;
    }

    public void setShowtime(Showtime showtime) {
        this.showtime = showtime;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }


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
