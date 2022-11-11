package Model.Ticket;

import Model.Pricing.PriceConfig;
import Model.Seat.Seats;
import Model.Cinema.Showtime;
/**
 * The Class SeniorTicket, which is a subclass of Ticket.
 * @see Ticket
 * 
 */
public class SeniorTicket extends Ticket {
    /**
     * Instantiates a new senior ticket.
     *
     * @param seats the seats
     * @param showtime the showtime
     */
    public SeniorTicket(Seats seats, Showtime showtime){super(seats, showtime);}
    /**
     * Gets the discount.
     *
     * @return the discount
     */
    @Override
    public int getTicketType() {
        return 4;
    }
    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "Ticket Type: Senior\n" + super.toString();
    }
}
