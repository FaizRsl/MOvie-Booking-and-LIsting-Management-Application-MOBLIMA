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
        this.price = calculatePrice();
    }

    public double calculatePrice(){
        double total = 0;
        for(int i=0; i<tickets.size(); i++){
            total += tickets.get(i).getPrice();
        }
        total = PriceController.GSTCalculation(total);
        return total;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public double getPrice() {
        return price;
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

        String returnString = "";

        returnString += "---------------------------------------------\n";
        returnString += "                      Order                  \n";
        returnString += "---------------------------------------------\n";

        returnString += "Transaction ID: " + this.id + "\n";
        returnString += "Buyer Name: " + this.buyerName + "\n";
        returnString += "Email Address: " + this.buyerEmail + "\n\n";

        returnString += "Tickets: \n\n";

        for (Ticket ticket : this.tickets){
            returnString += ticket.toString() + "\n";
        }

        returnString += String.format("Total Price: $%.2f",this.getPrice()) + " (inclusive of GST)";

        returnString += "\n---------------------------------------------\n";

        return returnString;
    }
}
