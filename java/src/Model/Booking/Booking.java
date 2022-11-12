package Model.Booking;

/**
 * The Class Booking.
 */

import Controller.PriceController;
import Model.Ticket.Ticket;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class Booking implements Serializable {

	/** ArrayList of Ticket objects. */

    private ArrayList<Ticket> tickets;

	/** The price of the booking. */

    private double price;

    /** The buyer of the booking's name. */

    private String buyerName;

    /** The buyer of the booking's email. */

    private String buyerEmail;

    /** Unique UID of booking. */

    private UUID id;

    /**
     * Instantiates a new booking.
     *
     * @param tickets the tickets
     * @param buyerName the buyer name
     * @param buyerEmail the buyer email
     */

    public Booking(ArrayList<Ticket> tickets, String buyerName, String buyerEmail){
        this.id = UUID.randomUUID();
        this.tickets = tickets;
        this.buyerName = buyerName;
        this.buyerEmail = buyerEmail;
    }

    /**
     * Gets the tickets of the booking.
     *
     * @return instance variable tickets, ArrayList of Ticket objects.
     */

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    /**
     * Gets the price.
     *
     * @return the price
     */

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the buyer name.
     *
     * @return the buyer name in type String
     */

    public String getBuyerName() {
        return buyerName;
    }

    /**
     * Gets the buyer email.
     *
     * @return string of buyer email
     */

    public String getBuyerEmail() {
        return buyerEmail;
    }

    /**
     * Gets the booking's unique id.
     *
     * @return UUID object representing booking's unique ID.
     */

    public UUID getId() {
        return id;
    }

    /**
     * To string. Overrides inherited toString() method for its own purposes.
     *
     * @return the string
     */

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("---------------------------------------------\n");
        sb.append("                      Order                  \n");
        sb.append("---------------------------------------------\n");
        sb.append(String.format("Transaction ID: %s \n",this.id));
        sb.append(String.format("Buyer Name: %s \n",this.buyerName));
        sb.append(String.format("Email Address: %s \n",this.buyerEmail));
        sb.append("\n");
        sb.append("Tickets:\n");
        sb.append("=============================================== \n");
        tickets.forEach(ticket ->
                sb.append(ticket.toString()).append("\n"));

        sb.append(String.format("Total Price: %.2f (inclusive of GST) \n",this.price));
        sb.append("---------------------------------------------\n");

        return sb.toString();
    }
}
