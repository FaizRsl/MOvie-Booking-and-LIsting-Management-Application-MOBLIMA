package Model.Seat;


import java.io.Serializable;

public class SeatLayout implements Serializable {

    private int rows;

    private int cols;

    private Seats[] nonExistenceSeats;

    private Seats[][] availableSeats;

    public SeatLayout(int rows, int cols, Seats[] nonExistenceSeats) {
        this.rows = rows;
        this.cols = cols;
        this.nonExistenceSeats = nonExistenceSeats;
        this.availableSeats= new Seats[rows][cols];
        initialiseSeats();
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public void initialiseSeats() {
        for (int row = 0 ; row < rows ; row++){
            for (int col = 0 ; col < cols ; col++){
                if (availableSeats[row][col] == null) {
                    availableSeats[row][col] = new Seats(row,col);
                }
            }
        }

        for (Seats seat: nonExistenceSeats){
            availableSeats[seat.getRows()][seat.getCols()] = null;
        }
    }

    public Seats getSeats(int rows, int cols){
        if(this.availableSeats[rows][cols] == null){
            return null;
        }
        return this.availableSeats[rows][cols];
    }

    public SeatLayout getSeatsInformation(){
        return new SeatLayout(this.rows,this.cols,this.nonExistenceSeats);
    }

    public void printSeatLayout(){
        int screenWidth = cols * 5 + "a a                  ".length();
        for (int x = 0 ; x < screenWidth ; x++){
            System.out.printf("-");
        }

        System.out.printf("\n");

        for (int x2 = 0 ; x2 < ((screenWidth - "Screen".length())/2) ; x2++){
            System.out.printf(" ");
        }

        System.out.printf("Screen\n");

        for (int x = 0 ; x < screenWidth ; x++){
            System.out.printf("-");
        }

        System.out.printf("\n");
        System.out.printf("       ");

        for (int z = 0 ; z < cols ; z++){
            if (z == (cols / 2)){
                System.out.printf("       ");
            }
            if ((z+1) < 10){
                System.out.printf("  %d  ",z+1);
            }else{
                System.out.printf("  %d ",z+1);
            }
        }

        System.out.printf("\n");

        Character rowChar = 'a';
        for (int row = 0 ; row < rows ; row++){
            System.out.printf(rowChar + "      ");
            for (int col = 0 ; col < cols ; col++){

                if (col == (cols / 2)){
                    System.out.printf("       ");
                }

                if (availableSeats[row][col] != null) {
                    if (availableSeats[row][col].isBooked()){
                        System.out.printf("[ * ]");
                    }else{
                        System.out.printf("[   ]");
                    }
                }else{
                    System.out.printf("     ");
                }
            }
            System.out.printf("      " + rowChar);
            System.out.printf("\n");
            rowChar++;
        }
    }
}
