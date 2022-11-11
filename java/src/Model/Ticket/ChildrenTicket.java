package Model.Ticket;

import Model.Pricing.PriceConfig;
import Model.Seat.Seats;
import Model.Cinema.Showtime;
/**
 * The Class ChildrenTicket.
 */
public class ChildrenTicket extends Ticket {
    /**
     * Instantiates a new children ticket.
     *
     * @param seats the seats
     * @param showtime the showtime
     */
    public ChildrenTicket(Seats seats, Showtime showtime){super(seats, showtime);}

    @Override
    public int getTicketType() {
        return 3;
    }
    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "Ticket Type: Child\n" + super.toString();
    }
}
