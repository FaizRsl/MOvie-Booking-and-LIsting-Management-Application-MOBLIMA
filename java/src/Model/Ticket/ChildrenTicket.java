package Model.Ticket;

import Model.Pricing.PriceConfig;
import Model.Seat.Seats;
import Model.Cinema.Showtime;

public class ChildrenTicket extends Ticket {

    public ChildrenTicket(Seats seats, Showtime showtime){super(seats, showtime);}

    @Override
    public int getTicketType() {
        return 3;
    }

    @Override
    public String toString() {
        return "Ticket Type: Child\n" + super.toString();
    }
}
