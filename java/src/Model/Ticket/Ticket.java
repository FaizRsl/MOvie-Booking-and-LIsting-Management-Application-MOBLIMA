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
        this.price = this.calculateTicketPrice();

    }
    public abstract double getDiscount();

    private double calculateTicketPrice(){
        return PriceController.calculateTicketPrice(this);
    }

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
        String returnString = "";
        returnString += "Seat ID: "+this.getSeats().toString() + "\n";
        returnString += "Price: $" + String.format("%.2f",this.getPrice()) + "\n";
        returnString += "Showtime: \n       " + this.getShowtime().toString();
        return  returnString;
    }

}
