package Model.Ticket;

import Model.Seat.Seats;
import Model.Cinema.Showtime;

public class AdultTicket extends Ticket {

    public AdultTicket(Seats seats, Showtime showtime){super(seats, showtime);}

    @Override
    public int getTicketType() {
        return 1;
    }

    @Override
    public String toString() {
        return "Ticket Type: Adult\n" + super.toString();
    }
}
