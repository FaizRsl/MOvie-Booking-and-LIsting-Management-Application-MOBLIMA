package Model.Ticket;

import Model.Pricing.PriceConfig;
import Model.Seat.Seats;
import Model.Cinema.Showtime;
/**
 * The Class StudentTicket, which is a subclass of Ticket.
 * #see Ticket
 */
public class StudentTicket extends Ticket {
    /**
     * Instantiates a new student ticket.
     *
     * @param seats the seats
     * @param showtime the showtime
     */
    public StudentTicket(Seats seats, Showtime showtime){super(seats, showtime);}

    @Override
    public int getTicketType() {
        return 2;
    }
    /**
     * Standard display function returning formatted ticket type
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "Ticket Type: Student\n" + super.toString();
    }
}
