package Model.Booking;

import Controller.PriceController;
import Model.Ticket.Ticket;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class Booking implements Serializable {

    private ArrayList<Ticket> tickets;

    private double price;

    private String buyerName;

    private String buyerEmail;

    private UUID id;

    public Booking(ArrayList<Ticket> tickets, String buyerName, String buyerEmail){
        this.id = UUID.randomUUID();
        this.tickets = tickets;
        this.buyerName = buyerName;
        this.buyerEmail = buyerEmail;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public UUID getId() {
        return id;
    }

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
