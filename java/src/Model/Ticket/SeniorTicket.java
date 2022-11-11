package Model.Ticket;

import Model.Pricing.PriceConfig;
import Model.Seat.Seats;
import Model.Cinema.Showtime;

public class SeniorTicket extends Ticket {

    public SeniorTicket(Seats seats, Showtime showtime){super(seats, showtime);}

    @Override
    public int getTicketType() {
        return 4;
    }

    @Override
    public String toString() {
        return "Ticket Type: Senior\n" + super.toString();
    }
}
