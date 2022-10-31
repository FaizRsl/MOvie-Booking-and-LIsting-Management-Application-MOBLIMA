package Model.Seat;

import java.io.Serializable;
public class Seats implements Serializable {

    private boolean booked;
    private int rows;
    private int cols;

    public Seats(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }
    public int getRows() {return rows;}
    public int getCols() {return cols;}
    public boolean isBooked() {return booked;}

    public void bookSeat() {
        booked = true;
    }

    public void unbookSeat() {booked = false;}

    @Override
    public String toString(){
        char row = 'A';
        row = (char)(row + this.rows);
        String returnString = String.valueOf(row) + String.valueOf(this.cols + 1);
        return returnString;
    }

}
