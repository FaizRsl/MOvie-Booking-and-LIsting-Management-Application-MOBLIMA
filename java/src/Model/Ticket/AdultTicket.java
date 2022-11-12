package Model.Ticket;

import Model.Seat.Seats;
import Model.Cinema.Showtime;
/**
 * class AdultTicket, inherits from class Ticket.
 */
public class AdultTicket extends Ticket {
	
    /**
	 * Instantiates a new adult ticket.
	 *
	 * @param seats    the seats
	 * @param showtime the showtime
	 */
	
    public AdultTicket(Seats seats, Showtime showtime){super(seats, showtime);}
    /**
	 * Gets the discount.
	 *
	 * @return discount in double format
	 */

    @Override
    public int getTicketType() {
        return 1;
    }
    
    /**
	 * Standard display function returning formatted ticket type
	 *
	 * @return the string
	 */
    @Override
    public String toString() {
        return "Ticket Type: Adult\n" + super.toString();
    }
}
