package Model.Ticket;

import Model.Pricing.PriceConfig;
import Model.Seat.Seats;
import Model.Cinema.Showtime;

public class StudentTicket extends Ticket {

    public StudentTicket(Seats seats, Showtime showtime){super(seats, showtime);}

    @Override
    public int getTicketType() {
        return 2;
    }

    @Override
    public String toString() {
        return "Ticket Type: Student\n" + super.toString();
    }
}
