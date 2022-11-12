package Model.Seat;

import java.io.Serializable;

/**
 * The Class Seats, representing each individual seat.
 */
public class Seats implements Serializable {
    /** Boolean to represent whether a seat has been booked. Instantiated as false automatically.*/

    private boolean booked;
    /** Integer representing row that seat is in. */

    private int rows;
    /** Integer representing column that seat is in. */

    private int cols;
    /**
	 * Instantiates a new seat.
	 *
	 * @param rows the row it is in
	 * @param cols the column it is in
	 */
    public Seats(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.booked = false;
    }
    /**
	 * Gets the row that the seat is in.
	 *
	 * @return the rows as an integer
	 */
    public int getRows() {return rows;}
    /**
	 * Gets the column that the seat is in.
	 *
	 * @return the column as an integer
	 */
    public int getCols() {return cols;}
    /**
	 * Checks if is booked.
	 *
	 * @return true, if is booked
	 */
    public boolean isBooked() {return booked;}
    /**
	 * Book seat. Sets booked instance variable as true
	 */
    public void bookSeat() {
        booked = true;
    }
    /**
	 * Unbook seat. Sets booked instance variable as false
	 */
    public void unbookSeat() {booked = false;}
    /**
	 * Displays formatted Seat data, overwriting the inherited method toString() for it's own purposes.
	 *
	 * @return the string
	 */
    @Override
    public String toString(){
        char row = 'A';
        row = (char)(row + this.rows);
        String returnString = String.valueOf(row) + String.valueOf(this.cols + 1);
        return returnString;
    }

}
